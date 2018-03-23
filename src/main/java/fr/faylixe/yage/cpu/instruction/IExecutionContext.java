package fr.faylixe.yage.cpu.instruction;

import fr.faylixe.yage.cpu.register.IRegisterProvider;
import fr.faylixe.yage.cpu.register.IRegister.ExtendedRegisters;
import fr.faylixe.yage.cpu.register.IRegister.Registers;
import fr.faylixe.yage.memory.IMemoryStream;

/**
 * 
 * @author fv
 */
public interface IExecutionContext extends IRegisterProvider, IInstructionStream, IMemoryStream {

	/**
	 * 
	 * @param source
	 * @param destination
	 */
	default void loadFromRegister(final Registers source, final Registers destination) {
		getRegister(destination).set(getRegister(source).get());
	}
	
	/**
	 * 
	 * @param destination
	 * @param address
	 */
	default void loadFromAddress(final Registers destination, final ExtendedRegisters address) {
		//getRegister(destination).set(read(lowAddress, highAddress));
	}
	
	/**
	 * 
	 * @param source
	 * @param destination
	 * @param offset
	 */
	default void loadFromAddress(final Registers destination, final Registers source, final int offset) {
		//
	}

	/**
	 * 
	 * @param destination
	 */
	default void loadFromAddress(final Registers destination) {
		// TODO : Read two immediate and load.
	}
	
	/**
	 * 
	 * @param destination
	 */
	default void loadFromValue(final Registers destination) {
		// TODO : Read value from next immediate.
	}

	/**
	 * 
	 * @param source
	 * @param address
	 */
	default void putToAddress(final Registers source, final ExtendedRegisters address) {
		
	}

	/**
	 * 
	 * @param source
	 * @param destination
	 * @param offset
	 */
	default void putToAddress(final Registers source, final Registers destination, final int offset) {
		
	}

	/**
	 * 
	 * @param source
	 */
	default void putToAddress(final Registers source) {
		// TODO : Read two immediate and put
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
