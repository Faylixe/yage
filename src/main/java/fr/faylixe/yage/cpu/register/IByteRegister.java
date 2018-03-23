package fr.faylixe.yage.cpu.register;

/**
 * 
 * @author fv
 */
public interface IByteRegister extends IRegister {

	/**
	 * 
	 * @return
	 */
	byte get();

	/**
	 * 
	 * @param value
	 */
	void set(byte value);

}
