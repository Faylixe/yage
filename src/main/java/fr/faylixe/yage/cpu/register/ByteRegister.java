package fr.faylixe.yage.cpu.register;

/**
 * 
 * https://stackoverflow.com/questions/2188660/convert-short-to-byte-in-java
 * 
 * @author fv
 */
public class ByteRegister implements IByteRegister {

	/** **/
	private byte state;

	/**
	 * 
	 * @return
	 */

	@Override
	public Registers getName() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public byte get() {
		return state;
	}

	/**
	 * 
	 * @param newState
	 */
	@Override
	public void set(final byte newState) {
		this.state = newState;
	}

	/**
	 * 
	 * @param high
	 * @param low
	 * @return
	 */
	static short getCompositeState(final ByteRegister high, final ByteRegister low) {
		return (short) ((high.get() << 8) | low.get());
	}

	/**
	 * 
	 * @param high
	 * @param low
	 * @param compositeState
	 */
	static void setCompositeState(final ByteRegister high, final ByteRegister low, final short compositeState) {
		// TODO : Ensure big endian is used.  
		high.set((byte)(compositeState >> 8));
		low.set((byte) compositeState);
	}

}
