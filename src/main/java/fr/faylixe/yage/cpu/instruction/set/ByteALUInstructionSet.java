package fr.faylixe.yage.cpu.instruction.set;

import static fr.faylixe.yage.cpu.register.IRegisterProvider.Register.*;
import static fr.faylixe.yage.cpu.register.IRegisterProvider.ExtendedRegister.*;
import static fr.faylixe.yage.cpu.instruction.IExecutableInstruction.add;

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

	ADD_A_A(0x87, 4, add(A, A)),
	ADD_A_B(0x80, 4, add(B, A)),
	ADD_A_C(0x81, 4, add(C, A)),
	ADD_A_D(0x82, 4, add(D, A)),
	ADD_A_E(0x83, 4, add(E, A)),
	ADD_A_H(0x84, 4, add(H, A)),
	ADD_A_L(0x85, 4, add(L, A)),
	ADD_A_HL(0x86, 8, add(A, A)),
	ADD_A_N(0xC6, 8, add(A, A)),

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

	ADC_A_A(0x8F, 4, null),
	ADC_A_B(0x88, 4, null),
	ADC_A_C(0x89, 4, null),
	ADC_A_D(0x8A, 4, null),
	ADC_A_E(0x8B, 4, null),
	ADC_A_H(0x8C, 4, null),
	ADC_A_L(0x8D, 4, null),
	ADC_A_HL(0x8E, 8, null),
	ADC_A_N(0xCE, 8, null),

	/**
	 * 
	 */

	INC_HL(0x34, 12, context -> {
		final int address = context.getExtendedRegister(HL).get();
		final byte value = context.readByte(address);
		context.writeByte((byte) (value + (byte) 1), address);
	}),

	/**
	 * 
	 */
	DEC_HL(0x35, 12, context -> {
		final int address = context.getExtendedRegister(HL).get();
		final byte value = context.readByte(address);
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
