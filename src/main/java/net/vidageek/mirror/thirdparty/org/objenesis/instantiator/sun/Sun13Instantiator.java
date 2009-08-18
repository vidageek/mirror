package net.vidageek.mirror.thirdparty.org.objenesis.instantiator.sun;

import net.vidageek.mirror.thirdparty.org.objenesis.ObjenesisException;

/**
 * Instantiates a class by making a call to internal Sun private methods. It is
 * only supposed to work on Sun HotSpot 1.3 JVM. This instantiator will not call
 * any constructors.
 * 
 * @author Leonardo Mesquita
 * @see net.vidageek.mirror.thirdparty.org.objenesis.instantiator.ObjectInstantiator
 */
@SuppressWarnings("all")
public class Sun13Instantiator extends Sun13InstantiatorBase {
    public Sun13Instantiator(final Class type) {
        super(type);
    }

    public Object newInstance() {
        try {
            return allocateNewObjectMethod.invoke(null, new Object[] { type, Object.class });
        } catch (Exception e) {
            throw new ObjenesisException(e);
        }
    }

}
