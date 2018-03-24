package fr.faylixe.yage.cpu.register;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.function.Consumer;

import org.junit.jupiter.api.Test;

/**
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
		assertFalse(register.isZero());
		assertFalse(register.isSubstraction());
		assertFalse(register.isHalfCarry());
		assertFalse(register.isCarry());
		assertEquals(0, register.get());
		test.accept(register);
	
	}

	/** Test zero flag operations. **/
	@Test
	public void testZeroFlag() {
		performFlagsRegisterTest(register -> {
			// TODO : Set zero flag.
			// TODO : Check register byte value.
			// TODO : Check register state.
		});
	}

	/** Test substraction flag operations. **/
	@Test
	public void testSubstractionFlag() {
		performFlagsRegisterTest(register -> {
			// TODO : Set zero flag.
			// TODO : Check register byte value.
			// TODO : Check register state.
		});
	}

	/** Test half carry flag operations. **/
	@Test
	public void testHalfCarryFlag() {
		performFlagsRegisterTest(register -> {
			// TODO : Set zero flag.
			// TODO : Check register byte value.
			// TODO : Check register state.
		});
	}

	/** Test carry flag operations. **/
	@Test
	public void testCarryFlag() {
		performFlagsRegisterTest(register -> {
			// TODO : Set zero flag.
			// TODO : Check register byte value.
			// TODO : Check register state.
		});
	}

}
