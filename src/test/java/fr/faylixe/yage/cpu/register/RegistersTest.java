package fr.faylixe.yage.cpu.register;

import static fr.faylixe.yage.cpu.register.IRegisterProvider.Register.*;
import static fr.faylixe.yage.cpu.register.IRegisterProvider.ExtendedRegister.*;

/**
 * Simple test that aims to validate register implementations
 * behavior through {@link IRegisterProvider} interface.
 * 
 * @author fv
 */
public final class RegistersTest implements IRegisterProviderTest {

	/** {@inheritDoc} **/
	@Override
	public IRegisterProvider getTestRegisterProvider() {
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
