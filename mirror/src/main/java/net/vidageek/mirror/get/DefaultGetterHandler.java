/**
 * 
 */
package net.vidageek.mirror.get;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

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
        if (target == null) {
            throw new IllegalArgumentException("target cannot be null");
        }
        this.provider = provider;
        clazz = target.getClass();
        this.target = target;
    }

    public DefaultGetterHandler(final ReflectionProvider provider, final Class<?> clazz) {
        if (clazz == null) {
            throw new IllegalArgumentException("clazz cannot be null");
        }
        this.provider = provider;
        this.clazz = clazz;
        target = null;
    }

    /**
     * Convenience method for {@link DefaultGetterHandler#field(Field)}
     * 
     * @param fieldName
     *            Name of the field that is going to be reflected.
     * @see DefaultGetterHandler#field(Field)
     * @throws IllegalArgumentException
     *             if fieldName is null or empty
     */
    public Object field(final String fieldName) {
        if ((fieldName == null) || (fieldName.trim().length() == 0)) {
            throw new IllegalArgumentException("fieldName cannot be null or empty.");
        }

        return field(getField(fieldName));
    }

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
    public Object field(final Field field) {
        if (field == null) {
            throw new IllegalArgumentException("field cannot be null");
        }
        if (!field.getDeclaringClass().isAssignableFrom(clazz)) {
            throw new IllegalArgumentException("field declaring class (" + field.getDeclaringClass().getName()
                    + ") doesn't match clazz " + clazz.getName());
        }

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
