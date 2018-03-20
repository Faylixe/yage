package fr.faylixe.jgb.cpu.instruction.set;

import fr.faylixe.jgb.cpu.instruction.IExecutableInstruction;
import fr.faylixe.jgb.cpu.instruction.IExecutionContext;
import fr.faylixe.jgb.cpu.instruction.IInstruction;

/**
 * 
 * @author fv
 */
public enum ShortLoadInstructionSet implements IInstruction {

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
	private ShortLoadInstructionSet(
			final short opcode,
			final byte cycle,
			final IExecutableInstruction executable) {
		this.opcode = opcode;
		this.cycle = cycle;
		this.executable = executable;
	}

	/** {@inheritDoc} **/
	@Override
	public void execute(final IExecutionContext context) {
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
