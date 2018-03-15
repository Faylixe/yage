package fr.faylixe.jgb.cpu;

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
	 * 
	 * @param value
	 */
	void sendByte(byte value);

}
