package fr.faylixe.jgb.memory;

import java.util.NavigableMap;
import java.util.TreeMap;

/**
 * - 16-bit address bus.
 * 
 * @author fv
 */
public final class AddressBus implements IMemoryStream {

	/** **/
	private final NavigableMap<Integer, MemoryBlock> memory;

	/**
	 * 
	 */
	public AddressBus() {
		this.memory = new TreeMap<>();
	}

	/**
	 * 
	 * @param address
	 * @return
	 */
	private MemoryBlock getAddressable(final int address) {
		final Integer nearAddressableOffset = memory.floorKey(address);
		if (nearAddressableOffset == null) {
			// TODO : Return optional ?
		}
		return memory.get(nearAddressableOffset);
	}

	/**
	 * 
	 * @param memoryBlock
	 */
	public void connect(final MemoryBlock memoryBlock) {
		if (memoryBlock == null) {
			throw new IllegalArgumentException();
		}
		final int offset = memoryBlock.getOffset();
		final MemoryBlock nearest = getAddressable(offset);
		// TODO : Check for address conflict.
		memory.put(memoryBlock.getOffset(), memoryBlock);
	}
	
	/**
	 * 
	 * @param memoryBlock
	 */
	public void disconnect(final MemoryBlock memoryBlock) {
		if (memoryBlock == null) {
			throw new IllegalArgumentException();
		}
		if (memory.remove(memoryBlock.getOffset()) == null) {
			throw new IllegalArgumentException();
		}
	}

	/** {@inheritDoc} **/
	@Override
	public byte readByte(final int address) {
		// TODO : Read memory from address bus.
		return 0;
	}

	/** {@inheritDoc} **/
	@Override
	public void writeByte(final byte value, final int address) {
		// TODO : Check feasability.
		// TODO : write.
	}

}
