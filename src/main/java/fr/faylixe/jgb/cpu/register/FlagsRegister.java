package fr.faylixe.jgb.cpu.register;

/**
 * TODO : Consider using symmetric flag setter.
 * 
 * @author fv
 */
public final class FlagsRegister extends ByteRegister {

	/**
	 * Indicates if zero flag (Z) is settled.
	 * 
	 * @return <tt>true</tt> if zero flag is settled, <tt>false</tt> otherwise.
	 */
	public boolean isZero() {
		return ((get() >> 7) & 1) == 1;
	}

	/**
	 * Indicates if substraction flag (N) is settled.
	 * 
	 * @return <tt>true</tt> if substraction flag is settled, <tt>false</tt> otherwise.
	 */
	public boolean isSubstraction() {
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
