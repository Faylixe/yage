package fr.faylixe.jgb.cpu;

import fr.faylixe.jgb.memory.IMemoryStream;

/**
 * CPU implementation for SharpLR35902 model.
 * 
 * The timings assume a CPU clock frequency of 4.194304 MHz
 * 
 * @author fv
 */
public class CPU {

	/** **/
	private final Register A;

	/** **/
	private final Register B;

	/** **/
	private final Register C;

	/** **/
	private final Register D;

	/** **/
	private final Register E;

	/** **/
	private final Register H;

	/** **/
	private final Register L;

	/** Flags register. **/
	private final FlagsRegister F;

	/** Stack Pointer register. **/
	private final ExtendedRegister SP;

	/** **/
	private final ExtendedRegister PC;

	/** **/
	private final IMemoryStream memoryStream;

	/**
	 * Default constructor.
	 * Initializes registers and clock.
	 * 
	 * @param memoryStream
	 */
	public CPU(final IMemoryStream memoryStream) {
		this.A = new Register();
		this.B = new Register();
		this.C = new Register();
		this.D = new Register();
		this.E = new Register();
		this.H = new Register();
		this.L = new Register();
		this.F = new FlagsRegister();
		this.SP = new ExtendedRegister();
		this.PC = new ExtendedRegister();
		this.memoryStream = memoryStream;
	}
	
	/**
	 * 
	 */
	public void tick() {
		// TODO : Read opcode (one byte).
		// TODO : if opcode == CB16 => extended set.
			// TODO : Read next byte.
		// TODO : else => main set.
	}

}
