package fr.faylixe.jgb.memory;

import org.junit.jupiter.api.BeforeEach;

/**
 * 
 * @author fv
 */
public final class AddressBusTest implements IMemoryStreamTest {

	/** Target test bus. **/
	private AddressBus addressBus;

	/** **/
	@BeforeEach
	public void setupAddressBus() {
		addressBus = new AddressBus();
		// TODO : Configure a main block here.
	}

	/** {@inheritDoc} **/
	@Override
	public IMemoryStream getTestMemoryStream() {
		return addressBus;
	}

}
