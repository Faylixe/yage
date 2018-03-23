package fr.faylixe.yage.memory.bank;

import fr.faylixe.yage.memory.IMemoryBank;
import fr.faylixe.yage.memory.IMemoryBankWriteTest;
import fr.faylixe.yage.memory.bank.ArrayMemoryBank;

/**
 * Unit test case for the array based memory bank.
 * 
 * @author fv
 */
public final class ArrayMemoryBankTest implements IMemoryBankWriteTest {

	/** {@inheritDoc} **/
	@Override
	public IMemoryBank createTestMemoryBank(final int size, final int offset) {
		return new ArrayMemoryBank(size, offset);
	}

}
