package fr.faylixe.jgb.cpu.register;

/**
 * 
 * @author fv
 */
public interface IRegister {

	/**
	 * 
	 * @author fv
	 */
	public enum Registers {
		A, B, C, D, E, F, H, L,
		BC, DE, HL, SP, PC
	}

	/**
	 * 
	 * @return
	 */
	Registers getName();

}
