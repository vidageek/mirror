package net.vidageek.mirror.provider.java;

import net.vidageek.mirror.exception.ReflectionProviderException;
import net.vidageek.mirror.provider.ConstructorBypassingReflectionProvider;
import net.vidageek.mirror.thirdparty.org.objenesis.ObjenesisException;
import net.vidageek.mirror.thirdparty.org.objenesis.ObjenesisStd;

/**
 * @author jonasabreu
 * 
 */
final public class ObjenesisConstructorBypassingReflectionProvider<T> implements
        ConstructorBypassingReflectionProvider<T> {

    private final Class<T> clazz;

    public ObjenesisConstructorBypassingReflectionProvider(final Class<T> clazz) {
        this.clazz = clazz;
    }

    @SuppressWarnings("unchecked")
    public T bypassConstructor() {
        try {
            ObjenesisStd objenesis = new ObjenesisStd();
            return (T) objenesis.getInstantiatorOf(clazz).newInstance();
        } catch (ObjenesisException e) {
            throw new ReflectionProviderException(
                    "could not instantiate without using a constructor. Maybe your VM is not supported by Objenesis."
                            + " Please check http://code.google.com/p/objenesis/wiki/ListOfCurrentlySupportedVMs.", e);
        }
    }

}
