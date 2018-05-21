package fr.faylixe.yage.cpu.register;

/**
 * Specific byte register that provides sugar methods
 * for bit flag access. Such flags are :
 * 
 * - Zero flag (Z) located at bit 7.
 * - Subtraction flag (N) located at bit 6.
 * - Half carry flag (H) located at bit 5.
 * - Carry flag (C) located at bit 4.
 * 
 * @author fv
 */
public final class FlagsRegister extends ByteRegister {

	/** Sets the zero flag (Z) to 1. **/
	public void setZero() {
		set((byte) (get() | (1 << 7)));
	}
	
	/**
	 * Sets the zero flag (Z) according to
	 * the given boolean <tt>value</tt>.
	 * 
	 * @param value Value to set.
	 */
	public void setZero(final boolean value) {
		if (value) { 
			setZero();
		}
		else {
			resetZero();
		}
	}

	/** Resets the zero flag (Z) to 0. **/
	public void resetZero() {
		set((byte) (get() & ~(1 << 7)));
	}

	/** Sets the subtraction flag (N) to 1. **/
	public void setSubtraction() {
		set((byte) (get() | (1 << 6)));
	}

	/** Resets the subtraction flag (N) to 0. **/
	public void resetSubtraction() {
		set((byte) (get() & ~(1 << 6)));
	}

	/** Sets the half carry flag (H) to 1. **/
	public void setHalfCarry() {
		set((byte) (get() | (1 << 5)));
	}

	/**
	 * Sets the half carry flag (H) according to
	 * the given boolean <tt>value</tt>.
	 * 
	 * @param value Value to set.
	 */
	public void setHalfCarry(final boolean value) {
		if (value) {
			setHalfCarry();
		}
		else {
			resetHalfCarry();
		}
	}

	/** Resets the half carry flag (H) to 0. **/
	public void resetHalfCarry() {
		set((byte) (get() & ~(1 << 5)));
	}

	/** Sets the carry flag (C) to 1. **/
	public void setCarry() {
		set((byte) (get() | (1 << 4)));
	}
	
	/**
	 * Sets the carry flag (C) according to
	 * the given boolean <tt>value</tt>.
	 * 
	 * @param value Value to set.
	 */
	public void setCarry(final boolean value) {
		if (value) {
			setCarry();
		}
		else {
			resetCarry();
		}
	}

	/** Resets the carry flag (C) to 0. **/
	public void resetCarry() {
		set((byte) (get() & ~(1 << 4)));
	}

	/**
	 * Indicates if zero flag (Z) is settled.
	 * 
	 * @return <tt>true</tt> if zero flag is settled, <tt>false</tt> otherwise.
	 */
	public boolean isZero() {
		return ((get() >> 7) & 1) == 1;
	}

	/**
	 * Indicates if subtraction flag (N) is settled.
	 * 
	 * @return <tt>true</tt> if subtraction flag is settled, <tt>false</tt> otherwise.
	 */
	public boolean isSubtraction() {
		return ((get() >> 6) & 1) == 1;
	}

	/**
	 * Indicates if half carry flag (H) is settled.
	 * 
	 * @return <tt>true</tt> if half carry flag is settled, <tt>false</tt> otherwise.
	 */
	public boolean isHalfCarry() {
		return ((get() >> 5) & 1) == 1;
	}

	/**
	 * Indicates if carry flag (C) is settled.
	 * 
	 * @return <tt>true</tt> if carry flag is settled, <tt>false</tt> otherwise.
	 */
	public boolean isCarry() {
		return ((get() >> 4) & 1) == 1;
	}

}
