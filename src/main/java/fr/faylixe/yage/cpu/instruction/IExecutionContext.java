package fr.faylixe.yage.cpu.instruction;

import fr.faylixe.yage.cpu.register.ByteRegister;
import fr.faylixe.yage.cpu.register.IRegisterProvider;
import fr.faylixe.yage.memory.IMemoryStream;

/**
 * TODO : Document inteface.
 * 
 * @author fv
 */
public interface IExecutionContext extends IRegisterProvider, IInstructionStream, IMemoryStream {

	/**
	 * 
	 * @param context
	 */
	static void normalizeFlagsRegister(final IExecutionContext context) {
		final ByteRegister register = context.getFlagsRegister();
		byte state = register.get();
		register.set((byte) (state & 0xF0));
	}

}
