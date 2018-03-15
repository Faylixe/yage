package fr.faylixe.jgb.cpu.register;

/**
 * 
 * https://stackoverflow.com/questions/2188660/convert-short-to-byte-in-java
 * 
 * @author fv
 */
public class Register {

	/** **/
	private byte state;

	/**
	 * 
	 * @return
	 */
	public byte getState() {
		return state;
	}

	/**
	 * 
	 * @param newState
	 */
	public void setState(final byte newState) {
		this.state = newState;
	}

	/**
	 * 
	 * @param high
	 * @param low
	 * @return
	 */
	static short getCompositeState(final Register high, final Register low) {
		return (short) ((high.getState() << 8) | low.getState());
	}

	/**
	 * 
	 * @param high
	 * @param low
	 * @param compositeState
	 */
	static void setCompositeState(final Register high, final Register low, final short compositeState) {
		// TODO : Ensure big endian is used.  
		high.setState((byte)(compositeState >> 8));
		low.setState((byte) compositeState);
	}

}
