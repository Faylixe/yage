package fr.faylixe.jgb.memory.bank;

/**
 * 
 * @author fv
 */
public class MemoryBank extends AbstractMemoryBank {
	
	/** Data this memory bank can hold. **/
	private final byte[] data;

	/**
	 * Default constructor.
	 * 
	 * @param size Size of this memory block (in bytes).
	 * @param offset Starting address offset.
	 */
	public MemoryBank(final int size, final int offset) {
		super(size, offset);
		this.data = new byte[size];
	}

	/**
	 * Ensures the given <tt>address</tt> is covered by this memory bank.
	 * 
	 * @param address Address to ensure validity.
	 * @throws IllegalAccessException If the given <tt>address</tt> is not covered by this bank.
	 */
	private void verifyAddress(final int address) throws IllegalAccessException {
		if (address < getOffset() || address >= (getOffset() + getSize())) {
			throw new IllegalAccessException();
		}
	}

	/** {@inheritDoc} **/
	@Override
	public byte readByte(final int address) throws IllegalAccessException {
		verifyAddress(address);
		return data[address - getOffset()];
	}

	/** {@inheritDoc} **/
	@Override
	public void writeByte(final byte value, final int address) throws IllegalAccessException {
		verifyAddress(address);
		data[address - getOffset()] = value;
	}

}
