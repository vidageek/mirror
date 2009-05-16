/**
 * 
 */
package net.vidageek.mirror.set.dsl;

import java.lang.reflect.Field;

import net.vidageek.mirror.set.FieldSetterByField;
import net.vidageek.mirror.set.FieldSetterByName;

/**
 * @author donizetti
 *
 */
public interface SetterHandler {
	
	/**
	 * Invoke this method to set a field by its name.
	 * 
	 * @param fieldName
	 *            name of the field to be set.
	 * @return An object responsible for setting a field.
	 * @throws IllegalArgumentException
	 *             if fieldName is null or empty.
	 * @see FieldSetterByName
	 */
	public FieldSetter field(final String fieldName);

	/**
	 * Invoke this method to set a field by its Field instance.
	 * 
	 * @param field
	 *            Field to be set.
	 * @return An object responsible for setting a field.
	 * @throws IllegalArgumentException
	 *             if field is null.
	 * @see FieldSetterByField
	 */
	public FieldSetter field(final Field field) ;

}
