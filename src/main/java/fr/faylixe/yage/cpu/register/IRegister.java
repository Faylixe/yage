package fr.faylixe.yage.cpu.register;

/**
 * 
 * @author fv
 */
public interface IRegister {

	/**
	 * 
	 * @author fv
	 */
	public enum Registers { A, B, C, D, E, F, H, L }
	
	public enum ExtendedRegisters { BC, DE, HL, SP, PC }

	/**
	 * 
	 * @return
	 */
	Registers getName();

}
