package net.vidageek.mirror.provider.experimental.sun15;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import net.vidageek.mirror.dsl.Mirror;
import net.vidageek.mirror.exception.ReflectionProviderException;
import net.vidageek.mirror.provider.FieldReflectionProvider;
import net.vidageek.mirror.provider.java.DefaultMirrorReflectionProvider;
import sun.misc.Unsafe;
import sun.reflect.FieldAccessor;
import sun.reflect.MethodAccessor;
import sun.reflect.ReflectionFactory;

/**
 * @author jonasabreu
 * 
 */
@SuppressWarnings("restriction")
final public class Sun15FieldReflectionProvider implements FieldReflectionProvider {

    private final static Unsafe unsafe;
    private final static long fieldAccessorFieldOffset;
    private final static MethodAccessor fieldAccessorAcquirer;
    private final FieldAccessor accessor;
    private final Object target;
    private final Field field;
    private final Class<?> clazz;
    private static final Object[] objectArray = new Object[] { true };

    static {
        Mirror mirror = new Mirror(new DefaultMirrorReflectionProvider());
        Field accessor = mirror.on(Field.class).reflect().field("overrideFieldAccessor");
        Method acquirer = mirror.on(Field.class).reflect().method("acquireFieldAccessor").withArgs(boolean.class);
        unsafe = (Unsafe) mirror.on(Unsafe.class).get().field("theUnsafe");
        fieldAccessorFieldOffset = unsafe.objectFieldOffset(accessor);
        fieldAccessorAcquirer = ReflectionFactory.getReflectionFactory().newMethodAccessor(acquirer);
    }

    public Sun15FieldReflectionProvider(final Object target, final Class<?> clazz, final Field field) {
        this.target = target;
        this.clazz = clazz;
        this.field = field;
        FieldAccessor accessor = (FieldAccessor) unsafe.getObject(field, fieldAccessorFieldOffset);
        if (accessor == null) {
            try {
                fieldAccessorAcquirer.invoke(field, objectArray);
            } catch (IllegalArgumentException e) {
                throw new ReflectionProviderException("Could not acquire FieldAccessor.", e);
            } catch (InvocationTargetException e) {
                throw new ReflectionProviderException("Could not acquire FieldAccessor.", e);
            }
            accessor = (FieldAccessor) unsafe.getObject(field, fieldAccessorFieldOffset);
        }
        this.accessor = accessor;
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
        field.setAccessible(true);
    }

}
