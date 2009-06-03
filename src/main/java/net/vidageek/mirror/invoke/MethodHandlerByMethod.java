/**
 * 
 */
package net.vidageek.mirror.invoke;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import net.vidageek.mirror.invoke.dsl.MethodHandler;
import net.vidageek.mirror.provider.MethodReflectionProvider;
import net.vidageek.mirror.provider.ReflectionProvider;

/**
 * This class is responsible for invoking a method using its instance.
 * 
 * @author jonasabreu
 */
public final class MethodHandlerByMethod implements MethodHandler {

    private final Object target;

    private final Class<?> clazz;

    private final Method method;

    private final ReflectionProvider provider;

    public MethodHandlerByMethod(final ReflectionProvider provider, final Object target, final Class<?> clazz,
            final Method method) {

        if (clazz == null) {
            throw new IllegalArgumentException("clazz cannot be null");
        }
        if (method == null) {
            throw new IllegalArgumentException("method cannot be null");
        }
        if (!method.getDeclaringClass().isAssignableFrom(clazz)) {
            throw new IllegalArgumentException("method " + method + " cannot be invoked on clazz " + clazz.getName());
        }
        this.provider = provider;
        this.target = target;
        this.clazz = clazz;
        this.method = method;
    }

    /**
     * {@inheritDoc MethodHandler#withArgs(Object...)}
     * 
     * @throws IllegalStateException
     *             if attempting to invoke an instance method without providing
     *             an object.
     * @see MethodReflectionProvider#setAccessible()
     * @see MethodReflectionProvider#invoke(Object[])
     */
    public Object withArgs(final Object... args) {
        if ((target == null) && !Modifier.isStatic(method.getModifiers())) {
            throw new IllegalStateException("attempt to call instance method " + method.getName() + " on class "
                    + clazz.getName());
        }

        MethodReflectionProvider methodReflectionProvider = provider.getMethodReflectionProvider(target, clazz, method);
        methodReflectionProvider.setAccessible();
        return methodReflectionProvider.invoke(args);
    }

    /**
     * {@inheritDoc MethodHandler#withoutArgs()}
     * 
     * This is a convenience method for
     * {@link MethodHandlerByMethod#withArgs(Object...)}
     */
    public Object withoutArgs() {
        return withArgs(new Object[0]);
    }

}
