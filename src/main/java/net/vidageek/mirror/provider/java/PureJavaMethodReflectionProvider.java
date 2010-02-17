/**
 * 
 */
package net.vidageek.mirror.provider.java;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import net.vidageek.mirror.exception.ReflectionProviderException;
import net.vidageek.mirror.provider.MethodReflectionProvider;

/**
 * @author jonasabreu
 * 
 */
public final class PureJavaMethodReflectionProvider implements MethodReflectionProvider {

	private final Object target;
	private final Method method;

	public PureJavaMethodReflectionProvider(final Object target, final Class<?> clazz, final Method method) {
		this.target = target;
		this.method = method;
	}

	public Class<?>[] getParameters() {
		return method.getParameterTypes();
	}

	public void setAccessible() {
		method.setAccessible(true);

	}

	public Object invoke(final Object[] args) {
		try {
			setAccessible();
			return method.invoke(target, args);

		} catch (IllegalArgumentException e) {
			throw new ReflectionProviderException("Could not invoke method " + method.getName(), e);
		} catch (IllegalAccessException e) {
			throw new ReflectionProviderException("Could not invoke method " + method.getName(), e);
		} catch (InvocationTargetException e) {
			throw new ReflectionProviderException("Could not invoke method " + method.getName(),
					e.getCause() == null ? e : e.getCause());
		} catch (NullPointerException e) {
			throw new ReflectionProviderException("Attempt to call an instance method ( " + method.getName()
					+ ") on a null object.", e);
		}
	}
}
