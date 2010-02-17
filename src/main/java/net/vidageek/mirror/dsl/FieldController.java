package net.vidageek.mirror.dsl;

import net.vidageek.mirror.reflect.dsl.AllMemberHandler;
import net.vidageek.mirror.reflect.dsl.FieldHandler;

/**
 * Class which provides reflection features to Fields.
 * 
 * @author donizetti
 */
public interface FieldController {

	/**
	 * This part of the DSL controls reflection of single reflection elements.
	 * 
	 * @return An object to control reflection of single reflection elements.
	 */
	public FieldHandler reflect();

	/**
	 * This part of the DSL controls reflection of lists of reflection elements.
	 * 
	 * @return An object to control reflection of lists of reflection elements.
	 */
	public AllMemberHandler reflectAll();

}
