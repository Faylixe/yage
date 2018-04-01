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
	 * 
	 * @return
	 */
	byte nextByte();

	/**
	 * Reads next two bytes from this stream and
	 * returns them as a composed short value.
	 * 
	 * @return Next two immediate value as a short.
	 */
	default short nextShort() {
		final byte leastSignificant = nextByte();
		final byte mostSignificant = nextByte();
		return BinaryUtils.compose(mostSignificant, leastSignificant);
	}

	/**
	 * 
	 * @param value
	 */
	void sendByte(byte value);

}
