package fr.faylixe.yage.cpu.register;

import fr.faylixe.yage.utils.BinaryUtils;

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
	 * Register value getter as byte array.
	 * 
	 * @return Byte decomposition of this register.
	 */
	default byte[] getBytes() {
		return BinaryUtils.decompose(get());
	}

	/**
	 * Register value setter.
	 * 
	 * @param value New value to set in this extended register.
	 */
	void set(short value);

}
