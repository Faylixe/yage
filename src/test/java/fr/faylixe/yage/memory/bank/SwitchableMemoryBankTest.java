package fr.faylixe.yage.memory.bank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.faylixe.yage.memory.IMemoryBank;
import fr.faylixe.yage.memory.IMemoryBankTest;
import fr.faylixe.yage.memory.bank.SwitchableMemoryBank;

/**
 * Unit test case for the switchable memory bank.
 * 
 * @author fv
 */
public final class SwitchableMemoryBankTest implements IMemoryBankTest {

	/** First memory bank that can be used. **/
	private IMemoryBank bank1;

	/** Second memory bank that can be used. **/
	private IMemoryBank bank2;

	/** Initializes mock banks. **/
	@BeforeEach
	public void setup() {
		bank1 = IMemoryBankTest.createMemoryBankMock();
		bank2 = IMemoryBankTest.createMemoryBankMock();
	}

	/** {@inheritDoc} **/
	@Override
	public IMemoryBank getTestMemoryBank() {
		final IMemoryBank switchableBank = SwitchableMemoryBank
				.builder(TEST_SIZE, TEST_OFFSET)
				.addMemoryBank(bank1)
				.addMemoryBank(bank2)
				.build();
		return switchableBank;
	}
	
	/** Ensures switching and operation delegation. **/
	@Test
	public void testSwitching() {
		performBankTest(bank -> {
			final SwitchableMemoryBank switchableBank = (SwitchableMemoryBank) bank;
			assertThrows(IllegalArgumentException.class, () -> switchableBank.switchBank(-1));
			assertThrows(IllegalArgumentException.class, () -> switchableBank.switchBank(2));
			assertEquals(2, switchableBank.getBankSize());
			try {
				switchableBank.writeByte((byte) 42, TEST_OFFSET);
				verify(bank1, times(1)).writeByte((byte) 42, TEST_OFFSET);
				verify(bank2, never()).writeByte((byte) 42, TEST_OFFSET);
				switchableBank.switchBank(1);
				switchableBank.writeByte((byte) 42, TEST_OFFSET);
				verify(bank1, times(1)).writeByte((byte) 42, TEST_OFFSET);
				verify(bank2, times(1)).writeByte((byte) 42, TEST_OFFSET);
			}
			catch (final IllegalAccessException e) {
				fail(e);
			}
		});
	}

}
