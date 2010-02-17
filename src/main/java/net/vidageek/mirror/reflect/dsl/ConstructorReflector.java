package net.vidageek.mirror.reflect.dsl;

import java.lang.reflect.Constructor;

import net.vidageek.mirror.exception.MirrorException;
import net.vidageek.mirror.provider.ClassReflectionProvider;

public interface ConstructorReflector<T> {
	/**
	 * Use this method to reflect a constructor using its argument list.
	 * 
	 * @param classes
	 *            argument types of the constructor to be reflected.
	 * @return A constructor matching args or null if none is found.
	 * @see ClassReflectionProvider#reflectConstructor(Class[])
	 */
	public Constructor<T> withArgs(final Class<?>... classes);

	/**
	 * This is a convenience method for
	 * {@link ConstructorReflector#withArgs(Class...)}
	 */
	public Constructor<T> withoutArgs();

	/**
	 * Use this method to reflect the only constructor on the class
	 * 
	 * @throws MirrorException
	 *             if more than one constructor is found on the class
	 * @return The only constructor of the class
	 */
	public Constructor<T> withAnyArgs();
}
