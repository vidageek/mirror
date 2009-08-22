/**
 * 
 */
package net.vidageek.mirror.reflect;

import java.lang.reflect.Constructor;
import java.util.List;

import net.vidageek.mirror.dsl.Mirror;
import net.vidageek.mirror.exception.MirrorException;
import net.vidageek.mirror.provider.ReflectionProvider;
import net.vidageek.mirror.reflect.dsl.ConstructorReflector;

/**
 * This class is responsible for reflecting a constructor.
 * 
 * @author jonasabreu
 */
public final class DefaultConstructorReflector<T> implements ConstructorReflector<T> {

    private final Class<T> clazz;

    private final ReflectionProvider provider;

    public DefaultConstructorReflector(final ReflectionProvider provider, final Class<T> clazz) {
        if (clazz == null) {
            throw new IllegalArgumentException("argument class cannot be null.");
        }
        this.provider = provider;
        this.clazz = clazz;
    }

    public Constructor<T> withArgs(final Class<?>... classes) {
        if (classes == null) {
            throw new IllegalArgumentException("classes cannot be null");
        }

        return provider.getClassReflectionProvider(clazz).reflectConstructor(classes);
    }

    public Constructor<T> withoutArgs() {
        return withArgs(new Class<?>[0]);
    }

    public Constructor<T> withAnyArgs() {
        List<Constructor<T>> constructors = new Mirror(provider).on(clazz).reflectAll().constructors();
        if (constructors.size() != 1) {
            throw new MirrorException("there is more than one constructor on class " + clazz.getName()
                    + ". withAnyArgs must be called only on classes with a single constructor.");
        }
        return constructors.get(0);
    }
}
