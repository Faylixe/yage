package fr.faylixe.yage.cpu.register;

/**
 * A register provider offer an access layer to
 * each type of register, indexed by name.
 * 
 * @author fv
 */
public interface IRegisterProvider {

	/** Enumeration of 8-bit register name. **/
	enum Register {
		A, B, C, D, E, F, H, L
	}

	/** Enumeration of 16-bit extended register name. **/
	enum ExtendedRegister {
		BC, DE, HL, SP, PC
	}

	/**
	 * Returns a 8-bit register instance associated
	 * to the given <tt>name</tt>.
	 * 
	 * @param name Name of the register to get.
	 * @return {@link ByteRegister} instance if any.
	 * @throws IllegalArgumentException If the given <tt>name</tt> is <tt>null</tt>.
	 */
	ByteRegister getRegister(Register name);

	/**
	 * Returns a 16-bit extended register instance
	 * associated to the given <tt>name</tt>.
	 * 
	 * @param name Name of the register to get.
	 * @return {@link ShortRegister} instance if any.
	 * @throws IllegalArgumentException If the given <tt>name</tt> is <tt>null</tt>.
	 */
	IShortRegister getExtendedRegister(ExtendedRegister name);
	
	/**
	 * Returns the F flags register instance.
	 * 
	 * @return {@link FlagsRegister} registered as {@link Register#F}.
	 * @throws IllegalStateException If such register is not available.
	 */
	default FlagsRegister getFlagsRegister() {
		final ByteRegister register = getRegister(Register.F);
		if (register == null) {
			throw new IllegalStateException();
		}
		if (register instanceof FlagsRegister) {
			return (FlagsRegister) register;
		}
		throw new IllegalStateException();
	}

}
