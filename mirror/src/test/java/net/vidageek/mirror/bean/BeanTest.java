package net.vidageek.mirror.bean;

import junit.framework.Assert;

import org.junit.Test;

final public class BeanTest {

    @Test
    public void testThatGeneratesGetter() {
        Assert.assertEquals("getField", new Bean().getter("field"));
        Assert.assertEquals("getField", new Bean().getter("Field"));
    }

    @Test
    public void testThatGeneratesSetter() {
        Assert.assertEquals("setField", new Bean().setter("field"));
        Assert.assertEquals("setField", new Bean().setter("Field"));
    }

}
