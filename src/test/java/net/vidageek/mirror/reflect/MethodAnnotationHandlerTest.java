package net.vidageek.mirror.reflect;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.lang.annotation.Annotation;

import net.vidageek.mirror.fixtures.AnnotationFixture;
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
public class MethodAnnotationHandlerTest {

	private ReflectionProvider provider;

	@Before
	public void setup() {
		provider = new DefaultMirrorReflectionProvider();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testThatThrowsExceptionIfClassIsNull() throws Exception {
		new DefaultMethodAnnotationHandler(provider, null, "method", AnnotationFixture.class);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testThatThrowsExceptionIfMethodNameIsNull() throws Exception {
		new DefaultMethodAnnotationHandler(provider, MethodFixture.class, null, AnnotationFixture.class);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testThatThrowsExceptionIfMethodNameIsEmpty() throws Exception {
		new DefaultMethodAnnotationHandler(provider, MethodFixture.class, "  \t  \n   ", AnnotationFixture.class);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testThatThrowsExceptionIfAnnotationIsNull() throws Exception {
		new DefaultMethodAnnotationHandler(provider, MethodFixture.class, "method", null);
	}

	@Test
	public void testThatReturnAnnotationInMethodWithoutArgs() {
		Annotation annotation = new DefaultMethodAnnotationHandler(provider, MethodFixture.class, "methodWithNoArgs",
				AnnotationFixture.class).withoutArgs();

		assertNotNull(annotation);
		assertTrue(AnnotationFixture.class.isAssignableFrom(annotation.getClass()));
	}

	@Test
	public void testThatReturnAnnotationInMethodWithArgs() {
		Annotation annotation = new DefaultMethodAnnotationHandler(provider, MethodFixture.class, "methodWithOneArg",
				AnnotationFixture.class).withArgs(String.class);

		assertNotNull(annotation);
		assertTrue(AnnotationFixture.class.isAssignableFrom(annotation.getClass()));
	}

}
