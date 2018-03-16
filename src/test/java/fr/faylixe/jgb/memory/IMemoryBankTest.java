package fr.faylixe.jgb.memory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.function.Consumer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

/**
 * Generic test for memory bank. Assumes that tested memory
 * bank always has the same properties, namely a 2 byte size and
 * a 4 address offset. Also as the memory bank is also a memory
 * stream, it assumes that the memory as the following data layout:
 * 
 * (4) -> 0 1 0 1 0 1 0 1
 * (5) -> 1 1 1 1 0 0 0 0
 * 
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

}
