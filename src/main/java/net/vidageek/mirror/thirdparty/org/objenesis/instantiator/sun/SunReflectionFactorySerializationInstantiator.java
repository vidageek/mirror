package net.vidageek.mirror.thirdparty.org.objenesis.instantiator.sun;

import java.io.NotSerializableException;
import java.lang.reflect.Constructor;

import net.vidageek.mirror.thirdparty.org.objenesis.ObjenesisException;
import net.vidageek.mirror.thirdparty.org.objenesis.instantiator.ObjectInstantiator;
import net.vidageek.mirror.thirdparty.org.objenesis.instantiator.SerializationInstantiatorHelper;
import sun.reflect.ReflectionFactory;

/**
 * Instantiates an object using internal sun.reflect.ReflectionFactory - a class
 * only available on JDK's that use Sun's 1.4 (or later) Java implementation.
 * This instantiator will create classes in a way compatible with serialization,
 * calling the first non-serializable superclass' no-arg constructor. This is
 * the best way to instantiate an object without any side effects caused by the
 * constructor - however it is not available on every platform.
 * 
 * @see ObjectInstantiator
 */
@SuppressWarnings("all")
public class SunReflectionFactorySerializationInstantiator implements ObjectInstantiator {

    private final Constructor mungedConstructor;

    public SunReflectionFactorySerializationInstantiator(final Class type) {

        Class nonSerializableAncestor = SerializationInstantiatorHelper.getNonSerializableSuperClass(type);
        ReflectionFactory reflectionFactory = ReflectionFactory.getReflectionFactory();
        Constructor nonSerializableAncestorConstructor;
        try {
            nonSerializableAncestorConstructor = nonSerializableAncestor.getConstructor((Class[]) null);
        } catch (NoSuchMethodException e) {
            /**
             * @todo (Henri) I think we should throw a NotSerializableException
             *       just to put the same message a ObjectInputStream.
             *       Otherwise, the user won't know if the null returned if a
             *       "Not serializable", a "No default constructor on ancestor"
             *       or a "Exception in constructor"
             */
            throw new ObjenesisException(new NotSerializableException(type + " has no suitable superclass constructor"));
        }

        mungedConstructor = reflectionFactory.newConstructorForSerialization(type, nonSerializableAncestorConstructor);
        mungedConstructor.setAccessible(true);
    }

    public Object newInstance() {
        try {
            return mungedConstructor.newInstance((Object[]) null);
        } catch (Exception e) {
            throw new ObjenesisException(e);
        }
    }
}
