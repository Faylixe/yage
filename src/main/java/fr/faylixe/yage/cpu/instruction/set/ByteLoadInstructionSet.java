package fr.faylixe.yage.cpu.instruction.set;

import static fr.faylixe.yage.cpu.instruction.IExecutionContext.load;
import static fr.faylixe.yage.cpu.register.IRegisterProvider.ExtendedRegister.*;
import static fr.faylixe.yage.cpu.register.IRegisterProvider.Register.*;

import fr.faylixe.yage.cpu.instruction.IExecutableInstruction;
import fr.faylixe.yage.cpu.instruction.IExecutionContext;
import fr.faylixe.yage.cpu.instruction.IInstruction;

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

	/** LD A, A **/
	LOAD_A_TO_A(0x7F, 4, NOP),

	/** LD B, A **/
	LOAD_B_TO_A(0x78, 4, load(B, A)),

	/** LD C, A **/
	LOAD_C_TO_A(0x79, 4, load(C, A)),

	/** LD D, A **/
	LOAD_D_TO_A(0x7A, 4, load(D, A)),

	/** LD E, A **/
	LOAD_E_TO_A(0x7B, 4, load(E, A)),

	/** LD H, A **/
	LOAD_H_TO_A(0x7C, 4, load(H, A)),

	/** LD L, A **/
	LOAD_L_TO_A(0x7D, 4, load(L, A)),

	/** LD (BC), A **/
	LOAD_BC_TO_A(0x0A, 8, load(BC, A)),

	/** LD (DE), A **/
	LOAD_DE_TO_A(0x1A, 8, load(DE, A)),

	/** LD (HL), A **/
	LOAD_HL_TO_A(0x7E, 8, load(HL, A)),

	/** LD (nn), A **/
	LOAD_NN_TO_A(0xFA, 16, context -> context.loadFromImmediateAddress(A)),

	/** LD #, A **/
	LOAD_N_TO_A(0x3E, 8, context -> context.loadFromImmediateValue(A)),

	/** LD A, B **/
	LOAD_A_TO_B(0x47, 4, load(A, B)),

	/** LD A, C **/
	LOAD_A_TO_C(0x4F, 4, load(A, C)),

	/** LD A, D **/
	LOAD_A_TO_D(0x57, 4, load(A, D)),

	/** LD A, E **/
	LOAD_A_TO_E(0x5F, 4, load(A, E)),

	/** LD A, H **/
	LOAD_A_TO_H(0x67, 4, load(A, H)),

	/** LD A, L **/
	LOAD_A_TO_L(0x6F, 4, load(A, L)),

	/** LD A, (BC) **/
	LOAD_A_TO_BC(0x02, 8, context -> context.putToAddress(A, BC)),

	/** LD A, (DE) **/
	LOAD_A_TO_DE(0x12, 8, context -> context.putToAddress(A, DE)),

	/** LD A, (HL) **/
	LOAD_A_TO_HL(0x77, 8, context -> context.putToAddress(A, HL)),

	/** LD A, (nn) **/
	LOAD_A_TO_NN(0x47, 16, context -> context.putToAddress(A)),

	/** LD A, ($FF00 + C) **/
	//LOAD_INTERRUPT_TO_A(0xF2, 8, context -> context.loadFromAddress(A, C, 0xFF00)),

	/** LD ($FF00 + C), A **/
	//LOAD_A_TO_INTERRUPT(0xE2, 8, context -> context.putToAddress(A, C, 0xFF00)),

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
