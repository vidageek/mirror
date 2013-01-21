package net.vidageek.mirror.bean;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

final public class BeanTest {

	@Test
	public void testThatGeneratesGetter() {
		assertEquals(2, new Bean().getter("field").size());
		assertTrue(new Bean().getter("field").contains("getField"));
		assertTrue(new Bean().getter("field").contains("isField"));
		assertTrue(new Bean().getter("Field").contains("isField"));
		assertTrue(new Bean().getter("Field").contains("getField"));
	}

	@Test
	public void testThatGeneratesSetter() {
		assertEquals("setField", new Bean().setter("field"));
		assertEquals("setField", new Bean().setter("Field"));
	}

}
