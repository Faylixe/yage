package fr.faylixe.yage.utils;

import static java.lang.Byte.toUnsignedInt;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.BitSet;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.faylixe.yage.cpu.register.FlagsRegister;

/**
 * Unit test to validate bits layout and transformation
 * using signed primitive types.
 * 
 * @author fv
 */
public final class BinaryUtilsTest {
	
	/** Class logger. **/
	private static final Logger LOG = LoggerFactory.getLogger(BinaryUtilsTest.class);

	/** Test 1 1 1 1 0 0 0 0 layout into a byte. **/
	@Test
	public void testHalfLayout() {
		final BitSet layout = new BitSet(8);
		IntStream.range(0, 4).forEach(layout::set);
		final byte [] bytes = layout.toByteArray();
		assertEquals(1, bytes.length);
		LOG.info("11110000 as signed byte : {}", bytes[0]);
	}

	/** Test 1 0 1 0 1 0 1 0 layout into a byte. **/
	@Test
	public void testAlternateLayout() {
		final BitSet layout = new BitSet();
		for (int i = 0; i < 8; i++) {
			layout.set(i, i % 2 == 0);
		}
		final byte [] bytes = layout.toByteArray();
		assertEquals(1, bytes.length);
		LOG.info("10101010 as signed byte : {}", bytes[0]);
	}

	/** Test 1 0 1 0 1 0 1 1 layout into a byte. **/
	@Test
	public void testSignedLayout() {
		final BitSet layout = new BitSet();
		for (int i = 0; i < 8; i++) {
			layout.set(i, i % 2 == 0);
		}
		layout.set(7);
		final byte [] bytes = layout.toByteArray();
		assertEquals(1, bytes.length);
		LOG.info("10101011 as signed byte : {}", bytes[0]);
	}

	/** Test {@link BinaryUtils#compose(byte, byte)} method. **/
	@Test
	public void testComposeShort() {
		final short composed = BinaryUtils.compose(
				(byte) 2,
				(byte) 3);
		assertEquals(515, composed);
	}
	
	/** Test {@link BinaryUtils#decompose(short)} method. **/
	@Test
	public void testDecomposeShort() {
		final byte [] decomposed = BinaryUtils.decompose((short) 515);
		assertEquals(2, decomposed[0]);
		assertEquals(3, decomposed[1]);
	}

	@Test
	public void testValue() {
		final byte a = (byte) 150;
		final byte b = (byte) 150;
		final int result = toUnsignedInt(a) + toUnsignedInt(b);
		LOG.info(
				"150 + 150 = {}; H = {}; C = {}",
				new Object[] {
						Byte.valueOf((byte) (result & 0xFF)),
						Boolean.valueOf(((a & 0xF) + (b & 0xf) > 0xF)),
						Boolean.valueOf(result > (Math.pow(2, 8) - 1))
				}
		);
	}

}
