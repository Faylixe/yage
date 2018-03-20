package fr.faylixe.jgb.memory.bank;

import static org.junit.jupiter.api.Assertions.fail;

/**
 * Unit test case for the array based memory bank.
 * 
 * @author fv
 */
public final class ArrayMemoryBankTest implements IMemoryBankTest {

	/** {@inheritDoc} **/
	@Override
	public IMemoryBank getTestMemoryBank() {
		final IMemoryBank bank = new ArrayMemoryBank(TEST_SIZE, TEST_OFFSET);
		try {
			bank.writeByte((byte) 85, TEST_OFFSET);
			bank.writeByte((byte) 15, TEST_OFFSET + 1);
		}
		catch (final IllegalAccessException e) {
			fail(e);
		}
		return bank;
	}

}
