package fr.faylixe.jgb.cpu;

/**
 * TODO : Document.
 * 
 * Note : Based on instruction into GBCPUMan documents that
 * can be found at http://marc.rawer.de/Gameboy/Docs/GBCPUman.pdf.
 * 
 * @author fv
 */
public final class InstructionExecutor {

	/**
	 * LD nn, n
	 * Put value nn into n, where n, is the next
	 * immediate byte, and nn is determined regarding
	 * of the following opcode table :
	 * 
	 * - B, n -> 06
	 * - C, n -> 0E
	 * - D, n -> 16
	 * - E, n -> 1E
	 * - H, n -> 26
	 * - L, n -> 2E
	 * 
	 * @see GBCPUMan, Page 65
	 */
	public void loadValueIntoRegister() {
		// TODO : Implements.
	}
	
	/**
	 * LD r1, r2
	 * Put value r2 into r1, where r1 and r2 could be
	 * one of the following register : A, B, C, D, E, H, L, (HL)
	 * 
	 * - A, (A->7F, B->78, C->79, D->7A, E->7B, H->7C, L->7D, (HL)->7E)
	 * - B, (B->40, C->41, D->42, E->43, H->44, L->45, (HL)->46)
	 * - C, (B->48, C->49, D->4A, E->4B, H->4C, L->4D, (HL)->4E)
	 * - D, (B->50, C->51, D->52, E->53, H->54, L->55, (HL)->56)
	 * - E, (B->58, C->59, D->5A, E->5B, H->5C, L->5D, (HL)->5E)
	 * - H, (B->60, C->61, D->62, E->63, H->64, L->65, (HL)->66)
	 * - L, (B->68, C->69, D->6A, E->6B, H->6C, L->6D, (HL)->6E)
	 * - (HL), (B->70, C->71, D->72, E->73, H->74, L->75, n->36)
	 * 
	 * TODO : Figure out opcode 36 (HL),n 
	 * 
	 * @see GBCPUMan, Page 66 - 67
	 */
	public void loadRegisterInRegister() {
		// TODO : Implements.
	}
	
	/**
	 * LD A,n
	 * Put value n into A, where n could be either A, B, C, D, E, H, L,
	 * (BC), (DE), (HL), nn (next two immediate byte with LS byte first),
	 * or #. The target n value is determined regarding of the following
	 * opcode table :
	 * 
	 * - A -> 7F
	 * - B -> 78
	 * - C -> 79
	 * - D -> 7A
	 * - E -> 7B
	 * - H -> 7C
	 * - L -> 7D
	 * - (BC) -> 0A 
	 * - (DE) -> 1A
	 * - (HL) -> 7E
	 * - nn -> FA
	 * - # -> 3E
	 * 
	 * @see GBCPUMan, Page 68
	 */
	public void loadAccumulator() {
		// TODO : Implements.
	}
	
	/**
	 * LD n, A.
	 * Same description as LD A, n, but with the following
	 * distinct opcode table for n parameter :
	 * 
	 * - A -> 7F
	 * - B -> 47
	 * - C -> 4F
	 * - D -> 57
	 * - E -> 5F
	 * - H -> 67
	 * - L -> 6F
	 * - (BC) -> 02
	 * - (DE) -> 12
	 * - (HL) -> 77
	 * - nn -> EA
	 * 
	 * Note that the # value for n is not available here.
	 * 
	 * @see GBCPUMan, Page 69
	 */
	public void loadAccumulatorReversed() {
		// TODO : Implements.
	}

	/**
	 * LD A, (C)
	 * Put value at address $FF00 + C into A which correspond
	 * to an I/O registers (address $FF00 to $FF7F).
	 * 
	 * The associated opcode is F2.
	 * 
	 * @see GBCPUMan, Page 70
	 */
	public void loadCIORegisterToAccumulator() {
		// TODO : Implements.
	}
	

	/**
	 * LD (C), A
	 * Put value from A at address $FF00 + C which correspond
	 * to an I/O registers (address $FF00 to $FF7F).
	 * 
	 * The associated opcode is E2.
	 * 
	 * @see GBCPUMan, Page 70
	 */
	public void loadAccumulatorToCIORegister() {
		// TODO : Implements.
	}

	// Page 71
	// TODO : LD A, (HLD)
	// TODO : LD A, (HL-)
	// TODO : LDD A, (HL)
	
	// Page 72
	// TODO : LD (HLD), A
	// TODO : LD (HL-), A
	// TODO : LDD (HL), A
	
	// Page 73
	// TODO : LD A, (HLI)
	// TODO : LD A, (HL+)
	// TODO : LDI A, (HL)
	
	// Page 74
	// TODO : LD (HLI), A
	// TODO : LD (HL+), A
	// TODO : LDI (HL), A

	/**
	 * LDH (n), A
	 * Put value from A at address $FF00 + n which correspond
	 * to an I/O registers (address $FF00 to $FF7F).
	 * 
	 * The considered n value is the next immediate byte value
	 * and the associated opcode is E0.
	 * 
	 * @see GBCPUMan, Page 75
	 */
	public void loadAccumulatorToIORegister() {
		// TODO : Implements.
	}
	
	/**
	 * LDH A, (n)
	 * Put value from address $FF00 + n which correspond
	 * to an I/O registers (address $FF00 to $FF7F) into A.
	 * 
	 * The considered n value is the next immediate byte value
	 * and the associated opcode is E0.
	 * 
	 * @see GBCPUMan, Page 75
	 */
	public void loadIORegisterToAccumulator() {
		// TODO : Implements.
	}

	/**
	 * LD n, nn
	 * Put value nn into a composite register of 16bit size
	 * denoted by n. The nn value consists in the next two
	 * immediate byte value, and the target register is determined
	 * regarding of the following opcode table : 
	 * 
	 * - BC -> 01
	 * - DE -> 11
	 * - HL -> 21
	 * - SP -> 31
	 * 
	 * @see GBCPUMan, Page 76
	 */
	public void loadValueInDoubleRegister() {
		// TODO : Implements.
	}

	/**
	 * LD SP, HL
	 * Put value from HL into SP.
	 * 
	 * The associated opcode is F9.
	 * 
	 * @see GBCPUMan, Page 76
	 */
	public void loadHLIntoStackPointer() {
		// TODO : Implements.
	}

	// Page 77
	// TODO : LD HL, SP+n

	/**
	 * LDHL SP, n
	 * Put address denoted by (SP value + n) into HL.
	 * The n value consists in the next immediate byte
	 * (which is SIGNED), and can affect Z, N, H and C flags.
	 * 
	 * The associated opcode is F8.
	 * 
	 * @see GBCPUMan, Page 77
	 */
	public void loadStackPointerOffsetIntoHL() {
		// TODO : Implements.
	}
	
	/**
	 * LD (nn), SP
	 * Put stack pointer at address nn. The nn value consists
	 * in next two immediate bytes.
	 * 
	 * The associated opcode is 08.
	 * 
	 * @see GBCPUMan, Page 78
	 */
	public void loadInStackPointer() {
		// TODO : Implements.
	}
	
	
	/**
	 * PUSH nn
	 * Push register pair nn into stack and decrement the
	 * stack pointer twice. The nn register pair could be either
	 * AF, BC, DE, HL regarding of the following opcode table :
	 * 
	 * - AF -> F5
	 * - BC -> C5
	 * - DE -> D5
	 * - HL -> E5
	 * 
	 * @see GBCPUMan, Page 78
	 */
	public void pushStack() {
		// TODO : Implements.
	}
	
	/**
	 * POP nn
	 * Pop two bytes off the stack into register pair nn and
	 * increment the stack pointer twice. The nn register pair
	 * could be either AF, BC, DE, HL regarding of the
	 * following opcode table :
	 * 
	 * - AF -> F1
	 * - BC -> C1
	 * - DE -> D1
	 * - HL -> E1
	 * 
	 * @see GBCPUMan, Page 79
	 */
	public void popStack() {
		// TODO : Implements.
	}

	/**
	 * ADD A, n
	 * Add n to A, where n could be either A, B, C, D, E,
	 * H, L, (HL), or # regarding of the following opcode table :
	 * 
	 * - A -> 87
	 * - B -> 80
	 * - C -> 81
	 * - D -> 82
	 * - E -> 83
	 * - H -> 84
	 * - L -> 85
	 * - (HL) -> 86
	 * - # -> C6
	 * 
	 * This operation can affect following flags :
	 * 
	 * - Z -> 1 if result is zero
	 * - N -> 0
	 * - H -> 1 if carry from bit 3
	 * - C -> 1 if carry from bit 7
	 * 
	 * @see GBCPUMan, Page 80
	 */
	public void add() {
		// TODO : Implements.
	}

	/**
	 * ADC A,n
	 * Add n + carry flag to A, where n could be either A, B, C, D, E,
	 * H, L, (HL), or # regarding of the following opcode table :
	 * 
	 * - A -> 8F
	 * - B -> 88
	 * - C -> 89
	 * - D -> 8A
	 * - E -> 8B
	 * - H -> 8C
	 * - L -> 8D
	 * - (HL) -> 8E 
	 * - # -> CE
	 * 
	 * This operation can affect following flags :
	 * 
	 * - Z -> 1 if result is zero
	 * - N -> 0
	 * - H -> 1 if carry from bit 3
	 * - C -> 1 if carry from bit 7
	 * 
	 * @see GBCPUMan, Page 81
	 */
	public void addAndCarry() {
		// TODO : Implements.
	}

}
