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

	/**
	 * Composite factory method that creates a new instruction from this
	 * one and the given <tt>next</tt> immediate instruction to execute.
	 * 
	 * @param next Next instruction to execute after this one.
	 * @return Composite created instruction.
	 */
	default IExecutableInstruction then(final IExecutableInstruction next) {
		return context -> {
			execute(context);
			next.execute(context);
		};
	}

	/** No operation static instance. **/
	static IExecutableInstruction NOP = context -> {};

}
