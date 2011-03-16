/**
 * 
 */
package net.vidageek.mirror.provider.compatibility;

import java.lang.reflect.Constructor;

import net.vidageek.mirror.fixtures.ConstructorFixture;
import net.vidageek.mirror.provider.ReflectionProvider;
import net.vidageek.mirror.provider.java.DefaultMirrorReflectionProvider;

import org.junit.Assert;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

/**
 * @author jonasabreu
 * 
 */
@RunWith(Theories.class)
public class ConstructorReflectionProviderCompatibilityTest implements ReflectionProviderDatapoints {

	private final ReflectionProvider defaultProvider = new DefaultMirrorReflectionProvider();

	@Theory
	public void testInstantiate(final ReflectionProvider r) {
		Constructor<ConstructorFixture> constructor = defaultProvider
				.getClassReflectionProvider(ConstructorFixture.class).reflectConstructor(new Class<?>[] {});

		ConstructorFixture instance = r.getConstructorReflectionProvider(ConstructorFixture.class, constructor)
				.instantiate(new Object[] {});

		Assert.assertEquals(new Integer(0), instance.getConstructor());

	}

	@Theory
	public void testInstantiateWithPrivateConstructor(final ReflectionProvider r) {
		Constructor<ConstructorFixture> constructor = defaultProvider
				.getClassReflectionProvider(ConstructorFixture.class).reflectConstructor(new Class<?>[] { Long.class });

		ConstructorFixture instance = r.getConstructorReflectionProvider(ConstructorFixture.class, constructor)
				.instantiate(2L);

		Assert.assertEquals(new Integer(4), instance.getConstructor());

	}

	@Theory
	public void testInstantiateWithPrimitiveArgs(final ReflectionProvider r) {
		Constructor<ConstructorFixture> constructor = defaultProvider
				.getClassReflectionProvider(ConstructorFixture.class).reflectConstructor(	new Class<?>[] { int.class,
																									long.class,
																									boolean.class });

		ConstructorFixture instance = r.getConstructorReflectionProvider(ConstructorFixture.class, constructor)
				.instantiate(2, 13L, false);

		Assert.assertEquals(new Integer(3), instance.getConstructor());
	}

	@Theory
	public void testGetParametersForConstructorWithoutArgs(final ReflectionProvider r) {
		Constructor<ConstructorFixture> constructor = defaultProvider
				.getClassReflectionProvider(ConstructorFixture.class).reflectConstructor(new Class<?>[] {});

		Class<?>[] types = r.getConstructorReflectionProvider(ConstructorFixture.class, constructor).getParameters();

		Assert.assertEquals(0, types.length);
	}

	@Theory
	public void testGetParametersForConstructorWithArgs(final ReflectionProvider r) {
		Constructor<ConstructorFixture> constructor = defaultProvider
				.getClassReflectionProvider(ConstructorFixture.class).reflectConstructor(	new Class<?>[] { int.class,
																									long.class,
																									boolean.class });

		Class<?>[] types = r.getConstructorReflectionProvider(ConstructorFixture.class, constructor).getParameters();

		Assert.assertEquals(3, types.length);
		Assert.assertEquals(int.class, types[0]);
		Assert.assertEquals(long.class, types[1]);
		Assert.assertEquals(boolean.class, types[2]);
	}

	@Theory
	public void testThatProviderDoesntChangeAccessibilityWhenReflecting(final ReflectionProvider r) {
		Constructor<ConstructorFixture> constructor = defaultProvider
				.getClassReflectionProvider(ConstructorFixture.class).reflectConstructor(new Class<?>[] { Long.class });

		Assert.assertFalse(constructor.isAccessible());

	}

	@Theory
	public void testSetAccessible(final ReflectionProvider r) {

		Constructor<ConstructorFixture> constructor = defaultProvider
				.getClassReflectionProvider(ConstructorFixture.class).reflectConstructor(new Class<?>[] { Long.class });

		Assert.assertFalse(constructor.isAccessible());

		r.getConstructorReflectionProvider(ConstructorFixture.class, constructor).setAccessible();

		Assert.assertTrue(constructor.isAccessible());

	}

}
