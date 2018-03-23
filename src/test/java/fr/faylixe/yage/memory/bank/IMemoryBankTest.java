package fr.faylixe.yage.memory.bank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.function.Consumer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import fr.faylixe.yage.memory.IMemoryStream;
import fr.faylixe.yage.memory.IMemoryStreamTest;
import fr.faylixe.yage.memory.bank.IMemoryBank;

/**
 * Generic test for memory bank. Ensures size and offset
 * properties, based on IMemoryStreamTest testing contract.
 * 
 * @see IMemoryStreamTest
 * @author fv
 */
@TestInstance(Lifecycle.PER_CLASS)
public interface IMemoryBankTest extends IMemoryStreamTest {

	/**
	 * Factory method that creates a target testing instance.
	 * 
	 * @return A test instance.
	 */
	IMemoryBank getTestMemoryBank();

	/** {@inheritDoc} **/
	@Override
	default IMemoryStream getTestMemoryStream() {
		return getTestMemoryBank();
	}

	/**
	 * Retrieves a test memory bank instance and
	 * pass it to the given test. Does not aims to
	 * be overriden.
	 *
	 * @param test Effective memory bank test to perform.
	 */
	default void performBankTest(final Consumer<IMemoryBank> test) {
		final IMemoryBank bank = getTestMemoryBank();
		assertNotNull(bank);
		test.accept(bank);
	}

	/** Testing bank size property. **/
	@Test
	default void testBankSize() {
		performBankTest(bank -> assertEquals(TEST_SIZE, bank.getSize()));
	}

	/** Testing bank offset property. **/
	@Test
	default void testBankOffset() {
		performBankTest(bank -> assertEquals(TEST_OFFSET, bank.getOffset()));
	}

	/**
	 * Creates a standard memory bank with 3 bytes at offset 4.
	 * 
	 * @return Created instance.
	 */
	static IMemoryBank createMemoryBankMock() {
		return createMemoryBankMock(TEST_SIZE, TEST_OFFSET);
	}

	/**
	 * Factory method for creating an IMemoryBank mock instance.
	 * Such mock instance is designed to match expected test properties.
	 * 
	 * @param size Size of the expected mock memory bank.
	 * @param offset Offset of the expected mock memory bank.
	 * @return Created mock instance.
	 */
	static IMemoryBank createMemoryBankMock(final int size, final int offset) {
		final IMemoryBank mockBank = createIOLessMemoryBankMock(size, offset);
		try {
			// Note : Value per layout can be computed through BinaryBenchmark.
			when(mockBank.readByte(anyInt())).thenAnswer(invocationMock -> {
				final int address = invocationMock.getArgument(0);
				if (address == offset) {
					return (byte) 85;
				}
				else if (address == offset + 1) {
					return (byte) 15;
				}
				else if (address == offset + 2) {
					return (byte) 0;
				}
				throw new IllegalAccessException();
			});
			when(mockBank.readBytes(anyInt(), anyInt())).thenCallRealMethod();
			when(mockBank.isAddressCovered(anyInt())).thenCallRealMethod();
		}
		catch (final IllegalAccessException e) {
			// Do nothing.
		}
		return mockBank;
	}
	
	/**
	 * Factory method for creating an IMemoryBank mock instance.
	 * Such mock instance only provides properties getter method.
	 * 
	 * @param size Size of the mocked memory bank.
	 * @param offset Offset of the mocked memory bank.
	 * @return Created mock instance.
	 */
	static IMemoryBank createIOLessMemoryBankMock(final int size, final int offset) {
		final IMemoryBank mockBank = mock(IMemoryBank.class);
		when(mockBank.getSize()).thenReturn(size);
		when(mockBank.getOffset()).thenReturn(offset);
		return mockBank;
	}

}
