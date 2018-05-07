package fr.faylixe.yage.cpu.instruction;

import static java.lang.Byte.toUnsignedInt;
import static java.lang.Short.toUnsignedInt;
import static java.lang.Math.pow;

import fr.faylixe.yage.cpu.register.FlagsRegister;
import fr.faylixe.yage.cpu.register.IRegisterProvider;
import fr.faylixe.yage.memory.IMemoryStream;

/**
 * TODO : Document inteface.
 * 
 * @author fv
 */
public interface IExecutionContext extends IRegisterProvider, IInstructionStream, IMemoryStream {

	/**
	 * Performs a safe sum between to given byte value, assuming
	 * that those values are considered as unsigned and though
	 * safely casted into integer to perform flags check and
	 * updates flag register accordingly.
	 * 
	 * @param a First byte to sum up.
	 * @param b Second byte to sum up.
	 * @return Result of the sum as a unsigned byte.
	 */
	default byte add(final byte a, final byte b) {
		final Integer result = toUnsignedInt(a) + toUnsignedInt(b);
		final FlagsRegister flags = getFlagsRegister();
		flags.resetSubtraction();
		flags.setZero(result == 0);
		flags.setHalfCarry(); // TODO : Map to condition.
		flags.setCarry(result >= pow(2, 8)); // TODO : Check for bound.
		// TODO : Validate casting from unsigned int to equivalent byte value.	
		return result.byteValue();
	}

	/**
	 * Performs a safe sum between to given short value, assuming
	 * that those values are considered as unsigned and though
	 * safely casted into integer to perform flags check and
	 * updates flag register accordingly.
	 * 
	 * @param a First short to sum up.
	 * @param b Second short to sum up.
	 * @return Result of the sum as a unsigned short.
	 */
	default short add(final short a, final short b) {
		final Integer result = toUnsignedInt(a) + toUnsignedInt(b);
		final FlagsRegister flags = getFlagsRegister();
		flags.resetSubtraction();
		flags.setZero(result == 0);
		flags.setHalfCarry(); // TODO : Map to condition.
		flags.setCarry(result >= pow(2, 16)); // TODO : Check for bound.
		// TODO : Validate casting from unsigned int to equivalent short value.	
		return result.shortValue();
	}

}
