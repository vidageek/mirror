package net.vidageek.mirror.thirdparty.org.objenesis;

import net.vidageek.mirror.thirdparty.org.objenesis.strategy.SerializingInstantiatorStrategy;

/**
 * Objenesis implementation using the {@link SerializingInstantiatorStrategy}.
 * 
 * @author Henri Tremblay
 */
public class ObjenesisSerializer extends ObjenesisBase {

   /**
    * Default constructor using the {@link net.vidageek.mirror.thirdparty.org.objenesis.strategy.SerializingInstantiatorStrategy}
    */
   public ObjenesisSerializer() {
      super(new SerializingInstantiatorStrategy());
   }

   /**
    * Instance using the {@link net.vidageek.mirror.thirdparty.org.objenesis.strategy.SerializingInstantiatorStrategy} with or without caching
    * {@link net.vidageek.mirror.thirdparty.org.objenesis.instantiator.ObjectInstantiator}s
    * 
    * @param useCache If {@link net.vidageek.mirror.thirdparty.org.objenesis.instantiator.ObjectInstantiator}s should be cached
    */
   public ObjenesisSerializer(boolean useCache) {
      super(new SerializingInstantiatorStrategy(), useCache);
   }
}
