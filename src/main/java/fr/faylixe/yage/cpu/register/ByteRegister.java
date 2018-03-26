package fr.faylixe.yage.cpu.register;

/**
 * 8-bit register implementation.
 * 
 * @author fv
 */
public class ByteRegister {

	/** Register associated value. **/
	private byte value;

	/**
	 * Register value getter.
	 * 
	 * @return Value stored by this register.
	 */
	public byte get() {
		return value;
	}

	/**
	 * Register value setter.
	 * 
	 * @param value New value to set in this register.
	 */
	public void set(final byte value) {
		this.value = value;
	}

}
