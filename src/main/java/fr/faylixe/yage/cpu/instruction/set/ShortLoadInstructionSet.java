package fr.faylixe.yage.cpu.instruction.set;

import static java.lang.Short.toUnsignedInt;

import static fr.faylixe.yage.cpu.instruction.IExecutableInstruction.copy;
import static fr.faylixe.yage.cpu.instruction.IExecutableInstruction.copyFromAddress;
import static fr.faylixe.yage.cpu.instruction.IExecutableInstruction.copyToAddress;
import static fr.faylixe.yage.cpu.instruction.IExecutableInstruction.copyNextShortValue;
import static fr.faylixe.yage.cpu.register.IRegisterProvider.Register.*;
import static fr.faylixe.yage.cpu.register.IRegisterProvider.ExtendedRegister.*;
import static fr.faylixe.yage.cpu.instruction.set.ShortALUInstructionSet.DEC_SP;
import static fr.faylixe.yage.cpu.instruction.set.ShortALUInstructionSet.INC_SP;

import fr.faylixe.yage.cpu.instruction.IExecutableInstruction;
import fr.faylixe.yage.cpu.instruction.IExecutionContext;
import fr.faylixe.yage.cpu.instruction.IInstruction;

/**
 * Instruction set which contains 16-bit load operations.
 * 
 * TODO : Validate opcode format (0x00XX vs 0xXX)
 * 
 * @see GBCPUman, page 76 - 79
 * 
 * @author fv
 */
public enum ShortLoadInstructionSet implements IInstruction {

	/**
	 * LD n, nn
	 * 
	 * Put the next two immediate value into 16-bit register
	 * denoted by <tt>n</tt>. Where <tt>nn</tt> could be either
	 * BC, DE, HL, or SP.
	 * 
	 * @see GBCPUMan page 76
	 */
	LD_BC_NN(0x01, 12, copyNextShortValue(BC)),
	
	/** @see #LD_BC_NN **/
	LD_DE_NN(0x11, 12, copyNextShortValue(DE)),

	/** @see #LD_BC_NN **/
	LD_HL_NN(0x21, 12, copyNextShortValue(HL)),

	/** @see #LD_BC_NN **/
	LD_SP_NN(0x31, 12, copyNextShortValue(SP)),
	
	/**
	 * LD SP, HL
	 * 
	 * Put value from HL register into SP register.
	 * 
	 * @see GBCPUMan page 76
	 */
	LD_SP_HL(0xF9, 8, copy(HL, SP)),

	/**
	 * LDHL SP, n
	 * 
	 * Put SP + n into HL register. Where <tt>n</tt> is
	 * the next immediate as a signed byte.
	 * 
	 * Flags affected :
	 * 
	 * Z - Reset
	 * N - Reset
	 * H - Set or reset according to operation.
	 * C - Set or reset according to operation.
	 * 
	 * @see GBCPUMan page 77
	 */
	LDHL_SP(0xF8, 12, context -> {
		final short sp = context.getExtendedRegister(SP).get();
		// Note : Since n is interpreted as signed,
		// it could be casted safely into short container.
		final short n = (short) context.nextByte();
		final short result = context.add(sp, n);
		context.getExtendedRegister(HL).set(result);
	}),

	/**
	 * LD (nn), SP
	 * 
	 * Put value from register SP into memory at the
	 * address denoted by next two immediate value.
	 * 
	 * @see GBCPUMan page 78
	 */
	LD_NN_SP(0x08, 20, context -> {
		final int address = toUnsignedInt(context.nextShort());
		final byte[] values = context.getExtendedRegister(SP).getBytes();
		context.writeBytes(values, address);
	}),
	
	/**
	 * PUSH nn
	 * 
	 * Pushes the content of extended register
	 * into memory stack. And decrements SP by two.
	 * 
	 * @see GBCPUMan page 78
	 */
	PUSH_AF(0xF5, 16,
			DEC_SP
			.then(copyToAddress(A, SP))
			.then(DEC_SP)
			.then(copyToAddress(F, SP))),

	/** @see #PUSH_AF **/
	PUSH_BC(0xC5, 16,
			DEC_SP
			.then(copyToAddress(B, SP))
			.then(DEC_SP)
			.then(copyToAddress(C, SP))),

	/** @see #PUSH_AF **/
	PUSH_DE(0xD5, 16,
			DEC_SP
			.then(copyToAddress(D, SP))
			.then(DEC_SP)
			.then(copyToAddress(E, SP))),

	/** @see #PUSH_AF **/
	PUSH_HL(0xE5, 16,
			DEC_SP
			.then(copyToAddress(H, SP))
			.then(DEC_SP)
			.then(copyToAddress(L, SP))),

	/**
	 * POP nn
	 * 
	 * Pops the content from the memory stack
	 * into given extended register. Then
	 * increments SP by two.
	 * 
	 * @see GBCPUMan page 79
	 */
	POP_AF(0xF1, 12,
			copyFromAddress(SP, F)
			.then(INC_SP)
			.then(copyFromAddress(SP, A))
			.then(INC_SP)
			.then(IExecutionContext::normalizeFlagsRegister)),

	/** @see #POP_AF **/
	POP_BC(0xC1, 12,
			copyFromAddress(SP, C)
			.then(INC_SP)
			.then(copyFromAddress(SP, B))
			.then(INC_SP)),

	/** @see #POP_AF **/
	POP_DE(0xD1, 12,
			copyFromAddress(SP, E)
			.then(INC_SP)
			.then(copyFromAddress(SP, D))
			.then(INC_SP)),

	/** @see #POP_AF **/
	POP_HL(0xE1, 12,
			copyFromAddress(SP, L)
			.then(INC_SP)
			.then(copyFromAddress(SP, H))
			.then(INC_SP)),

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
