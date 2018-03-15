package fr.faylixe.jgb.cpu.register;

/**
 * 
 * @author fv
 */
public class DefaultRegister implements Register {

	/** **/
	private byte state;

	/** {@inheritDoc} **/
	@Override
	public byte getState() {
		return state;
	}

	/** {@inheritDoc} **/
	@Override
	public void setState(final byte newState) {
		this.state = newState;
	}

}
