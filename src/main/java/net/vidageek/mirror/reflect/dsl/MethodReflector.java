package net.vidageek.mirror.reflect.dsl;

import java.lang.reflect.Method;

import net.vidageek.mirror.provider.ClassReflectionProvider;
import net.vidageek.mirror.reflect.DefaultMethodReflector;

public interface MethodReflector {
	/**
	 * This is a convenience method for
	 * {@link DefaultMethodReflector#withArgs(Class...)}
	 */
	public Method withoutArgs() ;

	/**
	 * Use this method to reflect a method with classes as its argument types.
	 * 
	 * @param classes
	 *            argument types of the method to be reflected.
	 * @return A method matching classes as argument types.
	 * @see ClassReflectionProvider#reflectMethod(String, Class[])
	 * @throws IllegalArgumentException
	 *             if classes is null
	 */
	public Method withArgs(final Class<?>... classes) ;
}
