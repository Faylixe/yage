package fr.faylixe.yage.utils;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * 
 * @author fv
 */
public final class StreamUtils {

	/** Private constructor for avoiding instantiation. **/
	private StreamUtils() {
		// Do nothing.
	}

	/**
	 * 
	 * @param source
	 * @return
	 */
	public static <T> Predicate<T> not(final Predicate<T> source) {
		return e -> !source.test(e);
	}

	/**
	 * 
	 * @param source
	 * @param filtered
	 * @return
	 */
	@SafeVarargs
	public static <T extends Enum<T>> Stream<T> of(final Class<T> source, final T ... filtered) {
		final List<T> ignored = Arrays.asList(filtered);
		return EnumSet.allOf(source)
			.stream()
			.filter(not(ignored::contains));
	}

}
