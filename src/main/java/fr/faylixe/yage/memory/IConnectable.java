package fr.faylixe.yage.memory;

/**
 * A component that aims to be connected to an address bus.
 * 
 * @author fv
 */
public interface IConnectable {

	/**
	 * Connect it self to the given <tt>addressBus</tt>.
	 * 
	 * @param addressBus Address bus to connect to.
	 */
	void connect(AddressBus addressBus);

	/**
	 * 
	 * @param addressBus
	 */
	void disconnect(AddressBus addressBus);

}
