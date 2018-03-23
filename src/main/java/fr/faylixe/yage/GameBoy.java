package fr.faylixe.yage;

import fr.faylixe.yage.cartridge.Cartridge;
import fr.faylixe.yage.cpu.CPU;
import fr.faylixe.yage.memory.AddressBus;
import fr.faylixe.yage.memory.RAM;

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
	private final AddressBus addressBus;

	/**
	 * 
	 */
	private GameBoy() {
		this.addressBus = new AddressBus(16);
		this.cpu = new CPU(addressBus, null);
		this.ram = new RAM();
		this.ram.connect(addressBus);
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
