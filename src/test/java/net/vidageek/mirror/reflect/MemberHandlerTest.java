package net.vidageek.mirror.reflect;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import net.vidageek.mirror.dsl.Mirror;
import net.vidageek.mirror.fixtures.AnnotationFixture;
import net.vidageek.mirror.fixtures.ConstructorFixture;
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
public class MemberHandlerTest {

    private ReflectionProvider provider;

    @Before
    public void setup() {
        provider = new DefaultMirrorReflectionProvider();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThatThrowsExceptionIfMemberIsNull() {
        new DefaultMemberHandler(provider, null);
    }

    @Test
    public void testThatReturnAnnotationFromConstructor() {
        final Constructor<ConstructorFixture> constructor = new Mirror()
            .on(ConstructorFixture.class)
            .reflect()
            .constructor()
            .withoutArgs();
        assertNotNull(constructor);

        final Annotation annotation = new DefaultMemberHandler(provider, constructor)
            .annotation(AnnotationFixture.class);
        assertNotNull(annotation);
        assertTrue(AnnotationFixture.class.isAssignableFrom(annotation.getClass()));
    }

    @Test
    public void testThatReturnAnnotationFromMethod() {
        final Method method = new Mirror().on(MethodFixture.class).reflect().method("methodWithNoArgs").withoutArgs();
        assertNotNull(method);

        final Annotation annotation = new DefaultMemberHandler(provider, method).annotation(AnnotationFixture.class);
        assertNotNull(annotation);
        assertTrue(AnnotationFixture.class.isAssignableFrom(annotation.getClass()));
    }

    @Test
    public void testThatReturnAnnotationFromField() {
        final Field field = new Mirror().on(FieldFixture.class).reflect().field("field");
        assertNotNull(field);

        final Annotation annotation = new DefaultMemberHandler(provider, field).annotation(AnnotationFixture.class);
        assertNotNull(annotation);
        assertTrue(AnnotationFixture.class.isAssignableFrom(annotation.getClass()));
    }
}
