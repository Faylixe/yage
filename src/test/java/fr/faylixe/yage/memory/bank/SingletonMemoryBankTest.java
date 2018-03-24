package fr.faylixe.yage.memory.bank;

import static fr.faylixe.yage.memory.IMemoryStreamTest.TEST_OFFSET;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.faylixe.yage.memory.IMemoryBank;
import fr.faylixe.yage.utils.ErrorlessTest;

/**
 * Unit test case for the singleton memory bank.
 * 
 * @author fv
 */
public final class SingletonMemoryBankTest {

	/** Target singleton bank. **/
	private IMemoryBank bank;

	/** Initialization method. **/
	@BeforeEach
	public void setup() {
		bank = new SingletonMemoryBank(TEST_OFFSET);
		ErrorlessTest.run(() -> bank.writeByte((byte) 69, TEST_OFFSET));
	}

	/** Test reading valid address **/
	@Test
	public void testAllowedReading() {
		ErrorlessTest.run(() -> assertEquals((byte) 69, bank.readByte(TEST_OFFSET)));
	}
	
	/** Test reading valid addresses. **/
	@Test
	public void testAllowedReadings() {
		ErrorlessTest.run(() -> {
			final byte [] values = bank.readBytes(TEST_OFFSET, 1);
			assertNotNull(values);
			assertEquals(1, values.length);
			assertEquals((byte) 69, values[0]);
		});
	}

	/** Test reading wrong address. **/
	@Test
	public void testUnallowedReading() {
		assertThrows(IllegalAccessException.class, () -> bank.readByte(0));
		assertThrows(IllegalAccessException.class, () -> bank.readByte(TEST_OFFSET - 1));
		assertThrows(IllegalAccessException.class, () -> bank.readByte(TEST_OFFSET + 1));
	}
	
	/** Test reading wrong addresses. **/
	@Test
	public void testUnallowedReadings() {
		assertThrows(IllegalAccessException.class, () -> bank.readBytes(0, 1));
		assertThrows(IllegalAccessException.class, () -> bank.readBytes(TEST_OFFSET - 1, 1));
		assertThrows(IllegalAccessException.class, () -> bank.readBytes(TEST_OFFSET + 1, 1));
		assertThrows(IllegalAccessException.class, () -> bank.readBytes(TEST_OFFSET, 2));
	}

	/** Test writing valid address. **/
	@Test
	public void testAllowedWriting() {
		ErrorlessTest.run(() -> bank.writeByte((byte) 42, TEST_OFFSET));
	}

	/** Test writing valid addresses. **/
	@Test
	public void testAllowedWritings() {
		ErrorlessTest.run(() -> bank.writeBytes(new byte[]{ 42 }, TEST_OFFSET));
	}

	/** Test writing wrong address. **/
	@Test
	public void testUnallowedWriting() {
		assertThrows(IllegalAccessException.class, () -> bank.writeByte((byte) 42, 0));
		assertThrows(IllegalAccessException.class, () -> bank.writeByte((byte) 42, TEST_OFFSET - 1));
		assertThrows(IllegalAccessException.class, () -> bank.writeByte((byte) 42, TEST_OFFSET + 1));
	}

	/** Test writing wrong addresses. **/
	@Test
	public void testUnallowedWritings() {
		assertThrows(IllegalArgumentException.class, () -> bank.writeBytes(null, TEST_OFFSET));
		assertThrows(IllegalArgumentException.class, () -> bank.writeBytes(new byte[] {}, TEST_OFFSET));
	}

}
