package fr.faylixe.jgb.cpu;

/**
 * 
 * @author fv
 */
public interface Register {

	/**
	 * 
	 * @return
	 */
	byte getState();

	/**
	 * 
	 * @param newState
	 */
	void setState(byte newState);

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
