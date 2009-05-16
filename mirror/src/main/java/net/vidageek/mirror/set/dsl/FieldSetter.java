/**
 * 
 */
package net.vidageek.mirror.set.dsl;

/**
 * Interface for classes responsible for setting fields
 * 
 * @author jonasabreu
 */
public interface FieldSetter {

	/**
	 * Use this method to set a value on the wrapped field.
	 * 
	 * @param value
	 *            Value to be set.
	 */
	public void withValue(final Object value);

}
