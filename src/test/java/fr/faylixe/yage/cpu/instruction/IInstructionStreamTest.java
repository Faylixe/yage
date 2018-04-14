package fr.faylixe.yage.cpu.instruction;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

/**
 * TODO : Write instruction test spec.
 * 
 * @author fv
 */
@TestInstance(Lifecycle.PER_CLASS)
public interface IInstructionStreamTest {

	/**
	 * Factory method that creates a target testing instance.
	 * 
	 * @return A test instance.
	 */
	IInstructionStream getTestInstructionStream();

	/**
	 * Static factory method that creates a mock instruction
	 * stream instance.
	 * 
	 * @return Created instance.
	 */
	static IInstructionStream createMockInstructionStream() {
		final IInstructionStream stream = mock(IInstructionStream.class);
		try {
			when(stream.nextByte()).thenReturn((byte) 42);
			when(stream.nextShort()).thenCallRealMethod();
		}
		catch (final IllegalAccessException e) {
			fail(e);
		}
		return stream;
	}

}
