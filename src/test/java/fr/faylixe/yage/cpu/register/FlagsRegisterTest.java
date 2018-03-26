package fr.faylixe.yage.cpu.register;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.function.Consumer;

import org.junit.jupiter.api.Test;

/**
 * Test flag register operations.
 * 
 * @author fv
 */
public final class FlagsRegisterTest {

	/**
	 * Initializes an empty flag register, assuming
	 * everything is alright, and executes the
	 * given <tt>test</tt> with it.
	 * 
	 * @param test Concrete test to perform.
	 */
	private void performFlagsRegisterTest(final Consumer<FlagsRegister> test) {
		final FlagsRegister register = new FlagsRegister();
		assertEquals(0, register.get());
		assertFalse(register.isZero());
		assertFalse(register.isSubtraction());
		assertFalse(register.isHalfCarry());
		assertFalse(register.isCarry());
		test.accept(register);
	
	}

	/** Test zero flag operations. **/
	@Test
	public void testZeroFlag() {
		performFlagsRegisterTest(register -> {
			register.setZero();
			assertEquals(-128, register.get());
			assertTrue(register.isZero());
			assertFalse(register.isSubtraction());
			assertFalse(register.isHalfCarry());
			assertFalse(register.isCarry());
		});
	}

	/** Test subtraction flag operations. **/
	@Test
	public void testSubtractionFlag() {
		performFlagsRegisterTest(register -> {
			register.setSubtraction();
			assertEquals(64, register.get());
			assertFalse(register.isZero());
			assertTrue(register.isSubtraction());
			assertFalse(register.isHalfCarry());
			assertFalse(register.isCarry());
		});
	}

	/** Test half carry flag operations. **/
	@Test
	public void testHalfCarryFlag() {
		performFlagsRegisterTest(register -> {
			register.setHalfCarry();
			assertEquals(32, register.get());
			assertFalse(register.isZero());
			assertFalse(register.isSubtraction());
			assertTrue(register.isHalfCarry());
			assertFalse(register.isCarry());
		});
	}

	/** Test carry flag operations. **/
	@Test
	public void testCarryFlag() {
		performFlagsRegisterTest(register -> {
			register.setCarry();
			assertEquals(16, register.get());
			assertFalse(register.isZero());
			assertFalse(register.isSubtraction());
			assertFalse(register.isHalfCarry());
			assertTrue(register.isCarry());
		});
	}

}
