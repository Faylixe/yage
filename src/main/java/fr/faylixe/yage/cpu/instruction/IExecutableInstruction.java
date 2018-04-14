package fr.faylixe.yage.cpu.instruction;

/**
 * Functional interface that represents an executable
 * processor instruction. Such instruction can be
 * executed by using an {@link IExecutionContext} instance
 * that provides all data stream (memory, registers, etc ...).
 * 
 * @author fv
 */
@FunctionalInterface
public interface IExecutableInstruction {

	/**
	 * Executes this instruction using the given <tt>context</tt>.
	 * 
	 * @param context Context to use.
	 * @throws IllegalAccessException If any error occurs during context usage.
	 */
	void execute(IExecutionContext context) throws IllegalAccessException;

	/** No operation static instance. **/
	static IExecutableInstruction NOP = context -> {};

}
