/**
 * 
 */
package net.vidageek.mirror.reflect;

import java.lang.reflect.Field;

import net.vidageek.mirror.Preconditions;
import net.vidageek.mirror.provider.ReflectionProvider;
import net.vidageek.mirror.reflect.dsl.FieldReflector;

/**
 * This class is responsible for reflecting a field.
 * 
 * @author jonasabreu
 */
public final class DefaultFieldReflector implements FieldReflector {

	private final String fieldName;

	private final ReflectionProvider provider;

	public DefaultFieldReflector(final ReflectionProvider provider, final String fieldName) {
		Preconditions.checkArgument(fieldName != null && fieldName.trim().length() > 0, "fieldName cannot be null or empty");
		this.provider = provider;
		this.fieldName = fieldName;
	}

	@SuppressWarnings("unchecked")
	public Field onClass(final Class clazz) {
		Preconditions.checkArgument(clazz != null, "clazz cannot be null");

		return provider.getClassReflectionProvider(clazz).reflectField(fieldName);
	}
}
