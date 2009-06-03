/**
 * 
 */
package net.vidageek.mirror.dsl;

import static org.junit.Assert.assertEquals;
import net.vidageek.mirror.exception.MirrorException;
import net.vidageek.mirror.fixtures.ClassFixture;

import org.junit.Test;

/**
 * @author jonasabreu
 * 
 */
public class MirrorTest {

    @Test(expected = IllegalArgumentException.class)
    public void testThatReflectClassThrowsExceptionIfClassNameIsNull() {
        new Mirror().reflectClass(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThatReflectClassThrowsExceptionIfClassNameIsEmpty() {
        new Mirror().reflectClass("  \n  \t  ");
    }

    @Test(expected = MirrorException.class)
    public void testThatReflectClassThrowsMirrorExceptionIfClassDoesntExists() {
        new Mirror().reflectClass("some.class.that.doesnt.Exists");
    }

    @Test
    public void testThatReflectedClassIsCorrect() {
        final Class<?> clazz = new Mirror().reflectClass("net.vidageek.mirror.fixtures.ClassFixture");

        assertEquals(ClassFixture.class, clazz);
    }

    @Test(expected = MirrorException.class)
    public void testThatReflectInvalidPrimitiveClassThrowsMirrorExceptionIfClassDoesntExists() {
        new Mirror().reflectClass("Int");
    }

    @Test
    public void testThatReflectedPrimitiveClassIsCorrect() {
        final Class<?> clazz = new Mirror().reflectClass("int");

        assertEquals(int.class, clazz);
    }

    @Test
    public void testThatReflectedVoidClassIsCorrect() {
        final Class<?> clazz = new Mirror().reflectClass("void");

        assertEquals(void.class, clazz);
    }

}
