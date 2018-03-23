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
	 * Name access getter.
	 * 
	 * @return {@link #toString()} representation of this object by default.
	 */
	default String getName() {
		return toString();
	}

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

	/**
	 * Indicates if the given <tt>address</tt> is covered
	 * by this memory bank.
	 * 
	 * @param address Address to check.
	 * @return <tt>true</tt> if the given <tt>address</tt> is covered, <tt>false</tt> otherwise.
	 */
	default boolean isAddressCovered(final int address) {
		return (address < getOffset() || address >= (getOffset() + getSize()));
	}

}
