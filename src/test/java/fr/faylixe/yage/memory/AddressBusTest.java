package fr.faylixe.yage.memory;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.faylixe.yage.memory.AddressBus;
import fr.faylixe.yage.memory.IMemoryStream;
import fr.faylixe.yage.memory.bank.IMemoryBank;
import fr.faylixe.yage.memory.bank.IMemoryBankTest;

/**
 * 
 * @author fv
 */
public final class AddressBusTest implements IMemoryStreamTest {

	/** Target test bus. **/
	private AddressBus addressBus;

	/** **/
	private IMemoryBank bank1;

	/** **/
	@BeforeEach
	public void setup() {
		addressBus = new AddressBus(16);
		bank1 = IMemoryBankTest.createMemoryBankMock();
		addressBus.connect(bank1);
	}

	/** {@inheritDoc} **/
	@Override
	public IMemoryStream getTestMemoryStream() {
		return addressBus;
	}

	/** **/
	@Test
	public void testIllegalconnect() {
		assertThrows(IllegalArgumentException.class, () -> addressBus.connect(null));
		// TODO : Check for conflicting space.
	}

	/** **/
	@Test
	public void testIllegalDisconnect() {
		assertThrows(IllegalArgumentException.class, () -> addressBus.disconnect(null));		
	}

}
