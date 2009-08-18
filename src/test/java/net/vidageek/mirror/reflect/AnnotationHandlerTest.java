package net.vidageek.mirror.reflect;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.lang.annotation.Annotation;

import net.vidageek.mirror.fixtures.AnnotationFixture;
import net.vidageek.mirror.fixtures.ClassFixture;
import net.vidageek.mirror.fixtures.FieldFixture;
import net.vidageek.mirror.fixtures.MethodFixture;
import net.vidageek.mirror.provider.ReflectionProvider;
import net.vidageek.mirror.provider.java.DefaultMirrorReflectionProvider;

import org.junit.Before;
import org.junit.Test;

/**
 * @author dnfeitosa
 * 
 */
@SuppressWarnings("unchecked")
public class AnnotationHandlerTest {

    private ReflectionProvider provider;

    @Before
    public void setup() {
        provider = new DefaultMirrorReflectionProvider();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThatThrowsExceptionIfClassIsNull() {
        new DefaultAnnotationHandler(provider, null, AnnotationFixture.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThatThrowsExceptionIfAnnotationIsNull() {
        new DefaultAnnotationHandler(provider, FieldFixture.class, null);
    }

    @Test
    public void testThatReturnAnnotationAtClass() throws Exception {
        Annotation annotation = new DefaultAnnotationHandler(provider, ClassFixture.class, AnnotationFixture.class).atClass();

        assertNotNull(annotation);
        assertTrue(AnnotationFixture.class.isAssignableFrom(annotation.getClass()));
    }

    @Test
    public void testThatReturnAnnotationAtField() throws Exception {
        Annotation annotation = new DefaultAnnotationHandler(provider, FieldFixture.class, AnnotationFixture.class)
                .atField("field");

        assertNotNull(annotation);
        assertTrue(AnnotationFixture.class.isAssignableFrom(annotation.getClass()));
    }

    @Test
    public void testThatReturnAnnotationAtMethodWithoutArgs() throws Exception {
        Annotation annotation = new DefaultAnnotationHandler(provider, MethodFixture.class, AnnotationFixture.class).atMethod(
                "methodWithNoArgs").withoutArgs();

        assertNotNull(annotation);
        assertTrue(AnnotationFixture.class.isAssignableFrom(annotation.getClass()));
    }

    @Test
    public void testThatReturnAnnotationAtMethodWithArgs() throws Exception {
        Annotation annotation = new DefaultAnnotationHandler(provider, MethodFixture.class, AnnotationFixture.class).atMethod(
                "methodWithOneArg").withArgs(String.class);

        assertNotNull(annotation);
        assertTrue(AnnotationFixture.class.isAssignableFrom(annotation.getClass()));
    }
}
