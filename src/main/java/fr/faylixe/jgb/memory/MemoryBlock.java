package fr.faylixe.jgb.memory;

/**
 * 
 * @author fv
 */
public class MemoryBlock implements IAddressable {

	/** Size of this memory block (in byte). **/
	private final int size;

	/** Starting address offset. **/
	private final int offset;

	/**
	 * Default constructor.
	 * 
	 * @param size Size of this memory block (in byte).
	 * @param offset Starting address offset.
	 */
	public MemoryBlock(final int size, final int offset) {
		this.size = size;
		this.offset = offset;
	}

	/** {@inheritDoc} **/
	@Override
	public int getSize() {
		return size;
	}

	/** {@inheritDoc} **/
	@Override
	public int getOffset() {
		return offset;
	}

}
