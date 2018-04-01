package fr.faylixe.yage.cpu.instruction;

import fr.faylixe.yage.utils.BinaryUtils;

/**
 * Stream in which CPU instruction are pushed.
 * Should be thread safe as it is aims to be used concurrently by the CPU thread.
 * 
 * @author fv
 */
public interface IInstructionStream {
	
	/**
	 * Reads next byte from this stream.
	 * 
	 * @return Next immediate byte.
	 * @throws IllegalAccessException If any error occurs while reading next byte.
	 */
	byte nextByte() throws IllegalAccessException;

	/**
	 * Reads next two bytes from this stream and
	 * returns them as a composed short value.
	 * 
	 * @return Next two immediate value as a short.
	 * @throws IllegalAccessException If any error occurs while reading next byte.
	 */
	default short nextShort() throws IllegalAccessException {
		final byte leastSignificant = nextByte();
		final byte mostSignificant = nextByte();
		return BinaryUtils.compose(mostSignificant, leastSignificant);
	}

	/**
	 * Puts the given byte <tt>value</tt> into this stream.
	 * 
	 * @param value Value to put.
	 */
	void sendByte(byte value);

}
