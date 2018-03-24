package fr.faylixe.yage.memory;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import fr.faylixe.yage.memory.IMemoryBank;
import fr.faylixe.yage.utils.ErrorlessTest;

/**
 * {@link IMemoryBankTest} extension that handles
 * expected write operation and only required to
 * create an empty test instance when overriden.
 * 
 * @author fv
 */
public interface IMemoryBankWriteTest extends IMemoryBankTest {

	/**
	 * Factory method that creates a target {@link IMemoryBank}
	 * instance that is writable.
	 * 
	 * @param size Size of the bank to create.
	 * @param offset Offset of the bank to create.
	 * @return Created instance.
	 */
	IMemoryBank createTestMemoryBank(int size, int offset);

	/** {@inheritDoc} **/
	@Override
	default IMemoryBank getTestMemoryBank() {
		final IMemoryBank bank = createTestMemoryBank(TEST_SIZE, TEST_OFFSET);
		try {
			bank.writeByte((byte) 85, TEST_OFFSET);
			bank.writeByte((byte) 15, TEST_OFFSET + 1);
		}
		catch (final IllegalAccessException e) {
			fail(e);
		}
		return bank;
	}

	/** Test writing wrong address **/
	@Test
	default void testAlllowedWriting() {
		performStreamTest(stream -> {
			ErrorlessTest.run(() -> stream.writeByte((byte) 42, 6));
		});
	}
	
	/** Test writing wrong address **/
	@Test
	default void testAlllowedWritings() {
		performStreamTest(stream -> {
			ErrorlessTest.run(() -> stream.writeBytes(new byte[] { 42 }, 6));
		});
	}

	/** Test writing wrong address **/
	@Test
	default void testUnalllowedWriting() {
		performStreamTest(stream -> {
			assertThrows(IllegalAccessException.class, () -> stream.writeByte((byte) 42, 69));
		});
	}

	/** Test writing wrong address **/
	@Test
	default void testUnalllowedWritings() {
		performStreamTest(stream -> {
			assertThrows(IllegalAccessException.class, () -> stream.writeBytes(new byte[] { 42 }, 0));
			assertThrows(IllegalAccessException.class, () -> stream.writeBytes(new byte[] { 42, 58 }, 6));
		});
	}

}
