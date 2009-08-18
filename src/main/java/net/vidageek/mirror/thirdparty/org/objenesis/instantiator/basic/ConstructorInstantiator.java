package net.vidageek.mirror.thirdparty.org.objenesis.instantiator.basic;

import java.lang.reflect.Constructor;

import net.vidageek.mirror.thirdparty.org.objenesis.ObjenesisException;
import net.vidageek.mirror.thirdparty.org.objenesis.instantiator.ObjectInstantiator;

/**
 * Instantiates a class by grabbing the no args constructor and calling
 * Constructor.newInstance(). This can deal with default public constructors,
 * but that's about it.
 * 
 * @see ObjectInstantiator
 */
@SuppressWarnings("all")
public class ConstructorInstantiator implements ObjectInstantiator {

    protected Constructor constructor;

    public ConstructorInstantiator(final Class type) {
        try {
            constructor = type.getDeclaredConstructor((Class[]) null);
        } catch (Exception e) {
            throw new ObjenesisException(e);
        }
    }

    public Object newInstance() {
        try {
            return constructor.newInstance((Object[]) null);
        } catch (Exception e) {
            throw new ObjenesisException(e);
        }
    }

}
