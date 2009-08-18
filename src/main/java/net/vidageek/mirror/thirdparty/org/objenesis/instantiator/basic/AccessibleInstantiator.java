package net.vidageek.mirror.thirdparty.org.objenesis.instantiator.basic;

/**
 * Instantiates a class by grabbing the no-args constructor, making it
 * accessible and then calling Constructor.newInstance(). Although this still
 * requires no-arg constructors, it can call non-public constructors (if the
 * security manager allows it).
 * 
 * @see net.vidageek.mirror.thirdparty.org.objenesis.instantiator.ObjectInstantiator
 */
@SuppressWarnings("all")
public class AccessibleInstantiator extends ConstructorInstantiator {

    public AccessibleInstantiator(final Class type) {
        super(type);
        if (constructor != null) {
            constructor.setAccessible(true);
        }
    }
}
