/**
 * 
 */
package net.vidageek.mirror.provider;

import net.vidageek.mirror.exception.ReflectionProviderException;

/**
 * Interface that defines reflection operations related to methods
 * 
 * @author jonasabreu
 */
public interface MethodReflectionProvider extends ReflectionElementReflectionProvider {

    /**
     * This method is used to reflect the wrapped method parameter array.
     * 
     * @return An array of class objects representing the method parameters.
     * @throws ReflectionProviderException
     */
    Class<?>[] getParameters();

    /**
     * This method is used to invoke the wrapped method using args as its
     * arguments.
     * 
     * @param args
     *            Arguments to be passed to method.
     * @return The value returned by the method or null if the method was void.
     * @throws ReflectionProviderException
     */
    Object invoke(Object[] args);
}
