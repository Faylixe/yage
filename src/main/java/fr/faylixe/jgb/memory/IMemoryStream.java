package fr.faylixe.jgb.memory;

/**
 * Interface that represents a I/O operation
 * of a memory. Namely reading byte(s) from
 * memory for a given address, and writing
 * byte(s) to a memory into a given location.
 * 
 * @author fv
 */
public interface IMemoryStream {
	
	/**
	 * Read a single byte from the given <tt>address</tt>.
	 * 
	 * @param address Address to read byte from.
	 * @return Read byte from the given <tt>address</tt>.
	 * @throws IllegalAccessException If the target address is out of addressing space.
	 */
	byte readByte(int address) throws IllegalAccessException;

	/**
	 * Read <tt>length</tt> byte sequentially starting
	 * from the given <tt>address</tt>. Segmented memory that
	 * implements this interface should override this method
	 * is order to control block overflow if needed.
	 * 
	 * @param address Starting offset to read byte sequence from.
	 * @param length Number of byte to read.
	 * @return An array of read bytes.
	 * @throws IllegalAccessException If any read reach out of addressing space.
	 */
	default byte [] readBytes(final int address, final int length) throws IllegalAccessException {
		final byte [] buffer = new byte[length];
		for (int i = 0; i < length; i++) {
			buffer[i] = readByte(address + i);
		}
		return buffer;
	}

	/**
	 * Write a single byte at the given <tt>address</tt>.
	 * 
	 * @param value Value to write.
	 * @param address Address to write <tt>value</tt> to.
	 * @throws IllegalAccessException If the target address is out of addressing space.
	 */
	void writeByte(byte value, int address) throws IllegalAccessException;

	/**
	 * Write the given byte <tt>values</tt> starting at the given <tt>address</tt>.
	 * 
	 * @param values Bytes to write at the given <tt>adress</tt>.
	 * @param address Address to start writing values.
	 * @throws IllegalAccessException If any write reach out of addressing space.
	 */
	default void writeBytes(final byte[] values, int address) throws IllegalAccessException {
		for (int i = 0; i < values.length; i++) {
			writeByte(values[i], address + i);
		}
	}

}
