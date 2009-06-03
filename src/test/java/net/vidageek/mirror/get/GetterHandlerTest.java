package net.vidageek.mirror.get;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Field;

import net.vidageek.mirror.dsl.Mirror;
import net.vidageek.mirror.fixtures.ChildFixture;
import net.vidageek.mirror.fixtures.FieldFixture;
import net.vidageek.mirror.fixtures.SuperClassFixture;
import net.vidageek.mirror.provider.ReflectionProvider;
import net.vidageek.mirror.provider.java.PureJavaReflectionProvider;

import org.junit.Before;
import org.junit.Test;

/**
 * @author jonasabreu
 * 
 */
public class GetterHandlerTest {

    private ReflectionProvider provider;

    @Before
    public void setup() {
        provider = new PureJavaReflectionProvider();
    }

    @Test(expected = IllegalStateException.class)
    public void testThatThrowsExceptionIfTryToGetInstanceFieldOnClass() {
        new Mirror().on(FieldFixture.class).get().field("field");
    }

    @Test
    public void testThatCanGetField() {
        FieldFixture fixture = new FieldFixture(10);
        Integer result = (Integer) new Mirror().on(fixture).get().field("field");
        assertEquals(result, new Integer(fixture.getField()));
    }

    @Test
    public void testThatCanGetStaticField() {
        FieldFixture fixture = new FieldFixture(10);
        Integer result = (Integer) new Mirror().on(FieldFixture.class).get().field("staticField");
        assertEquals(result, new Integer(fixture.getStaticField()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThatThrowsExceptionIfTargetIsNull() {
        new DefaultGetterHandler(provider, (Object) null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThatThrowsExceptionIfClassIsNull() {
        new DefaultGetterHandler(provider, (Class<?>) null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThatThrowsExceptionIfFieldNameIsNull() {
        new DefaultGetterHandler(provider, new Object()).field((String) null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThatThrowsExceptionIfFieldIsNull() {
        new DefaultGetterHandler(provider, new Object()).field((Field) null);
    }

    /*
     * Test to avoid bug #62, described at
     * http://bugs.vidageek.net/bug.php?op=show&bugid=62
     */
    @Test
    public void testThatThrowsExceptionIfFieldDeclaringClassIsNotAssinableFromClass() {
        Field field = new Mirror(provider).on(SuperClassFixture.class).reflect().field("superClassString");

        new DefaultGetterHandler(provider, new ChildFixture()).field(field);
    }

    /*
     * Test to avoid bug #58, described at
     * http://bugs.vidageek.net/bug.php?op=show&bugid=58
     */
    @Test
    public void testThatCanReturnValueFromField() {
        Field field = FieldFixture.class.getDeclaredFields()[0];

        new DefaultGetterHandler(provider, new FieldFixture(1)).field(field);
    }

}
