package fr.faylixe.yage.memory.bank;

import fr.faylixe.yage.memory.bank.BitSetMemoryBank;
import fr.faylixe.yage.memory.bank.IMemoryBank;

/**
 * Unit test case for the bitset based memory bank.
 * 
 * @author fv
 */
public final class BitSetMemoryBankTest implements IMemoryBankWriteTest {

	/** {@inheritDoc} **/
	@Override
	public IMemoryBank createTestMemoryBank(final int size, final int offset) {
		return new BitSetMemoryBank(size, offset);
	}

}
