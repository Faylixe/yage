package fr.faylixe.yage.cpu.register;

/**
 * 
 * @author fv
 */
public interface IShortRegister extends IRegister {

	/**
	 * 
	 * @return
	 */
	short get();

	/**
	 * 
	 * @param value
	 */
	void set(short value);

}
