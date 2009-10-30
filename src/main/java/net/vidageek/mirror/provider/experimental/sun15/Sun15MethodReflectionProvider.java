package net.vidageek.mirror.provider.experimental.sun15;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import net.vidageek.mirror.exception.ReflectionProviderException;
import net.vidageek.mirror.provider.MethodReflectionProvider;
import sun.reflect.MethodAccessor;

/**
 * @author jonasabreu
 * 
 */
@SuppressWarnings("restriction")
final public class Sun15MethodReflectionProvider implements MethodReflectionProvider {

    private final Object target;
    private final net.vidageek.mirror.provider.experimental.sun15.Method method;
    private final MethodReflectionProvider provider = null;
    private final MethodAccessor accessor;

    public Sun15MethodReflectionProvider(final Object target, final Class<?> clazz, final Method method) {
        this.target = target;
        this.method = (net.vidageek.mirror.provider.experimental.sun15.Method) (AnnotatedElement) method;
        {
            if (this.method.methodAccessor == null) {
                this.method.acquireMethodAccessor();
            }
            accessor = this.method.methodAccessor;
        }
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

    }
}
