package net.vidageek.mirror.list.dsl;

import java.util.List;

/**
 * This is a list interface that creates some methods to perform some functional
 * operations
 * 
 * @author jonasabreu
 * 
 */
public interface MirrorList<T> extends List<T> {

    MirrorList<T> matching(final Matcher<T> matcher);

    <E> MirrorList<E> mapping(Mapper<T, E> mapper);
}
