package fr.faylixe.yage.memory;

import static java.lang.Math.pow;

import java.util.NavigableMap;
import java.util.TreeMap;

import fr.faylixe.yage.memory.bank.IMemoryBank;

/**
 * Address bus class which is driven by the {@link IMemoryStream} contract.
 * Such address bus indexes memory banks through a navigable map which
 * provide a fast access for a given address.
 * 
 * @author fv
 */
public final class AddressBus implements IMemoryStream {

	/** Memory bank connected to this address bus. **/
	private final NavigableMap<Integer, IMemoryBank> memoryBanks;

	/** Size of this address bus (expressed in number of addresses available). **/
	private final int size;

	/**
	 * Default constructor. Turns the given bit size
	 * into address bound such as :
	 * 
	 * <code>bound = 2<sup>size</sup></code>.
	 * 
	 * @param size Size of this address bus (expressed in bit).
	 */
	public AddressBus(final int size) {
		this.size = (int) pow(2, size);
		this.memoryBanks = new TreeMap<>();
	}

	/**
	 * Ensures the given <tt>address</tt> do not collide with the
	 * bank denoted by the given <tt>boundOffset</tt>.
	 * 
	 * @param address Address to verify.
	 * @param boundOffset Index of the bank to check collision with.
	 */
	private void verifyAddressCollision(final int address, final Integer boundOffset) {
		if (boundOffset != null) {
			final IMemoryBank boundBank = memoryBanks.get(boundOffset);
			if (boundBank.isAddressCovered(address)) {
				throw new IllegalArgumentException();
			}
		}
	}

	/**
	 * Ensures the given <tt>address</tt> do not collide with any
	 * address this address bus can already handle. Aims to be used
	 * for bank connection only.
	 * 
	 * @param address Address to verify.
	 * @throws IllegalArgumentException if the given <tt>address</tt> is already covered.
	 */
	private void verifyAddressCollision(final int address) {
		if (address < 0 || address >= size) {
			throw new IllegalArgumentException();
		}
		verifyAddressCollision(address, memoryBanks.floorKey(address));
		verifyAddressCollision(address, memoryBanks.ceilingKey(address));
	}

	/**
	 * Connects the given <tt>memoryBank</tt> to this address bus.  
	 * Such bank can be connected if the bank offset is not covered
	 * already by another bank, as the (offset + size) address.
	 * 
	 * @param memoryBank Bank to connect.
	 * @throws IllegalArgumentException If given bank is <tt>null</tt> or conflicting with already connected.
	 */
	public void connect(final IMemoryBank memoryBank) {
		if (memoryBank == null) {
			throw new IllegalArgumentException();
		}
		verifyAddressCollision(memoryBank.getOffset());
		verifyAddressCollision(memoryBank.getOffset() + memoryBank.getSize());
		memoryBanks.put(memoryBank.getOffset(), memoryBank);
	}
	
	/**
	 * Disconnect the given <tt>memoryBank</tt>.
	 * 
	 * @param memoryBank Bank to disconnect.
	 * @throws IllegalArgumentException If given bank is <tt>null</tt> or not connected to this bus.
	 */
	public void disconnect(final IMemoryBank memoryBank) {
		if (memoryBank == null) {
			throw new IllegalArgumentException();
		}
		if (memoryBanks.remove(memoryBank.getOffset()) == null) {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Shortcut method for memory bank access for a given <tt>address</tt>.
	 * 
	 * @param address Address to get stream for.
	 * @return Target memory stream associated to the given <tt>address</tt>.
	 * @throws IllegalAccessException If the address is not covered by this bus, or not connected.
	 */
	private IMemoryStream getMemoryStream(final int address) throws IllegalAccessException {
		if (address < 0 || address >= size) {
			throw new IllegalAccessException("Address not covered by this bus");			
		}
		final Integer nearAddressableOffset = memoryBanks.floorKey(address);
		if (nearAddressableOffset == null) {
			throw new IllegalAccessException("Address not covered by this bus");
		}
		return memoryBanks.get(nearAddressableOffset);
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
