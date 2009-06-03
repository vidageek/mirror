package net.vidageek.mirror.invoke;

import static org.junit.Assert.assertEquals;
import net.vidageek.mirror.dsl.Mirror;
import net.vidageek.mirror.exception.MirrorException;
import net.vidageek.mirror.fixtures.ConstructorFixture;

import org.junit.Test;

/**
 * 
 * @author jonasabreu
 * 
 */
public class ClassInstantiationTest {

    @Test
    public void testThatNewInstanceReturnsAnInstanceOfCorrectClass() {
        ConstructorFixture instance = new Mirror().on(ConstructorFixture.class).invoke().constructor().withoutArgs();
        assertEquals(ConstructorFixture.class, instance.getClass());
        assertEquals(new Integer(0), instance.getConstructor());
    }

    @Test
    public void testThatNewInstanceWithOneArgWorks() {
        ConstructorFixture instance = new Mirror().on(ConstructorFixture.class).invoke().constructor().withArgs("");

        assertEquals(ConstructorFixture.class, instance.getClass());
        assertEquals(new Integer(1), instance.getConstructor());
    }

    @Test
    public void testThatNewInstanceWithTwoArgsWorks() {
        ConstructorFixture instance = new Mirror().on(ConstructorFixture.class).invoke().constructor().withArgs("", 1);

        assertEquals(ConstructorFixture.class, instance.getClass());
        assertEquals(new Integer(2), instance.getConstructor());
    }

    @Test
    public void testThatNewInstanceWithPrimitiveTypesWorks() {
        ConstructorFixture instance = new Mirror().on(ConstructorFixture.class).invoke().constructor().withArgs(1, 1L,
                true);

        assertEquals(ConstructorFixture.class, instance.getClass());
        assertEquals(new Integer(3), instance.getConstructor());
    }

    @Test
    public void testThatInvokesPrivateConstructor() {
        ConstructorFixture instance = new Mirror().on(ConstructorFixture.class).invoke().constructor().withArgs(1L);

        assertEquals(ConstructorFixture.class, instance.getClass());
        assertEquals(new Integer(4), instance.getConstructor());
    }

    @Test(expected = MirrorException.class)
    public void testThatThrowsExceptionIfConstructorIsNotFound() {
        new Mirror().on(ConstructorFixture.class).invoke().constructor().withArgs(1L, "");

    }

    @Test
    public void testThatCanCallConstructorWithArray() {
        ConstructorFixture instance = new Mirror().on(ConstructorFixture.class).invoke().constructor().withArgs(
                new int[10]);

        assertEquals(ConstructorFixture.class, instance.getClass());
        assertEquals(new Integer(5), instance.getConstructor());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThatThrowsExceptionIfStringIsNull() {
        new Mirror().on((String) null).invoke().constructor().withArgs("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThatThrowsExceptionIfStringIsBlank() {
        new Mirror().on("  \n  \t  ").invoke().constructor().withArgs("");
    }

}
