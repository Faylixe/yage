package fr.faylixe.yage.memory;

import fr.faylixe.yage.memory.bank.ArrayMemoryBank;

/**
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
public class MemoryMap implements IConnectable {

	/** **/
	private IMemoryBank vram;

	/** **/
	private IMemoryBank wram0;

	/** **/
	private IMemoryBank wram1;

	/** **/
	private IMemoryBank echo;

	/** **/
	private IMemoryBank oam;

	/** **/
	private IMemoryBank io;

	/** **/
	private IMemoryBank hram;

	/** **/
	private IMemoryBank ier;

	/**
	 * 
	 */
	private MemoryMap() {
		this.vram = new ArrayMemoryBank(8 * 1024, 0x8000);
		this.wram0 = new ArrayMemoryBank(8 * 1024, 0x8000);
		this.wram1 = new ArrayMemoryBank(8 * 1024, 0x8000);
		this.hram = new ArrayMemoryBank(8 * 1024, 0x8000);
	}

	/** {@inheritDoc} **/
	@Override
	public void connect(final AddressBus addressBus) {
		// TODO Auto-generated method stub
		
	}

	/** {@inheritDoc} **/
	@Override
	public void disconnect(final AddressBus addressBus) {
		// TODO Auto-generated method stub
		
	}


	public static void create() {
		// 8000-9FFF   8KB Video RAM (VRAM)
		new ArrayMemoryBank(8 * 1024, 0x8000);
	}

}
