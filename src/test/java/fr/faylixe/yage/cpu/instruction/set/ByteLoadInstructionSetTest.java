package fr.faylixe.yage.cpu.instruction.set;

import static fr.faylixe.yage.cpu.register.IRegisterProvider.Register.*;
import static fr.faylixe.yage.cpu.instruction.set.ByteLoadInstructionSet.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;

import fr.faylixe.yage.cpu.instruction.IInstructionSetTest;

/**
 * 
 * @author fv
 */
public final class ByteLoadInstructionSetTest implements IInstructionSetTest {

	/** Test LD A, A instruction. **/
	@Test
	public void testLoadAToA() {
		performInstructionTest(0x7F, 4, LOAD_A_TO_A, context -> {
			assertEquals(1, context.getRegister(A).get());
		});
	}

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

	/** Test LD (NN), A instruction. **/
	@Test
	public void testLoadNNToA() {
		performInstructionTest(0xFA, 16, LOAD_NN_TO_A, context -> {
			assertEquals(0, context.getRegister(A).get());
			verify(context, times(1)).nextShort();
			verify(context, times(1)).readByte(10794);
		});
	}

	/** Test LD #, A instruction. **/
	@Test
	public void testLoadNToA() {
		performInstructionTest(0x3E, 8, LOAD_N_TO_A, context -> {
			assertEquals(42, context.getRegister(A).get());
		});
	}

	/** Test LD A, B instruction. **/
	@Test
	public void testLoadAToB() {
		performInstructionTest(0x47, 4, LOAD_A_TO_B, context -> {
			assertEquals(1, context.getRegister(B).get());
		});
	}
	
	/** Test LD A, C instruction. **/
	@Test
	public void testLoadAToC() {
		performInstructionTest(0x4F, 4, LOAD_A_TO_C, context -> {
			assertEquals(1, context.getRegister(C).get());
		});
	}

	/** Test LD A, D instruction. **/
	@Test
	public void testLoadAToD() {
		performInstructionTest(0x57, 4, LOAD_A_TO_D, context -> {
			assertEquals(1, context.getRegister(D).get());
		});
	}

	/** Test LD A, E instruction. **/
	@Test
	public void testLoadAToE() {
		performInstructionTest(0x5F, 4, LOAD_A_TO_E, context -> {
			assertEquals(1, context.getRegister(E).get());
		});
	}

	/** Test LD A, H instruction. **/
	@Test
	public void testLoadAToH() {
		performInstructionTest(0x67, 4, LOAD_A_TO_H, context -> {
			assertEquals(1, context.getRegister(H).get());
		});
	}

	/** Test LD A, L instruction. **/
	@Test
	public void testLoadAToL() {
		performInstructionTest(0x6F, 4, LOAD_A_TO_L, context -> {
			assertEquals(1, context.getRegister(L).get());
		});
	}

}
