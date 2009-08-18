/**
 * 
 */
package net.vidageek.mirror.reflect;

import net.vidageek.mirror.provider.ReflectionProvider;
import net.vidageek.mirror.provider.java.DefaultMirrorReflectionProvider;

import org.junit.Before;
import org.junit.Test;

/**
 * @author jonasabreu
 * 
 */
@SuppressWarnings("unchecked")
public class ReflectionHandlerTest {

    private ReflectionProvider provider;

    @Before
    public void setup() {
        provider = new DefaultMirrorReflectionProvider();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThatThrowsExceptionIfClassIsNull() {
        new DefaultReflectionHandler(provider, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThatThrowsExceptionIfFieldNameIsNull() {
        new DefaultReflectionHandler(provider, DefaultReflectionHandler.class).field(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThatThrowsExceptionIfFieldNameIsEmpty() {
        new DefaultReflectionHandler(provider, DefaultReflectionHandler.class).field("  \n   \t  ");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThatThrowsExceptionIfMethodNameIsNull() {
        new DefaultReflectionHandler(provider, DefaultReflectionHandler.class).method(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThatThrowsExceptionIfMethodNameIsEmpty() {
        new DefaultReflectionHandler(provider, DefaultReflectionHandler.class).method("  \n   \t  ");
    }

}
