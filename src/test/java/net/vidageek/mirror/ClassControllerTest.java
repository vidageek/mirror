/**
 * 
 */
package net.vidageek.mirror;

import net.vidageek.mirror.provider.java.DefaultMirrorReflectionProvider;

import org.junit.Test;

/**
 * @author jonasabreu
 * 
 */
public class ClassControllerTest {

	@SuppressWarnings("unchecked")
	@Test(expected = IllegalArgumentException.class)
	public void testThatThrowsExceptionIfClassIsNull() {
		new DefaultClassController(new DefaultMirrorReflectionProvider(), null);
	}

}
