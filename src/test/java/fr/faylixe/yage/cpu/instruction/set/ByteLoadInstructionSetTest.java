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

	// LD nn, n

	/** Test LD B, n instruction. **/
	@Test
	public void testLoadNToB() {
		performInstructionTest(0x06, 8, LOAD_N_TO_B, createRegistersTest(B, (byte) 42));
	}

	/** Test LD C, n instruction. **/
	@Test
	public void testLoadNToC() {
		performInstructionTest(0x0E, 8, LOAD_N_TO_C, createRegistersTest(C, (byte) 42));
	}

	/** Test LD D, n instruction. **/
	@Test
	public void testLoadNToD() {
		performInstructionTest(0x16, 8, LOAD_N_TO_D, createRegistersTest(D, (byte) 42));
	}

	/** Test LD E, n instruction. **/
	@Test
	public void testLoadNToE() {
		performInstructionTest(0x1E, 8, LOAD_N_TO_E, createRegistersTest(E, (byte) 42));
	}

	/** Test LD H, n instruction. **/
	@Test
	public void testLoadNToH() {
		performInstructionTest(0x26, 8, LOAD_N_TO_H, createRegistersTest(H, (byte) 42));
	}

	/** Test LD L, n instruction. **/
	@Test
	public void testLoadNToL() {
		performInstructionTest(0x2E, 8, LOAD_N_TO_L, createRegistersTest(L, (byte) 42));
	}

	// LD r1, r2
	
	/** Test LD B, B instruction. **/
	@Test
	public void testLoadBToB() {
		performInstructionTest(0x40, 4, LOAD_B_TO_B, createRegistersTest());	
	}
	
	/** Test LD B, C instruction.  **/
	@Test
	public void testLoadCToB() {
		performInstructionTest(0x41, 4, LOAD_C_TO_B, createRegistersTest(B, (byte) 3));
	}
	
	/** Test LD B, D instruction.  **/
	@Test
	public void testLoadDToB() {
		performInstructionTest(0x42, 4, LOAD_D_TO_B, createRegistersTest(B, (byte) 4));
	}

	/** Test LD B, E instruction.  **/
	@Test
	public void testLoadEToB() {
		performInstructionTest(0x43, 4, LOAD_E_TO_B, createRegistersTest(B, (byte) 5));
	}

	/** Test LD B, H instruction.  **/
	@Test
	public void testLoadHToB() {
		performInstructionTest(0x44, 4, LOAD_H_TO_B, createRegistersTest(B, (byte) 6));
	}

	/** Test LD B, L instruction.  **/
	@Test
	public void testLoadLToB() {
		performInstructionTest(0x45, 4, LOAD_L_TO_B, createRegistersTest(B, (byte) 7));
	}

	/** Test LD B, (HL) instruction.  **/
	@Test
	public void testLoadHLToB() {
		performInstructionTest(0x46, 8, LOAD_HL_TO_B, context -> {
			createRegistersTest(B, (byte) 0).accept(context);
			verify(context, times(1)).readByte(1543);
		});
	}
	
	/** Test LD C, B instruction. **/
	@Test
	public void testLoadBToC() {
		performInstructionTest(0x48, 4, LOAD_B_TO_C, createRegistersTest(C, (byte) 2));	
	}
	
	/** Test LD C, C instruction.  **/
	@Test
	public void testLoadCToC() {
		performInstructionTest(0x49, 4, LOAD_C_TO_C, createRegistersTest());
	}
	
	/** Test LD C, D instruction.  **/
	@Test
	public void testLoadDToC() {
		performInstructionTest(0x4A, 4, LOAD_D_TO_C, createRegistersTest(C, (byte) 4));
	}

	/** Test LD C, E instruction.  **/
	@Test
	public void testLoadEToC() {
		performInstructionTest(0x4B, 4, LOAD_E_TO_C, createRegistersTest(C, (byte) 5));
	}

	/** Test LD C, H instruction.  **/
	@Test
	public void testLoadHToC() {
		performInstructionTest(0x4C, 4, LOAD_H_TO_C, createRegistersTest(C, (byte) 6));
	}

	/** Test LD C, L instruction.  **/
	@Test
	public void testLoadLToC() {
		performInstructionTest(0x4D, 4, LOAD_L_TO_C, createRegistersTest(C, (byte) 7));
	}

	/** Test LD C, (HL) instruction.  **/
	@Test
	public void testLoadHLToC() {
		performInstructionTest(0x4E, 8, LOAD_HL_TO_C, context -> {
			createRegistersTest(C, (byte) 0).accept(context);
			verify(context, times(1)).readByte(1543);
		});
	}
	
	/** Test LD D, B instruction. **/
	@Test
	public void testLoadBToD() {
		performInstructionTest(0x50, 4, LOAD_B_TO_D, createRegistersTest(D, (byte) 2));	
	}
	
	/** Test LD D, C instruction.  **/
	@Test
	public void testLoadCToD() {
		performInstructionTest(0x51, 4, LOAD_C_TO_D, createRegistersTest(D, (byte) 3));
	}
	
	/** Test LD D, D instruction.  **/
	@Test
	public void testLoadDToD() {
		performInstructionTest(0x52, 4, LOAD_D_TO_D, createRegistersTest());
	}

	/** Test LD D, E instruction.  **/
	@Test
	public void testLoadEToD() {
		performInstructionTest(0x53, 4, LOAD_E_TO_D, createRegistersTest(D, (byte) 5));
	}

	/** Test LD D, H instruction.  **/
	@Test
	public void testLoadHToD() {
		performInstructionTest(0x54, 4, LOAD_H_TO_D, createRegistersTest(D, (byte) 6));
	}

	/** Test LD D, L instruction.  **/
	@Test
	public void testLoadLToD() {
		performInstructionTest(0x55, 4, LOAD_L_TO_D, createRegistersTest(D, (byte) 7));
	}

	/** Test LD D, (HL) instruction.  **/
	@Test
	public void testLoadHLToD() {
		performInstructionTest(0x56, 8, LOAD_HL_TO_D, context -> {
			createRegistersTest(D, (byte) 0).accept(context);
			verify(context, times(1)).readByte(1543);
		});
	}
	
	/** Test LD E, B instruction. **/
	@Test
	public void testLoadBToE() {
		performInstructionTest(0x40, 4, LOAD_B_TO_E, createRegistersTest(E, (byte) 2));	
	}
	
	/** Test LD E, C instruction.  **/
	@Test
	public void testLoadCToE() {
		performInstructionTest(0x41, 4, LOAD_C_TO_E, createRegistersTest(E, (byte) 3));
	}
	
	/** Test LD E, D instruction.  **/
	@Test
	public void testLoadDToE() {
		performInstructionTest(0x42, 4, LOAD_D_TO_E, createRegistersTest(E, (byte) 4));
	}

	/** Test LD E, E instruction.  **/
	@Test
	public void testLoadEToE() {
		performInstructionTest(0x43, 4, LOAD_E_TO_E, createRegistersTest());
	}

	/** Test LD E, H instruction.  **/
	@Test
	public void testLoadHToE() {
		performInstructionTest(0x44, 4, LOAD_H_TO_E, createRegistersTest(E, (byte) 6));
	}

	/** Test LD E, L instruction.  **/
	@Test
	public void testLoadLToE() {
		performInstructionTest(0x45, 4, LOAD_L_TO_E, createRegistersTest(E, (byte) 7));
	}

	/** Test LD E, (HL) instruction.  **/
	@Test
	public void testLoadHLToE() {
		performInstructionTest(0x46, 8, LOAD_HL_TO_E, context -> {
			createRegistersTest(B, (byte) 0).accept(context);
			verify(context, times(1)).readByte(1543);
		});
	}
	
	// TODO : To check
	
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
