package fr.faylixe.jgb.memory;

/**
 * 
 * @author fv
 */
public interface IMemoryStream {
	
	/**
	 * 
	 * @param address
	 * @return
	 * @throws IllegalAccessException
	 */
	byte readByte(int address) throws IllegalAccessException;

	
	/**
	 * 
	 * @param value
	 * @param address
	 * @throws IllegalAccessException
	 */
	void writeByte(byte value, int address) throws IllegalAccessException;

}
