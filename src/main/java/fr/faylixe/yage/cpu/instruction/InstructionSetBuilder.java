package fr.faylixe.yage.cpu.instruction;

import java.util.ArrayList;
import java.util.List;

import fr.faylixe.yage.cpu.instruction.set.BitInstructionSet;
import fr.faylixe.yage.cpu.instruction.set.ByteALUInstructionSet;
import fr.faylixe.yage.cpu.instruction.set.ByteLoadInstructionSet;
import fr.faylixe.yage.cpu.instruction.set.CallInstructionSet;
import fr.faylixe.yage.cpu.instruction.set.JumpInstructionSet;
import fr.faylixe.yage.cpu.instruction.set.MiscInstructionSet;
import fr.faylixe.yage.cpu.instruction.set.RestartInstructionSet;
import fr.faylixe.yage.cpu.instruction.set.ReturnInstructionSet;
import fr.faylixe.yage.cpu.instruction.set.RotateShiftInstructionSet;
import fr.faylixe.yage.cpu.instruction.set.ShortALUInstructionSet;
import fr.faylixe.yage.cpu.instruction.set.ShortLoadInstructionSet;

import static java.util.Arrays.asList;

/**
 * This builder acts as an entry point for CPU
 * to access all implemented sub instruction set
 * through factory method {@link #getInstructionSet()}.
 * 
 * @author fv
 */
public final class InstructionSetBuilder {

	/** List of instruction that are part of the instruction set. **/
	private final List<IInstruction> instructionSet;

	/** Private constructor for avoiding instantiation. **/
	private InstructionSetBuilder() {
		this.instructionSet = new ArrayList<>();
	}

	/**
	 * Adds given <tt>instructions</tt> to the internal list.
	 * 
	 * @param instructions Instruction to add.
	 * @return Reference of this builder to chain call.
	 */
	public InstructionSetBuilder add(final IInstruction [] instructions) {
		instructionSet.addAll(asList(instructions));
		return this;
	}
	
	/**
	 * Builds a valid, indexed by opcode, array of instruction
	 * that is made of all instructions added to this builder.
	 * 
	 * @return Built array of opcode indexed instruction.
	 */
	public IInstruction [] build() {
		final short highest = instructionSet
			.parallelStream()
			.map(IInstruction::getOpcode)
			.max(Short::compareTo)
			.orElse((short) 0);
		final IInstruction [] result = new IInstruction[highest];
		for (final IInstruction instruction : instructionSet) {
			result[instruction.getOpcode()] = instruction;
		}
		return result;
	}

	/**
	 * Static factory method that builds the gameboy instruction
	 * set by indexing subset holded by enumeration in
	 * <tt>fr.faylixe.yage.cpu.instruction.set</tt> package.
	 * 
	 * @return Array of opcode indexed instruction.
	 */
	public static IInstruction [] getInstructionSet() {
		return new InstructionSetBuilder()
			.add(ByteLoadInstructionSet.values())
			.add(ShortLoadInstructionSet.values())
			.add(ByteALUInstructionSet.values())
			.add(ShortALUInstructionSet.values())
			.add(MiscInstructionSet.values())
			.add(RotateShiftInstructionSet.values())
			.add(BitInstructionSet.values())
			.add(CallInstructionSet.values())
			.add(JumpInstructionSet.values())
			.add(RestartInstructionSet.values())
			.add(ReturnInstructionSet.values())
			.build();
	}

}
