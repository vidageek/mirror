package net.vidageek.mirror.provider.sun15;

import junit.framework.Test;
import net.vidageek.mirror.provider.test.ReflectionProviderTest;

/**
 * @author jonasabreu
 * 
 */
final public class Sun15CompatibilityTest {

    public static Test suite() {
        return new ReflectionProviderTest(new Sun15ReflectionProvider());
    }

}
