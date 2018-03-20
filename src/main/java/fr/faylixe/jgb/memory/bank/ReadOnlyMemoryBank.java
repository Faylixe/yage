package fr.faylixe.jgb.memory.bank;

/**
 * Simple memory bank decoration that prevents writing.
 * 
 * @author fv
 */
public final class ReadOnlyMemoryBank implements IMemoryBank {

	/** Delegate memory bank that is protected from write operation. **/
	private final IMemoryBank delegate;

	/**
	 * Default constructor.
	 * 
	 * @param delegate Delegate memory bank that should be write protected.
	 */
	public ReadOnlyMemoryBank(final IMemoryBank delegate) {
		this.delegate = delegate;
	}

	/** {@inheritDoc} **/
	@Override
	public int getSize() {
		return delegate.getSize();
	}

	/** {@inheritDoc} **/
	@Override
	public int getOffset() {
		return delegate.getOffset();
	}

	/** {@inheritDoc} **/
	@Override
	public void writeByte(final byte value, final int address) throws IllegalAccessException {
		throw new IllegalAccessException("Attemping to write into read only memory block.");
	}

	/** {@inheritDoc} **/
	@Override
	public void writeBytes(final byte[] values, final int address) throws IllegalAccessException {
		throw new IllegalAccessException("Attemping to write into read only memory block.");
	}

	/** {@inheritDoc} **/
	@Override
	public byte readByte(final int address) throws IllegalAccessException {
		return delegate.readByte(address);
	}

	/** {@inheritDoc} **/
	@Override
	public byte[] readBytes(final int address, final int length) throws IllegalAccessException {
		return delegate.readBytes(address, length);
	}

}
