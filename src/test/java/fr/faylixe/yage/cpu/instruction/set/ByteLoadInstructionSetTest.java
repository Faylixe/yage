package fr.faylixe.yage.cpu.instruction.set;

import static fr.faylixe.yage.cpu.register.IRegisterProvider.Register.A;
import static fr.faylixe.yage.cpu.instruction.set.ByteLoadInstructionSet.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import fr.faylixe.yage.cpu.instruction.IInstructionSetTest;

/**
 * 
 * @author fv
 */
public final class ByteLoadInstructionSetTest implements IInstructionSetTest {

	/** Test LD A, A instruction. **/
	@Test
	public void testLoadBToA() {
		performInstructionTest(0x78, 4, LOAD_B_TO_A, context -> {
			assertEquals(2, context.getRegister(A).get());
		});
	}

	/** Test LD C, A instruction. **/
	@Test
	public void testLoadCToA() {
		performInstructionTest(0x79, 4, LOAD_C_TO_A, context -> {
			assertEquals(3, context.getRegister(A).get());
		});
	}

	/** Test LD D, A instruction. **/
	@Test
	public void testLoadDToA() {
		performInstructionTest(0x7A, 4, LOAD_D_TO_A, context -> {
			assertEquals(4, context.getRegister(A).get());
		});
	}

	/** Test LD E, A instruction. **/
	@Test
	public void testLoadEToA() {
		performInstructionTest(0x7B, 4, LOAD_E_TO_A, context -> {
			assertEquals(5, context.getRegister(A).get());
		});
	}

	/** Test LD H, A instruction. **/
	@Test
	public void testLoadHToA() {
		performInstructionTest(0x7C, 4, LOAD_H_TO_A, context -> {
			assertEquals(6, context.getRegister(A).get());
		});
	}

	/** Test LD L, A instruction. **/
	@Test
	public void testLoadLToA() {
		performInstructionTest(0x7D, 4, LOAD_L_TO_A, context -> {
			assertEquals(7, context.getRegister(A).get());
		});
	}

}
