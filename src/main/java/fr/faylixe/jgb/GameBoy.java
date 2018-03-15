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
	 */
	private GameBoy() {
		this.addressBus = new AddressBus();
		this.cpu = new CPU(addressBus);
		this.ram = new RAM();
		this.ram.connect(addressBus);
		this.lcd = new LCD();
	}

	/**
	 * 
	 * @param cartridge
	 */
	public void insertCartridge(final Cartridge cartridge) {
		cartridge.connect(addressBus);
		// TODO : Load data from cartridge ?
	}
	
	/**
	 * 
	 */
	public void removeCartridge() {
		
		addressBus.disconnect(null);
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
