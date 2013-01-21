package net.vidageek.mirror.list;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import net.vidageek.mirror.list.dsl.Mapper;
import net.vidageek.mirror.list.dsl.Matcher;
import net.vidageek.mirror.list.dsl.MirrorList;

import org.junit.Test;

/**
 * @author jonasabreu
 * 
 */
final public class BackedMirrorListTest {

	@Test
	public void testFilter() {
		MirrorList<Integer> list = new BackedMirrorList<Integer>(Arrays.asList(1, 2, 3, 4));
		MirrorList<Integer> result = list.matching(new Matcher<Integer>() {

			public boolean accepts(final Integer element) {
				return element > 1;
			}
		});
		
		assertEquals(3, result.size());
		assertEquals(new Integer(2), result.get(0));
		assertEquals(new Integer(3), result.get(1));
		assertEquals(new Integer(4), result.get(2));
	}

	@Test
	public void testMap() {
		MirrorList<Integer> list = new BackedMirrorList<Integer>(Arrays.asList(1, 2, 3, 4));
		MirrorList<String> result = list.mappingTo(new Mapper<Integer, String>() {

			public String map(final Integer element) {
				return "" + element;
			}

		});
		
		assertEquals(4, result.size());
		assertEquals("1", result.get(0));
		assertEquals("2", result.get(1));
		assertEquals("3", result.get(2));
		assertEquals("4", result.get(3));
	}

}
