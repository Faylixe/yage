package fr.faylixe.yage.cpu.register;

/**
 * 16-bit register implementation.
 * 
 * @author fv
 */
public class ShortRegister implements IShortRegister {

	/** Register associated value. **/
	private short value;

	/** {@inheritDoc} **/
	@Override
	public short get() {
		return value;
	}

	/** {@inheritDoc} **/
	@Override
	public void set(final short value) {
		this.value = value;
	}

}
