package fr.faylixe.yage.memory.bank;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import fr.faylixe.yage.memory.IMemoryBank;
import fr.faylixe.yage.memory.IMemoryStream;

/**
 * TODO : Class javadoc
 * TODO : Make it thread safe.
 * 
 * @author fv
 */
public final class SwitchableMemoryBank extends AbstractMemoryBank {

	/** Memory bank this switch handles. **/
	private final IMemoryStream [] banks;

	/** Pointer into the current activated bank. **/
	private final AtomicInteger current;

	/**
	 * SwitchableMemoryBank builder class.
	 * 
	 * @author fv
	 */
	public static final class Builder {

		/** Size of the target built bank. **/
		private final int size;
	
		/** Offset of the target built bank. **/
		private final int offset;

		/** List of memory bank that will define the built switchable bank. **/
		private final List<IMemoryBank> banks;

		/**
		 * Default constructor.
		 * 
		 * @param size Size of the target built bank.
		 * @param offset Offset of the target built bank.
		 */
		private Builder(final int size, final int offset) {
			this.size = size;
			this.offset = offset;
			this.banks = new ArrayList<IMemoryBank>();
		}
		
		/**
		 * Adds the given <tt>bank</tt> to the bank pool.
		 * 
		 * @param bank Bank to add to the pool.
		 * @return Reference of this builder.
		 * @throws IllegalArgumentException If the given bank is not valid (null or not matching).
		 */
		public Builder addMemoryBank(final IMemoryBank bank) {
			if (bank == null) {
				throw new IllegalArgumentException("Null bank");
			}
			if (bank.getSize() != size) {
				throw new IllegalArgumentException("Size not matching");
			}
			if (bank.getOffset() != offset) {
				throw new IllegalArgumentException("Offset not matching");
			}
			banks.add(bank);
			return this;
		}

		/**
		 * Builds and returns a valid SwitchableMemoryBank.
		 * 
		 * @return Built instance.
		 * @throws IllegalStateException If no bank has been added to this builder.
		 */
		public SwitchableMemoryBank build() {
			if (banks.isEmpty()) {
				throw new IllegalStateException("No bank available");
			}
			final IMemoryBank [] switchable = new IMemoryBank[banks.size()];
			banks.toArray(switchable);
			return new SwitchableMemoryBank(switchable, size, offset);
		}

	}

	/**
	 * Factory method for creating builder instance.
	 * 
	 * @param size Size of the target built bank.
	 * @param offset Offset of the target built bank.
	 * @return Built builder instance.
	 */
	public static Builder builder(final int size, final int offset) {
		return new Builder(size, offset);
	}

	/**
	 * Default constructor.
	 * 
	 * @param banks Memory bank this switch handles.
	 * @param size Size of this memory block (in bytes).
	 * @param offset Starting address offset.
	 */
	private SwitchableMemoryBank(
			final IMemoryBank [] banks,
			final int size,
			final int offset) {
		super(size, offset);
		this.banks = banks;
		this.current = new AtomicInteger();
	}

	/**
	 * Bank size getter.
	 * 
	 * @return Number of bank this switchable bank dispose.
	 */
	public int getBankSize() {
		return banks.length;
	}
	
	/**
	 * Switch the current active bank using <tt>target</tt> index.
	 * 
	 * @param target Target index of the bank to set as current.
	 * @throws IllegalArgumentException If given target is not valid.
	 */
	public void switchBank(final int target) {
		if (target < 0 || target >= banks.length) {
			throw new IllegalArgumentException();
		}
		current.set(target);
	}

	/** {@inheritDoc} **/
	@Override
	public byte readByte(final int address) throws IllegalAccessException {
		final IMemoryStream stream = banks[current.get()];
		return stream.readByte(address);
	}

	/** {@inheritDoc} **/
	@Override
	public byte[] readBytes(final int address, final int length) throws IllegalAccessException {
		final IMemoryStream stream = banks[current.get()];
		return stream.readBytes(address, length);
	}

	/** {@inheritDoc} **/
	@Override
	public void writeByte(final byte value, final int address) throws IllegalAccessException {
		final IMemoryStream stream = banks[current.get()];
		stream.writeByte(value, address);
	}

	/** {@inheritDoc} **/
	@Override
	public void writeBytes(final byte[] values, final int address) throws IllegalAccessException {
		final IMemoryStream stream = banks[current.get()];
		stream.writeBytes(values, address);
	}

}
