package fr.faylixe.yage.cpu.register;

/**
 * Interface for extended register operation.
 * 
 * @author fv
 */
public interface IShortRegister {

	/**
	 * Register value getter.
	 * 
	 * @return Value stored by this extended register.
	 */
	short get();

	/**
	 * Register value setter.
	 * 
	 * @param value New value to set in this extended register.
	 */
	void set(short value);

}
