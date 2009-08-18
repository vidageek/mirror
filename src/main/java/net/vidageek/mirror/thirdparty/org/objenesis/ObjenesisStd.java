package net.vidageek.mirror.thirdparty.org.objenesis;

import net.vidageek.mirror.thirdparty.org.objenesis.strategy.StdInstantiatorStrategy;

/**
 * Objenesis implementation using the {@link net.vidageek.mirror.thirdparty.org.objenesis.strategy.StdInstantiatorStrategy}.
 * 
 * @author Henri Tremblay
 */
public class ObjenesisStd extends ObjenesisBase {

   /**
    * Default constructor using the {@link net.vidageek.mirror.thirdparty.org.objenesis.strategy.StdInstantiatorStrategy}
    */
   public ObjenesisStd() {
      super(new StdInstantiatorStrategy());
   }

   /**
    * Instance using the {@link net.vidageek.mirror.thirdparty.org.objenesis.strategy.StdInstantiatorStrategy} with or without
    * caching {@link net.vidageek.mirror.thirdparty.org.objenesis.instantiator.ObjectInstantiator}s
    * 
    * @param useCache If {@link net.vidageek.mirror.thirdparty.org.objenesis.instantiator.ObjectInstantiator}s should be cached
    */
   public ObjenesisStd(boolean useCache) {
      super(new StdInstantiatorStrategy(), useCache);
   }
}
