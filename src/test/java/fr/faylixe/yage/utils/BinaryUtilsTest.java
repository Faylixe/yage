package fr.faylixe.yage.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.BitSet;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

}
