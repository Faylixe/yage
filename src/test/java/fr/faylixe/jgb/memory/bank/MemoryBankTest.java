package fr.faylixe.jgb.memory.bank;

import fr.faylixe.jgb.memory.IMemoryBank;
import fr.faylixe.jgb.memory.IMemoryBankTest;

/**
 * 
 * @author fv
 */
public final class MemoryBankTest implements IMemoryBankTest {

	/** {@inheritDoc} **/
	@Override
	public IMemoryBank getTestMemoryBank() {
		final IMemoryBank bank = new ArrayMemoryBank(TEST_SIZE, TEST_OFFSET);
		// TODO : Fill up with data layout.
		return bank;
	}

}
