/**
 * 
 */
package net.vidageek.mirror.set;

import java.lang.reflect.Field;

import net.vidageek.mirror.Preconditions;
import net.vidageek.mirror.dsl.Mirror;
import net.vidageek.mirror.exception.MirrorException;
import net.vidageek.mirror.provider.ReflectionProvider;
import net.vidageek.mirror.set.dsl.FieldSetter;

/**
 * This class is responsible for setting a value on a field choosen by its field
 * name.
 * 
 * @author jonasabreu
 */
public final class FieldSetterByName implements FieldSetter {

	private final String fieldName;

	private final Object target;

	private final Class<?> clazz;

	private final ReflectionProvider provider;

	public FieldSetterByName(final ReflectionProvider provider, final String fieldName, final Object target,
			final Class<?> clazz) {
		Preconditions.checkArgument(fieldName != null && fieldName.trim().length() > 0, "fieldName cannot be null or empty");
		Preconditions.checkArgument(clazz != null, "clazz cannot be null");

		this.provider = provider;
		this.fieldName = fieldName;
		this.target = target;
		this.clazz = clazz;
	}

	public void withValue(final Object value) {
		Field field = new Mirror(provider).on(clazz).reflect().field(fieldName);
		if (field == null) {
			throw new MirrorException("could not find field " + fieldName + " on class " + clazz.getName());
		}
		new FieldSetterByField(provider, target, clazz, field).withValue(value);

	}

}
