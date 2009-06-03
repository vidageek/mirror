/**
 * 
 */
package net.vidageek.mirror.set;

import java.lang.reflect.Field;

import net.vidageek.mirror.provider.ReflectionProvider;
import net.vidageek.mirror.set.dsl.FieldSetter;
import net.vidageek.mirror.set.dsl.SetterHandler;

/**
 * This class is responsible for providing field setting features.
 * 
 * @author jonasabreu
 */
public final class DefaultSetterHandler implements SetterHandler {

	private final Object target;

	private final Class<?> clazz;

	private final ReflectionProvider provider;

	public DefaultSetterHandler(final ReflectionProvider provider, final Object target) {
		if (target == null) {
			throw new IllegalArgumentException("target cannot be null");
		}
		this.provider = provider;
		this.target = target;
		clazz = target.getClass();
	}

	public DefaultSetterHandler(final ReflectionProvider provider, final Class<?> clazz) {
		if (clazz == null) {
			throw new IllegalArgumentException("clazz cannot be null");
		}
		this.provider = provider;
		this.clazz = clazz;
		target = null;
	}

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
	public FieldSetter field(final String fieldName) {
		if (fieldName == null || fieldName.trim().length() == 0) {
			throw new IllegalArgumentException("fieldName cannot be null or empty.");
		}
		return new FieldSetterByName(provider, fieldName, target, clazz);
	}

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
	public FieldSetter field(final Field field) {
		if (field == null) {
			throw new IllegalArgumentException("parameter field cannot be null.");
		}
		return new FieldSetterByField(provider, target, clazz, field);
	}
}
