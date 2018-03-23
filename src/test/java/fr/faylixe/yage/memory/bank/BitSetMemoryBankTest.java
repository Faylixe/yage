package fr.faylixe.yage.memory.bank;

import static org.junit.jupiter.api.Assertions.fail;

import fr.faylixe.yage.memory.bank.BitSetMemoryBank;
import fr.faylixe.yage.memory.bank.IMemoryBank;

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
