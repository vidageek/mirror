package net.vidageek.mirror.provider;

import net.vidageek.mirror.exception.ReflectionProviderException;

/**
 * @author jonasabreu
 * 
 */
public interface ConstructorBypassingReflectionProvider<T> {

    /**
     * This method is used to create an object without using a constructor.
     * 
     * @throws ReflectionProviderException
     */
    T bypassConstructor();

}
