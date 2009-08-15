package net.vidageek.mirror.list;

import java.util.Arrays;

import junit.framework.Assert;
import net.vidageek.mirror.dsl.Matcher;
import net.vidageek.mirror.list.dsl.MirrorList;

import org.junit.Test;

/**
 * @author jonasabreu
 * 
 */
final public class BackedMirrorListTest {

    @Test
    public void testThatFilterIsCalled() {
        MirrorList<Integer> list = new BackedMirrorList<Integer>(Arrays.asList(1, 2, 3, 4));
        MirrorList<Integer> result = list.matching(new Matcher<Integer>() {

            public boolean accepts(final Integer element) {
                return element > 1;
            }
        });
        Assert.assertEquals(3, result.size());
        Assert.assertEquals(new Integer(2), result.get(0));
        Assert.assertEquals(new Integer(3), result.get(1));
        Assert.assertEquals(new Integer(4), result.get(2));
    }

}
