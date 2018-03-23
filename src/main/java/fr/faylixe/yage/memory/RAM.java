package fr.faylixe.yage.memory;

import fr.faylixe.yage.memory.bank.ArrayMemoryBank;

/**
 *
 * - 8000-9FFF   8KB Video RAM (VRAM) (switchable bank 0-1 in CGB Mode)
 * - C000-CFFF   4KB Work RAM Bank 0 (WRAM)
 * - D000-DFFF   4KB Work RAM Bank 1 (WRAM)  (switchable bank 1-7 in CGB Mode)
 * - E000-FDFF   Same as C000-DDFF (ECHO)    (typically not used)
 * - FE00-FE9F   Sprite Attribute Table (OAM)
 * - FF00-FF7F   I/O Ports
 * - FF80-FFFE   High RAM (HRAM)
 * - FFFF        Interrupt Enable Register
 * @author fv
 */
public class RAM implements IConnectable {

	/** First Work RAM block. **/
	private final ArrayMemoryBank wram0;

	/** Second Work RAM block. **/
	private final ArrayMemoryBank wram1;

	/** Video RAM block. **/
	private final ArrayMemoryBank vram;

	/**
	 * Default constructor.
	 * Initializes associated memory blocks.
	 */
	public RAM() {
		this.vram = new ArrayMemoryBank(8 * 1024, 0x8000);
		this.wram0 = new ArrayMemoryBank(4 * 1024, 0xC000);
		this.wram1 = new ArrayMemoryBank(4 * 1024, 0xD000);
	}
	
	/** {@inheritDoc} **/
	@Override
	public void connect(final AddressBus addressBus) {
		addressBus.connect(vram);
		addressBus.connect(wram0);
		addressBus.connect(wram1); 
	}

}
