package fr.faylixe.yage.cpu.instruction;

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
