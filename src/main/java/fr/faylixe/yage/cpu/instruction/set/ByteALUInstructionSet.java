package fr.faylixe.yage.cpu.instruction.set;

import static fr.faylixe.yage.cpu.register.IRegisterProvider.ExtendedRegister.*;

import fr.faylixe.yage.cpu.instruction.IExecutableInstruction;
import fr.faylixe.yage.cpu.instruction.IExecutionContext;
import fr.faylixe.yage.cpu.instruction.IInstruction;

/**
 * 
 * @author fv
 */
public enum ByteALUInstructionSet implements IInstruction {

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
