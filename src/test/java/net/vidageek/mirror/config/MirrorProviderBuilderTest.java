/**
 * 
 */
package net.vidageek.mirror.config;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import net.vidageek.mirror.fake.FakeProvider;
import net.vidageek.mirror.provider.ReflectionProvider;
import net.vidageek.mirror.provider.java.DefaultMirrorReflectionProvider;

import org.junit.Test;

/**
 * @author jonasabreu
 * 
 */
public class MirrorProviderBuilderTest {

	@Test
	public void testThatInstantiatesPureJavaReflectionProviderIfPropertiesNotFound() {
		ReflectionProvider provider = new MirrorProviderBuilder(null).createProvider();

		assertEquals(DefaultMirrorReflectionProvider.class, provider.getClass());
	}

	@Test
	public void testThatInstantiatesProviderAtProperties() {
		ReflectionProvider provider = new MirrorProviderBuilder(
				asStream("provider.class = net.vidageek.mirror.fake.FakeProvider")).createProvider();

		assertEquals(FakeProvider.class, provider.getClass());
	}

	private InputStream asStream(final String string) {
		return new ByteArrayInputStream(string.getBytes());
	}
}
