package fr.faylixe.jgb.cpu.instruction.set;

import static fr.faylixe.jgb.cpu.register.IRegister.Registers.*;

import fr.faylixe.jgb.cpu.instruction.IExecutableInstruction;
import fr.faylixe.jgb.cpu.instruction.IExecutionContext;
import fr.faylixe.jgb.cpu.instruction.IInstruction;

/**
 * Instruction set which contains 8-bit load operations.
 * 
 * @see GBCPUman, page 65 - 75
 * @author fv
 */
public enum ByteLoadInstructionSet implements IInstruction {

	/** **/
	LOAD_B_TO_N(0x06, 8, context -> {}),
	LOAD_C_TO_N(0x0E, 8, context -> {}),
	LOAD_D_TO_N(0x16, 8, context -> {}),
	LOAD_E_TO_N(0x1E, 8, context -> {}),
	LOAD_H_TO_N(0x26, 8, context -> {}),
	LOAD_L_TO_N(0x2E, 8, context -> {}),

	// TODO : LD r1, r2

	/** **/
	LOAD_A_TO_A(0x7F, 4, context -> {}),
	LOAD_B_TO_A(0x78, 4, context -> context.copyRegister(B, A)),
	LOAD_C_TO_A(0x79, 4, context -> context.copyRegister(C, A)),
	LOAD_D_TO_A(0x7A, 4, context -> context.copyRegister(D, A)),
	LOAD_E_TO_A(0x7B, 4, context -> context.copyRegister(E, A)),
	LOAD_H_TO_A(0x7C, 4, context -> context.copyRegister(H, A)),
	LOAD_L_TO_A(0x7D, 4, context -> context.copyRegister(L, A)),
	LOAD_BC_TO_A(0x0A, 8, context -> context.setRegister(A, B, C)),
	LOAD_DE_TO_A(0x1A, 8, context -> context.setRegister(A, D, E)),
	LOAD_HL_TO_A(0x7E, 8, context -> context.setRegister(A, H, L)),
	LOAD_NN_TO_A(0xFA, 16, context -> context.getRegister(A).set(context.readFromImmediates())),
	LOAD_N_TO_A(0x3E, 8, context -> context.getRegister(A).set(context.nextImmediate())),

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
