package fr.faylixe.jgb.memory.bank;

import static org.junit.jupiter.api.Assertions.fail;

import fr.faylixe.jgb.memory.IMemoryBankTest;

/**
 * Unit test case for the bitset based memory bank.
 * 
 * @author fv
 */
public final class BitSetMemoryBankTest implements IMemoryBankTest {

	/** {@inheritDoc} **/
	@Override
	public IMemoryBank getTestMemoryBank() {
		final IMemoryBank bank = new BitSetMemoryBank(TEST_SIZE, TEST_OFFSET);
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
