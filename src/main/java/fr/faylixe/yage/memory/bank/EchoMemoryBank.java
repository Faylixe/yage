package fr.faylixe.yage.memory.bank;

import fr.faylixe.yage.memory.IMemoryBank;

/**
 * Simple view of a given memory bank indexed to another starting
 * offset. Such bank is only a proxy to the echoed bank, allowing
 * to perform I/O operation to the target echoed bank using another
 * address space.
 * 
 * @author fv
 */
public final class EchoMemoryBank extends AbstractMemoryBank {

	/** Delegate memory bank echoed. **/
	private final IMemoryBank echoed;

	/**
	 * Default constructor.
	 * 
	 * @param echoed Delegate memory bank echoed.
	 * @param offset Starting offset of the echo.
	 */
	public EchoMemoryBank(final IMemoryBank echoed, final int offset) {
		super(echoed.getSize(), offset);
		this.echoed = echoed;
	}

	/**
	 * Normalizes the given address in order to
	 * have it pointing out the corresponding
	 * echoed memory bank address.
	 *  
	 * @param address Address to normalize.
	 * @return Normalized address relative to the echo.
	 */
	protected int normalizeAddress(final int address) {
		return echoed.getOffset() + (address - getOffset());
	}

	/** {@inheritDoc} **/
	@Override
	public byte readByte(final int address) throws IllegalAccessException {
		verifyAddress(address);
		final int normalized = normalizeAddress(address);
		return echoed.readByte(normalized);
	}

	/** {@inheritDoc} **/
	@Override
	public byte[] readBytes(final int address, final int length) throws IllegalAccessException {
		verifyAddress(address);
		verifyAddress(address + length - 1);
		final int normalized = normalizeAddress(address);
		return echoed.readBytes(normalized, length);
	}

	/** {@inheritDoc} **/
	@Override
	public void writeByte(final byte value, final int address) throws IllegalAccessException {
		verifyAddress(address);
		final int normalized = normalizeAddress(address);
		echoed.writeByte(value, normalized);
	}

	/** {@inheritDoc} **/
	@Override
	public void writeBytes(final byte[] values, final int address) throws IllegalAccessException {
		verifyAddress(address);
		verifyAddress(address + values.length - 1);
		final int normalized = normalizeAddress(address);
		echoed.writeBytes(values, normalized);
	}

}
