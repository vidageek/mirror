/**
 * 
 */
package net.vidageek.mirror.reflect;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.lang.reflect.Constructor;

import junit.framework.Assert;
import net.vidageek.mirror.exception.MirrorException;
import net.vidageek.mirror.fixtures.ConstructorFixture;
import net.vidageek.mirror.fixtures.UniqueConstructorFixture;
import net.vidageek.mirror.provider.ReflectionProvider;
import net.vidageek.mirror.provider.java.DefaultMirrorReflectionProvider;

import org.junit.Before;
import org.junit.Test;

/**
 * @author jonasabreu
 * 
 */
public class ConstructorReflectorTest {

    private ReflectionProvider provider;

    @Before
    public void setup() {
        provider = new DefaultMirrorReflectionProvider();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThrowsExceptionIfClassIsNull() {
        new DefaultConstructorReflector<Object>(provider, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThrowsExceptionIfClassesIsNull() {
        new DefaultConstructorReflector<ConstructorFixture>(provider, ConstructorFixture.class)
            .withArgs((Class<?>[]) null);
    }

    @Test
    public void testThatCanReflectConstructor() throws Exception {
        Constructor<ConstructorFixture> c = new DefaultConstructorReflector<ConstructorFixture>(provider,
                ConstructorFixture.class).withArgs(String.class);
        assertNotNull(c);
        assertEquals(c.newInstance("").getClass(), ConstructorFixture.class);
    }

    @Test
    public void testThatCanReflectConstructorWithPrimitiveType() throws Exception {
        Constructor<ConstructorFixture> c = new DefaultConstructorReflector<ConstructorFixture>(provider,
                ConstructorFixture.class).withArgs(long.class);
        c.setAccessible(true);
        assertNotNull(c);
        assertEquals(c.newInstance(1L).getClass(), ConstructorFixture.class);
    }

    @Test
    public void testThatCanReflectConstructorWithWrapperType() throws Exception {
        Constructor<ConstructorFixture> c = new DefaultConstructorReflector<ConstructorFixture>(provider,
                ConstructorFixture.class).withArgs(Long.class);
        c.setAccessible(true);
        assertNotNull(c);
        assertEquals(c.newInstance(new Long(1)).getClass(), ConstructorFixture.class);
    }

    @Test
    public void testThatFindsUniqueConstructor() {
        Constructor<UniqueConstructorFixture> constructor = new DefaultConstructorReflector<UniqueConstructorFixture>(
                provider, UniqueConstructorFixture.class).withAnyArgs();
        assertNotNull(constructor);
        Constructor<UniqueConstructorFixture> constructor2 = new DefaultConstructorReflector<UniqueConstructorFixture>(
                provider, UniqueConstructorFixture.class).withoutArgs();
        Assert.assertEquals(constructor2, constructor);
    }

    @Test(expected = MirrorException.class)
    public void testThatThrowsMirrorExceptionIfMoreThanOneMethodIsFound() {
        new DefaultConstructorReflector<ConstructorFixture>(provider, ConstructorFixture.class).withAnyArgs();
    }

}
