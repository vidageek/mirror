package net.vidageek.mirror.reflect.dsl;

import java.lang.reflect.Constructor;

import net.vidageek.mirror.provider.ClassReflectionProvider;
import net.vidageek.mirror.reflect.DefaultConstructorReflector;

public interface ConstructorReflector<T> {
	/**
	 * Use this method to reflect a constructor using its argument list.
	 * 
	 * @param classes
	 *            argument types of the constructor to be reflected.
	 * @return A constructor matching args or null if none is found.
	 * @see ClassReflectionProvider#reflectConstructor(Class[])
	 */
	public Constructor<T> withArgs(final Class<?>... classes) ;

	/**
	 * This is a convenience method for
	 * {@link DefaultConstructorReflector#withArgs(Class...)}
	 */
	public Constructor<T> withoutArgs() ;
}
