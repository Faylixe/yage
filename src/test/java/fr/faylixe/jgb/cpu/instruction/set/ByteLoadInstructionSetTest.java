package fr.faylixe.jgb.cpu.instruction.set;

import org.junit.jupiter.api.Test;

import fr.faylixe.jgb.cpu.instruction.IInstructionTest;

/**
 * 
 * @author fv
 */
public final class ByteLoadInstructionSetTest implements IInstructionTest {

	/** **/
	@Test
	public void testLoadBToA() {
		performInstructionTest(0x78, 4, ByteLoadInstructionSet.LOAD_B_TO_A, context -> {
			// TODO : Ensure call here.
			// TODO : Ensure value here.
		});
	}

}
