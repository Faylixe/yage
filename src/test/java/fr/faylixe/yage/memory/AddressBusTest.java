package fr.faylixe.yage.memory;

import static java.lang.Math.pow;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import static fr.faylixe.yage.memory.bank.IMemoryBankTest.createMemoryBankMock;
import static fr.faylixe.yage.memory.bank.IMemoryBankTest.createIOLessMemoryBankMock;

import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.function.Executable;

import fr.faylixe.yage.memory.AddressBus;
import fr.faylixe.yage.memory.IMemoryStream;
import fr.faylixe.yage.memory.bank.IMemoryBank;

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
		bank1 = createMemoryBankMock();
		addressBus.connect(bank1);
	}

	/** {@inheritDoc} **/
	@Override
	public IMemoryStream getTestMemoryStream() {
		return addressBus;
	}

	/**
	 * Factory method that build an executable bus connection test
	 * from the given <tt>bank</tt> assuming that the given bank
	 * is illegal.
	 * 
	 * @param bank Bank to build test for.
	 * @return Built executable test.
	 */
	private Executable buildIllegalConnectTest(final IMemoryBank bank) {
		return () -> assertThrows(IllegalArgumentException.class, () -> addressBus.connect(bank));
	}

	/**
	 * Factory method that build an executable bus connection test
	 * from the given <tt>offset</tt> address by building a mock
	 * memory from it.
	 * 
	 * @param offset Offset built memory will start from.
	 * @return Built executable test.
	 */
	private Executable buildIllegalConnectTest(final int offset) {
		return buildIllegalConnectTest(createIOLessMemoryBankMock(TEST_SIZE, offset));
	}

	/** Test connecting null or conflicting addressing. **/
	@TestFactory
	public Collection<DynamicTest> testIllegalconnect() {
		return Arrays.asList(
				dynamicTest("null bank", buildIllegalConnectTest(null)),
				dynamicTest("negative address", buildIllegalConnectTest(-1)),
				dynamicTest("address out of space", buildIllegalConnectTest((int) pow(2, 16))),
				dynamicTest("copy", buildIllegalConnectTest(TEST_OFFSET)),
				dynamicTest("lower overlap", buildIllegalConnectTest(TEST_OFFSET - 1)),
				dynamicTest("upper overlap", buildIllegalConnectTest(TEST_OFFSET + TEST_SIZE - 1))
		);
	}

	/** Test disconnecting <tt>null</tt> or not connected bank. **/
	@Test
	public void testIllegalDisconnect() {
		assertThrows(IllegalArgumentException.class, () -> addressBus.disconnect(null));
		assertThrows(IllegalArgumentException.class, () -> addressBus.disconnect(createIOLessMemoryBankMock(0, 0)));
	}

}
