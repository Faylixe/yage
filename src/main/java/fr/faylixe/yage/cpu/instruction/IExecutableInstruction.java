package fr.faylixe.yage.cpu.instruction;

import fr.faylixe.yage.cpu.register.IRegisterProvider.ExtendedRegister;
import fr.faylixe.yage.cpu.register.IRegisterProvider.Register;

/**
 * Functional interface that represents an executable
 * processor instruction. Such instruction can be
 * executed by using an {@link IExecutionContext} instance
 * that provides all data stream (memory, registers, etc ...).
 * 
 * /!\ TODO : Ensure address casting.
 * 
 * @author fv
 */
@FunctionalInterface
public interface IExecutableInstruction {

	/**
	 * Executes this instruction using the given <tt>context</tt>.
	 * 
	 * @param context Context to use.
	 * @throws IllegalAccessException If any error occurs during context usage.
	 */
	void execute(IExecutionContext context) throws IllegalAccessException;

	/**
	 * Composite factory method that creates a new instruction from this
	 * one and the given <tt>next</tt> immediate instruction to execute.
	 * 
	 * @param next Next instruction to execute after this one.
	 * @return Composite created instruction.
	 */
	default IExecutableInstruction then(final IExecutableInstruction next) {
		return context -> {
			execute(context);
			next.execute(context);
		};
	}

	/** No operation static instance. **/
	static IExecutableInstruction NOP = context -> {};

	/**
	 * Builds an instruction that load value from <tt>source</tt>
	 * register to the given <tt>destination</tt> register.
	 * 
	 * @param source Register to copy value from.
	 * @param destination Register to copy value into.
	 * @return Built instruction.
	 */
	static IExecutableInstruction copy(final Register source, final Register destination) {
		return context -> {
			final byte value = context.getRegister(source).get();
			context.getRegister(destination).set(value);
		};
	}

	/**
	 * Builds an instruction that load value from <tt>source</tt>
	 * register to the given <tt>destination</tt> register.
	 * 
	 * @param source Register to copy value from.
	 * @param destination Register to copy value into.
	 * @return Built instruction.
	 */
	static IExecutableInstruction copy(final ExtendedRegister source, final ExtendedRegister destination) {
		return context -> {
			final short value = context.getExtendedRegister(source).get();
			context.getExtendedRegister(destination).set(value);
		};
	}
	/**
	 * Builds an instruction that loads the value from the memory
	 * location denoted by <tt>offset</tt> padded by the <tt>source</tt>
	 * register value into <tt>destination</tt> register.
	 * 
	 * @param source Register that gives the source address complement.
	 * @param offset Memory address offset to compute source location from.
	 * @param destination Destination register to copy value to.
	 * @return Built instruction.
	 */
	static IExecutableInstruction copyFromAddress(final Register source, final int offset, final Register destination) {
		return context -> {
			// TODO : Ensure casting (signed -> unsigned).
			final int address = offset + context.getRegister(source).get();
			final byte value = context.readByte(address);
			context.getRegister(destination).set(value);
		};
	}

	/**
	 * Builds an instruction that loads the value at the address
	 * held by the given <tt>source</tt> register into the given
	 * <tt>destination</tt> register.
	 * 
	 * @param source Register to read target memory address from.
	 * @param destination Register to load read value into.
	 * @return Built instruction.
	 */
	static IExecutableInstruction copyFromAddress(final ExtendedRegister source, final Register destination) {
		return context -> {
			// TODO : Ensure casting (signed -> unsigned).
			final int address = context.getExtendedRegister(source).get();
			final byte value = context.readByte(address);
			context.getRegister(destination).set(value);
		};
	}

	/**
	 * Builds an instruction that retrieves a memory address from
	 * next two immediate and load it associated value into the
	 * given <tt>destination</tt> register.
	 * 
	 * @param destination Register to load read value into.
	 * @return Built instruction.
	 */
	static IExecutableInstruction copyFromAddress(final Register destination) {
		return context -> {
			// TODO : Ensure casting (signed -> unsigned).
			final int address = context.nextShort();
			final byte value = context.readByte(address);
			context.getRegister(destination).set(value);
		};
	}

	/**
	 * Builds an instruction that loads the value at the address
	 * held by the given <tt>destination</tt> register from the given
	 * <tt>source</tt> register.
	 * 
	 * @param source Register to get value to write from.
	 * @return Built instruction.
	 */
	static IExecutableInstruction copyToAddress(final Register source) {
		return context -> {
			// TODO : Ensure casting (signed -> unsigned).
			final int address = context.nextShort();
			final byte value = context.getRegister(source).get();
			context.writeByte(value, address);
		};
	}

	/**
	 * Builds an instruction that loads the value at the address
	 * held by the given <tt>destination</tt> register from the given
	 * <tt>source</tt> register.
	 * 
	 * @param source Register to get value to write from.
	 * @param destination Register to read target memory address from.
	 * @return Built instruction.
	 */
	static IExecutableInstruction copyToAddress(final Register source, final ExtendedRegister destination) {
		return context -> {
			// TODO : Ensure casting (signed -> unsigned).
			final int address = context.getExtendedRegister(destination).get();
			final byte value = context.getRegister(source).get();
			context.writeByte(value, address);
		};
	}

	/**
	 * Builds an instruction that loads the value at the address
	 * held by the given <tt>destination</tt> register from the given
	 * <tt>source</tt> register.
	 * 
	 * @param source Register to get value to write from.
	 * @param destination Register to read target memory address from.
	 * @return Built instruction.
	 */
	static IExecutableInstruction copyToAddress(final ExtendedRegister source, final ExtendedRegister destination) {
		return context -> {
			// TODO : Ensure casting (signed -> unsigned).
			final int address = context.getExtendedRegister(destination).get();
			final byte[] values = context.getExtendedRegister(source).getBytes();
			context.writeBytes(values, address);
		};
	}

	/**
	 * Builds an instruction that loads the value from <tt>source</tt>
	 * register at the memory address denoted by the <tt>destination</tt>
	 * register padded by the given <tt>offset</tt>.
	 * 
	 * @param source Source register to copy value from.
	 * @param destination Register that gives the target address complement.
	 * @param offset Memory address offset to compute target location from.
	 * @return Built instruction.
	 */
	static IExecutableInstruction copyToAddress(final Register source, final Register destination, final int offset) {
		return context -> {
			// TODO : Ensure casting (signed -> unsigned).
			final int address = offset + context.getRegister(destination).get();
			final byte value = context.getRegister(source).get();
			context.writeByte(value, address);
		};
	}

	/**
	 * Builds an instruction that write to a memory address 
	 * denoted by the given register the next immediate value.
	 * 
	 * @param destination Register to get target address from.
	 * @return Built instruction.
	 */
	static IExecutableInstruction copyNextValue(final ExtendedRegister destination) {
		return context -> {
			// TODO : Ensure casting (signed -> unsigned).
			final int address = context.getExtendedRegister(destination).get();
			final byte value = context.nextByte();
			context.writeByte(value, address);
		};
	}
	
	/**
	 * 
	 * @param destination
	 * @return
	 */
	static IExecutableInstruction copyNextShortValue(final ExtendedRegister destination) {
		return context -> {
			final short value =	context.nextShort();
			context.getExtendedRegister(destination).set(value);
		};
	}

	/**
	 * Builds an instruction that loads value read from next
	 * immediate into the given <tt>destination</tt> register.
	 * 
	 * @param destination Register to load read value into.
	 * @return Built instruction.
	 */
	static IExecutableInstruction copyNextValue(final Register destination) {
		return context -> {
			final byte value = context.nextByte();
			context.getRegister(destination).set(value);
		};
	}
	
	/**
	 * 
	 * @param source
	 * @param destination
	 * @return
	 */
	static IExecutableInstruction add(final Register source, final Register destination) {
		return context -> {
			final byte a = context.getRegister(source).get();
			final byte b = context.getRegister(destination).get();
			final byte result = context.add(a, b);
			context.getRegister(destination).set(result);
		};
	}

}
