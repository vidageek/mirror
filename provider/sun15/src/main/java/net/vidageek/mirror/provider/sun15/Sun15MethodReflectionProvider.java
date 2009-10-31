package net.vidageek.mirror.provider.sun15;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import net.vidageek.mirror.dsl.Mirror;
import net.vidageek.mirror.exception.ReflectionProviderException;
import net.vidageek.mirror.provider.MethodReflectionProvider;
import net.vidageek.mirror.provider.java.DefaultMirrorReflectionProvider;
import net.vidageek.mirror.provider.java.PureJavaMethodReflectionProvider;
import sun.misc.Unsafe;
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
    private final MethodAccessor accessor;
    private final static long methodAccessorFieldOffset;
    private final static Unsafe unsafe;
    private final static MethodAccessor methodAccessorAcquirer;
    private final static Object[] emptyObjectArray = new Object[0];
    private final Class<?> clazz;

    static {
        Mirror mirror = new Mirror(new DefaultMirrorReflectionProvider());
        Field accessor = mirror.on(Method.class).reflect().field("methodAccessor");
        Method acquirer = mirror.on(Method.class).reflect().method("acquireMethodAccessor").withoutArgs();
        unsafe = (Unsafe) mirror.on(Unsafe.class).get().field("theUnsafe");
        methodAccessorFieldOffset = unsafe.objectFieldOffset(accessor);
        methodAccessorAcquirer = ReflectionFactory.getReflectionFactory().newMethodAccessor(acquirer);

    }

    public Sun15MethodReflectionProvider(final Object target, final Class<?> clazz, final Method method) {
        this.target = target;
        this.clazz = clazz;
        this.method = method;
        MethodAccessor accessor = (MethodAccessor) unsafe.getObject(method, methodAccessorFieldOffset);
        if (accessor == null) {
            try {
                methodAccessorAcquirer.invoke(method, emptyObjectArray);
            } catch (IllegalArgumentException e) {
                throw new ReflectionProviderException("Could not aquire MethodAccessor.", e);
            } catch (InvocationTargetException e) {
                throw new ReflectionProviderException("Could not aquire MethodAccessor.", e);
            }
            accessor = (MethodAccessor) unsafe.getObject(method, methodAccessorFieldOffset);
        }
        this.accessor = accessor;
    }

    public Class<?>[] getParameters() {
        return new PureJavaMethodReflectionProvider(target, clazz, method).getParameters();
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
        method.setAccessible(true);
    }
}
