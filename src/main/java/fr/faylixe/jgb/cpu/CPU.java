package fr.faylixe.jgb.cpu;

import fr.faylixe.jgb.cpu.register.DefaultRegister;
import fr.faylixe.jgb.cpu.register.FlagsRegister;
import fr.faylixe.jgb.cpu.register.Register;

/**
 * {@link CPU} implementation for SharpLR35902 model.
 * 
 * The timings assume a CPU clock frequency of 4.194304 MHz
 * 
 * @author fv
 */
public class CPU {

	/** **/
	private byte A;

	/** Flags register. **/
	private FlagsRegister F;

	/** **/
	private Register B;

	/** **/
	private Register C;

	/** **/
	private Register D;

	/** **/
	private Register E;

	/** **/
	private Register H;

	/** **/
	private Register L;

	// TODO : Add SP and PC

	/**
	 * 
	 */
	public CPU() {
	}

}
