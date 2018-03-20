package fr.faylixe.jgb.cpu.instruction;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.function.Consumer;

/**
 * 
 * @author fv
 */
public interface IInstructionTest {

	/**
	 * 
	 * @param expectedOpcode
	 * @param expectedCycle
	 * @param instruction
	 * @param test
	 */
	default void performInstructionTest(
			final int expectedOpcode,
			final int expectedCycle,
			final IInstruction instruction,
			final Consumer<IExecutionContext> test) {
		assertEquals(expectedOpcode, instruction.getOpcode());
		assertEquals(expectedCycle, instruction.getCycle());
		final IExecutionContext context = null; // TODO : Create mock here.
		instruction.execute(context);
	}

}
