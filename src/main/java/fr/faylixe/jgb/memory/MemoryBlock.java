package fr.faylixe.jgb.memory;

/**
 * 
 * @author fv
 */
public class MemoryBlock implements IMemoryStream {

	/** Size of this memory block (in byte). **/
	private final int size;

	/** Starting address offset. **/
	private final int offset;
	
	/** Data this memory block can hold. **/
	private final byte[] data;

	/**
	 * Default constructor.
	 * 
	 * @param size Size of this memory block (in bytes).
	 * @param offset Starting address offset.
	 */
	public MemoryBlock(final int size, final int offset) {
		this.size = size;
		this.offset = offset;
		this.data = new byte[size];
	}

	/**
	 * Getter for the size of this memory block.
	 * 
	 * @return Size of this memory block (in bytes).
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Getter for the starting address offset of this block.
	 * 
	 * @return Starting address offset of this block.
	 */
	public int getOffset() {
		return offset;
	}

	/** {@inheritDoc} **/
	@Override
	public byte readByte(final int address) throws IllegalAccessException {
		if (address < offset || address >= (address + size)) {
			throw new IllegalAccessException();
		}
		return data[address - offset];
	}

	/** {@inheritDoc} **/
	@Override
	public void writeByte(final byte value, final int address) throws IllegalAccessException {
		if (address < offset || address >= (address + size)) {
			throw new IllegalAccessException();
		}
		data[address - offset] = value;
	}

}
