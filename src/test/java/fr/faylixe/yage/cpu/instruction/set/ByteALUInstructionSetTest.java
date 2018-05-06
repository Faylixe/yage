package fr.faylixe.yage.cpu.instruction.set;

import static fr.faylixe.yage.cpu.instruction.set.ByteALUInstructionSet.*;

import static java.util.Arrays.asList;

import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import java.util.Collection;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import fr.faylixe.yage.cpu.instruction.IInstructionSetTest;

/**
 * 
 * @author fv
 */
public final class ByteALUInstructionSetTest implements IInstructionSetTest {

	/**
	 * 
	 * @return
	 */
	@TestFactory
	public Collection<DynamicTest> testIncrement() {
		return asList(
			dynamicTest("INC (HL)", () -> performInstructionTest(0x34, 12, INC_HL, context -> {
				// TODO : Validate.
			}))
		);
	}
	
	/**
	 * 
	 * @return
	 */
	@TestFactory
	public Collection<DynamicTest> testDecrement() {
		return asList(
			dynamicTest("DEC HL", () -> {})
		);
	}

}
