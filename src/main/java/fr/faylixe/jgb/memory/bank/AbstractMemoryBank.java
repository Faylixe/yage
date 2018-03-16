package fr.faylixe.jgb.memory.bank;

import fr.faylixe.jgb.memory.IMemoryBank;

/**
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
