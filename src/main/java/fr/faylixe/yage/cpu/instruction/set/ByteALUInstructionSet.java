package fr.faylixe.yage.cpu.instruction.set;

import static fr.faylixe.yage.cpu.register.IRegisterProvider.Register.*;
import static fr.faylixe.yage.cpu.register.IRegisterProvider.ExtendedRegister.*;
import static fr.faylixe.yage.cpu.instruction.IExecutableInstruction.add;
import static fr.faylixe.yage.cpu.instruction.IExecutableInstruction.adc;
import static fr.faylixe.yage.cpu.instruction.IExecutableInstruction.and;
import static fr.faylixe.yage.cpu.instruction.IExecutableInstruction.increment;
import static fr.faylixe.yage.cpu.instruction.IExecutableInstruction.or;
import static fr.faylixe.yage.cpu.instruction.IExecutableInstruction.sub;
import static fr.faylixe.yage.cpu.instruction.IExecutableInstruction.xor;
import static fr.faylixe.yage.cpu.IDataSource.IMMEDIATE;

import fr.faylixe.yage.cpu.instruction.IExecutableInstruction;
import fr.faylixe.yage.cpu.instruction.IExecutionContext;
import fr.faylixe.yage.cpu.instruction.IInstruction;

/**
 * 
 * @author fv
 */
public enum ByteALUInstructionSet implements IInstruction {

	/**
	 * ADD A, n
	 * 
	 * Add value from <tt>n</tt> into A register. Where <tt>n</tt>
	 * could either be A, B, C, D, E, H, L register, or value located
	 * at memory address (HL), or next byte immediate value.
	 * 
	 * Flags affected :
	 * 
	 * Z - Set if result is zero.
	 * N - Reset
	 * H - Set if carry from bit 3
	 * C - Set if carry from bit 7
	 *  
	 * @see GBCPUMan page 80
	 */

	ADD_A_A(0x87, 4, add(A)),
	ADD_A_B(0x80, 4, add(B)),
	ADD_A_C(0x81, 4, add(C)),
	ADD_A_D(0x82, 4, add(D)),
	ADD_A_E(0x83, 4, add(E)),
	ADD_A_H(0x84, 4, add(H)),
	ADD_A_L(0x85, 4, add(L)),
	ADD_A_HL(0x86, 8, add(HL)),
	ADD_A_N(0xC6, 8, add(IMMEDIATE)),

	/**
	 * ADC A, n
	 * 
	 * Add value from <tt>n</tt> + carry flag into A register and .
	 * Where <tt>n</tt> could either be A, B, C, D, E, H, L register,
	 * or value located at memory address (HL), or next byte immediate value.
	 * 
	 * Flags affected :
	 * 
	 * Z - Set if result is zero.
	 * N - Reset
	 * H - Set if carry from bit 3
	 * C - Set if carry from bit 7
	 * 
	 * @see GBCPUMan page 81
	 */

	ADC_A_A(0x8F, 4, adc(A)),
	ADC_A_B(0x88, 4, adc(B)),
	ADC_A_C(0x89, 4, adc(C)),
	ADC_A_D(0x8A, 4, adc(D)),
	ADC_A_E(0x8B, 4, adc(E)),
	ADC_A_H(0x8C, 4, adc(H)),
	ADC_A_L(0x8D, 4, adc(L)),
	ADC_A_HL(0x8E, 8, adc(HL)),
	ADC_A_N(0xCE, 8, adc(IMMEDIATE)),

	/**
	 * SUB A, n
	 * 
	 * Subtract value from <tt>n</tt> into A register. Where <tt>n</tt>
	 * could either be A, B, C, D, E, H, L register, or value located
	 * at memory address (HL), or next byte immediate value.
	 * 
	 * Flags affected :
	 * 
	 * Z - Set if result is zero.
	 * N - Set.
	 * H - Set if no borrow from bit 4.
	 * C - Set if no borrow.
	 *  
	 * @see GBCPUMan page 82
	 */

	SUB_A_A(0x97, 4, sub(A)),
	SUB_A_B(0x90, 4, sub(B)),
	SUB_A_C(0x91, 4, sub(C)),
	SUB_A_D(0x92, 4, sub(D)),
	SUB_A_E(0x93, 4, sub(E)),
	SUB_A_H(0x94, 4, sub(H)),
	SUB_A_L(0x95, 4, sub(L)),
	SUB_A_HL(0x96, 8, sub(HL)),
	SUB_A_N(0xD6, 8, sub(IMMEDIATE)),

	/**
	 * AND A, n
	 * 
	 * Performs a logical AND between <tt>n</tt> and A register.
	 * Where <tt>n</tt> could either be A, B, C, D, E, H, L register,
	 * or value located at memory address (HL), or next byte immediate value.
	 * 
	 * Flags affected :
	 * 
	 * Z - Set if result is zero.
	 * N - Reset
	 * H - Set
	 * C - Reset
	 * 
	 * @see GBCPUMan page 84
	 */

	AND_A_A(0xA7, 4, and(A)),
	AND_A_B(0xA0, 4, and(B)),
	AND_A_C(0xA1, 4, and(C)),
	AND_A_D(0xA2, 4, and(D)),
	AND_A_E(0xA3, 4, and(E)),
	AND_A_H(0xA4, 4, and(H)),
	AND_A_L(0xA5, 4, and(L)),
	AND_A_HL(0xA6, 8, and(HL)),
	AND_A_N(0xE6, 8, and(IMMEDIATE)),

	/**
	 * OR A, n
	 * 
	 * Performs a logical OR between <tt>n</tt> and A register.
	 * Where <tt>n</tt> could either be A, B, C, D, E, H, L register,
	 * or value located at memory address (HL), or next byte immediate value.
	 * 
	 * Flags affected :
	 * 
	 * Z - Set if result is zero.
	 * N - Reset
	 * H - Reset
	 * C - Reset
	 * 
	 * @see GBCPUMan page 85
	 */

	OR_A_A(0xB7, 4, or(A)),
	OR_A_B(0xB0, 4, or(B)),
	OR_A_C(0xB1, 4, or(C)),
	OR_A_D(0xB2, 4, or(D)),
	OR_A_E(0xB3, 4, or(E)),
	OR_A_H(0xB4, 4, or(H)),
	OR_A_L(0xB5, 4, or(L)),
	OR_A_HL(0xB6, 8, or(HL)),
	OR_A_N(0xF6, 8, or(IMMEDIATE)),

	/**
	 * XOR A, n
	 * 
	 * Performs a logical XOR between <tt>n</tt> and A register.
	 * Where <tt>n</tt> could either be A, B, C, D, E, H, L register,
	 * or value located at memory address (HL), or next byte immediate value.
	 * 
	 * Flags affected :
	 * 
	 * Z - Set if result is zero.
	 * N - Reset
	 * H - Reset
	 * C - Reset
	 * 
	 * @see GBCPUMan page 86
	 */

	XOR_A_A(0xAF, 4, xor(A)),
	XOR_A_B(0xA8, 4, xor(B)),
	XOR_A_C(0xA9, 4, xor(C)),
	XOR_A_D(0xAA, 4, xor(D)),
	XOR_A_E(0xAB, 4, xor(E)),
	XOR_A_H(0xAC, 4, xor(H)),
	XOR_A_L(0xAD, 4, xor(L)),
	XOR_A_HL(0xAE, 8, xor(HL)),
	XOR_A_N(0xEE, 8, xor(IMMEDIATE)),

	/**
	 * INC n
	 * 
	 * Increments <tt>n</tt> here <tt>n</tt> could either
	 * be A, B, C, D, E, H, L register, or value located
	 * at memory address (HL).
	 * 
	 * Flags affected :
	 * 
	 * Z - Set if result is zero.
	 * N - Reset
	 * H - Set if carry from bit 3
	 * C - Not affected
	 * 
	 * @see GBCPUMan page 88
	 */

	INC_A(0x3C, 4, increment(A)),
	INC_B(0x04, 4, increment(B)),
	INC_C(0x0C, 4, increment(C)),
	INC_D(0x14, 4, increment(D)),
	INC_E(0x1C, 4, increment(E)),
	INC_H(0x24, 4, increment(H)),
	INC_L(0x2C, 4, increment(L)),
	INC_HL(0x34, 12, increment(HL)),

	/**
	 * 
	 */
	DEC_HL(0x35, 12, context -> {
		final int address = context.getExtendedRegister(HL).get();
		final byte value = context.readByte(address);
		// TODO : Ensure operation.
		context.writeByte((byte) (value - (byte) 1), address);
	}),

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
	private ByteALUInstructionSet(
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
