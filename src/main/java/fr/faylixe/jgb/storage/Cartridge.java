package fr.faylixe.jgb.storage;

import fr.faylixe.jgb.memory.AddressBus;
import fr.faylixe.jgb.memory.IConnectable;
import fr.faylixe.jgb.memory.MemoryBlock;

/**
 * 
 * @author fv
 */
public final class Cartridge implements IConnectable {

	/** External RAM switchable bank. **/
	private final MemoryBlock eram;

	/** ROM fixed bank 00. **/
	private final MemoryBlock rom0;

	/** ROM switchable bank number **/
	private final MemoryBlock rom1;

	/**
	 * Default constructor.
	 */
	protected Cartridge() {
		this.eram = new MemoryBlock(8 * 1024, 0xA000);
		this.rom0 = new MemoryBlock(16 * 1024, 0x0000);
		this.rom1 = new MemoryBlock(16 * 1024, 0x4000);
	}

	/** {@inheritDoc} **/
	@Override
	public void connect(final AddressBus addressBus) {
		addressBus.connect(eram); 
		addressBus.connect(rom0);
		addressBus.connect(rom1);
	}

}
