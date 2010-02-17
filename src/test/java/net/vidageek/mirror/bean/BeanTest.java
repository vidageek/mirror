package net.vidageek.mirror.bean;

import junit.framework.Assert;

import org.junit.Test;

final public class BeanTest {

	@Test
	public void testThatGeneratesGetter() {
		Assert.assertEquals(2, new Bean().getter("field").size());
		Assert.assertTrue(new Bean().getter("field").contains("getField"));
		Assert.assertTrue(new Bean().getter("field").contains("isField"));
		Assert.assertTrue(new Bean().getter("Field").contains("isField"));
		Assert.assertTrue(new Bean().getter("Field").contains("getField"));
	}

	@Test
	public void testThatGeneratesSetter() {
		Assert.assertEquals("setField", new Bean().setter("field"));
		Assert.assertEquals("setField", new Bean().setter("Field"));
	}

}
