package fr.faylixe.yage.cpu.instruction;

/**
 * 
 * @author fv
 */
@FunctionalInterface
public interface IExecutableInstruction {

	/**
	 * 
	 * @param context
	 */
	void execute(IExecutionContext context) throws IllegalAccessException;

}
