package fr.faylixe.yage.memory.bank;

import fr.faylixe.yage.memory.IMemoryStream;

/**
 * Generic interface that represents a memory bank.
 * Such bank have I/O operation provided thanks to
 * IMemoryStream interface.
 * 
 * @author fv
 */
public interface IMemoryBank extends IMemoryStream {

	/**
	 * Getter for the size of this memory block.
	 * 
	 * @return Size of this memory block (in bytes).
	 */
	int getSize();

	/**
	 * Getter for the starting address offset of this block.
	 * 
	 * @return Starting address offset of this block.
	 */
	int getOffset();

}
