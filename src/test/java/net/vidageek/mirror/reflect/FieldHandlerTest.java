package net.vidageek.mirror.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import junit.framework.Assert;
import net.vidageek.mirror.dsl.Mirror;
import net.vidageek.mirror.fixtures.AnnotationFixture;
import net.vidageek.mirror.fixtures.FieldFixture;
import net.vidageek.mirror.provider.java.DefaultMirrorReflectionProvider;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author dnfeitosa
 * 
 */
public class FieldHandlerTest {

    private DefaultMirrorReflectionProvider provider;
    private Field annotatedField;

    @Before
    public void setup() {
        provider = new DefaultMirrorReflectionProvider();
        annotatedField = new Mirror().on(FieldFixture.class).reflect().field("field");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThatThrowsExceptionIfFieldIsNull() {
        new DefaultFieldHandler(provider, null);
    }

    @Test
    public void testThatAnnotationsReflectorIsProperlyDispatched() {
        Annotation annotation = new DefaultFieldHandler(provider, annotatedField).annotation(AnnotationFixture.class);

        Assert.assertTrue(AnnotationFixture.class.isAssignableFrom(annotation.getClass()));
    }

}
