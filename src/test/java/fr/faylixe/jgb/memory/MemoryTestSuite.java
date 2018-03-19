package fr.faylixe.jgb.memory;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import fr.faylixe.jgb.memory.bank.ArrayMemoryBankTest;
import fr.faylixe.jgb.memory.bank.BitSetMemoryBank;
import fr.faylixe.jgb.memory.bank.ReadOnlyMemoryBankTest;
import fr.faylixe.jgb.memory.bank.SwitchableMemoryBankTest;

@RunWith(Suite.class)
@SuiteClasses({
	ReadOnlyMemoryBankTest.class,
	ArrayMemoryBankTest.class,
	BitSetMemoryBank.class,
	SwitchableMemoryBankTest.class,
	AddressBusTest.class
})
public final class MemoryTestSuite {

}
