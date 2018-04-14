package fr.faylixe.yage.cpu.instruction;

import fr.faylixe.yage.cpu.register.IRegisterProvider;
import fr.faylixe.yage.memory.IMemoryStream;

/**
 * 
 * @author fv
 */
public interface IExecutionContext extends IRegisterProvider, IInstructionStream, IMemoryStream {

	/**
	 * Copies value from <tt>source</tt> register to the
	 * given <tt>destination</tt> register.
	 * 
	 * @param source Register to copy value from.
	 * @param destination Register to copy value into.
	 */
	static IExecutableInstruction load(final Register source, final Register destination) {
		return context -> {
			final byte value = context.getRegister(source).get();
			context.getRegister(destination).set(value);
		};
	}
	
	/**
	 * Loads the value at the address held by the given <tt>source</tt>
	 * register into the given <tt>destination</tt> register.
	 * 
	 * @param source Register to read target memory address from.
	 * @param destination Register to load read value into.
	 */
	static IExecutableInstruction load(final ExtendedRegister source, final Register destination) {
		return context -> {
			final int address = context.getExtendedRegister(source).get();
			final byte value = context.readByte(address);
			context.getRegister(destination).set(value);
		};
	}

	/**
	 * Retrieves a memory address from next two immediates and load it
	 * associated value into the given <tt>destination</tt> register.
	 * 
	 * @param destination Register to load read value into.
	 * @throws IllegalAccessException If any error occurs while reading target address.
	 */
	default void loadFromImmediateAddress(final Register destination)
			throws IllegalAccessException {
		final int address = nextShort();
		final byte value = readByte(address);
		getRegister(destination).set(value);
	}

	/**
	 * Loads value read from next immediate into the given
	 * <tt>destination</tt> register.
	 * 
	 * @param destination Register to load read value into.
	 * @throws IllegalAccessException If any error occurs while reading next immediate.
	 */
	default void loadFromImmediateValue(final Register destination)
			throws IllegalAccessException {
		final byte value = nextByte();
		getRegister(destination).set(value);
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

}
