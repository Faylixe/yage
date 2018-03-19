package fr.faylixe.jgb.cpu;

import fr.faylixe.jgb.cpu.register.ExtendedRegister;
import fr.faylixe.jgb.cpu.register.FlagsRegister;
import fr.faylixe.jgb.cpu.register.Register;
import fr.faylixe.jgb.memory.IMemoryStream;

/**
 * CPU implementation for SharpLR35902 model.
 * 
 * The timings assume a CPU clock frequency of 4.194304 MHz
 * 
 * @author fv
 */
public class CPU implements Runnable {

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

	/** **/
	private final IInstructionStream instructionStream;

	/**
	 * Default constructor.
	 * Initializes registers and clock.
	 * 
	 * @param memoryStream
	 * @param instructionStream
	 */
	public CPU(final IMemoryStream memoryStream, final IInstructionStream instructionStream) {
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
		this.instructionStream = instructionStream;
	}

	/** {@inheritDoc} **/
	@Override
	public void run() {
		final byte prefix = instructionStream.nextByte();
		final byte opcode;
		if (prefix == 0) { // TODO : Check for CB prefix value.
			opcode = instructionStream.nextByte();
		}
		else {
			opcode = prefix;
		}
		// TODO : Read opcode (one byte).
		// TODO : if opcode == CB16 => extended set.
			// TODO : Read next byte.
		// TODO : else => main set.
	}

}
