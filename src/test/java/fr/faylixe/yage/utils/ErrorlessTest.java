package fr.faylixe.yage.utils;

import static org.junit.jupiter.api.Assertions.fail;

/**
 * 
 * @author fv
 */
@FunctionalInterface
public interface ErrorlessTest {

	/**
	 * 
	 * @throws Exception
	 */
	void run() throws Exception;

	/**
	 * 
	 * @param test
	 */
	static void run(final ErrorlessTest test) {
		try {
			test.run();
		}
		catch (final Exception e) {
			fail(e);
		}
	}

}
