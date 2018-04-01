package fr.faylixe.yage.utils;

import org.mockito.stubbing.Answer;

/**
 * 
 * @author fv
 */
public final class MockitoUtils {

	/**
	 * Function that can throws any exception.
	 *
	 * @param <A> Input type of the function.
	 * @param <B> Output type of the function.
	 */
	@FunctionalInterface
	public interface ThrowingFunction<A, B> {

		/**
		 * Function application method.
		 * @param a Input to work with.
		 * @return Result of this function.
		 * @throws Throwable If any error occurs while processing input.
		 */
		B apply(A a) throws Exception;

	}
	
	/**
	 * Bifunction that can throws any exception.
	 *
	 * @param <A> First input type of the function.
	 * @param <B> Second input type of the function.
	 * @param <C> Output type of the function.
	 */
	@FunctionalInterface
	public interface ThrowingBifunction<A, B, C> {
		
		/**
		 * Function application method.
		 * 
		 * @param a First input to work with.
		 * @param b Second input to work with.
		 * @return Result of this function.
		 * @throws Throwable If any error occurs while processing inputs.
		 */
		C apply(A a, B b) throws Exception;

	}

	/**
	 * Consumer that can throws any exception.
	 *
	 * @param <A> Type this consumer accept.
	 */
	@FunctionalInterface
	public interface ThrowingConsumer<A> {

		/**
		 * Consumes the given input.
		 * 
		 * @param a Input to consume.
		 * @throws Throwable If any error occurs while processing input.
		 */
		void accept(A a) throws Exception;

	}

	/**
	 * Biconsumer that can throws any exception.
	 *
	 * @param <A> First type this consumer accept.
	 * @param <B> Second type this consumer accept.
	 */
	@FunctionalInterface
	public interface ThrowingBiconsumer<A, B> {

		/**
		 * Consumes the given input.
		 * 
		 * @param a First input to consume.
		 * @param b Second input to consume.
		 * @throws Throwable If any error occurs while processing inputs.
		 */
		void accept(A a, B b) throws Exception;

	}
	
	/**
	 * Supplier that can throws any exception.
	 * 
	 * @param <A> Type of data supplied.
	 */
	@FunctionalInterface
	public interface ThrowingSupplier<A> {
		
		/**
		 * Returns the target supplied data.
		 * 
		 * @return Required data.
		 * @throws Exception If any error occurs.
		 */
		A get() throws Exception;

	}

	/** Private constructor for avoiding instantiation. **/
	private MockitoUtils() {
		// Do nothing.
	}

	/**
	 * Factory method that creates a answer from the given function.
	 * 
	 * @param function {@link ThrowingFunction} to build answer from.
	 * @return Built answer.
	 */
	public static <A, B> Answer<B> toAnswer(final ThrowingFunction<A, B> function) {
		return invocation -> function.apply(invocation.getArgument(0));
	}
	
	/**
	 * Factory method that creates a answer from the given bifunction.
	 * 
	 * @param binfunction {@link ThrowingBiunction} to build answer from.
	 * @return Built answer.
	 */
	public static <A, B, C> Answer<C> toAnswer(final ThrowingBifunction<A, B, C> bifunction) {
		return invocation -> bifunction.apply(
				invocation.getArgument(0),
				invocation.getArgument(1)
		);
	}

	/**
	 * Factory method that creates a answer from the given consumer.
	 * 
	 * @param consumer {@link ThrowingConsumer} to build answer from.
	 * @return Built answer.
	 */
	public static <A> Answer<?> toAnswer(final ThrowingConsumer<A> consumer) {
		return invocation -> {
			consumer.accept(invocation.getArgument(0));
			return null;
		};
	}

	/**
	 * Factory method that creates a answer from the given biconsumer.
	 * 
	 * @param biconsumer {@link ThrowingBiconsumer} to build answer from.
	 * @return Built answer.
	 */
	public static <A, B> Answer<?> toAnswer(final ThrowingBiconsumer<A, B> biconsumer) {
		return invocation -> {
			biconsumer.accept(
					invocation.getArgument(0),
					invocation.getArgument(1)
			);
			return null;
		};
	}

	/**
	 * Factory method that creates a answer from the given supplier.
	 * 
	 * @param supplier {@link ThrowingSupplier} to build answer from.
	 * @return Built answer
	 */
	public static <A> Answer<A> toAnswer(final ThrowingSupplier<A> supplier) {
		return invocation -> supplier.get();
	}

}
