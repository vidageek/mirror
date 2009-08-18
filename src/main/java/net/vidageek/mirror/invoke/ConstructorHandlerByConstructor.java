/**
 * 
 */
package net.vidageek.mirror.invoke;

import java.lang.reflect.Constructor;

import net.vidageek.mirror.invoke.dsl.ConstructorHandler;
import net.vidageek.mirror.provider.ConstructorBypassingReflectionProvider;
import net.vidageek.mirror.provider.ConstructorReflectionProvider;
import net.vidageek.mirror.provider.ReflectionProvider;

/**
 * This class is responsible for invoking a constructor provided.
 * 
 * @author jonasabreu
 */
public final class ConstructorHandlerByConstructor<T> implements ConstructorHandler<T> {

    private final Constructor<T> constructor;

    private final Class<T> clazz;

    private final ReflectionProvider provider;

    public ConstructorHandlerByConstructor(final ReflectionProvider provider, final Class<T> clazz,
            final Constructor<T> con) {
        if (clazz == null) {
            throw new IllegalArgumentException("clazz cannot be null");
        }
        if (con == null) {
            throw new IllegalArgumentException("constructor cannot be null");
        }
        if (!clazz.equals(con.getDeclaringClass())) {
            throw new IllegalArgumentException("constructor declaring type should be " + clazz.getName() + " but was "
                    + con.getDeclaringClass().getName());
        }
        this.provider = provider;
        this.clazz = clazz;
        this.constructor = con;
    }

    public T withArgs(final Object... args) {
        ConstructorReflectionProvider<T> constructorReflectionProvider = provider.getConstructorReflectionProvider(
                clazz, constructor);
        constructorReflectionProvider.setAccessible();
        return constructorReflectionProvider.instantiate(args);
    }

    public T withoutArgs() {
        return withArgs(new Object[0]);
    }

    public T bypasser() {
        ConstructorBypassingReflectionProvider<T> bypassingProvider = provider
            .getConstructorBypassingReflectionProvider(clazz);
        return bypassingProvider.bypassConstructor();
    }

}
