package net.vidageek.mirror.set;

import junit.framework.Assert;
import net.vidageek.mirror.dsl.Mirror;
import net.vidageek.mirror.fixtures.FieldFixture;
import net.vidageek.mirror.provider.ReflectionProvider;
import net.vidageek.mirror.provider.java.DefaultMirrorReflectionProvider;

import org.junit.Before;
import org.junit.Test;

/**
 * @author jonasabreu
 * 
 */
public class FieldSetterByNameTest {

	private ReflectionProvider provider;

	@Before
	public void setup() {
		provider = new DefaultMirrorReflectionProvider();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testThatThrowsExceptionIfFieldNameIsNull() {
		new FieldSetterByName(provider, null, new Object(), this.getClass());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testThatThrowsExceptionIfFieldNameIsBlank() {
		new FieldSetterByName(provider, "  \n  \t  ", new Object(), this.getClass());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testThatThrowsExceptionIfClassIsNull() {
		new FieldSetterByName(provider, "field", new Object(), null);
	}

	@Test
	public void testThatCanSetFieldIfFieldIsFinal() {
		final FieldFixture fixture = new FieldFixture(0);
		new FieldSetterByName(provider, "finalField", fixture, FieldFixture.class).withValue(2);
		Assert.assertEquals(2, new Mirror(provider).on(fixture).get().field("finalField"));
	}
}
