package fr.faylixe.jgb.memory;

import java.util.NavigableMap;
import java.util.TreeMap;

/**
 * - 16-bit address bus.
 * 
 * @author fv
 */
public final class AddressBus {

	/** **/
	private final NavigableMap<Short, IAddressable> memories;

	/**
	 * 
	 */
	public AddressBus() {
		this.memories = new TreeMap<>();
	}

	/**
	 * 
	 * @param address
	 * @return
	 */
	private IAddressable getAddressable(final short address) {
		final Short nearAddressableOffset = memories.floorKey(address);
		if (nearAddressableOffset == null) {
			// TODO : Return optional ?
		}
		return memories.get(nearAddressableOffset);
	}

	/**
	 * 
	 * @param offset
	 * @param addressable
	 */
	protected void connect(final IAddressable addressable) {
		if (addressable == null) {
			throw new IllegalArgumentException();
		}
		final short offset = addressable.getOffset();
		final IAddressable nearest = getAddressable(offset);
		// TODO : Check for address conflict.
		memories.put(addressable.getOffset(), addressable);
	}
	
	/**
	 * 
	 * @param addressable
	 */
	public void disconnect(final IAddressable addressable) {
		if (addressable == null) {
			throw new IllegalArgumentException();
		}
		if (memories.remove(addressable.getOffset()) == null) {
			throw new IllegalArgumentException();
		}
	}
	
}
