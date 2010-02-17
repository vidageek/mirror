/**
 * 
 */
package net.vidageek.mirror.set;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import net.vidageek.mirror.exception.MirrorException;
import net.vidageek.mirror.matcher.ClassArrayMatcher;
import net.vidageek.mirror.matcher.MatchType;
import net.vidageek.mirror.provider.FieldReflectionProvider;
import net.vidageek.mirror.provider.ReflectionProvider;
import net.vidageek.mirror.set.dsl.FieldSetter;

/**
 * @author jonasabreu
 * 
 */
public final class FieldSetterByField implements FieldSetter {

	private final Object target;

	private final Field field;

	private final Class<?> clazz;

	private final ReflectionProvider provider;

	public FieldSetterByField(final ReflectionProvider provider, final Object target, final Class<?> clazz,
			final Field field) {
		if (clazz == null) {
			throw new IllegalArgumentException("clazz cannot be null");
		}
		if (field == null) {
			throw new IllegalArgumentException("field cannot be null");
		}
		if (!field.getDeclaringClass().isAssignableFrom(clazz)) {
			throw new IllegalArgumentException("field declaring class (" + field.getDeclaringClass().getName()
					+ ") doesn't match clazz " + clazz.getName());
		}
		this.provider = provider;
		this.target = target;
		this.clazz = clazz;
		this.field = field;
	}

	public void withValue(final Object value) {
		if ((target == null) && !Modifier.isStatic(field.getModifiers())) {
			throw new MirrorException("attempt to set instance field " + field.getName() + " on class "
					+ clazz.getName());
		}

		if ((value == null) && field.getType().isPrimitive()) {
			throw new IllegalArgumentException("cannot set null value on primitive field");
		}

		if (value != null) {
			MatchType match = new ClassArrayMatcher(value.getClass()).match(field.getType());
			if (MatchType.DONT_MATCH.equals(match)) {
				throw new IllegalArgumentException("Value of type " + value.getClass() + " cannot be set on field "
						+ field.getName() + " of type " + field.getType() + " from class " + clazz.getName()
						+ ". Incompatible types");
			}
		}
		FieldReflectionProvider fieldReflectionProvider = provider.getFieldReflectionProvider(target, clazz, field);
		fieldReflectionProvider.setAccessible();
		fieldReflectionProvider.setValue(value);
	}
}
