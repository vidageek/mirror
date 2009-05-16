package net.vidageek.mirror.invoke;

import java.lang.reflect.Method;

import net.vidageek.mirror.dsl.Mirror;
import net.vidageek.mirror.exception.MirrorException;
import net.vidageek.mirror.invoke.dsl.MethodHandler;
import net.vidageek.mirror.provider.ReflectionProvider;

/**
 * Class responsible for invoking methods using is name and arguments.
 * 
 * @author jonasabreu
 */
public final class MethodHandlerByName implements MethodHandler {

    private final Object target;

    private final String methodName;

    private final Class<?> clazz;

    private final ReflectionProvider provider;

    public MethodHandlerByName(final ReflectionProvider provider, final Object target, final Class<?> clazz,
            final String methodName) {
        if (clazz == null) {
            throw new IllegalArgumentException("clazz can't be null");
        }

        if ((methodName == null) || (methodName.trim().length() == 0)) {
            throw new IllegalArgumentException("methodName can't be null");
        }
        this.provider = provider;

        this.target = target;
        this.clazz = clazz;
        this.methodName = methodName;
    }

    /**
     * {@inheritDoc MethodHandler#withoutArgs()}
     * 
     * This is a convenience method for
     * {@link MethodHandlerByName#withArgs(Object...)}
     * 
     * @see MethodHandlerByName#withArgs(Object...)
     */
    public <T> T withoutArgs() {
        return withArgs(new Object[0]);
    }

    /**
     * {@inheritDoc MethodHandler#withArgs(Object...)}
     * 
     * This is a convenience method for
     * {@link MethodHandlerByMethod#withArgs(Object...)}
     * 
     * @see MethodHandlerByMethod#withArgs(Object...)
     * 
     * @throws MirrorException
     *             if method is not found on class
     */
    public <T> T withArgs(final Object... args) {
        return new MethodHandlerByMethod(provider, target, clazz, getMethod(args)).withArgs(args);
    }

    private Method getMethod(final Object[] args) {
        int length = args == null ? 0 : args.length;

        Class<?>[] classes = new Class<?>[length];
        for (int i = 0; i < length; i++) {
            if (args[i] == null) {
                throw new IllegalArgumentException(
                        "Cannot invoke a method by name if one of it's arguments is null. First reflect the method.");
            }
            classes[i] = args[i].getClass();
        }

        Method method = new Mirror(provider).on(clazz).reflect().method(methodName).withArgs(classes);
        if (method == null) {
            throw new MirrorException("Could not find method " + methodName + " on class " + clazz.getName());
        }
        return method;

    }

}
