package fr.faylixe.yage.cpu.register;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static fr.faylixe.yage.cpu.register.IRegisterProvider.Register.*;
import static fr.faylixe.yage.cpu.register.IRegisterProvider.ExtendedRegister.*;

import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import fr.faylixe.yage.cpu.register.IRegisterProvider.ExtendedRegister;
import fr.faylixe.yage.cpu.register.IRegisterProvider.Register;
import fr.faylixe.yage.utils.MockitoUtils.ThrowingConsumer;
import fr.faylixe.yage.utils.StreamUtils;

/**
 * Test interface for components that aims to deliver access to register.
 * The test scenario assumes following registers states defined by
 * {@link #EXPECTED_REGISTERS} and {@link #EXPECTED_EXTENDED_REGISTERS}
 * maps.
 * 
 * @author fv
 */
@TestInstance(Lifecycle.PER_CLASS)
public interface IRegisterProviderTest {

	/**
	 * Expected values for 8-bit registers.
	 * 
	 * ---------------------------------
	 * | A | F | B | C | D | E | H | L |
	 * ---------------------------------
	 * | 1 | 0 | 2 | 3 | 4 | 5 | 6 | 7 | 
	 * ---------------------------------
	 */
	static final Map<Register, Byte> EXPECTED_REGISTERS = Map.of(
			A, (byte) 1,
			B, (byte) 2,
			C, (byte) 3,
			D, (byte) 4,
			E, (byte) 5,
			F, (byte) 0,
			H, (byte) 6,
			L, (byte) 7
	);

	/**
	 * Expected values for 16-bit registers.
	 * 
	 * -------------------------------------
	 * | AF  | BC  | DE   | HL   | SP | PC |
	 * -------------------------------------
	 * | 256 | 515 | 1029 | 1543 | 69 | 42 |
	 * -------------------------------------
	 */
	static final Map<ExtendedRegister, Short> EXPECTED_EXTENDED_REGISTERS = Map.of(
			AF, (short) 256,
			BC, (short) 515,
			DE, (short) 1029,
			HL, (short) 1543,
			SP, (short) 69,
			PC, (short) 42
	);

	/**
	 * Tests values for 8-bit registers denoted
	 * by the given <tt>stream</tt>.
	 * 
	 * @param stream Stream of register to test.
	 * @param provider Provider to get register value from.
	 */
	private static void testRegisters(
			final Stream<Register> stream,
			final IRegisterProvider provider) {
		stream.forEach(register -> {
			assertEquals(
					(byte) EXPECTED_REGISTERS.get(register),
					provider.getRegister(register).get()
			);
		});
	}
	
	/**
	 * Tests values for 16-bit registers denoted
	 * by the given <tt>stream</tt>.
	 * 
	 * @param stream Stream of register to test.
	 * @param provider Provider to get register value from.
	 */
	private static void testExtendedRegisters(
			final Stream<ExtendedRegister> stream,
			final IRegisterProvider provider) {
		stream.forEach(register -> {
			assertEquals(
					(short) EXPECTED_EXTENDED_REGISTERS.get(register),
					provider.getExtendedRegister(register).get()
			);
		});
	}

	/**
	 * Static factory that creates a set of assertions for registers state.
	 * 
	 * @return Created register provider test.
	 */
	static <T extends IRegisterProvider> ThrowingConsumer<T> createRegistersTest() {
		return provider -> {
			testRegisters(Stream.of(Register.values()), provider);
			testExtendedRegisters(Stream.of(ExtendedRegister.values()), provider);
		};
	}

	/**
	 * Static factory that creates a set of assertions for register state.
	 * Created test assumes that one register has a different value than expected.
	 * 
	 * @param distinct Register that is supposed to have a distinct value.
	 * @param expected Expected value for the distinct register.
	 * @return Created register provider test.
	 */
	static <T extends IRegisterProvider> ThrowingConsumer<T> createRegistersTest(
			final Register distinct,
			final byte expected) {
		return provider -> {
			assertEquals(expected, provider.getRegister(distinct).get());
			testRegisters(StreamUtils.of(Register.class, distinct), provider);
		};
	}

	/**
	 * Factory method that creates a target testing instance.
	 * 
	 * @return A test instance.
	 */
	IRegisterProvider getTestRegisterProvider();

	/**
	 * Retrieves a test register provider instance and
	 * pass it to the given test. Does not aims to
	 * be overriden.
	 *
	 * @param test Effective provider test to perform.
	 */
	default void performRegisterProviderTest(final Consumer<IRegisterProvider> test) {
		final IRegisterProvider provider = getTestRegisterProvider();
		assertNotNull(provider);
		test.accept(provider);
	}

	/** Test illegal register access. **/
	@Test
	default void testNullRegisterAccess() {
		performRegisterProviderTest(provider -> {
			assertThrows(IllegalArgumentException.class, () -> provider.getRegister(null));
			assertThrows(IllegalArgumentException.class, () -> provider.getExtendedRegister(null));
		});
	}

	/** Test expected register values. **/
	@Test
	default void testRegisters() {
		performRegisterProviderTest(provider -> {
			testRegisters(Stream.of(Register.values()), provider);
		});		
	}

	/** Test expected extended register values. **/
	@Test
	default void testExtendedRegisters() {
		performRegisterProviderTest(provider -> {
			testExtendedRegisters(Stream.of(ExtendedRegister.values()), provider);
		});
	}

	/**
	 * Factory method for creating mock {@link IRegisterProvider} instance.
	 * 
	 * @return Built mock instance.
	 */
	static IRegisterProvider createMockRegisterProvider() {
		final MockRegisterProviderBuilder builder = new MockRegisterProviderBuilder();
		for (final Register register : Register.values()) {
			builder.addRegister(register, EXPECTED_REGISTERS.get(register));
		}
		return builder
				.addCompositeRegister(AF, A, F)
				.addCompositeRegister(BC, B, C)
				.addCompositeRegister(DE, D, E)
				.addCompositeRegister(HL, H, L)
				.addExtendedRegister(SP, (short) 69)
				.addExtendedRegister(PC, (short) 42)
				.build();
	}

}
