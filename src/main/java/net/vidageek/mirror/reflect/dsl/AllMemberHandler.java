package net.vidageek.mirror.reflect.dsl;

import java.lang.annotation.Annotation;
import java.util.List;

import net.vidageek.mirror.list.dsl.Matcher;
import net.vidageek.mirror.provider.AnnotatedElementReflectionProvider;

public interface AllMemberHandler {

    /**
     * Use this method to reflect a list of annotations on the AnnotatedElement
     * 
     * @return The list of annotation or a empty list if none was found.
     * @see AnnotatedElementReflectionProvider#getAnnotations()
     */
    public List<Annotation> annotations();

    /**
     * Use this method to reflect all annotations on the wrapped
     * AnnotatedElement that matches matcher.
     * 
     * @return The list of annotations or an empty list if none was accepted by
     *         the matcher.
     * @see AllMemberHandler#annotations()
     * @deprecated Use annotations().matching(Matcher<Annotation>) instead.
     */
    @Deprecated
    public List<Annotation> annotationsMatching(Matcher<Annotation> matcher);

}
