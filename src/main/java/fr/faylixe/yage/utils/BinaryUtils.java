package fr.faylixe.yage.utils;

/**
 * Static method for common binary operation.
 * 
 * @author fv
 */
public final class BinaryUtils {

	/** Private constructor for avoiding instantiation. **/
	private BinaryUtils() {
		// Do nothing.
	}

	/**
	 * Composes a 16-bit vector from two given bytes.
	 * 
	 * @param mostSignificant Most significant byte to use.
	 * @param leastSignificant Least significant byte to use.
	 * @return Composed value as short.
	 * @see https://stackoverflow.com/questions/2188660/convert-short-to-byte-in-java
	 */
	public static short compose(
			final byte mostSignificant,
			final byte leastSignificant) {
		return (short) (((mostSignificant & 0xFF) << 8) | (leastSignificant & 0xFF));
	}

	/**
	 * Decomposes the given 16-bit <tt>value</tt> into two bytes.
	 * 
	 * @param value Value to decompose.
	 * @return Array of byte (most significant first).
	 */
	public static byte[] decompose(final short value) {
		return null;
	}

}
