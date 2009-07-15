package net.vidageek.mirror.matcher;

import java.util.ArrayList;
import java.util.List;

import net.vidageek.mirror.dsl.Matcher;

/**
 * This class is responsible for filtering a list.
 * 
 * @author jonasabreu
 * 
 */
final public class ListFilter {

    public <A> List<A> filter(final Matcher<A> matcher, final List<A> list) {
        List<A> filteredList = new ArrayList<A>();
        for (A element : list) {
            if (matcher.accepts(element)) {
                filteredList.add(element);
            }
        }
        return filteredList;
    }

}
