/**
 * 
 */
package net.vidageek.mirror.provider.java;

import java.lang.reflect.Field;

import net.vidageek.mirror.exception.ReflectionProviderException;
import net.vidageek.mirror.provider.FieldReflectionProvider;

/**
 * @author jonasabreu
 * 
 */
public final class PureJavaFieldReflectionProvider implements FieldReflectionProvider {

    private final Object target;
    private final Class<?> clazz;
    private final Field field;

    public PureJavaFieldReflectionProvider(final Object target, final Class<?> clazz, final Field field) {
        this.target = target;
        this.clazz = clazz;
        this.field = field;
    }

    public void setValue(final Object value) {
        try {
            setAccessible();
            field.set(target, value);

        } catch (final IllegalAccessException e) {
            throw new ReflectionProviderException("could not set value " + value + " on field " + field.getName()
                    + " of class " + clazz.getName());
        }
    }

    public Object getValue() {
        try {
            setAccessible();
            return field.get(target);
        } catch (final IllegalAccessException e) {
            throw new ReflectionProviderException("could not get value for field " + field.getName() + " of class "
                    + clazz.getName());
        }
    }

    public void setAccessible() {
        field.setAccessible(true);
    }
}
