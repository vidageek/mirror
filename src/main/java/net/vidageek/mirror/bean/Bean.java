package net.vidageek.mirror.bean;

import java.util.Arrays;
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
		String capitalized = capitalize(fieldName);
		return Arrays.asList("get" + capitalized, "is" + capitalized);
	}

	public String setter(final String fieldName) {
		return "set" + capitalize(fieldName);
	}

}
