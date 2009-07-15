package net.vidageek.mirror.bean;

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

    public String getter(final String fieldName) {
        return "get" + capitalize(fieldName);
    }

    public String setter(final String fieldName) {
        return "set" + capitalize(fieldName);
    }

}
