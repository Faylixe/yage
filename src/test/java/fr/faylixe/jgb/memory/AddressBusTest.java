package fr.faylixe.jgb.memory;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import fr.faylixe.jgb.MockitoExtension;

/**
 * 
 * @author fv
 */
@ExtendWith(MockitoExtension.class)
public final class AddressBusTest implements IMemoryStreamTest {

	/** Target test bus. **/
	private AddressBus addressBus;

	/** **/
	@Mock
	private IMemoryBank mainBank;

	/** **/
	@BeforeEach
	public void setup() {
		addressBus = new AddressBus();
		when(mainBank.getSize()).thenReturn(TEST_SIZE);
		when(mainBank.getOffset()).thenReturn(TEST_OFFSET);
		//when(mainBank.readByte(any(Integer.class)))
	}

	/** {@inheritDoc} **/
	@Override
	public IMemoryStream getTestMemoryStream() {
		// TODO : Configure address bus with plugged bank here.
		return addressBus;
	}

	/** **/
	@Test
	public void testConnect() {
		assertThrows(IllegalArgumentException.class, () -> addressBus.connect(null));
		//addressBus.connect();
	}

	/** **/
	@Test
	public void testDisconnect() {
		assertThrows(IllegalArgumentException.class, () -> addressBus.disconnect(null));		
		// TODO : Test out of range value.
	}

}
