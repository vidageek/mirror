package net.vidageek.mirror.reflect.dsl;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import net.vidageek.mirror.list.dsl.Matcher;
import net.vidageek.mirror.list.dsl.MirrorList;
import net.vidageek.mirror.provider.ClassReflectionProvider;

public interface AllReflectionHandler<T> {
	/**
	 * Use this method to reflect all fields on the wrapped class
	 * 
	 * @return The list of fields or an empty list if none was found.
	 * @see ClassReflectionProvider#reflectAllFields()
	 */
	public MirrorList<Field> fields();

	/**
	 * Use this method to reflect all methods on the wrapped class
	 * 
	 * @return The list of methods or an empty list if none was found.
	 * @see ClassReflectionProvider#reflectAllMethods()
	 */
	public MirrorList<Method> methods();

	/**
	 * Use this method to reflect all constructors on the wrapped class
	 * 
	 * @return The list of constructors or an empty list if none was found.
	 * @see ClassReflectionProvider#reflectAllConstructors()
	 */
	public MirrorList<Constructor<T>> constructors();

	/**
	 * Use this method to reflect all annotations on a AccessibleObject
	 * 
	 * @return An object responsible for reflecting annotations.
	 */
	public AllAnnotationsHandler annotations();

	/**
	 * 
	 * Use this method to reflect all setters that follow JavaBean convention
	 * 
	 * @return A list of setter methods
	 */
	public MirrorList<Method> setters();

	/**
	 * 
	 * Use this method to reflect all getters that follow JavaBean convention
	 * 
	 * @return A list of getter methods
	 */
	public MirrorList<Method> getters();

	/**
	 * Use this method to reflect all fields on the wrapped class that matches
	 * matcher.
	 * 
	 * @return The list of fields or an empty list if none was accepted by the
	 *         matcher.
	 * @see AllReflectionHandler#fields()
	 * @deprecated Use fields().matching(Matcher<Field>) instead.
	 */
	@Deprecated
	public List<Field> fieldsMatching(Matcher<Field> matcher);

	/**
	 * Use this method to reflect all methods on the wrapped class that matches
	 * matcher.
	 * 
	 * @return The list of methods or an empty list if none was accepted by the
	 *         matcher.
	 * @see AllReflectionHandler#methods()
	 * @deprecated Use methods().matching(Matcher<Method>) instead.
	 */
	@Deprecated
	public List<Method> methodsMatching(Matcher<Method> matcher);

	/**
	 * Use this method to reflect all constructors on the wrapped class that
	 * matches matcher.
	 * 
	 * @return The list of constructors or an empty list if none was accepted by
	 *         the matcher.
	 * @see AllReflectionHandler#constructors()
	 * @deprecated Use constructors().matching(Matcher<Constructor<T>>) instead.
	 */
	@Deprecated
	public List<Constructor<T>> constructorsMatching(Matcher<Constructor<T>> matcher);

	/**
	 * Use this method to reflect all annotations on the wrapped
	 * AnnotatedElement that matches matcher.
	 * 
	 * @return The list of annotations or an empty list if none was accepted by
	 *         the matcher.
	 * @see AllReflectionHandler#annotations()
	 * @deprecated This functionality is going to be dropped.
	 */
	@Deprecated
	public List<Annotation> annotationsMatching(Matcher<Annotation> matcher);

}
