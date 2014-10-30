/**
 * 
 */
package net.vidageek.mirror.get;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import net.vidageek.mirror.Preconditions;
import net.vidageek.mirror.dsl.Mirror;
import net.vidageek.mirror.exception.MirrorException;
import net.vidageek.mirror.get.dsl.GetterHandler;
import net.vidageek.mirror.provider.FieldReflectionProvider;
import net.vidageek.mirror.provider.ReflectionProvider;

/**
 * Class to provide field reading features.
 * 
 * @author jonasabreu
 */
public final class DefaultGetterHandler implements GetterHandler {

	private final Object target;

	private final Class<?> clazz;

	private final ReflectionProvider provider;

	public DefaultGetterHandler(final ReflectionProvider provider, final Object target) {
		Preconditions.checkArgument(target != null, "target cannot be null");
		this.provider = provider;
		clazz = target.getClass();
		this.target = target;
	}

	public DefaultGetterHandler(final ReflectionProvider provider, final Class<?> clazz) {
		Preconditions.checkArgument(clazz != null, "clazz cannot be null");
		this.provider = provider;
		this.clazz = clazz;
		target = null;
	}

	public Object field(final String fieldName) {
		Preconditions.checkArgument(fieldName != null && fieldName.trim().length() > 0, "fieldName cannot be null or empty");
		return field(getField(fieldName));
	}

	public Object field(final Field field) {
		Preconditions.checkArgument(field != null, "field cannot be null");
		Preconditions.checkArgument(field.getDeclaringClass().isAssignableFrom(clazz), 
				"field declaring class (%s) doesn't match clazz %s", field.getDeclaringClass().getName(), clazz.getName());

		if ((target == null) && !Modifier.isStatic(field.getModifiers())) {
			throw new IllegalStateException("attempt to get instance field " + field.getName() + " on class "
					+ clazz.getName());
		}

		FieldReflectionProvider fieldReflectionProvider = provider.getFieldReflectionProvider(target, clazz, field);
		fieldReflectionProvider.setAccessible();

		return fieldReflectionProvider.getValue();

	}

	private Field getField(final String fieldName) {
		Field field = new Mirror(provider).on(clazz).reflect().field(fieldName);

		if (field == null) {
			throw new MirrorException("could not find field " + fieldName + " for class " + clazz.getName());
		}
		return field;
	}

}
