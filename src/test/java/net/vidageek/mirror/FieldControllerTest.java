package net.vidageek.mirror;

import net.vidageek.mirror.provider.java.PureJavaReflectionProvider;

import org.junit.Test;

/**
 * 
 * @author dnfeitosa
 * 
 */
public class FieldControllerTest {

	@Test(expected = IllegalArgumentException.class)
	public void testThatThrowsExceptionIfFieldIsNull() {
		new DefaultFieldController(new PureJavaReflectionProvider(), null);
	}

}
