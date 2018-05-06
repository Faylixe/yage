package fr.faylixe.yage.cpu.register;

import fr.faylixe.yage.utils.BinaryUtils;

/**
 * Extended register view composed of two 8-bit register.
 * 
 * @author fv
 */
public final class CompositeShortRegister implements IShortRegister {

	/** Most significant byte holder. **/
	private final ByteRegister mostSignificant;

	/** Least significant byte holder. **/
	private final ByteRegister leastSignificant;

	/**
	 * Default constructor.
	 * 
	 * @param mostSignificant Most significant byte holder.
	 * @param leastSignificant Least significant byte holder.
	 */
	public CompositeShortRegister(
			final ByteRegister mostSignificant,
			final ByteRegister leastSignificant) {
		this.mostSignificant = mostSignificant;
		this.leastSignificant = leastSignificant;
	}

	/** {@inheritDoc} **/
	@Override
	public short get() {
		return BinaryUtils.compose(
				mostSignificant.get(),
				leastSignificant.get());
	}
	
	/** {@inheritDoc} **/
	@Override
	public byte[] getBytes() {
		return new byte[] {
			mostSignificant.get(),
			leastSignificant.get()
		};
	}

	/** {@inheritDoc} **/
	@Override
	public void set(final short value) {
		final byte [] bytes = BinaryUtils.decompose(value);
		mostSignificant.set(bytes[0]);
		leastSignificant.set(bytes[1]);
	}

}
