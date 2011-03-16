package net.vidageek.mirror.provider.experimental.sun15;

import junit.framework.Test;
import net.vidageek.mirror.provider.test.ReflectionProviderTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author jonasabreu
 * 
 */
@RunWith(Suite.class)
final public class Sun15CompatibilityTest {

	public static Test suite() {
		return new ReflectionProviderTest(new Sun15ReflectionProvider());
	}

}
