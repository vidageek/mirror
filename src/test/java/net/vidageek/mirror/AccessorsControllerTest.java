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
public class AccessorsControllerTest {

    @Test(expected = IllegalArgumentException.class)
    public void testThatThrowsExceptionIfTargetIsNull() {
        new DefaultAccessorsController(new DefaultMirrorReflectionProvider(), null);
    }

}
