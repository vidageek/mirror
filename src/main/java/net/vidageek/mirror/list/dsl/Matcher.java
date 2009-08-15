package net.vidageek.mirror.list.dsl;

/**
 * This is the interface defining matchers. Matchers are used to determine which
 * elements should be included on a list.
 * 
 * @author jonasabreu
 * 
 * @param <T>
 *            Type of the matcher.
 */
public interface Matcher<T> {

    /**
     * This method determines if the element must be allowed to be on the list.
     * 
     * @param element
     * 
     * @return true if the element is allowed. false otherwise.
     */
    boolean accepts(T element);

}
