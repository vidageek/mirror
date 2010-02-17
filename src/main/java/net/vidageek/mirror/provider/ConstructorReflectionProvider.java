/**
 * 
 */
package net.vidageek.mirror.provider;

import net.vidageek.mirror.exception.ReflectionProviderException;

/**
 * Interface that defines reflection operations related to constructors
 * 
 * @author jonasabreu
 */
public interface ConstructorReflectionProvider<T> extends ReflectionElementReflectionProvider {

	/**
	 * this method is used to instantiate an Object using wrapped constructor.
	 * 
	 * @param args
	 *            Arguments used to instantiate object.
	 * @return An instance provided by wrapped constructor.
	 * @throws ReflectionProviderException
	 */
	T instantiate(final Object... args);

	/**
	 * This method is used to reflect the wrapped constructor parameter array.
	 * 
	 * @return An array of class objects representing the method parameters.
	 * @throws ReflectionProviderException
	 */
	Class<?>[] getParameters();
}
