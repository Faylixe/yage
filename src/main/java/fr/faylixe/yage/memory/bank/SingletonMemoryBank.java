package fr.faylixe.yage.memory.bank;

/**
 * A memory bank that holds a single address / value pair.
 * 
 * @author fv
 */
public final class SingletonMemoryBank implements IMemoryBank {

	/** Address of this singleton. **/
	private final int offset;

	/** Current value of this singleton. **/
	private byte value;

	/**
	 * Default constructor.
	 * 
	 * @param offset Address of this singleton.
	 */
	public SingletonMemoryBank(final int offset) {
		this.offset = offset;
	}

	/** {@inheritDoc} **/
	@Override
	public byte readByte(final int address) throws IllegalAccessException {
		if (address != offset) {
			throw new IllegalAccessException();
		}
		return value;
	}

	/** {@inheritDoc} **/
	@Override
	public void writeByte(final byte value, final int address) throws IllegalAccessException {
		if (address != offset) {
			throw new IllegalAccessException();
		}
	}

	/** {@inheritDoc} **/
	@Override
	public int getSize() {
		return 1;
	}

	/** {@inheritDoc} **/
	@Override
	public int getOffset() {
		return offset;
	}

}
