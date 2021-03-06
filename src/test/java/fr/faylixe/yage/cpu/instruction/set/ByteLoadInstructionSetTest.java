package fr.faylixe.yage.cpu.instruction.set;

import static fr.faylixe.yage.cpu.instruction.set.ByteLoadInstructionSet.*;
import static fr.faylixe.yage.cpu.register.IRegisterProvider.Register.*;
import static fr.faylixe.yage.cpu.register.IRegisterProviderTest.createRegistersTest;
import static fr.faylixe.yage.cpu.instruction.IInstructionSetTest.createMemoryReadTest;
import static fr.faylixe.yage.cpu.instruction.IInstructionSetTest.createMemoryWriteTest;

import static java.util.Arrays.asList;

import static org.junit.jupiter.api.DynamicTest.dynamicTest;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Collection;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import fr.faylixe.yage.cpu.instruction.IInstructionSetTest;

/**
 * Units / integration testing for ByteLoad instruction set.
 * 
 * @author fv
 */
public final class ByteLoadInstructionSetTest implements IInstructionSetTest {

	/**
	 * Test factory for byte load instruction that
	 * load next immediate value into a register.
	 * 
	 * @return Collection of next immediate value load test.
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
	 * Test factory for byte load instruction for B register.
	 * 
	 * @return Collection of B load register tests.
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
			dynamicTest("LD B, (HL)", () -> performInstructionTest(0x46, 8, LD_B_HL, createMemoryReadTest(1543, (byte) 0, B)))
		);
	}

	/**
	 * Test factory for byte load instruction for C register.
	 * 
	 * @return Collection of C load register tests.
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
			dynamicTest("LD C, (HL)", () -> performInstructionTest(0x4E, 8, LD_C_HL, createMemoryReadTest(1543, (byte) 0, C)))
		);
	}
	
	/**
	 * Test factory for byte load instruction for D register.
	 * 
	 * @return Collection of D load register tests.
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
			dynamicTest("LD D, (HL)", () -> performInstructionTest(0x56, 8, LD_D_HL, createMemoryReadTest(1543, (byte) 0, D)))
		);
	}
	
	/**
	 * Test factory for byte load instruction for E register.
	 * 
	 * @return Collection of E load register tests.
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
			dynamicTest("LD E, (HL)", () -> performInstructionTest(0x5E, 8, LD_E_HL, createMemoryReadTest(1543, (byte) 0, E)))
		);
	}

	/**
	 * Tests factory for byte load instruction for H register.
	 * 
	 * @return Collection of H load register tests.
	 */
	@TestFactory
	public Collection<DynamicTest> testLoadToH() {
		return asList(
			dynamicTest("LD H, B", () -> performInstructionTest(0x60, 4, LD_H_B, createRegistersTest(H, (byte) 2))),
			dynamicTest("LD H, C", () -> performInstructionTest(0x61, 4, LD_H_C, createRegistersTest(H, (byte) 3))),
			dynamicTest("LD H, D", () -> performInstructionTest(0x62, 4, LD_H_D, createRegistersTest(H, (byte) 4))),
			dynamicTest("LD H, E", () -> performInstructionTest(0x63, 4, LD_H_E, createRegistersTest(H, (byte) 5))),
			dynamicTest("LD H, H", () -> performInstructionTest(0x64, 4, LD_H_H, createRegistersTest())),
			dynamicTest("LD H, L", () -> performInstructionTest(0x65, 4, LD_H_L, createRegistersTest(H, (byte) 7))),
			dynamicTest("LD H, (HL)", () -> performInstructionTest(0x66, 8, LD_H_HL, createMemoryReadTest(1543, (byte) 0, H)))
		);
	}

	/**
	 * Test factory for byte load instruction for L register.
	 * 
	 * @return Collection of L load register tests.
	 */
	@TestFactory
	public Collection<DynamicTest> testLoadToL() {
		return asList(
			dynamicTest("LD L, B", () -> performInstructionTest(0x68, 4, LD_L_B, createRegistersTest(L, (byte) 2))),
			dynamicTest("LD L, C", () -> performInstructionTest(0x69, 4, LD_L_C, createRegistersTest(L, (byte) 3))),
			dynamicTest("LD L, D", () -> performInstructionTest(0x6A, 4, LD_L_D, createRegistersTest(L, (byte) 4))),
			dynamicTest("LD L, E", () -> performInstructionTest(0x6B, 4, LD_L_E, createRegistersTest(L, (byte) 5))),
			dynamicTest("LD L, H", () -> performInstructionTest(0x6C, 4, LD_L_H, createRegistersTest(L, (byte) 6))),
			dynamicTest("LD L, L", () -> performInstructionTest(0x6D, 4, LD_L_L, createRegistersTest())),
			dynamicTest("LD L, (HL)", () -> performInstructionTest(0x6E, 8, LD_L_HL, createMemoryReadTest(1543, (byte) 0, L)))
		);
	}

	/**
	 * Test factory for byte load instruction for (HL) register.
	 * 
	 * @return Collection of (HL) load tests.
	 */
	@TestFactory
	public Collection<DynamicTest> testLoadToHL() {
		return asList(
			dynamicTest("LD (HL), B", () -> performInstructionTest(0x70, 8, LD_HL_B, createMemoryWriteTest(1543, (byte) 2))),
			dynamicTest("LD (HL), C", () -> performInstructionTest(0x71, 8, LD_HL_C, createMemoryWriteTest(1543, (byte) 3))),
			dynamicTest("LD (HL), D", () -> performInstructionTest(0x72, 8, LD_HL_D, createMemoryWriteTest(1543, (byte) 4))),
			dynamicTest("LD (HL), E", () -> performInstructionTest(0x73, 8, LD_HL_E, createMemoryWriteTest(1543, (byte) 5))),
			dynamicTest("LD (HL), H", () -> performInstructionTest(0x74, 8, LD_HL_H, createMemoryWriteTest(1543, (byte) 6))),
			dynamicTest("LD (HL), L", () -> performInstructionTest(0x75, 8, LD_HL_L, createMemoryWriteTest(1543, (byte) 7))),
			dynamicTest("LD (HL), n", () -> performInstructionTest(0x36, 12, LD_HL_N, createMemoryWriteTest(1543, (byte) 42)))
		);
	}

	/**
	 * Test factory for byte load instruction to A register.
	 * 
	 * @return Collection of A load tests.
	 */
	@TestFactory
	public Collection<DynamicTest> testLoadToA() {
		return asList(
			dynamicTest("LD A, A", () -> performInstructionTest(0x7F, 4, LD_A_A, createRegistersTest())),
			dynamicTest("LD A, B", () -> performInstructionTest(0x78, 4, LD_A_B, createRegistersTest(A, (byte) 0x2))),
			dynamicTest("LD A, C", () -> performInstructionTest(0x79, 4, LD_A_C, createRegistersTest(A, (byte) 0x3))),
			dynamicTest("LD A, D", () -> performInstructionTest(0x7A, 4, LD_A_D, createRegistersTest(A, (byte) 0x4))),
			dynamicTest("LD A, E", () -> performInstructionTest(0x7B, 4, LD_A_E, createRegistersTest(A, (byte) 0x5))),
			dynamicTest("LD A, H", () -> performInstructionTest(0x7C, 4, LD_A_H, createRegistersTest(A, (byte) 0x6))),
			dynamicTest("LD A, L", () -> performInstructionTest(0x7D, 4, LD_A_L, createRegistersTest(A, (byte) 0x7))),
			dynamicTest("LD A, (BC)", () -> performInstructionTest(0x0A, 8, LD_A_BC, createMemoryReadTest(515, (byte) 0,A))),
			dynamicTest("LD A, (DE)", () -> performInstructionTest(0x1A, 8, LD_A_DE, createMemoryReadTest(1029, (byte) 0,A))),
			dynamicTest("LD A, (HL)", () -> performInstructionTest(0x7E, 8, LD_A_HL, createMemoryReadTest(1543, (byte) 0, A))),
			dynamicTest("LD A, (nn)", () -> performInstructionTest(0xFA, 16, LD_A_NN, context -> {
					createMemoryReadTest(10794, (byte) 0, A);
					verify(context, times(1)).nextShort();
			})),
			dynamicTest("LD A, n", () -> performInstructionTest(0x3E, 8, LD_A_N, createRegistersTest(A, (byte) 42)))
		);
	}

	/**
	 * Test factory for byte load instruction from A register.
	 * 
	 * @return Collection of A based load tests.
	 */
	@TestFactory
	public Collection<DynamicTest> testLoadFromA() {
		return asList(
			dynamicTest("LD B, A", () -> performInstructionTest(0x47, 4, LD_B_A, createRegistersTest(B, (byte) 0x1))),
			dynamicTest("LD C, A", () -> performInstructionTest(0x4F, 4, LD_C_A, createRegistersTest(C, (byte) 0x1))),
			dynamicTest("LD D, A", () -> performInstructionTest(0x57, 4, LD_D_A, createRegistersTest(D, (byte) 0x1))),
			dynamicTest("LD E, A", () -> performInstructionTest(0x5F, 4, LD_E_A, createRegistersTest(E, (byte) 0x1))),
			dynamicTest("LD H, A", () -> performInstructionTest(0x67, 4, LD_H_A, createRegistersTest(H, (byte) 0x1))),
			dynamicTest("LD L, A", () -> performInstructionTest(0x6F, 4, LD_L_A, createRegistersTest(L, (byte) 0x1))),
			dynamicTest("LD (BC), A", () -> performInstructionTest(0x02, 8, LD_BC_A, createMemoryWriteTest(515, (byte) 0x1))),
			dynamicTest("LD (DE), A", () -> performInstructionTest(0x12, 8, LD_DE_A, createMemoryWriteTest(1029, (byte) 0x1))),
			dynamicTest("LD (HL), A", () -> performInstructionTest(0x77, 8, LD_HL_A, createMemoryWriteTest(1543, (byte) 0x1))),
			dynamicTest("LD (nn), A", () -> performInstructionTest(0xEA, 16, LD_NN_A, createMemoryWriteTest(10794, (byte) 0x1)))
		);
	}

	/**
	 * Test factory for byte load instruction based on $FF00 interrupt
	 * memory address range.
	 * 
	 * @return Collection of INTERRUPT based I/O tests.
	 */
	@TestFactory
	public Collection<DynamicTest> testInterruptLoads() {
		return asList(
			dynamicTest("LD A, (C)", () -> performInstructionTest(0xF2, 8, LD_A_INTERRUPT, createMemoryReadTest(0xFF03, (byte) 0, A))),
			dynamicTest("LD (C), A", () -> performInstructionTest(0xE2, 8, LD_INTERRUPT_A, createMemoryWriteTest(0xFF03, (byte) 1)))
		);
	}

	// TODO : Implements LDD / LDI after validating INC / DEC ALU operation.
	
	// TODO : Implements LDH
	
}
