package net.vidageek.mirror.provider.sun15;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import net.vidageek.mirror.exception.ReflectionProviderException;
import net.vidageek.mirror.provider.ConstructorReflectionProvider;
import net.vidageek.mirror.provider.java.PureJavaConstructorReflectionProvider;
import sun.reflect.ConstructorAccessor;
import sun.reflect.ReflectionFactory;

/**
 * @author jonasabreu
 * 
 */
@SuppressWarnings("restriction")
final public class Sun15ConstructorReflectionProvider<T> implements ConstructorReflectionProvider<T> {

    private final ConstructorAccessor accessor;
    private final ConstructorReflectionProvider<T> provider;
    private final Class<T> clazz;
    private final Constructor<T> constructor;

    public Sun15ConstructorReflectionProvider(final Class<T> clazz, final Constructor<T> constructor) {
        this.clazz = clazz;
        this.constructor = constructor;
        this.accessor = ReflectionFactory.getReflectionFactory().newConstructorAccessor(constructor);
        provider = new PureJavaConstructorReflectionProvider<T>(clazz, constructor);
    }

    public Class<?>[] getParameters() {
        return provider.getParameters();
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
    }

}
