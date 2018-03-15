package fr.faylixe.jgb.memory;

/**
 * Simple memory block extension that prevents writing.
 * 
 * @author fv
 */
public class ReadOnlyMemoryBlock extends MemoryBlock {

	/**
	 * Default constructor.
	 * 
	 * @param size Size of this memory block (in bytes).
	 * @param offset Starting address offset.
	 */
	public ReadOnlyMemoryBlock(final int size, final int offset) {
		super(size, offset);
	}

	/** {@inheritDoc} **/
	@Override
	public void writeByte(final byte value, final int address) throws IllegalAccessException {
		throw new IllegalAccessException("Attemping to write into read only memory block.");
	}

}
