package fr.faylixe.jgb.memory.bank;

/**
 * Memory bank implementation using a simple
 * byte array as storage datastructure.
 * 
 * @author fv
 */
public final class ArrayMemoryBank extends AbstractMemoryBank {
	
	/** Data this memory bank can hold. **/
	private final byte[] data;

	/**
	 * Default constructor.
	 * 
	 * @param size Size of this memory bank (in bytes).
	 * @param offset Starting address offset.
	 */
	public ArrayMemoryBank(final int size, final int offset) {
		super(size, offset);
		this.data = new byte[size];
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
