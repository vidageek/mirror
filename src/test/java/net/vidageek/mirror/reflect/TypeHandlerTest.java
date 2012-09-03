package net.vidageek.mirror.reflect;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

public class TypeHandlerTest {

	@Test
	public void testThatReflectClassIsACollection() {
		assertTrue(new DefaultTypeHandler(ArrayList.class).isCollection());
		assertFalse(new DefaultTypeHandler(Integer.class).isCollection());
	}

	@Test
	public void testThatReflectClassIsAPrimitive() {
		assertTrue(new DefaultTypeHandler(Integer.class).isPrimitive());
		assertFalse(new DefaultTypeHandler(ArrayList.class).isPrimitive());
	}
}