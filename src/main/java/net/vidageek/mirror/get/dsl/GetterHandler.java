package net.vidageek.mirror.get.dsl;

import java.lang.reflect.Field;

import net.vidageek.mirror.get.DefaultGetterHandler;
import net.vidageek.mirror.provider.FieldReflectionProvider;

public interface GetterHandler {
	/**
	 * Convenience method for {@link GetterHandler#field(Field)}
	 * 
	 * @param fieldName
	 *            Name of the field that is going to be reflected.
	 * @see DefaultGetterHandler#field(Field)
	 * @throws IllegalArgumentException
	 *             if fieldName is null or empty
	 */
	public Object field(final String fieldName);

	/**
	 * This part of the DSL controls which field is going to have it's value
	 * read.
	 * 
	 * @param field
	 *            Field object representing the field from which the value is
	 *            going to be read.
	 * @return The value of the field represented by this field.
	 * @throws IllegalArgumentException
	 *             if field is null or if field does not exists in this
	 *             class/object
	 * @throws IllegalStateException
	 *             if you are trying to access an instance field without
	 *             providing an object.
	 * @see FieldReflectionProvider#setAccessible()
	 * @see FieldReflectionProvider#getValue()
	 */
	public Object field(final Field field);

}
