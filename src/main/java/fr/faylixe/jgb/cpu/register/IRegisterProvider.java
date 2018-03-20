package fr.faylixe.jgb.cpu.register;

import fr.faylixe.jgb.cpu.register.IRegister.ExtendedRegisters;
import fr.faylixe.jgb.cpu.register.IRegister.Registers;

/**
 * 
 * @author fv
 */
public interface IRegisterProvider {

	/**
	 * 
	 * @param name
	 * @return
	 */
	IByteRegister getRegister(Registers name);

	/**
	 * 
	 * @param name
	 * @return
	 */
	IShortRegister getExtendedRegister(ExtendedRegisters name);
	
	/**
	 * 
	 * @return
	 */
	FlagsRegister getF();

}
