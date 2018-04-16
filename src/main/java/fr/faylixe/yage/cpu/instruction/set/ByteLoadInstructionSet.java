package fr.faylixe.yage.cpu.instruction.set;

import static fr.faylixe.yage.cpu.instruction.IExecutionContext.load;
import static fr.faylixe.yage.cpu.instruction.IExecutionContext.loadAddress;
import static fr.faylixe.yage.cpu.instruction.IExecutionContext.loadValue;
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

	/** LD B, n **/
	LOAD_N_TO_B(0x06, 8, loadValue(B)),

	/** LD C, n **/
	LOAD_N_TO_C(0x0E, 8, loadValue(C)),
	
	/** LD D, n **/
	LOAD_N_TO_D(0x16, 8, loadValue(D)),
	
	/** LD E, n **/
	LOAD_N_TO_E(0x1E, 8, loadValue(E)),
	
	/** LD H, n **/
	LOAD_N_TO_H(0x26, 8, loadValue(H)),

	/** LD L, n **/
	LOAD_N_TO_L(0x2E, 8, loadValue(L)),

	/** LD B, B **/
	LOAD_B_TO_B(0x40, 4, NOP),

	/** LD B, C **/
	LOAD_C_TO_B(0x41, 4, load(C, B)),
	
	/** LD B, D **/
	LOAD_D_TO_B(0x42, 4, load(D, B)),
	
	/** LD B, E **/
	LOAD_E_TO_B(0x43, 4, load(E, B)),

	/** LD B, H **/
	LOAD_H_TO_B(0x44, 4, load(H, B)),

	/** LD B, L **/
	LOAD_L_TO_B(0x45, 4, load(L, B)),

	/** LD B, (HL) **/
	LOAD_HL_TO_B(0x46, 8, load(HL, B)),
	
	/** LD C, B **/
	LOAD_B_TO_C(0x48, 4, load(B, C)),

	/** LD C, C **/
	LOAD_C_TO_C(0x49, 4, NOP),
	
	/** LD C, D **/
	LOAD_D_TO_C(0x4A, 4, load(D, C)),
	
	/** LD C, E **/
	LOAD_E_TO_C(0x4B, 4, load(E, C)),
	
	/** LD C, H **/
	LOAD_H_TO_C(0x4C, 4, load(H, C)),
	
	/** LD C, L **/
	LOAD_L_TO_C(0x4D, 4, load(L, C)),
	
	/** LD C, (HL) **/
	LOAD_HL_TO_C(0x4E, 8, load(HL, C)),

	/** LD D, B **/
	LOAD_B_TO_D(0x50, 4, load(B, D)),

	/** LD D, C **/
	LOAD_C_TO_D(0x51, 4, load(C, D)),

	/** LD D, D **/
	LOAD_D_TO_D(0x52, 4, NOP),

	/** LD D, E **/
	LOAD_E_TO_D(0x53, 4, load(E, D)),
	
	/** LD D, H **/
	LOAD_H_TO_D(0x54, 4, load(H, D)),

	/** LD D, L **/
	LOAD_L_TO_D(0x55, 4, load(L, D)),

	/** LD D, (HL) **/
	LOAD_HL_TO_D(0x56, 8, load(HL, D)),

	/** LD E, B **/
	LOAD_B_TO_E(0x58, 4, load(B, E)),

	/** LD E, C **/
	LOAD_C_TO_E(0x59, 4, load(C, E)),

	/** LD E, D **/
	LOAD_D_TO_E(0x5A, 4, load(D, E)),

	/** LD E, E **/
	LOAD_E_TO_E(0x5B, 4, NOP),

	/** LD E, H **/
	LOAD_H_TO_E(0x5C, 4, load(H, E)),

	/** LD E, L **/
	LOAD_L_TO_E(0x5D, 4, load(L, E)),

	/** LD E, (HL) **/
	LOAD_HL_TO_E(0x5E, 8, load(HL, E)),

	/** LD H, B **/
	LOAD_B_TO_H(0x60, 4, load(B, H)),

	/** LD H, C **/
	LOAD_C_TO_H(0x61, 4, load(C, H)),

	/** LD H, D **/
	LOAD_D_TO_H(0x62, 4, load(D, H)),

	/** LD H, E **/
	LOAD_E_TO_H(0x63, 4, load(E, H)),

	/** LD H, H **/
	LOAD_H_TO_H(0x64, 4, NOP),

	/** LD H, L **/
	LOAD_L_TO_H(0x65, 4, load(L, H)),

	/** LD H, (HL) **/
	LOAD_HL_TO_H(0x66, 8, load(HL, H)),

	/** LD L, B **/
	LOAD_B_TO_L(0x68, 4, load(B, L)),

	/** LD L, C **/
	LOAD_C_TO_L(0x69, 4, load(C, L)),

	/** LD L, D **/
	LOAD_D_TO_L(0x6A, 4, load(D, L)),

	/** LD L, E **/
	LOAD_E_TO_L(0x6B, 4, load(E, L)),

	/** LD L, H **/
	LOAD_H_TO_L(0x6C, 4, load(H, L)),

	/** LD L, L **/
	LOAD_L_TO_L(0x6D, 4, load(L, L)),

	/** LD L, (HL) **/
	LOAD_HL_TO_L(0x6E, 8, load(HL, L)),
	
	/** LD (HL), B **/
	LOAD_B_TO_HL(0x70, 8, load(B, HL)),

	/** LD (HL), C **/
	LOAD_C_TO_HL(0x71, 8, load(C, HL)),

	/** LD (HL), D **/
	LOAD_D_TO_HL(0x72, 8, load(D, HL)),

	/** LD (HL), E **/
	LOAD_E_TO_HL(0x73, 8, load(E, HL)),

	/** LD (HL), H **/
	LOAD_H_TO_HL(0x74, 8, load(H, HL)),

	/** LD (HL), L **/
	LOAD_L_TO_HL(0x75, 8, load(L, HL)),

	/** LD (HL), B **/
	LOAD_N_TO_HL(0x36, 12, loadAddress(HL)),
	
	// TODO : To check.

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
	LOAD_NN_TO_A(0xFA, 16, loadAddress(A)),

	/** LD #, A **/
	LOAD_N_TO_A(0x3E, 8, loadValue(A)),

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
	//LOAD_A_TO_BC(0x02, 8, context -> context.putToAddress(A, BC)),

	/** LD A, (DE) **/
	//LOAD_A_TO_DE(0x12, 8, context -> context.putToAddress(A, DE)),

	/** LD A, (HL) **/
	//LOAD_A_TO_HL(0x77, 8, context -> context.putToAddress(A, HL)),

	/** LD A, (nn) **/
	//LOAD_A_TO_NN(0x47, 16, context -> context.putToAddress(A)),

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
