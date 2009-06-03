package net.vidageek.mirror.set;

import net.vidageek.mirror.exception.MirrorException;
import net.vidageek.mirror.fixtures.FieldFixture;
import net.vidageek.mirror.provider.ReflectionProvider;
import net.vidageek.mirror.provider.java.PureJavaReflectionProvider;

import org.junit.Before;
import org.junit.Test;

/**
 * @author jonasabreu
 * 
 */
public class FieldSetterByNameTest {

    private ReflectionProvider provider;

    @Before
    public void setup() {
        provider = new PureJavaReflectionProvider();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThatThrowsExceptionIfFieldNameIsNull() {
        new FieldSetterByName(provider, null, new Object(), this.getClass());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThatThrowsExceptionIfFieldNameIsBlank() {
        new FieldSetterByName(provider, "  \n  \t  ", new Object(), this.getClass());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThatThrowsExceptionIfClassIsNull() {
        new FieldSetterByName(provider, "field", new Object(), null);
    }

    @Test(expected = MirrorException.class)
    public void testThatThrowsExceptionIfFieldIsFinalAndStatic() {
        new FieldSetterByName(provider, "STATIC_FINAL_FIELD", new FieldFixture(0), FieldFixture.class).withValue(0);
    }

    @Test(expected = MirrorException.class)
    public void testThatThrowsExceptionIfFieldIsFinal() {
        new FieldSetterByName(provider, "finalField", new FieldFixture(0), FieldFixture.class).withValue(0);
    }
}
