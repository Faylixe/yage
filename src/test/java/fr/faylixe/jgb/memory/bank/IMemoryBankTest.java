package fr.faylixe.jgb.memory.bank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.function.Consumer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import fr.faylixe.jgb.memory.IMemoryStream;
import fr.faylixe.jgb.memory.IMemoryStreamTest;
import fr.faylixe.jgb.memory.bank.IMemoryBank;

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
	 * Factory method for creating an IMemoryBank mock instance.
	 * Such mock instance is designed to match expected test properties.
	 * 
	 * @return Created mock instance.
	 */
	static IMemoryBank createMemoryBankMock() {
		final IMemoryBank mockBank = mock(IMemoryBank.class);
		when(mockBank.getSize()).thenReturn(TEST_SIZE);
		when(mockBank.getOffset()).thenReturn(TEST_OFFSET);
		try {
			// Note : Value per layout can be computed through BinaryBenchmark.
			when(mockBank.readByte(anyInt())).thenAnswer(invocationMock -> {
				final int address = invocationMock.getArgument(0);
				if (address == TEST_OFFSET) {
					return (byte) 85;
				}
				else if (address == TEST_OFFSET + 1) {
					return (byte) 15;
				}
				else if (address == TEST_OFFSET + 2) {
					return (byte) 0;
				}
				throw new IllegalAccessException();
			});
			when(mockBank.readBytes(anyInt(), anyInt())).thenCallRealMethod();
		}
		catch (final IllegalAccessException e) {
			// Do nothing.
		}
		return mockBank;
	}

}
