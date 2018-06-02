package fr.faylixe.yage.cpu.register;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import fr.faylixe.yage.cpu.register.IRegisterProvider.ExtendedRegister;
import fr.faylixe.yage.cpu.register.IRegisterProvider.Register;

/**
 * Builder class that aims to build a mock {@link IRegisterProvider}
 * instance using concrete registers instance internally.
 * 
 * @author fv
 */
public final class MockRegisterProviderBuilder {

	/** Concrete 8-bit register instances used by built mock. **/
	private final Map<Register, ByteRegister> registers;

	/** Concrete 16-bit register instances used by built mock. **/
	private final Map<ExtendedRegister, IShortRegister> extendedRegisters;

	/**
	 * Default constructor.
	 */
	public MockRegisterProviderBuilder() {
		this.registers = new HashMap<>();
		this.extendedRegisters = new HashMap<>();
	}
	
	/**
	 * Factory method that creates an appropriate byte
	 * register instance for a given name. 
	 *
	 * @param name Name of the register instance to create.
	 * @return Created instance.
	 */
	private ByteRegister createByteRegister(final Register name) {
		if (name == Register.F) {
			return new FlagsRegister();
		}
		return new ByteRegister();
	}

	/**
	 * Adds an 8-bit register indexed by the given <tt>name</tt>
	 * settled with the given <tt>value</tt>.
	 * 
	 * @param name Name of the register to add.
	 * @param value Value of the register to add.
	 * @return Reference of this builder in order to chain call.
	 */
	public MockRegisterProviderBuilder addRegister(final Register name, final byte value) {
		if (name == null) {
			throw new IllegalArgumentException();
		}
		if (registers.containsKey(name)) {
			throw new IllegalStateException("Register " + name.toString() + " already exists");
		}
		final ByteRegister register = createByteRegister(name	);
		register.set(value);
		registers.put(name, register);
		return this;
	}
	
	/**
	 * Adds an 16-bit composite view indexed by the given <tt>name</tt>
	 * using existing 8-bit register denoted by <tt>mostSignificant</tt>
	 * and <tt>leastSignificant</tt>.
	 *
	 * @param name Name of the composite view to add.
	 * @param mostSignificant Name of the most significant register to use.
	 * @param leastSignificant Name of the least significant register to use.
	 * @return Reference of this builder in order to chain call.
	 */
	public MockRegisterProviderBuilder addCompositeRegister(
			final ExtendedRegister name,
			final Register mostSignificant,
			final Register leastSignificant) {
		if (name == null || mostSignificant == null || leastSignificant == null) {
			throw new IllegalArgumentException();
		}
		if (!registers.containsKey(mostSignificant) || ! registers.containsKey(leastSignificant)) {
			throw new IllegalStateException();
		}
		final IShortRegister register = new CompositeShortRegister(
				registers.get(mostSignificant),
				registers.get(leastSignificant));
		extendedRegisters.put(name, register);
		return this;
	}

	/**
	 * Adds an 16-bit register indexed by the given <tt>name</tt>
	 * settled with the given <tt>value</tt>.
	 * 
	 * @param name Name of the register to add.
	 * @param value Value of the register to add.
	 * @return Reference of this builder in order to chain call.
	 */
	public MockRegisterProviderBuilder addExtendedRegister(final ExtendedRegister name, final short value) {
		if (name == null) {
			throw new IllegalArgumentException();
		}
		if (extendedRegisters.containsKey(name)) {
			throw new IllegalStateException("Register " + name.toString() + " already exists");
		}
		final ShortRegister register = new ShortRegister();
		register.set(value);
		extendedRegisters.put(name, register);
		return this;
	}

	/**
	 * Builds the target mock instance using internal registered
	 * registers. After instance is built or configured, indexed
	 * register map are cleared in order to avoid register conflict.
	 * 
	 * @return Built instance.
	 */
	public IRegisterProvider build() {
		final IRegisterProvider provider = mock(IRegisterProvider.class);
		when(provider.getRegister(null)).thenThrow(IllegalArgumentException.class);
		when(provider.getExtendedRegister(null)).thenThrow(IllegalArgumentException.class);
		for (final Register name : registers.keySet()) {
			when(provider.getRegister(name)).thenReturn(registers.get(name));
		}
		for (final ExtendedRegister name : extendedRegisters.keySet()) {
			when(provider.getExtendedRegister(name)).thenReturn(extendedRegisters.get(name));
		}
		registers.clear();
		extendedRegisters.clear();
		return provider;
	}

}
