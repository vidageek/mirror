package net.vidageek.mirror.matcher;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import static net.vidageek.mirror.matcher.MatchType.DONT_MATCH;
import static net.vidageek.mirror.matcher.MatchType.MATCH;
import static net.vidageek.mirror.matcher.MatchType.PERFECT;

import static org.junit.Assert.assertEquals;

/**
 * @author jonasabreu
 * 
 */
public class ClassArrayMatcherTest {

    @Test(expected = IllegalArgumentException.class)
    public void testThatThrowsExceptionIfConstructorArgumentIsNull() {
        new ClassArrayMatcher((Class<?>[]) null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThatThrowsExceptionIfMatchArgumentIsNull() {
        new ClassArrayMatcher(new Class<?>[0]).match((Class<?>[]) null);
    }

    @Test
    public void testThatDifferentNumberOfElementsDontMatch() {
        assertEquals(DONT_MATCH, new ClassArrayMatcher(new Class<?>[0]).match(new Class<?>[2]));
    }

    @Test
    public void testThatEqualClassesGivesPerfectMatch() {
        Class<?>[] classes = { String.class, Object.class, List.class };
        assertEquals(PERFECT, new ClassArrayMatcher(classes).match(classes));
    }

    @Test
    public void testThatEmptyArraysGivesPerfectMatch() {
        Class<?>[] classes = {};
        assertEquals(PERFECT, new ClassArrayMatcher(classes).match(classes));
    }

    @Test
    public void testThatMatchInterfaces() {
        Class<?>[] classes = { ArrayList.class };
        assertEquals(MATCH, new ClassArrayMatcher(classes).match(new Class<?>[] { List.class }));
    }

    @Test
    public void testThatMatchSuperClasses() {
        Class<?>[] classes = { String.class };
        assertEquals(MATCH, new ClassArrayMatcher(classes).match(new Class<?>[] { Object.class }));
    }

    @Test
    public void testThatDifferentClassesDoesntMatch() {
        Class<?>[] classes = { System.class, Object.class, ArrayList.class };
        assertEquals(DONT_MATCH, new ClassArrayMatcher(classes).match(new Class<?>[] { String.class, Object.class,
                List.class }));
    }

    @Test
    public void testThatAcceptsPrimitiveTypesAsWrappers() {
        Class<?>[] classes = { int.class };
        assertEquals(PERFECT, new ClassArrayMatcher(classes).match(new Class<?>[] { Integer.class }));
    }

    @Test
    public void testThatAcceptsWrappersAsPrimitiveTypes() {
        Class<?>[] classes = { Integer.class };
        assertEquals(PERFECT, new ClassArrayMatcher(classes).match(new Class<?>[] { int.class }));
    }
}
