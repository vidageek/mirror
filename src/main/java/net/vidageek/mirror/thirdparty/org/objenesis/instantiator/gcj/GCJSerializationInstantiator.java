package net.vidageek.mirror.thirdparty.org.objenesis.instantiator.gcj;

import net.vidageek.mirror.thirdparty.org.objenesis.ObjenesisException;
import net.vidageek.mirror.thirdparty.org.objenesis.instantiator.SerializationInstantiatorHelper;

/**
 * Instantiates a class by making a call to internal GCJ private methods. It is
 * only supposed to work on GCJ JVMs. This instantiator will create classes in a
 * way compatible with serialization, calling the first non-serializable
 * superclass' no-arg constructor.
 * 
 * @author Leonardo Mesquita
 * @see net.vidageek.mirror.thirdparty.org.objenesis.instantiator.ObjectInstantiator
 */
@SuppressWarnings("all")
public class GCJSerializationInstantiator extends GCJInstantiatorBase {
    private final Class superType;

    public GCJSerializationInstantiator(final Class type) {
        super(type);
        superType = SerializationInstantiatorHelper.getNonSerializableSuperClass(type);
    }

    public Object newInstance() {
        try {
            return newObjectMethod.invoke(dummyStream, new Object[] { type, superType });
        } catch (Exception e) {
            throw new ObjenesisException(e);
        }
    }

}
