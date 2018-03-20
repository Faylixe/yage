package fr.faylixe.jgb.cpu.instruction;

import fr.faylixe.jgb.cpu.register.IRegister.Registers;
import fr.faylixe.jgb.cpu.register.IRegisterProvider;
import fr.faylixe.jgb.memory.IMemoryStream;

/**
 * 
 * @author fv
 */
public interface IExecutionContext extends IRegisterProvider, IInstructionStream, IMemoryStream {

	default void setRegister(
			final Registers destination,
			final Registers lowAddress,
			final Registers highAddress) {
		getRegister(destination).set(read(lowAddress, highAddress));
	}

	/**
	 * 
	 * @param low
	 * @param high
	 * @return
	 */
	default byte read(final Registers low, final Registers high) {
		return 0;
	}
	
	/**
	 * 
	 * @return
	 */
	default byte readFromImmediates() {
		return 0;
	}

	/**
	 * 
	 * @return
	 */
	default byte nextImmediate() {
		return nextByte();
	}

	/**
	 * 
	 * @return
	 */
	default short nextImmediates() {
		final byte low = nextByte();
		final byte high = nextByte();
		return 0;
	}

}
