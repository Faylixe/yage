package fr.faylixe.yage.cpu.instruction.set;

import org.junit.jupiter.api.Test;

import fr.faylixe.yage.cpu.instruction.IInstructionTest;
import fr.faylixe.yage.cpu.instruction.set.ByteLoadInstructionSet;

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
