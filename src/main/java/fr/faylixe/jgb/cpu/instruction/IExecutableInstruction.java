package fr.faylixe.jgb.cpu.instruction;

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
	void execute(IExecutionContext context);

}
