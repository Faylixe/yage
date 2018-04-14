package fr.faylixe.yage.memory.bank;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import static fr.faylixe.yage.memory.IMemoryStreamTest.TEST_OFFSET;
import static fr.faylixe.yage.memory.IMemoryStreamTest.TEST_SIZE;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.faylixe.yage.memory.IMemoryBank;
import fr.faylixe.yage.memory.IMemoryBankTest;
import fr.faylixe.yage.utils.ErrorlessTest;

/**
 * Unit test case for the echo memory bank.
 * 
 * @author fv
 */
public final class EchoMemoryBankTest {

	/** Target echoed bank. **/
	private IMemoryBank echoed;

	/** Tested echo instance. **/
	private IMemoryBank echo;

	/** Initializes target instance. **/
	@BeforeEach
	public void setUp() {
		echoed = IMemoryBankTest.createMemoryBankMock();
		echo = new EchoMemoryBank(echoed, TEST_OFFSET + TEST_SIZE);
	}

	/** Test read byte to a target echoed address. **/
	@Test
	public void testEchoReading() {
		ErrorlessTest.run(() -> {
			echo.readByte(TEST_OFFSET + TEST_SIZE);
			verify(echoed, times(1)).readByte(TEST_OFFSET);
		});
	}

	/** Test read bytes to a target echoed address. **/
	@Test
	public void testEchoReadings() {
		ErrorlessTest.run(() -> {
			echo.readBytes(TEST_OFFSET + TEST_SIZE, 2);
			verify(echoed, times(1)).readBytes(TEST_OFFSET, 2);
		});
	}

	/** Test write byte to a target echoed address. **/
	@Test
	public void testEchoWriting() {
		ErrorlessTest.run(() -> {
			echo.writeByte((byte) 42, TEST_OFFSET + TEST_SIZE);
			verify(echoed, times(1)).writeByte((byte) 42, TEST_OFFSET);
		});
	}

	/** Test write bytes to a target echoed address. **/
	@Test
	public void testEchoWritings() {
		ErrorlessTest.run(() -> {
			final byte [] values = { 42 };
			echo.writeBytes(values, TEST_OFFSET + TEST_SIZE);
			verify(echoed, times(1)).writeBytes(values, TEST_OFFSET);
		});
	}

}
