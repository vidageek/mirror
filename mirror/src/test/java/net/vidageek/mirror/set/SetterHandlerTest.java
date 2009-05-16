/**
 * 
 */
package net.vidageek.mirror.set;

import java.lang.reflect.Field;

import net.vidageek.mirror.provider.ReflectionProvider;
import net.vidageek.mirror.provider.java.PureJavaReflectionProvider;

import org.junit.Before;
import org.junit.Test;

/**
 * @author jonasabreu
 * 
 */
public class SetterHandlerTest {

	private ReflectionProvider provider;

	@Before
	public void setup() {
		provider = new PureJavaReflectionProvider();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testThatThrowsExceptionIfTargetIsNull() {
		new DefaultSetterHandler(provider, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testThatThrowsExceptionIfFieldNameIsNull() {
		new DefaultSetterHandler(provider, new Object()).field((String) null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testThatThrowsExceptionIfFieldIsNull() {
		new DefaultSetterHandler(provider, new Object()).field((Field) null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testThatThrowsExceptionIfFieldNameIsEmpty() {
		new DefaultSetterHandler(provider, new Object()).field("  \t   \n  ");
	}

}
