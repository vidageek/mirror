/**
 * 
 */
package net.vidageek.mirror.invoke;

import java.lang.reflect.Constructor;

import net.vidageek.mirror.dsl.Mirror;
import net.vidageek.mirror.fixtures.ConstructorFixture;
import net.vidageek.mirror.provider.ReflectionProvider;
import net.vidageek.mirror.provider.java.PureJavaReflectionProvider;

import org.junit.Before;
import org.junit.Test;

/**
 * @author jonasabreu
 * 
 */
public class ConstructorHandlerByConstructorTest {

    private ReflectionProvider provider;

    @Before
    public void setup() {
        provider = new PureJavaReflectionProvider();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThatThrowsExceptionIfClassIsNull() {
        Constructor<ConstructorFixture> con = new Mirror(provider).on(ConstructorFixture.class).reflect()
                .constructor().withoutArgs();
        new ConstructorHandlerByConstructor<ConstructorFixture>(provider, null, con);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThatThrowsExceptionIfConstructorIsNull() {
        new ConstructorHandlerByConstructor<ConstructorFixture>(provider, ConstructorFixture.class, null);
    }

    @SuppressWarnings("unchecked")
    @Test(expected = IllegalArgumentException.class)
    public void testThatThrowsExceptionIfConstrucorIsNotFromClass() {
        Constructor<ConstructorFixture> con = new Mirror(provider).on(ConstructorFixture.class).reflect()
                .constructor().withoutArgs();
        new ConstructorHandlerByConstructor(provider, Object.class, con);
    }
}
