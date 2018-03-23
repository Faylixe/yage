package fr.faylixe.yage.memory.bank;

/**
 * Abstract memory bank implementation that handles size and offset.
 * 
 * @author fv
 */
public abstract class AbstractMemoryBank implements IMemoryBank {

	/** Size of this memory block (in byte). **/
	private final int size;

	/** Starting address offset. **/
	private final int offset;

	/**
	 * Default constructor.
	 * 
	 * @param size Size of this memory block (in bytes).
	 * @param offset Starting address offset.
	 */
	public AbstractMemoryBank(final int size, final int offset) {
		this.size = size;
		this.offset = offset;
	}

	/**
	 * Ensures the given <tt>address</tt> is covered by this memory bank.
	 * 
	 * @param address Address to ensure validity.
	 * @throws IllegalAccessException If the given <tt>address</tt> is not covered by this bank.
	 */
	public final void verifyAddress(final int address) throws IllegalAccessException {
		if (!isAddressCovered(address)) {
			throw new IllegalAccessException();
		}
	}

	/** {@inheritDoc} **/
	@Override
	public final boolean isAddressCovered(final int address) {
		return (address < getOffset() || address >= (getOffset() + getSize()));
	}

	/** {@inheritDoc} **/
	@Override
	public final int getSize() {
		return size;
	}

	/** {@inheritDoc} **/
	@Override
	public final int getOffset() {
		return offset;
	}

}
