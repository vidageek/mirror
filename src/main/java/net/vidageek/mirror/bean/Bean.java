package net.vidageek.mirror.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to make it a little more pleasant to concatenate strings.
 * 
 * @author jonasabreu
 * 
 */
final public class Bean {

    private String capitalize(final String fieldName) {
        return fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
    }

    public List<String> getter(final String fieldName) {
    	List<String> list = new ArrayList<String>();
    	list.add("get" + capitalize(fieldName));
    	list.add("is" + capitalize(fieldName));
        return list;
    }

    public String setter(final String fieldName) {
        return "set" + capitalize(fieldName);
    }

}
