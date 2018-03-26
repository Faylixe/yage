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
	ShortRegister getExtendedRegister(ExtendedRegister name);
	
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

	/**
	 * Note : https://stackoverflow.com/questions/2188660/convert-short-to-byte-in-java
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
