package net.vidageek.mirror.reflect;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

import java.lang.annotation.Annotation;
import java.util.List;

import net.vidageek.mirror.fixtures.ClassFixture;
import net.vidageek.mirror.fixtures.FieldFixture;
import net.vidageek.mirror.fixtures.MethodFixture;
import net.vidageek.mirror.provider.ReflectionProvider;
import net.vidageek.mirror.provider.java.DefaultMirrorReflectionProvider;

import org.junit.Before;
import org.junit.Test;

public class AllAnnotationsHandlerTest {

	private ReflectionProvider provider;

	@Before
	public void setup() {
		provider = new DefaultMirrorReflectionProvider();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testThatThrowsExceptionIfClassIsNull() {
		new DefaultAllAnnotationsHandler(provider, null);
	}

	@Test
	public void testThatReflectAllAnnotationsInClass() {
		List<Annotation> annotations = new DefaultAllAnnotationsHandler(provider, ClassFixture.class).atClass();

		assertNotNull(annotations);
		assertEquals(2, annotations.size());
	}

	@Test
	public void testThatReflectAllAnnotationsInField() {
		List<Annotation> annotations = new DefaultAllAnnotationsHandler(provider, FieldFixture.class).atField("field");

		assertNotNull(annotations);
		assertEquals(2, annotations.size());
	}

	@Test
	public void testThatReflectAllAnnotationsInMethodWithoutArgs() {
		List<Annotation> annotations = new DefaultAllAnnotationsHandler(provider, MethodFixture.class)
				.atMethod("methodWithNoArgs").withoutArgs();

		assertNotNull(annotations);
		assertEquals(2, annotations.size());
	}

	@Test
	public void testThatReflectAllAnnotationsInMethodWithArgs() {
		List<Annotation> annotations = new DefaultAllAnnotationsHandler(provider, MethodFixture.class)
				.atMethod("methodWithOneArg").withArgs(String.class);

		assertNotNull(annotations);
		assertEquals(2, annotations.size());
	}

}
