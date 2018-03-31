package fr.faylixe.yage.cpu.register;

/**
 * 16-bit register implementation.
 * 
 * @author fv
 */
public class ShortRegister {

	/** Register associated value. **/
	private short value;

	/**
	 * Register value getter.
	 * 
	 * @return Value stored by this extended register.
	 */
	public short get() {
		return value;
	}

	/**
	 * Register value setter.
	 * 
	 * @param value New value to set in this extended register.
	 */
	public void set(final short value) {
		this.value = value;
	}

}
