package net.vidageek.mirror.provider.compatibility;

import junit.framework.Assert;
import net.vidageek.mirror.fixtures.ConstructorThatThrowsException;
import net.vidageek.mirror.provider.ReflectionProvider;

import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

/**
 * @author jonasabreu
 * 
 */
@RunWith(Theories.class)
final public class ObjenesisConstructorBypassingReflectionProviderCompatibilityTest implements
		ReflectionProviderDatapoints {

	@Theory
	public void testThatInstantiatesWithoutInvokingAConstructor(final ReflectionProvider r) {
		ConstructorThatThrowsException instance = r
				.getConstructorBypassingReflectionProvider(ConstructorThatThrowsException.class).bypassConstructor();
		Assert.assertNotNull(instance);
	}

}
