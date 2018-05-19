package fr.faylixe.yage.cpu;

import java.util.HashMap;
import java.util.Map;

import fr.faylixe.yage.cpu.instruction.IExecutionContext;
import fr.faylixe.yage.cpu.instruction.IInstruction;
import fr.faylixe.yage.cpu.instruction.IInstructionStream;
import fr.faylixe.yage.cpu.instruction.InstructionSetBuilder;
import fr.faylixe.yage.cpu.register.ByteRegister;
import fr.faylixe.yage.cpu.register.CompositeShortRegister;
import fr.faylixe.yage.cpu.register.FlagsRegister;
import fr.faylixe.yage.cpu.register.IShortRegister;
import fr.faylixe.yage.cpu.register.ShortRegister;
import fr.faylixe.yage.memory.IMemoryStream;

import static fr.faylixe.yage.cpu.register.IRegisterProvider.Register.*;
import static fr.faylixe.yage.cpu.register.IRegisterProvider.ExtendedRegister.*;

/**
 * CPU implementation for SharpLR35902 model.
 * 
 * The timings assume a CPU clock frequency of 4.194304 MHz
 * 
 * @author fv
 */
public class CPU implements Runnable, IExecutionContext {

	/** 8-bit registers. **/
	private final Map<Register, ByteRegister> registers;

	/** 16-bit registers. **/
	private final Map<ExtendedRegister, IShortRegister> extendedRegisters;

	/** Instruction set supported by this CPU. **/
	private final IInstruction [] instructionSet;

	/** Address bus connected to this CPU. **/
	private final IMemoryStream memoryStream;

	/** Instruction stream connected to this CPU. **/
	private final IInstructionStream instructionStream;

	/**
	 * Default constructor.
	 * Initializes registers and clock.
	 * 
	 * @param memoryStream Address bus connected to this CPU.
	 * @param instructionStream Instruction stream connected to this CPU.
	 */
	public CPU(final IMemoryStream memoryStream, final IInstructionStream instructionStream) {
		this.memoryStream = memoryStream;
		this.instructionStream = instructionStream;
		this.instructionSet = InstructionSetBuilder.getInstructionSet();
		this.registers = new HashMap<>(); // TODO : Check if concurrency is required.
		this.extendedRegisters = new HashMap<>(); // TODO : Check if concurrency is required.
		// Build register map.
		for (final Register register : Register.values()) {
			registers.put(register, F.equals(register) ? new FlagsRegister() : new ByteRegister());
		}
		// Build extended register map.
		extendedRegisters.put(AF, new CompositeShortRegister(getRegister(A), getRegister(F)));
		extendedRegisters.put(BC, new CompositeShortRegister(getRegister(B), getRegister(B)));
		extendedRegisters.put(DE, new CompositeShortRegister(getRegister(D), getRegister(E)));
		extendedRegisters.put(HL, new CompositeShortRegister(getRegister(H), getRegister(L)));
		extendedRegisters.put(SP, new ShortRegister());
		extendedRegisters.put(PC, new ShortRegister());
	}
	
	/** {@inheritDoc} **/
	@Override
	public ByteRegister getRegister(final Register name) {
		return registers.get(name);
	}

	/** {@inheritDoc} **/
	@Override
	public IShortRegister getExtendedRegister(final ExtendedRegister name) {
		return extendedRegisters.get(name);
	}

	/** {@inheritDoc} **/
	@Override
	public byte nextByte() throws IllegalAccessException {
		return instructionStream.nextByte();
	}
	
	/** {@inheritDoc} **/
	@Override
	public void sendByte(final byte value) {
		instructionStream.sendByte(value);
	}

	/** {@inheritDoc} **/
	@Override
	public byte readByte(final int address) throws IllegalAccessException {
		return memoryStream.readByte(address);
	}

	/** {@inheritDoc} **/
	@Override
	public void writeByte(final byte value, final int address) throws IllegalAccessException {
		memoryStream.writeByte(value, address);
	}

	/** {@inheritDoc} **/
	@Override
	public void run() {
		try {
			final byte prefix = instructionStream.nextByte();
			final byte opcode; // TODO : Read opcode (one byte).
			// TODO : if opcode == CB16 => extended set.
			if (prefix == 0) { // TODO : Check for CB prefix value.
				opcode = instructionStream.nextByte();
			}
			// TODO : else => main set.
			else {
				opcode = prefix;
			}
			instructionSet[opcode].execute(this);
		}
		catch (final IllegalAccessException e) {
			// TODO
		}
	}

}
