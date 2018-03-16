package fr.faylixe.jgb.memory.bank;

import java.util.BitSet;

/**
 * BitSet based memory bank implementation.
 * 
 * WARNING : Bitset manipulates bytes array using
 * little endian.
 * 
 * @author fv
 */
public final class BitSetMemoryBank extends AbstractMemoryBank {

	/** Delegate bitset that holds bank data. **/
	private final BitSet data;

	/**
	 * Default constructor.
	 * 
	 * @param size Size of this memory bank (in bytes).
	 * @param offset Starting address offset.
	 */
	public BitSetMemoryBank(final int size, final int offset) {
		super(size, offset);
		this.data = new BitSet(size);
	}

	/** {@inheritDoc} **/
	@Override
	public byte readByte(final int address) throws IllegalAccessException {
		return readBytes(address, 1)[0];
	}
	
	/** {@inheritDoc} **/
	@Override
	public byte[] readBytes(final int address, final int length) throws IllegalAccessException {
		verifyAddress(address);
		if (length > 1) {
			verifyAddress(address + length);
		}
		final int start = address - getOffset();
		try {
			return data
					.get(start, start + (8 * length))
					.toByteArray();
		}
		catch (final IndexOutOfBoundsException e) {
			throw new IllegalAccessException(e.getMessage());
		}
	}

	/** {@inheritDoc} **/
	@Override
	public void writeByte(final byte value, final int address) throws IllegalAccessException {
		writeBytes(new byte[]{value}, address);
	}
	
	/** {@inheritDoc} **/
	@Override
	public void writeBytes(final byte[] values, final int address) throws IllegalAccessException {
		// TODO : Ensures values is little endian or recode it.
		final BitSet bits = BitSet.valueOf(values);
		// TODO : Check if reading bit per bit is more faster.
		for (int i = 0; i < values.length * 8; i++) {
			data.set(address + i, bits.get(i));
		}
	}

}
