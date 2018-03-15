package fr.faylixe.jgb.memory;

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
public class RAM {

	/** **/
	private final WorkRAM wram0;

	/** **/
	private final WorkRAM wram1;

	/** **/
	private final VideoRAM vram;

	/** **/
	public RAM() {
		this.vram = new VideoRAM();
		this.wram0 = new WorkRAM((short) 0xC000);
		this.wram1 = new WorkRAM((short) 0xD000);
	}
	
	/**
	 * 
	 * @param addressBus
	 */
	public void connect(final AddressBus addressBus) {
		addressBus.connect(vram); 	// 8000-9FFF
		addressBus.connect(wram0); 	// C000-CFFF
		addressBus.connect(wram1); 	// D000-DFFF
	}

}
