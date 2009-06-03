package net.vidageek.mirror.reflect.dsl;

import java.lang.reflect.Field;

import net.vidageek.mirror.provider.ClassReflectionProvider;

public interface FieldReflector {
	/**
	 * This method reflect the field fieldName at class clazz
	 * 
	 * @param clazz
	 *            Class where the field is going to be reflected.
	 * @return The field reflected.
	 * @see ClassReflectionProvider#reflectField(String)
	 * @throws IllegalArgumentException
	 *             if clazz is null
	 */
	@SuppressWarnings("unchecked")
	public Field onClass(final Class clazz) ;
}
