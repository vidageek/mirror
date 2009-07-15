/**
 * 
 */
package net.vidageek.mirror.reflect;

import java.lang.reflect.Method;

import net.vidageek.mirror.provider.ReflectionProvider;
import net.vidageek.mirror.reflect.dsl.MethodReflector;

/**
 * This class is responsible for reflecting methods.
 * 
 * @author jonasabreu
 */
public final class DefaultMethodReflector implements MethodReflector {

    private final String methodName;

    private final Class<?> clazz;

    private final ReflectionProvider provider;

    public DefaultMethodReflector(final ReflectionProvider provider, final String methodName, final Class<?> clazz) {
        if ((methodName == null) || (methodName.trim().length() == 0)) {
            throw new IllegalArgumentException("methodName cannot be null or empty");
        }
        if (clazz == null) {
            throw new IllegalArgumentException("clazz cannnot be null");
        }
        this.provider = provider;
        this.methodName = methodName.trim();
        this.clazz = clazz;
    }

    public Method withoutArgs() {
        return withArgs(new Class<?>[0]);
    }

    public Method withArgs(final Class<?>... classes) {
        if (classes == null) {
            throw new IllegalArgumentException("classes cannot be null");
        }
        return provider.getClassReflectionProvider(clazz).reflectMethod(methodName, classes);
    }

}
