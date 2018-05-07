package fr.faylixe.yage.cpu.instruction;

import static java.lang.Byte.toUnsignedInt;
import static java.lang.Math.pow;

import fr.faylixe.yage.cpu.register.FlagsRegister;
import fr.faylixe.yage.cpu.register.IRegisterProvider;
import fr.faylixe.yage.memory.IMemoryStream;

/**
 * 
 * @author fv
 */
public interface IExecutionContext extends IRegisterProvider, IInstructionStream, IMemoryStream {

	/**
	 * 
	 * @param a
	 * @param b
	 */
	default byte add(final byte a, final Byte b) {
		final Integer result = toUnsignedInt(a) + toUnsignedInt(b);
		final FlagsRegister flags = getFlagsRegister();
		flags.resetSubtraction();
		flags.setZero(result == 0);
		flags.setHalfCarry(); // TODO : Map to condition.
		flags.setCarry(result >= pow(2, 16));
		// TODO : Validate casting from unsigned int to equivalent byte value.	
		return result.byteValue();
	}

}
