package fr.faylixe.yage.cpu;

import fr.faylixe.yage.cpu.instruction.IExecutionContext;

/**
 * 
 * @author fv
 */
public interface IDataSource {

	/**
	 * Reads the byte value from this data source using
	 * the given <tt>context</tt>.
	 * 
	 * @param context Context to read data from.
	 * @return Read byte data.
	 * @throws IllegalAccessException If any error occurs while reading value.
	 */
	byte read(IExecutionContext context) throws IllegalAccessException;

	/**
	 * Writes the given byte <tt>value</tt> to this
	 * data source using the given <tt>context</tt>.
	 * 
	 * @param context Context to write data from;
	 * @param value Value to write.
	 * @throws IllegalAccessException If any error occurs while writing value.
	 */
	void write(IExecutionContext context, byte value) throws IllegalAccessException;

	/** Data source instance that read data from immediate. **/
	static IDataSource IMMEDIATE = new IDataSource() {
		
		/** {@inheritDoc} **/
		@Override
		public void write(final IExecutionContext context, final byte value) throws IllegalAccessException {
			throw new IllegalAccessException();
		}
		
		/** {@inheritDoc} **/
		@Override
		public byte read(final IExecutionContext context) throws IllegalAccessException {
			return context.nextByte();
		}

	};
}
