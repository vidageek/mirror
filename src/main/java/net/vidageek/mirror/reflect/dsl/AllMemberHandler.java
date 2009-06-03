package net.vidageek.mirror.reflect.dsl;

import java.lang.annotation.Annotation;
import java.util.List;

import net.vidageek.mirror.provider.AnnotatedElementReflectionProvider;

public interface AllMemberHandler {
	

    /**
     * Use this method to reflect a list of annotations on the AccessibleObject
     * 
     * @return The list of annotation or a empty list if none was found.
     * @see AnnotatedElementReflectionProvider#getAnnotations()
     */
    public List<Annotation> annotations() ;

}
