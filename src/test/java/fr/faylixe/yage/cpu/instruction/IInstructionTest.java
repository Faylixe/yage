package fr.faylixe.yage.cpu.instruction;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.function.Consumer;

import fr.faylixe.yage.cpu.instruction.IExecutionContext;
import fr.faylixe.yage.cpu.instruction.IInstruction;

/**
 * Assuming following memory layout :
 * 
 * 0 -> 0
 * 1 -> 1
 * . -> .
 * n -> n
 * 
 * And following registers state :
 * 
 * A -> 0x01, F -> 0x00
 * B -> 0x02, C -> 0x03
 * D -> 0x04, E -> 0x05
 * H -> 0x06, L -> 0x07
 * SP -> 0x08
 * PC -> 0x09
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
		//instruction.execute(context);
	}

}
