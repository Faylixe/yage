package fr.faylixe.yage.cpu.instruction.set;

import static fr.faylixe.yage.cpu.register.IRegisterProvider.Register.*;
import static fr.faylixe.yage.cpu.instruction.set.ByteLoadInstructionSet.*;
import static fr.faylixe.yage.cpu.register.IRegisterProviderTest.createRegistersTest;

import static java.util.Arrays.asList;

import static org.junit.jupiter.api.DynamicTest.dynamicTest;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Collection;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import fr.faylixe.yage.cpu.instruction.IInstructionSetTest;

/**
 * 
 * @author fv
 */
public final class ByteLoadInstructionSetTest implements IInstructionSetTest {

	/**
	 * 
	 * @return
	 */
	@TestFactory
	public Collection<DynamicTest> testLoadFromImmediate() {
		return asList(
				dynamicTest("LD B, n", () -> performInstructionTest(0x06, 8, LD_B_N, createRegistersTest(B, (byte) 42))),
				dynamicTest("LD C, n", () -> performInstructionTest(0x0E, 8, LD_C_N, createRegistersTest(C, (byte) 42))),
				dynamicTest("LD D, n", () -> performInstructionTest(0x16, 8, LD_D_N, createRegistersTest(D, (byte) 42))),
				dynamicTest("LD E, n", () -> performInstructionTest(0x1E, 8, LD_E_N, createRegistersTest(E, (byte) 42))),
				dynamicTest("LD H, n", () -> performInstructionTest(0x26, 8, LD_H_N, createRegistersTest(H, (byte) 42))),
				dynamicTest("LD L, n", () -> performInstructionTest(0x2E, 8, LD_L_N, createRegistersTest(L, (byte) 42)))
		);
	}
	
	/**
	 * 
	 * @return
	 */
	@TestFactory
	public Collection<DynamicTest> testLoadToB() {
		return asList(
				dynamicTest("LD B, B", () -> performInstructionTest(0x40, 4, LD_B_B, createRegistersTest())),
				dynamicTest("LD B, C", () -> performInstructionTest(0x41, 4, LD_B_C, createRegistersTest(B, (byte) 3))),
				dynamicTest("LD B, D", () -> performInstructionTest(0x42, 4, LD_B_D, createRegistersTest(B, (byte) 4))),
				dynamicTest("LD B, E", () -> performInstructionTest(0x43, 4, LD_B_E, createRegistersTest(B, (byte) 5))),
				dynamicTest("LD B, H", () -> performInstructionTest(0x44, 4, LD_B_H, createRegistersTest(B, (byte) 6))),
				dynamicTest("LD B, L", () -> performInstructionTest(0x45, 4, LD_B_L, createRegistersTest(B, (byte) 7))),
				dynamicTest("LD B, (HL)", () -> performInstructionTest(0x46, 8, LD_B_HL, context -> {
					createRegistersTest(B, (byte) 0).accept(context);
					verify(context, times(1)).readByte(1543);
				}))
		);
	}

	/**
	 * 
	 * @return
	 */
	@TestFactory
	public Collection<DynamicTest> testLoadToC() {
		return asList(
			dynamicTest("LD C, B", () -> performInstructionTest(0x48, 4, LD_C_B, createRegistersTest(C, (byte) 2))),
			dynamicTest("LD C, C", () -> performInstructionTest(0x49, 4, LD_C_C, createRegistersTest())),
			dynamicTest("LD C, D", () -> performInstructionTest(0x4A, 4, LD_C_D, createRegistersTest(C, (byte) 4))),
			dynamicTest("LD C, E", () -> performInstructionTest(0x4B, 4, LD_C_E, createRegistersTest(C, (byte) 5))),
			dynamicTest("LD C, H", () -> performInstructionTest(0x4C, 4, LD_C_H, createRegistersTest(C, (byte) 6))),
			dynamicTest("LD C, L", () -> performInstructionTest(0x4D, 4, LD_C_L, createRegistersTest(C, (byte) 7))),
			dynamicTest("LD C, (HL)", () -> performInstructionTest(0x4E, 8, LD_C_HL, context -> {
				createRegistersTest(C, (byte) 0).accept(context);
				verify(context, times(1)).readByte(1543);
			}))
		);
	}
	
	/**
	 * 
	 * @return
	 */
	@TestFactory
	public Collection<DynamicTest> testLoadToD() {
		return asList(
			dynamicTest("LD D, B", () -> performInstructionTest(0x50, 4, LD_D_B, createRegistersTest(D, (byte) 2))),
			dynamicTest("LD D, C", () -> performInstructionTest(0x51, 4, LD_D_C, createRegistersTest(D, (byte) 3))),
			dynamicTest("LD D, D", () -> performInstructionTest(0x52, 4, LD_D_D, createRegistersTest())),
			dynamicTest("LD D, E", () -> performInstructionTest(0x53, 4, LD_D_E, createRegistersTest(D, (byte) 5))),
			dynamicTest("LD D, H", () -> performInstructionTest(0x54, 4, LD_D_H, createRegistersTest(D, (byte) 6))),
			dynamicTest("LD D, L", () -> performInstructionTest(0x55, 4, LD_D_L, createRegistersTest(D, (byte) 7))),
			dynamicTest("LD D, HL", () -> performInstructionTest(0x56, 8, LD_D_HL, context -> {
				createRegistersTest(D, (byte) 0).accept(context);
				verify(context, times(1)).readByte(1543);
			}))
		);
	}
	
	/**
	 * 
	 * @return
	 */
	@TestFactory
	public Collection<DynamicTest> testLoadToE() {
		return asList(
			dynamicTest("LD E, B", () -> performInstructionTest(0x58, 4, LD_E_B, createRegistersTest(E, (byte) 2))),
			dynamicTest("LD E, C", () -> performInstructionTest(0x59, 4, LD_E_C, createRegistersTest(E, (byte) 3))),
			dynamicTest("LD E, D", () -> performInstructionTest(0x5A, 4, LD_E_D, createRegistersTest(E, (byte) 4))),
			dynamicTest("LD E, E", () -> performInstructionTest(0x5B, 4, LD_E_E, createRegistersTest())),
			dynamicTest("LD E, H", () -> performInstructionTest(0x5C, 4, LD_E_H, createRegistersTest(E, (byte) 6))),
			dynamicTest("LD E, L", () -> performInstructionTest(0x5D, 4, LD_E_L, createRegistersTest(E, (byte) 7))),
			dynamicTest("LD E, HL", () -> performInstructionTest(0x5E, 8, LD_E_HL, context -> {
				createRegistersTest(E, (byte) 0).accept(context);
				verify(context, times(1)).readByte(1543);
			}))
		);
	}

	/**
	 * 
	 * @return
	 */
	@TestFactory
	public Collection<DynamicTest> testLoadToA() {
		return asList(
			// TODO : A, A -> NOP.
			dynamicTest("LD A, B", () -> performInstructionTest(0x78, 4, LD_A_B, createRegistersTest(A, (byte) 0x2))),
			dynamicTest("LD A, C", () -> performInstructionTest(0x79, 4, LD_A_C, createRegistersTest(A, (byte) 0x3))),
			dynamicTest("LD A, D", () -> performInstructionTest(0x7A, 4, LD_A_D, createRegistersTest(A, (byte) 0x4))),
			dynamicTest("LD A, E", () -> performInstructionTest(0x7B, 4, LD_A_E, createRegistersTest(A, (byte) 0x5))),
			dynamicTest("LD A, H", () -> performInstructionTest(0x7C, 4, LD_A_H, createRegistersTest(A, (byte) 0x6))),
			// TODO : (BC), (DE), (HL)
			dynamicTest("LD A, NN", () -> performInstructionTest(0xFA, 16, LD_A_NN, context -> {
					createRegistersTest(A, (byte) 0).accept(context);
					verify(context, times(1)).nextShort();
					verify(context, times(1)).readByte(10794);
			})),
			dynamicTest("LD A, #", () -> performInstructionTest(0x3E, 8, LD_A_N, createRegistersTest(A, (byte) 42)))
		);
	}

	/**
	 * 
	 * @return
	 */
	@TestFactory
	public Collection<DynamicTest> testLoadFromA() {
		return asList(
			dynamicTest("LD B, A", () -> performInstructionTest(0x47, 4, LD_B_A, createRegistersTest(B, (byte) 0x1))),
			dynamicTest("LD C, A", () -> performInstructionTest(0x4F, 4, LD_C_A, createRegistersTest(C, (byte) 0x1))),
			dynamicTest("LD D, A", () -> performInstructionTest(0x57, 4, LD_D_A, createRegistersTest(D, (byte) 0x1))),
			dynamicTest("LD E, A", () -> performInstructionTest(0x5F, 4, LD_E_A, createRegistersTest(E, (byte) 0x1))),
			dynamicTest("LD H, A", () -> performInstructionTest(0x67, 4, LD_H_A, createRegistersTest(H, (byte) 0x1))),
			dynamicTest("LD L, A", () -> performInstructionTest(0x6F, 4, LD_L_A, createRegistersTest(L, (byte) 0x1)))
			// TODO : (BC), (DE), (HL), (nn)
		);
	}

}
