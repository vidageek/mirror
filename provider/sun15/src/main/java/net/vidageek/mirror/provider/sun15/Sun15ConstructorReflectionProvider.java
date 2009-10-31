package net.vidageek.mirror.provider.sun15;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import net.vidageek.mirror.dsl.Mirror;
import net.vidageek.mirror.exception.ReflectionProviderException;
import net.vidageek.mirror.provider.ConstructorReflectionProvider;
import net.vidageek.mirror.provider.java.DefaultMirrorReflectionProvider;
import net.vidageek.mirror.provider.java.PureJavaConstructorReflectionProvider;
import sun.misc.Unsafe;
import sun.reflect.ConstructorAccessor;
import sun.reflect.MethodAccessor;
import sun.reflect.ReflectionFactory;

/**
 * @author jonasabreu
 * 
 */
@SuppressWarnings("restriction")
final public class Sun15ConstructorReflectionProvider<T> implements ConstructorReflectionProvider<T> {

    private static Unsafe unsafe;
    private static long constructorAccessorFieldOffset;
    private static MethodAccessor constructorAccessorAcquirer;
    private final ConstructorAccessor accessor;
    private final Class<T> clazz;
    private final Constructor<T> constructor;
    private static final Object[] emptyObjectArray = new Object[0];

    static {
        Mirror mirror = new Mirror(new DefaultMirrorReflectionProvider());
        Field accessor = mirror.on(Constructor.class).reflect().field("constructorAccessor");
        Method acquirer = mirror.on(Constructor.class).reflect().method("acquireConstructorAccessor").withoutArgs();
        unsafe = (Unsafe) mirror.on(Unsafe.class).get().field("theUnsafe");
        constructorAccessorFieldOffset = unsafe.objectFieldOffset(accessor);
        constructorAccessorAcquirer = ReflectionFactory.getReflectionFactory().newMethodAccessor(acquirer);
    }

    public Sun15ConstructorReflectionProvider(final Class<T> clazz, final Constructor<T> constructor) {
        this.clazz = clazz;
        this.constructor = constructor;
        ConstructorAccessor accessor = (ConstructorAccessor) unsafe.getObject(constructor,
                constructorAccessorFieldOffset);
        if (accessor == null) {
            try {
                constructorAccessorAcquirer.invoke(constructor, emptyObjectArray);
            } catch (IllegalArgumentException e) {
                throw new ReflectionProviderException("Could not acquire ConstructorAccessor.", e);
            } catch (InvocationTargetException e) {
                throw new ReflectionProviderException("Could not acquire ConstructorAccessor.", e);
            }

            accessor = (ConstructorAccessor) unsafe.getObject(constructor, constructorAccessorFieldOffset);
        }
        this.accessor = accessor;
    }

    public Class<?>[] getParameters() {
        return new PureJavaConstructorReflectionProvider<T>(clazz, constructor).getParameters();
    }

    @SuppressWarnings("unchecked")
    public T instantiate(final Object... args) {
        try {
            return (T) accessor.newInstance(args);
        } catch (IllegalArgumentException e) {
            throw new ReflectionProviderException("could not invoke constructor " + constructor.toGenericString()
                    + " on class " + clazz.getName(), e);
        } catch (InstantiationException e) {
            throw new ReflectionProviderException("could not invoke constructor " + constructor.toGenericString()
                    + " on class " + clazz.getName(), e);
        } catch (InvocationTargetException e) {
            throw new ReflectionProviderException("could not invoke constructor " + constructor.toGenericString()
                    + " on class " + clazz.getName(), e);
        }
    }

    public void setAccessible() {
        constructor.setAccessible(true);
    }

}
