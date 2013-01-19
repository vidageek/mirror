package net.vidageek.mirror.invoke;

import static org.junit.Assert.assertEquals;
import net.vidageek.mirror.fixtures.BeanFixture;
import net.vidageek.mirror.provider.ReflectionProvider;
import net.vidageek.mirror.provider.java.DefaultMirrorReflectionProvider;

import org.junit.Before;
import org.junit.Test;

final public class DefaultSetterMethodHandlerTest {

	private ReflectionProvider provider;

	@Before
	public void setup() {
		provider = new DefaultMirrorReflectionProvider();
	}

	@Test
	public void testThatInvokesSetter() {
		BeanFixture target = new BeanFixture();
		new DefaultSetterMethodHandler(provider, target, "field").withValue("bar");

		assertEquals("bar", target.getField());
	}
}
