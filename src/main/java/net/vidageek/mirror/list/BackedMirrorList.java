package net.vidageek.mirror.list;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import net.vidageek.mirror.list.dsl.Mapper;
import net.vidageek.mirror.list.dsl.Matcher;
import net.vidageek.mirror.list.dsl.MirrorList;

/**
 * This is list implementation that delegates to a received list.
 * 
 * @author jonasabreu
 * 
 */
final public class BackedMirrorList<T> implements MirrorList<T> {

    private final List<T> list;

    public BackedMirrorList(final List<T> list) {
        this.list = Collections.unmodifiableList(list);
    }

    public MirrorList<T> matching(final Matcher<T> matcher) {
        List<T> filteredList = new ArrayList<T>();
        for (T element : list) {
            if (matcher.accepts(element)) {
                filteredList.add(element);
            }
        }
        return new BackedMirrorList<T>(filteredList);
    }

    public <E> MirrorList<E> mappingTo(final Mapper<T, E> mapper) {
        List<E> mappedList = new ArrayList<E>();
        for (T element : list) {
            mappedList.add(mapper.map(element));
        }
        return new BackedMirrorList<E>(mappedList);
    }

    /*
     * Delegated methods. Please just ignore then.
     */
    public void add(final int index, final T element) {
        list.add(index, element);
    }

    public boolean add(final T o) {
        return list.add(o);
    }

    public boolean addAll(final Collection<? extends T> c) {
        return list.addAll(c);
    }

    public boolean addAll(final int index, final Collection<? extends T> c) {
        return list.addAll(index, c);
    }

    public void clear() {
        list.clear();
    }

    public boolean contains(final Object o) {
        return list.contains(o);
    }

    public boolean containsAll(final Collection<?> c) {
        return list.containsAll(c);
    }

    @Override
    public boolean equals(final Object o) {
        return list.equals(o);
    }

    public T get(final int index) {
        return list.get(index);
    }

    @Override
    public int hashCode() {
        return list.hashCode();
    }

    public int indexOf(final Object o) {
        return list.indexOf(o);
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public Iterator<T> iterator() {
        return list.iterator();
    }

    public int lastIndexOf(final Object o) {
        return list.lastIndexOf(o);
    }

    public ListIterator<T> listIterator() {
        return list.listIterator();
    }

    public ListIterator<T> listIterator(final int index) {
        return list.listIterator(index);
    }

    public T remove(final int index) {
        return list.remove(index);
    }

    public boolean remove(final Object o) {
        return list.remove(o);
    }

    public boolean removeAll(final Collection<?> c) {
        return list.removeAll(c);
    }

    public boolean retainAll(final Collection<?> c) {
        return list.retainAll(c);
    }

    public T set(final int index, final T element) {
        return list.set(index, element);
    }

    public int size() {
        return list.size();
    }

    public List<T> subList(final int fromIndex, final int toIndex) {
        return list.subList(fromIndex, toIndex);
    }

    public Object[] toArray() {
        return list.toArray();
    }

    public <E> E[] toArray(final E[] a) {
        return list.toArray(a);
    }

}
