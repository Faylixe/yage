package fr.faylixe.yage.cpu.register;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static fr.faylixe.yage.cpu.register.IRegisterProvider.Register.*;
import static fr.faylixe.yage.cpu.register.IRegisterProvider.ExtendedRegister.*;

import java.util.function.Consumer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

/**
 * Test interface for components that aims to
 * deliver access to register. The test scenario
 * assumes following register states :
 * 
 * ---------------------------------
 * | A | F | B | C | D | E | H | L |
 * ---------------------------------
 * | 1 | 0 | 2 | 3 | 4 | 5 | 6 | 7 | 
 * ---------------------------------
 * 
 * Which implies following extended values :
 * 
 * --------------------------
 * | AF | BC  | DE   | HL   |
 * --------------------------
 * | 1  | 515 | 1029 | 1543 |
 * --------------------------
 * 
 * Plus following random values :
 * 
 * - SP -> 69
 * - PC -> 42
 * 
 * @author fv
 */
@TestInstance(Lifecycle.PER_CLASS)
public interface IRegisterProviderTest {

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
			assertEquals(1, provider.getRegister(A).get());
			assertEquals(2, provider.getRegister(B).get());
			assertEquals(3, provider.getRegister(C).get());
			assertEquals(4, provider.getRegister(D).get());
			assertEquals(5, provider.getRegister(E).get());
			assertEquals(0, provider.getRegister(F).get());
			assertEquals(6, provider.getRegister(H).get());
			assertEquals(7, provider.getRegister(L).get());
		});		
	}

	/** Test expected extended register values. **/
	@Test
	default void testExtendedRegisters() {
		performRegisterProviderTest(provider -> {
			assertEquals(256, provider.getExtendedRegister(AF).get());
			assertEquals(515, provider.getExtendedRegister(BC).get());
			assertEquals(1029, provider.getExtendedRegister(DE).get());
			assertEquals(1543, provider.getExtendedRegister(HL).get());
			assertEquals(69, provider.getExtendedRegister(SP).get());
			assertEquals(42, provider.getExtendedRegister(PC).get());
		});
	}

	/**
	 * Factory method for creating mock {@link IRegisterProvider} instance.
	 * 
	 * @return Built mock instance.
	 */
	static IRegisterProvider createMockRegisterProvider() {
		return new MockRegisterProviderBuilder()
				.addRegister(A, (byte) 1)
				.addRegister(B, (byte) 2)
				.addRegister(C, (byte) 3)
				.addRegister(D, (byte) 4)
				.addRegister(E, (byte) 5)
				.addRegister(F, (byte) 0)
				.addRegister(H, (byte) 6)
				.addRegister(L, (byte) 7)
				.addCompositeRegister(AF, A, F)
				.addCompositeRegister(BC, B, C)
				.addCompositeRegister(DE, D, E)
				.addCompositeRegister(HL, H, L)
				.addExtendedRegister(SP, (short) 69)
				.addExtendedRegister(PC, (short) 42)
				.build();
	}

}
