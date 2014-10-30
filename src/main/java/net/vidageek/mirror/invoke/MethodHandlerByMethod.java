/**
 * 
 */
package net.vidageek.mirror.invoke;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import net.vidageek.mirror.Preconditions;
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
		Preconditions.checkArgument(clazz != null, "clazz cannot be null");
		Preconditions.checkArgument(method != null, "method cannot be null");
		Preconditions.checkArgument(method.getDeclaringClass().isAssignableFrom(clazz), 
				"Method %s cannot be invoked on clazz %s", method, clazz.getName());
		this.provider = provider;
		this.target = target;
		this.clazz = clazz;
		this.method = method;
	}

	public Object withArgs(final Object... args) {
		if ((target == null) && !Modifier.isStatic(method.getModifiers())) {
			throw new IllegalStateException("attempt to call instance method " + method.getName() + " on class "
					+ clazz.getName());
		}

		MethodReflectionProvider methodReflectionProvider = provider.getMethodReflectionProvider(target, clazz, method);
		methodReflectionProvider.setAccessible();
		return methodReflectionProvider.invoke(args);
	}

	public Object withoutArgs() {
		return withArgs(new Object[0]);
	}

}
