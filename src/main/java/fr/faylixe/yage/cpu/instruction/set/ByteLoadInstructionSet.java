package fr.faylixe.yage.cpu.instruction.set;

import static fr.faylixe.yage.cpu.instruction.IExecutableInstruction.copy;
import static fr.faylixe.yage.cpu.instruction.IExecutableInstruction.copyFromAddress;
import static fr.faylixe.yage.cpu.instruction.IExecutableInstruction.copyToAddress;
import static fr.faylixe.yage.cpu.instruction.IExecutableInstruction.copyNextValue;
import static fr.faylixe.yage.cpu.register.IRegisterProvider.ExtendedRegister.*;
import static fr.faylixe.yage.cpu.register.IRegisterProvider.Register.*;
import static fr.faylixe.yage.cpu.instruction.set.ByteALUInstructionSet.DEC_HL;
import static fr.faylixe.yage.cpu.instruction.set.ByteALUInstructionSet.INC_HL;

import fr.faylixe.yage.cpu.instruction.IExecutableInstruction;
import fr.faylixe.yage.cpu.instruction.IExecutionContext;
import fr.faylixe.yage.cpu.instruction.IInstruction;

/**
 * Instruction set which contains 8-bit load operations.
 * 
 * TODO : Validate opcode format (0x00XX vs 0xXX)
 * 
 * @see GBCPUman, page 65 - 75
 * @author fv
 */
public enum ByteLoadInstructionSet implements IInstruction {

	/**
	 * LD nn, n
	 * 
	 * Put the next immediate value into 8-bit register
	 * denoted by <tt>nn</tt>. Where <tt>nn</tt> could be
	 * either B, C, D, E, H, L register.
	 * 
	 * @see GBCPUMan page 65
	 */
	LD_B_N(0x06, 8, copyNextValue(B)),

	/** @see #LD_B_N **/
	LD_C_N(0x0E, 8, copyNextValue(C)),

	/** @see #LD_B_N **/
	LD_D_N(0x16, 8, copyNextValue(D)),

	/** @see #LD_B_N **/
	LD_E_N(0x1E, 8, copyNextValue(E)),

	/** @see #LD_B_N **/
	LD_H_N(0x26, 8, copyNextValue(H)),

	/** @see #LD_B_N **/
	LD_L_N(0x2E, 8, copyNextValue(L)),

	/**
	 * LD r1, r2
	 * 
	 * Put value denoted by <tt>r2</tt> into place holder
	 * denoted by <tt>r1</tt>.
	 * 
	 * Placeholder could be either B, C, D, E, H, L register
	 * or memory location denoted by the address held by (HL)
	 * extended register.
	 * 
	 * Value could be loaded from either B, C, D, E, H, L register,
	 * or memory location denoted by the address held by (HL)
	 * extended register, or next immediate value.
	 * 
	 * Note : A register is not considered here as it is duplicated in
	 * dedicated accumulator load instructions later.
	 * 
	 * @see GBCPUMan page 66 - 67
	 */
	LD_B_B(0x40, 4, NOP),

	/** @see #LD_B_B **/
	LD_B_C(0x41, 4, copy(C, B)),

	/** @see #LD_B_B **/
	LD_B_D(0x42, 4, copy(D, B)),

	/** @see #LD_B_B **/
	LD_B_E(0x43, 4, copy(E, B)),

	/** @see #LD_B_B **/
	LD_B_H(0x44, 4, copy(H, B)),

	/** @see #LD_B_B **/
	LD_B_L(0x45, 4, copy(L, B)),

	/** @see #LD_B_B **/
	LD_B_HL(0x46, 8, copyFromAddress(HL, B)),

	/** @see #LD_B_B **/
	LD_C_B(0x48, 4, copy(B, C)),

	/** @see #LD_B_B **/
	LD_C_C(0x49, 4, NOP),

	/** @see #LD_B_B **/
	LD_C_D(0x4A, 4, copy(D, C)),

	/** @see #LD_B_B **/
	LD_C_E(0x4B, 4, copy(E, C)),

	/** @see #LD_B_B **/
	LD_C_H(0x4C, 4, copy(H, C)),

	/** @see #LD_B_B **/
	LD_C_L(0x4D, 4, copy(L, C)),

	/** @see #LD_B_B **/
	LD_C_HL(0x4E, 8, copyFromAddress(HL, C)),

	/** @see #LD_B_B **/
	LD_D_B(0x50, 4, copy(B, D)),

	/** @see #LD_B_B **/
	LD_D_C(0x51, 4, copy(C, D)),

	/** @see #LD_B_B **/
	LD_D_D(0x52, 4, NOP),

	/** @see #LD_B_B **/
	LD_D_E(0x53, 4, copy(E, D)),

	/** @see #LD_B_B **/
	LD_D_H(0x54, 4, copy(H, D)),

	/** @see #LD_B_B **/
	LD_D_L(0x55, 4, copy(L, D)),

	/** @see #LD_B_B **/
	LD_D_HL(0x56, 8, copyFromAddress(HL, D)),

	/** @see #LD_B_B **/
	LD_E_B(0x58, 4, copy(B, E)),

	/** @see #LD_B_B **/
	LD_E_C(0x59, 4, copy(C, E)),

	/** @see #LD_B_B **/
	LD_E_D(0x5A, 4, copy(D, E)),

	/** @see #LD_B_B **/
	LD_E_E(0x5B, 4, NOP),

	/** @see #LD_B_B **/
	LD_E_H(0x5C, 4, copy(H, E)),

	/** @see #LD_B_B **/
	LD_E_L(0x5D, 4, copy(L, E)),

	/** @see #LD_B_B **/
	LD_E_HL(0x5E, 8, copyFromAddress(HL, E)),

	/** @see #LD_B_B **/
	LD_H_B(0x60, 4, copy(B, H)),

	/** @see #LD_B_B **/
	LD_H_C(0x61, 4, copy(C, H)),

	/** @see #LD_B_B **/
	LD_H_D(0x62, 4, copy(D, H)),

	/** @see #LD_B_B **/
	LD_H_E(0x63, 4, copy(E, H)),

	/** @see #LD_B_B **/
	LD_H_H(0x64, 4, NOP),

	/** @see #LD_B_B **/
	LD_H_L(0x65, 4, copy(L, H)),

	/** @see #LD_B_B **/
	LD_H_HL(0x66, 8, copyFromAddress(HL, H)),

	/** @see #LD_B_B **/
	LD_L_B(0x68, 4, copy(B, L)),

	/** @see #LD_B_B **/
	LD_L_C(0x69, 4, copy(C, L)),

	/** @see #LD_B_B **/
	LD_L_D(0x6A, 4, copy(D, L)),

	/** @see #LD_B_B **/
	LD_L_E(0x6B, 4, copy(E, L)),

	/** @see #LD_B_B **/
	LD_L_H(0x6C, 4, copy(H, L)),

	/** @see #LD_B_B **/
	LD_L_L(0x6D, 4, copy(L, L)),

	/** @see #LD_B_B **/
	LD_L_HL(0x6E, 8, copyFromAddress(HL, L)),

	/** @see #LD_B_B **/
	LD_HL_B(0x70, 8, copyToAddress(B, HL)),

	/** @see #LD_B_B **/
	LD_HL_C(0x71, 8, copyToAddress(C, HL)),

	/** @see #LD_B_B **/
	LD_HL_D(0x72, 8, copyToAddress(D, HL)),

	/** @see #LD_B_B **/
	LD_HL_E(0x73, 8, copyToAddress(E, HL)),

	/** @see #LD_B_B **/
	LD_HL_H(0x74, 8, copyToAddress(H, HL)),

	/** @see #LD_B_B **/
	LD_HL_L(0x75, 8, copyToAddress(L, HL)),

	/** @see #LD_B_B **/
	LD_HL_N(0x36, 12, copyNextValue(HL)),
	
	/**
	 * LD A, n
	 * 
	 * Put the value denoted by <tt>n</tt> into A register.
	 * 
	 * Value could be loaded from either A, B, C, D, E, H, L
	 * register, or memory location denoted by the address
	 * held by (BC), (DE), (HL) extended registers, or next
	 * immediate value.
	 * 
	 * @see GBCPUMan page 68
	 */
	LD_A_A(0x7F, 4, NOP),

	/** @see #LD_A_A **/
	LD_A_B(0x78, 4, copy(B, A)),

	/** @see #LD_A_A **/
	LD_A_C(0x79, 4, copy(C, A)),

	/** @see #LD_A_A **/
	LD_A_D(0x7A, 4, copy(D, A)),

	/** @see #LD_A_A **/
	LD_A_E(0x7B, 4, copy(E, A)),

	/** @see #LD_A_A **/
	LD_A_H(0x7C, 4, copy(H, A)),

	/** @see #LD_A_A **/
	LD_A_L(0x7D, 4, copy(L, A)),

	/** @see #LD_A_A **/
	LD_A_BC(0x0A, 8, copyFromAddress(BC, A)),

	/** @see #LD_A_A **/
	LD_A_DE(0x1A, 8, copyFromAddress(DE, A)),

	/** @see #LD_A_A **/
	LD_A_HL(0x7E, 8, copyFromAddress(HL, A)),

	/** @see #LD_A_A **/
	LD_A_NN(0xFA, 16, copyFromAddress(A)),

	/** @see #LD_A_A **/
	LD_A_N(0x3E, 8, copyNextValue(A)),

	/**
	 * LD n, A
	 * 
	 * Put the value denoted by <tt>n</tt> into A register.
	 * 
	 * Value could be loaded from either A, B, C, D, E, H, L
	 * register, or memory location denoted by the address
	 * held by (BC), (DE), (HL) extended registers, or next
	 * immediate value.
	 * 
	 * @see GBCPUMan page 69
	 */
	LD_B_A(0x47, 4, copy(A, B)),

	/** @see #LD_B_A **/
	LD_C_A(0x4F, 4, copy(A, C)),

	/** @see #LD_B_A **/
	LD_D_A(0x57, 4, copy(A, D)),

	/** @see #LD_B_A **/
	LD_E_A(0x5F, 4, copy(A, E)),

	/** @see #LD_B_A **/
	LD_H_A(0x67, 4, copy(A, H)),

	/** @see #LD_B_A **/
	LD_L_A(0x6F, 4, copy(A, L)),

	/** @see #LD_B_A **/
	LD_BC_A(0x02, 8, copyToAddress(A, BC)),

	/** @see #LD_B_A **/
	LD_DE_A(0x12, 8, copyToAddress(A, DE)),

	/** @see #LD_B_A **/
	LD_HL_A(0x77, 8, copyToAddress(A, HL)),

	/** @see #LD_B_A **/
	LD_NN_A(0xEA, 16, copyToAddress(A)),

	/**
	 * LD A, (C)
	 * LD A, ($FF00 + C)
	 * 
	 * Put value from address ($FF00 + C) into A register.
	 * 
	 * @see GBCPUMan page 70
	 */
	LD_A_INTERRUPT(0xF2, 8, copyFromAddress(C, 0xFF00, A)),
	
	/**
	 * LD (C), A
	 * LD ($FF00 + C), A
	 * 
	 * Put value from from A register to address ($FF00 + C).
	 * 
	 * @see GBCPUMan page 70
	 */
	LD_INTERRUPT_A(0xE2, 8, copyToAddress(A, C, 0xFF00)),

	/**
	 * LD A, (HLD)
	 * LD A, (HL-)
	 * LDD A, (HL)
	 * 
	 * Put value located at address (HL) into A register.
	 * Then decrement value located at (HL).
	 * 
	 * @see GBCPUMan page 71
	 */
	LDD_A_HL(0x3A, 8, LD_A_HL.then(DEC_HL)),
	
	/** 
	 * LD (HLD) A
	 * LD (HL-), A
	 * LDD (HL), A

	 * Put value from A register into memory at address (HL).
	 * Then decrement value located at (HL).
	 * 
	 * @see GBCPUMan page 72
	 */
	LDD_HL_A(0X32, 8, LD_HL_A.then(DEC_HL)),

	/**
	 * LD A, (HLI)
	 * LD A, (HL+)
	 * LDI A, (HL)
	 * 
	 * Put value located at address (HL) into A register.
	 * Then increment value located at (HL).
	 * 
	 * @see GBCPUMan page 73
	 */
	LDI_A_HL(0x2A, 8, LD_A_HL.then(INC_HL)),

	/**
	 * LD (HLI), A
	 * LD (HL+), A
	 * LDI (HL), A
	 * 
	 * Put value from A register into memory at address (HL).
	 * Then increment value located at (HL).
	 * 
	 * @see GBCPUMan page 74
	 */
	LDI_HL_A(0x22, 8, LD_HL_A.then(INC_HL)),

	/**
	 * LDH (n), A
	 * 
	 * Put value from A register into memory at address
	 * denoted by ($FF00 + n) where <tt>n</tt> is the next
	 * immediate value.
	 * 
	 * /!\ Ensure address computation with type inference.
	 * 
	 * @see GBCPUMan page 75
	 */
	LDH_N_A(0xE0, 12, context -> {
		// TODO : Check for casting (signed -> unsigned).
		final int address = 0xFF00 + context.nextByte();
		final byte value = context.getRegister(A).get();
		context.writeByte(value, address);
	}),
	
	/**
	 * LDH A, (n)
	 * 
	 * Put value memory address denoted by ($FF00 + n) where
	 * <tt>n</tt> is the next immediate value, into A register.
	 * 
	 * /!\ Ensure address computation with type inference.
	 * 
	 * @see GBCPUMan page 75
	 */
	LDH_A_N(0xF0, 12, context -> {
		// TODO : Check for casting (signed -> unsigned).
		final int address = 0xFF00 + context.nextByte();
		final byte value = context.readByte(address);
		context.getRegister(A).set(value);
	})

	;

	/** Associated opcode. **/
	private final short opcode;

	/** Machine cycle this instruction consume. **/
	private final byte cycle;

	/** Delegate executable instruction. **/
	private final IExecutableInstruction executable;
	
	/**
	 * Default constructor.
	 * 
	 * @param opcode Associated opcode.
	 * @param cycle Machine cycle this instruction consume.
	 * @param executable Delegate executable instruction.
	 */
	private ByteLoadInstructionSet(
			final int opcode,
			final int cycle,
			final IExecutableInstruction executable) {
		this.opcode = (short) opcode;
		this.cycle = (byte) cycle;
		this.executable = executable;
	}

	/** {@inheritDoc} **/
	@Override
	public void execute(final IExecutionContext context) throws IllegalAccessException {
		executable.execute(context);
	}

	/** {@inheritDoc} **/
	@Override
	public short getOpcode() {
		return opcode;
	}

	/** {@inheritDoc} **/
	@Override
	public byte getCycle() {
		return cycle;
	}

}
