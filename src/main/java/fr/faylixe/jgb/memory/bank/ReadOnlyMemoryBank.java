package fr.faylixe.jgb.memory.bank;

/**
 * Simple memory bank extension that prevents writing.
 * 
 * @author fv
 */
public final class ReadOnlyMemoryBank extends MemoryBank {

	/**
	 * Default constructor.
	 * 
	 * @param size Size of this memory block (in bytes).
	 * @param offset Starting address offset.
	 */
	public ReadOnlyMemoryBank(final int size, final int offset) {
		super(size, offset);
	}

	/** {@inheritDoc} **/
	@Override
	public void writeByte(final byte value, final int address) throws IllegalAccessException {
		throw new IllegalAccessException("Attemping to write into read only memory block.");
	}

}
