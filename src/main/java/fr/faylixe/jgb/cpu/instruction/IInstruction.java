package fr.faylixe.jgb.cpu.instruction;

/**
 * 
 * @author fv
 */
public interface IInstruction extends IExecutableInstruction {

	/**
	 * 
	 * @return
	 */
	short getOpcode();

	/**
	 * 
	 * @return
	 */
	byte getCycle();

}
