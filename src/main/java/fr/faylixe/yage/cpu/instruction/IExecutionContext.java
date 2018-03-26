package fr.faylixe.yage.cpu.instruction;

import fr.faylixe.yage.cpu.register.IRegisterProvider;
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
	default void loadFromRegister(final Register source, final Register destination) {
		getRegister(destination).set(getRegister(source).get());
	}
	
	/**
	 * 
	 * @param destination
	 * @param address
	 */
	default void loadFromAddress(final Register destination, final ExtendedRegister address) {
		//getRegister(destination).set(read(lowAddress, highAddress));
	}
	
	/**
	 * 
	 * @param source
	 * @param destination
	 * @param offset
	 */
	default void loadFromAddress(final Register destination, final Register source, final int offset) {
		//
	}

	/**
	 * 
	 * @param destination
	 */
	default void loadFromAddress(final Register destination) {
		// TODO : Read two immediate and load.
	}
	
	/**
	 * 
	 * @param destination
	 */
	default void loadFromValue(final Register destination) {
		// TODO : Read value from next immediate.
	}

	/**
	 * 
	 * @param source
	 * @param address
	 */
	default void putToAddress(final Register source, final ExtendedRegister address) {
		
	}

	/**
	 * 
	 * @param source
	 * @param destination
	 * @param offset
	 */
	default void putToAddress(final Register source, final Register destination, final int offset) {
		
	}

	/**
	 * 
	 * @param source
	 */
	default void putToAddress(final Register source) {
		// TODO : Read two immediate and put
	}

	/**
	 * 
	 * @param low
	 * @param high
	 * @return
	 */
	default byte read(final Register low, final Register high) {
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
