package fr.faylixe.jgb.memory;

import java.util.NavigableMap;
import java.util.TreeMap;

import fr.faylixe.jgb.memory.bank.IMemoryBank;

/**
 * - 16-bit address bus.
 * 
 * @author fv
 */
public final class AddressBus implements IMemoryStream {

	/** **/
	private final NavigableMap<Integer, IMemoryBank> memory;

	/**
	 * Default constructor.
	 */
	public AddressBus() {
		this.memory = new TreeMap<>();
	}

	/**
	 * 
	 * @param memoryBank
	 */
	public void connect(final IMemoryBank memoryBank) {
		if (memoryBank == null) {
			throw new IllegalArgumentException();
		}
		// final int offset = memoryBlock.getOffset();
		// TODO : Check for address conflict.
		memory.put(memoryBank.getOffset(), memoryBank);
	}
	
	/**
	 * 
	 * @param memoryBank
	 */
	public void disconnect(final IMemoryBank memoryBank) {
		if (memoryBank == null) {
			throw new IllegalArgumentException();
		}
		if (memory.remove(memoryBank.getOffset()) == null) {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * TODO : Document. Ensure behavior.
	 * @param address
	 * @return
	 * @throws IllegalAccessException
	 */
	private IMemoryStream getMemoryStream(final int address) throws IllegalAccessException {
		final Integer nearAddressableOffset = memory.floorKey(address);
		if (nearAddressableOffset == null) {
			throw new IllegalAccessException("Unreachable memory address");
		}
		// TODO : Check for more coverage ?
		return memory.get(nearAddressableOffset);
	}

	/** {@inheritDoc} **/
	@Override
	public byte readByte(final int address) throws IllegalAccessException {
		return getMemoryStream(address).readByte(address);
	}

	/** {@inheritDoc} **/
	@Override
	public byte[] readBytes(final int address, final int length) throws IllegalAccessException {
		return getMemoryStream(address).readBytes(address, length);
	}

	/** {@inheritDoc} **/
	@Override
	public void writeByte(final byte value, final int address) throws IllegalAccessException {
		getMemoryStream(address).writeByte(value, address);
	}

	/** {@inheritDoc} **/
	@Override
	public void writeBytes(final byte[] values, final int address) throws IllegalAccessException {
		getMemoryStream(address).writeBytes(values, address);
	}

}
