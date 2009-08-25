package net.vidageek.mirror.provider.experimental.sun15;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import net.vidageek.mirror.exception.ReflectionProviderException;
import net.vidageek.mirror.provider.MethodReflectionProvider;
import net.vidageek.mirror.provider.java.PureJavaMethodReflectionProvider;
import sun.reflect.MethodAccessor;
import sun.reflect.ReflectionFactory;

/**
 * @author jonasabreu
 * 
 */
@SuppressWarnings("restriction")
final public class Sun15MethodReflectionProvider implements MethodReflectionProvider {

    private final Object target;
    private final Method method;
    private final MethodReflectionProvider provider;
    private final MethodAccessor accessor;

    public Sun15MethodReflectionProvider(final Object target, final Class<?> clazz, final Method method) {
        this.target = target;
        this.method = method;
        provider = new PureJavaMethodReflectionProvider(target, clazz, method);
        accessor = ReflectionFactory.getReflectionFactory().newMethodAccessor(method);

    }

    public Class<?>[] getParameters() {
        return provider.getParameters();
    }

    public Object invoke(final Object[] args) {
        try {
            return accessor.invoke(target, args);
        } catch (IllegalArgumentException e) {
            throw new ReflectionProviderException("Could not invoke method " + method.getName(), e);
        } catch (InvocationTargetException e) {
            throw new ReflectionProviderException("Could not invoke method " + method.getName(), e);
        } catch (NullPointerException e) {
            throw new ReflectionProviderException("Attempt to call an instance method ( " + method.getName()
                    + ") on a null object.", e);
        }
    }

    public void setAccessible() {
        // MethodAccessor just ignores accessibility issues.

    }

}
