package fr.faylixe.jgb.memory.bank;

import java.util.concurrent.atomic.AtomicInteger;

import fr.faylixe.jgb.memory.IMemoryStream;

/**
 * TODO : Consider instoring synchronization mecanism.
 * 
 * @author fv
 */
public final class SwitchableMemoryBank extends AbstractMemoryBank {

	/** Memory bank this switch handles. **/
	private final IMemoryStream [] banks;

	/** Pointer into the current activated bank. **/
	private final AtomicInteger current;

	/**
	 * Default constructor.
	 * 
	 * @param size
	 * @param offset
	 */
	public SwitchableMemoryBank(
			final int size,
			final int offset) {
		super(size, offset);
		this.banks = new IMemoryStream[0];
		this.current = new AtomicInteger();
	}

	/** {@inheritDoc} **/
	@Override
	public byte readByte(final int address) throws IllegalAccessException {
		final IMemoryStream bank = banks[current.get()];
		return bank.readByte(address);
	}

	/** {@inheritDoc} **/
	@Override
	public void writeByte(final byte value, final int address) throws IllegalAccessException {
		final IMemoryStream bank = banks[current.get()];
		bank.writeByte(value, address);
	}

}
