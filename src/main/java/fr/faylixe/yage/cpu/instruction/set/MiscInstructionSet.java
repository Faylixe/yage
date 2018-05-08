package fr.faylixe.yage.cpu.instruction.set;

import static fr.faylixe.yage.cpu.register.IRegisterProvider.Register.*;

import fr.faylixe.yage.cpu.instruction.IExecutableInstruction;
import fr.faylixe.yage.cpu.instruction.IExecutionContext;
import fr.faylixe.yage.cpu.instruction.IInstruction;
import fr.faylixe.yage.cpu.register.ByteRegister;
import fr.faylixe.yage.cpu.register.FlagsRegister;

/**
 * 
 * @author fv
 */
public enum MiscInstructionSet implements IInstruction {

	/**
	 * CPL
	 * 
	 * Flip all bits into A register.
	 * 
	 * @see GBCPUMan page 95
	 */
	CPL(0x2F, 4, context -> {
		final ByteRegister register = context.getRegister(A);
		final byte current = register.get();
		register.set((byte) ~current);
	}),

	/**
	 * CCF
	 * 
	 * Complements the carry flag.
	 * 
	 * TODO : Flags affected.
	 * 
	 * @see GBCPUMan page 96
	 */
	CCF(0x3F, 4, context -> {
		final FlagsRegister register = context.getFlagsRegister();
		register.resetSubtraction();
		register.resetHalfCarry();
		if (register.isCarry()) {
			register.resetCarry();			
		}
		else {
			register.setCarry();
		}
	}),

	/**
	 * SCF
	 * 
	 * Set the carry flag.
	 * 
	 * TODO : Flags affected.
	 * 
	 * @see GBCPUMan page 96
	 */
	SCF(0x37, 4, context -> {
		final FlagsRegister register = context.getFlagsRegister();
		register.resetSubtraction();
		register.resetHalfCarry();
		register.setCarry();
	}),

	/**
	 * NOP
	 * 
	 * Do nothing againt 4 CPU cycle.
	 * 
	 * @see GBCPUMan page 97
	 */
	NOP(0x00, 4, IExecutableInstruction.NOP)

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
	private MiscInstructionSet(
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
