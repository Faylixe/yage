package fr.faylixe.yage.memory.bank;

import static org.junit.jupiter.api.Assertions.fail;

/**
 * {@link IMemoryBankTest} extension that handles
 * expected write operation and only required to
 * create an empty test instance when overriden.
 * 
 * @author fv
 */
public interface IMemoryBankWriteTest extends IMemoryBankTest {

	/**
	 * Factory method that creates a target {@link IMemoryBank}
	 * instance that is writable.
	 * 
	 * @param size Size of the bank to create.
	 * @param offset Offset of the bank to create.
	 * @return Created instance.
	 */
	IMemoryBank createTestMemoryBank(int size, int offset);

	/** {@inheritDoc} **/
	@Override
	default IMemoryBank getTestMemoryBank() {
		final IMemoryBank bank = createTestMemoryBank(TEST_SIZE, TEST_OFFSET);
		try {
			bank.writeByte((byte) 85, TEST_OFFSET);
			bank.writeByte((byte) 15, TEST_OFFSET + 1);
		}
		catch (final IllegalAccessException e) {
			fail(e);
		}
		return bank;
	}

}
