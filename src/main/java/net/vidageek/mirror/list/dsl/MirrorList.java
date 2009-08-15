package net.vidageek.mirror.list.dsl;

import java.util.List;

import net.vidageek.mirror.dsl.Matcher;

/**
 * @author jonasabreu
 * 
 */
public interface MirrorList<T> extends List<T> {

    MirrorList<T> matching(final Matcher<T> matcher);
}
