package net.vidageek.mirror.reflect.dsl;

import java.lang.annotation.Annotation;

/**
 * 
 * @author donizetti
 *
 */
public interface FieldHandler {
	
	public <T extends Annotation> T annotation(Class<T> annotation) ;

	public ParameterizedElementHandler genericType() ;

}
