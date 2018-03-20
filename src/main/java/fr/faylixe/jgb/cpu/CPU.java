package fr.faylixe.jgb.cpu;

import fr.faylixe.jgb.cpu.register.ShortRegister;
import fr.faylixe.jgb.cpu.register.FlagsRegister;
import fr.faylixe.jgb.cpu.instruction.IInstructionStream;
import fr.faylixe.jgb.cpu.register.ByteRegister;
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
	private final ByteRegister A;

	/** **/
	private final ByteRegister B;

	/** **/
	private final ByteRegister C;

	/** **/
	private final ByteRegister D;

	/** **/
	private final ByteRegister E;

	/** **/
	private final ByteRegister H;

	/** **/
	private final ByteRegister L;

	/** Flags register. **/
	private final FlagsRegister F;

	/** Stack Pointer register. **/
	private final ShortRegister SP;

	/** **/
	private final ShortRegister PC;

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
		this.A = new ByteRegister();
		this.B = new ByteRegister();
		this.C = new ByteRegister();
		this.D = new ByteRegister();
		this.E = new ByteRegister();
		this.H = new ByteRegister();
		this.L = new ByteRegister();
		this.F = new FlagsRegister();
		this.SP = new ShortRegister();
		this.PC = new ShortRegister();
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
