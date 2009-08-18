package net.vidageek.mirror.thirdparty.org.objenesis.strategy;

import net.vidageek.mirror.thirdparty.org.objenesis.instantiator.ObjectInstantiator;

/**
 * Defines a strategy to determine the best instantiator for a class.
 */
@SuppressWarnings("all")
public interface InstantiatorStrategy {

    /**
     * Create a dedicated instantiator for the given class
     * 
     * @param type
     *            Class that will be instantiate
     * @return Dedicated instantiator
     */
    ObjectInstantiator newInstantiatorOf(Class type);
}
