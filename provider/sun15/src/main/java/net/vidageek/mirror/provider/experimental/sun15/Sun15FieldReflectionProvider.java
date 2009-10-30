package net.vidageek.mirror.provider.experimental.sun15;

import java.lang.reflect.Field;

import net.vidageek.mirror.exception.ReflectionProviderException;
import net.vidageek.mirror.provider.FieldReflectionProvider;
import sun.reflect.FieldAccessor;
import sun.reflect.ReflectionFactory;

/**
 * @author jonasabreu
 * 
 */
@SuppressWarnings("restriction")
final public class Sun15FieldReflectionProvider implements FieldReflectionProvider {

    private final FieldAccessor accessor;
    private final Object target;
    private final Field field;
    private final Class<?> clazz;

    public Sun15FieldReflectionProvider(final Object target, final Class<?> clazz, final Field field) {
        this.target = target;
        this.clazz = clazz;
        this.field = field;
        // the last parameter is true to enable final field setting.
        accessor = ReflectionFactory.getReflectionFactory().newFieldAccessor(field, true);
    }

    public Object getValue() {
        return accessor.get(target);
    }

    public void setValue(final Object value) {
        try {
            accessor.set(target, value);
        } catch (IllegalArgumentException e) {
            throw new ReflectionProviderException("could not set value " + value + " on field " + field.getName()
                    + " of class " + clazz.getName());
        } catch (IllegalAccessException e) {
            throw new ReflectionProviderException("could not set value " + value + " on field " + field.getName()
                    + " of class " + clazz.getName());
        }
    }

    public void setAccessible() {
    }

}
