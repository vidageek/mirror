package net.vidageek.mirror.reflect;

import net.vidageek.mirror.provider.java.PureJavaReflectionProvider;

import org.junit.Test;

/**
 * 
 * @author dnfeitosa
 * 
 */
public class ParameterizedElementHandlerTest {

	@Test(expected = IllegalArgumentException.class)
	public void testThatThrowExceptionIfAccessorIsNull() {
		new DefaultParameterizedElementHandler(new PureJavaReflectionProvider(), null);
	}

}
