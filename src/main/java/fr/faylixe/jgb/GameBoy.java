package fr.faylixe.jgb;

import fr.faylixe.jgb.cpu.CPU;
import fr.faylixe.jgb.display.LCD;
import fr.faylixe.jgb.memory.AddressBus;
import fr.faylixe.jgb.memory.RAM;
import fr.faylixe.jgb.storage.Cartridge;

/**
 * 
 * @author fv
 */
public class GameBoy {

	/** **/
	private final CPU cpu;

	/** **/
	private final RAM ram;

	/** **/
	private final LCD lcd;

	/** **/
	private final AddressBus addressBus;

	/**
	 * 
	 * @param cpu
	 * @param lcd
	 */
	private GameBoy(
			final CPU cpu,
			final LCD lcd) {
		this.addressBus = new AddressBus();
		this.ram = new RAM();
		this.cpu = cpu;
		this.lcd = lcd;
	}

	/**
	 * 
	 * @param cartridge
	 */
	public void insertCartridge(final Cartridge cartridge) {
	}
	
	/**
	 * 
	 */
	public void removeCartridge() {
		
	}

	/**
	 * 
	 * @return
	 */
	public static GameBoy create() {
		// TODO : Create motherboard.
		return null;
	}
}
