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
	 * Default constructor.
	 */
	public AddressBus() {
		this.memory = new TreeMap<>();
	}

	/**
	 * 
	 * @param memoryBlock
	 */
	public void connect(final MemoryBlock memoryBlock) {
		if (memoryBlock == null) {
			throw new IllegalArgumentException();
		}
		// final int offset = memoryBlock.getOffset();
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

	/**
	 * TODO : Document. Ensure behavior.
	 * @param address
	 * @return
	 * @throws IllegalAccessException
	 */
	private MemoryBlock getMemoryBlock(final int address) throws IllegalAccessException {
		final Integer nearAddressableOffset = memory.floorKey(address);
		if (nearAddressableOffset == null) {
			throw new IllegalAccessException("Unreachable memory address");
		}
		return memory.get(nearAddressableOffset);
	}

	/** {@inheritDoc} **/
	@Override
	public byte readByte(final int address) throws IllegalAccessException {
		return getMemoryBlock(address).readByte(address);
	}

	/** {@inheritDoc} **/
	@Override
	public void writeByte(final byte value, final int address) throws IllegalAccessException {
		getMemoryBlock(address).writeByte(value, address);
	}

}
