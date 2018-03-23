package fr.faylixe.yage.memory;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import fr.faylixe.yage.memory.bank.ArrayMemoryBankTest;
import fr.faylixe.yage.memory.bank.BitSetMemoryBank;
import fr.faylixe.yage.memory.bank.ReadOnlyMemoryBankTest;
import fr.faylixe.yage.memory.bank.SwitchableMemoryBankTest;

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
