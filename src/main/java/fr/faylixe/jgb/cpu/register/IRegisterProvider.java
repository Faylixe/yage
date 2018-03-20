package fr.faylixe.jgb.cpu.register;

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
	IShortRegister getExtendedRegister(Registers name);
	
	/**
	 * 
	 * @return
	 */
	FlagsRegister getF();

	/**
	 * 
	 * @param source
	 * @param destination
	 */
	default void copyRegister(final Registers source, final Registers destination) {
		getRegister(destination).set(getRegister(source).get());
	}

}
