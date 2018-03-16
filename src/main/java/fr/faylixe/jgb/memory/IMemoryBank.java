package fr.faylixe.jgb.memory;

/**
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
