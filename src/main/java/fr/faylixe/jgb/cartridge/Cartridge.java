package fr.faylixe.jgb.cartridge;

import fr.faylixe.jgb.memory.AddressBus;
import fr.faylixe.jgb.memory.IConnectable;
import fr.faylixe.jgb.memory.bank.ArrayMemoryBank;
import fr.faylixe.jgb.memory.bank.ReadOnlyMemoryBank;

/**
 * 
 * @author fv
 */
public final class Cartridge implements IConnectable {

	/** External RAM switchable bank. **/
	private final ArrayMemoryBank eram;

	/** ROM fixed bank 00. **/
	private final ArrayMemoryBank rom0;

	/** ROM switchable bank number **/
	private final ArrayMemoryBank rom1;

	/**
	 * Default constructor.
	 */
	protected Cartridge() {
		this.eram = new ArrayMemoryBank(8 * 1024, 0xA000);
		this.rom0 = null;//new ReadOnlyMemoryBank(16 * 1024, 0x0000);
		this.rom1 = null;//new ReadOnlyMemoryBank(16 * 1024, 0x4000);
	}

	/** {@inheritDoc} **/
	@Override
	public void connect(final AddressBus addressBus) {
		addressBus.connect(eram); 
		addressBus.connect(rom0);
		addressBus.connect(rom1);
	}

}
