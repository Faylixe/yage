package fr.faylixe.yage.cpu.instruction.set;

import static fr.faylixe.yage.cpu.register.IRegisterProvider.Register.*;
import static fr.faylixe.yage.cpu.instruction.set.ByteLoadInstructionSet.*;
import static fr.faylixe.yage.cpu.register.IRegisterProviderTest.createRegistersTest;
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
		performInstructionTest(0x78, 4, LOAD_B_TO_A, createRegistersTest(A, (byte) 0x2));
	}

	/** Test LD C, A instruction. **/
	@Test
	public void testLoadCToA() {
		performInstructionTest(0x79, 4, LOAD_C_TO_A, createRegistersTest(A, (byte) 0x3));
	}

	/** Test LD D, A instruction. **/
	@Test
	public void testLoadDToA() {
		performInstructionTest(0x7A, 4, LOAD_D_TO_A, createRegistersTest(A, (byte) 0x4));
	}

	/** Test LD E, A instruction. **/
	@Test
	public void testLoadEToA() {
		performInstructionTest(0x7B, 4, LOAD_E_TO_A, createRegistersTest(A, (byte) 0x5));
	}

	/** Test LD H, A instruction. **/
	@Test
	public void testLoadHToA() {
		performInstructionTest(0x7C, 4, LOAD_H_TO_A, createRegistersTest(A, (byte) 0x6));
	}

	/** Test LD (NN), A instruction. **/
	@Test
	public void testLoadNNToA() {
		performInstructionTest(0xFA, 16, LOAD_NN_TO_A, context -> {
			createRegistersTest(A, (byte) 0).accept(context);
			verify(context, times(1)).nextShort();
			verify(context, times(1)).readByte(10794);
		});
	}

	/** Test LD #, A instruction. **/
	@Test
	public void testLoadNToA() {
		performInstructionTest(0x3E, 8, LOAD_N_TO_A, createRegistersTest(A, (byte) 42));
	}

	/** Test LD A, B instruction. **/
	@Test
	public void testLoadAToB() {
		performInstructionTest(0x47, 4, LOAD_A_TO_B, createRegistersTest(B, (byte) 0x1));
	}
	
	/** Test LD A, C instruction. **/
	@Test
	public void testLoadAToC() {
		performInstructionTest(0x4F, 4, LOAD_A_TO_C, createRegistersTest(C, (byte) 0x1));
	}

	/** Test LD A, D instruction. **/
	@Test
	public void testLoadAToD() {
		performInstructionTest(0x57, 4, LOAD_A_TO_D, createRegistersTest(D, (byte) 0x1));
	}

	/** Test LD A, E instruction. **/
	@Test
	public void testLoadAToE() {
		performInstructionTest(0x5F, 4, LOAD_A_TO_E, createRegistersTest(E, (byte) 0x1));
	}

	/** Test LD A, H instruction. **/
	@Test
	public void testLoadAToH() {
		performInstructionTest(0x67, 4, LOAD_A_TO_H, createRegistersTest(H, (byte) 0x1));
	}

	/** Test LD A, L instruction. **/
	@Test
	public void testLoadAToL() {
		performInstructionTest(0x6F, 4, LOAD_A_TO_L, createRegistersTest(L, (byte) 0x1));
	}

}
