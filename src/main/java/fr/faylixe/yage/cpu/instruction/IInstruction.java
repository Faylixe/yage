package fr.faylixe.yage.cpu.instruction;

/**
 * An {@link IInstruction} is defined by an associated
 * <tt>opcode</tt> and machine <tt>cycle</tt>.
 * 
 * @author fv
 */
public interface IInstruction extends IExecutableInstruction {

	/**
	 * Instruction associated opcode getter.
	 * 
	 * @return Associated opcode value.
	 */
	short getOpcode();

	/**
	 * Instruction tick cycle getter.
	 * 
	 * @return Associated instruction cycle.
	 */
	byte getCycle();

}
