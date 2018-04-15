package fr.faylixe.yage.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

/**
 * Provides tests for stream utils methods.
 * 
 * @author fv
 */
public final class StreamUtilsTest {

	/** Enum for testing. **/
	public enum TestEnum {
		A, B, C, D
	}

	/** Test enum stream factory method. **/
	@Test
	public void testEnumStream() {
		final List<TestEnum> filtered = StreamUtils
			.of(TestEnum.class, TestEnum.A)
			.collect(Collectors.toList());
		assertEquals(3, filtered.size());
		assertTrue(filtered.contains(TestEnum.B));
		assertTrue(filtered.contains(TestEnum.C));
		assertTrue(filtered.contains(TestEnum.D));
	}

}
