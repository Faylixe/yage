package fr.faylixe.yage.memory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.function.Consumer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import fr.faylixe.yage.memory.IMemoryStream;

/**
 * Generic test for memory stream. Assumes that tested memory
 * stream always has the same properties, namely a 3 byte size
 * and a 4 address offset. It also assumes that the memory has
 * the following data layout:
 * 
 * (4) -> 1 0 1 0 1 0 1 0
 * (5) -> 1 1 1 1 0 0 0 0 
 * (6) -> X X X X X X X X
 * 
 * The last memory row is dedicated to writing tests and so is not
 * covered by reading test.
 * 
 * As a matter of design, this test interface only testing reading
 * through memory stream as this operation is always consistent
 * regarding of the implementation. Writing test should be implemented
 * in test class implementation.
 * 
 * @author fv
 */
@TestInstance(Lifecycle.PER_CLASS)
public interface IMemoryStreamTest {

	/** Expected size of the memory to be tested. **/
	static final int TEST_SIZE = 3;

	/** Expected offset of the memory to be tested. **/
	static final int TEST_OFFSET = 4;

	/**
	 * Factory method that creates a target testing instance.
	 * 
	 * @return A test instance.
	 */
	IMemoryStream getTestMemoryStream();

	/**
	 * Retrieves a test memory bank instance and
	 * pass it to the given test. Does not aims to
	 * be overriden.
	 *
	 * @param test Effective memory bank test to perform.
	 */
	default void performStreamTest(final Consumer<IMemoryStream> test) {
		final IMemoryStream stream = getTestMemoryStream();
		assertNotNull(stream);
		test.accept(stream);
	}

	/**
	 * Ensures the bit <tt>position</tt> from the given <tt>value</tt>
	 * if the one <tt>expected</tt>;
	 * 
	 * @param value Byte to control.
	 * @param position Bit position.
	 * @param expected Expected value.
	 */
	default void verifyBit(final byte value, final int position, final int expected) {
		final int bit = (value >> position) & 1;
		assertEquals(expected, bit);
	}

	/**
	 * Ensures the given <tt>value</tt> is the one expected
	 * for the first memory byte, namely (1,0,1,0,1,0,1,0).
	 * for the second memory byte, namely (1,1,1,1,0,0,0,0).
	 * 
	 * @param bytes
	 */
	default void verifyBytes(final Byte[] bytes) {
		// Note : value can be checked in BinaryBenchmark tests.
		// Note : 10101010 -> 85 as signed byte.
		assertEquals((byte) 85, (byte) bytes[0]);
		for (int i = 0; i < 8; i += 2) {
			verifyBit(bytes[0], i, 1);
		}
		for (int i = 1; i < 8; i += 2) {
			verifyBit(bytes[0], i, 0);
		}
		// Note : 11110000 -> 15 as signed byte.
		assertEquals((byte) 15, (byte) bytes[1]);
		for (int i = 0; i < 8; i++) {
			verifyBit(bytes[1], i, i < 4 ? 1 : 0);
		}
	}

	/** Test reading valid address **/
	@Test
	default void testAllowedReading() {
		performStreamTest(stream -> {
			try {
				verifyBytes(new Byte[] {
						stream.readByte(TEST_OFFSET),
						stream.readByte(TEST_OFFSET + 1)
				});
			}
			catch (final IllegalAccessException e) {
				fail(e);
			}			
		});
	}
	
	/** Test reading valid addresses. **/
	@Test
	default void testAllowedReadings() {
		performStreamTest(stream -> {
			try {
				final byte[] bytes = stream.readBytes(TEST_OFFSET, 2);
				verifyBytes(new Byte[] {
						bytes[0],
						bytes[1]
				});
			}
			catch (final IllegalAccessException e) {
				fail(e);
			}			
		});
	}

}
