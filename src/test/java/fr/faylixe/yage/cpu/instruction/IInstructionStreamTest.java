package fr.faylixe.yage.cpu.instruction;

import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
public interface IInstructionStreamTest {

	/**
	 * Factory method that creates a target testing instance.
	 * 
	 * @return A test instance.
	 */
	IInstructionStream getTestInstructionStream();

}
