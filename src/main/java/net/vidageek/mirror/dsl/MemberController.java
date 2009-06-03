package net.vidageek.mirror.dsl;

import net.vidageek.mirror.reflect.dsl.AllMemberHandler;
import net.vidageek.mirror.reflect.dsl.MemberHandler;

/**
 * 
 * @author donizetti
 *
 */
public interface MemberController {
	/**
	 * This part of the DSL controls reflection of single reflection elements.
	 * 
	 * @return An object to control reflection of single elements.
	 */
	public MemberHandler reflect() ;
	
	 /**
     * This part of the DSL controls reflection of a list of reflection
     * elements.
     * 
     * @return An object to control reflection of lists of elements.
     */
    public AllMemberHandler reflectAll() ;

}
