package fr.faylixe.jgb.memory;

/**
 * 
 * @author fv
 */
public class WorkRAM implements IAddressable {

	/** **/
	private final short offset;

	/**
	 * 
	 * @param offset
	 */
	protected WorkRAM(final short offset) {
		this.offset = offset;
	}

	/** {@inheritDoc} **/
	@Override
	public int getSize() {
		return 4 * 1024;
	}

	/** {@inheritDoc} **/
	@Override
	public short getOffset() {
		return offset;
	}

}
