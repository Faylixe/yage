package fr.faylixe.jgb.memory;

/**
 * 
 * @author fv
 */
public class VideoRAM implements IAddressable {

	/** {@inheritDoc} **/
	@Override
	public int getSize() {
		return 8 * 1024;
	}

	/** {@inheritDoc} **/
	@Override
	public short getOffset() {
		return (short) 0x8000;
	}

}
