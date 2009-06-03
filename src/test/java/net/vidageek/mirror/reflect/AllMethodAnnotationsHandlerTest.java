package net.vidageek.mirror.reflect;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.lang.annotation.Annotation;
import java.util.List;

import net.vidageek.mirror.fixtures.MethodFixture;
import net.vidageek.mirror.provider.ReflectionProvider;
import net.vidageek.mirror.provider.java.PureJavaReflectionProvider;

import org.junit.Before;
import org.junit.Test;

public class AllMethodAnnotationsHandlerTest {

    private ReflectionProvider provider;

    @Before
    public void setup() {
        provider = new PureJavaReflectionProvider();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThatThrowsExceptionIfClassIsNull() {
        new DefaultAllMethodAnnotationsHandler(provider, null, "method");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThatThrowsExceptionIfMethodNameIsNUll() throws Exception {
        new DefaultAllMethodAnnotationsHandler(provider, MethodFixture.class, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThatThrowsExceptionIfMethodNameIsEmpty() throws Exception {
        new DefaultAllMethodAnnotationsHandler(provider, MethodFixture.class, "  \t  \n  ");
    }

    @Test
    public void testThatReturnAllAnnotationsInMethodWithoutArgs() throws Exception {
        List<Annotation> annotations = new DefaultAllMethodAnnotationsHandler(provider, MethodFixture.class,
                "methodWithNoArgs").withoutArgs();

        assertNotNull(annotations);
        assertEquals(2, annotations.size());
    }

    @Test
    public void testThatReturnAllAnnotationsInMethodWithoArgs() throws Exception {
        List<Annotation> annotations = new DefaultAllMethodAnnotationsHandler(provider, MethodFixture.class,
                "methodWithOneArg").withArgs(String.class);

        assertNotNull(annotations);
        assertEquals(2, annotations.size());
    }

}
