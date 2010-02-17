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

	public FieldSetter field(final String fieldName) {
		if ((fieldName == null) || (fieldName.trim().length() == 0)) {
			throw new IllegalArgumentException("fieldName cannot be null or empty.");
		}
		return new FieldSetterByName(provider, fieldName, target, clazz);
	}

	public FieldSetter field(final Field field) {
		if (field == null) {
			throw new IllegalArgumentException("parameter field cannot be null.");
		}
		return new FieldSetterByField(provider, target, clazz, field);
	}
}
