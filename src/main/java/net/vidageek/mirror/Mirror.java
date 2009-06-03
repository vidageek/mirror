package net.vidageek.mirror;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;

import net.vidageek.mirror.dsl.AccessorsController;
import net.vidageek.mirror.dsl.ClassController;
import net.vidageek.mirror.dsl.FieldController;
import net.vidageek.mirror.dsl.MemberController;
import net.vidageek.mirror.exception.MirrorException;

/**
 * 
 * @deprecated This class was deprecated because of it's static methods. Use #
 *             {@link net.vidageek.mirror.dsl.Mirror} instead.
 * 
 * @author jonasabreu
 */
@Deprecated
public final class Mirror {

    private static final net.vidageek.mirror.dsl.Mirror mirror;

    static {
        mirror = new net.vidageek.mirror.dsl.Mirror();
    }

    private Mirror() {
    }

    /**
     * This method will reflect a Class object that is represented by className.
     * 
     * @param className
     *            Full qualified name of the class you want to reflect.
     * @return A Class object represented by className.
     * @throws MirrorException
     *             if no class on current ClassLoader has class represented by
     *             className.
     * @throws IllegalArgumentException
     *             if className is null or empty.
     */
    public static Class<?> reflectClass(final String className) {
        return mirror.reflectClass(className);
    }

    /**
     * Method to access reflection on clazz.
     * 
     * @param clazz
     *            Class object to be used.
     * @return A object that allows you to access reflection on class clazz.
     * @throws IllegalArgumentException
     *             if clazz is null.
     */
    public static <T> ClassController<T> on(final Class<T> clazz) {
        return mirror.on(clazz);
    }

    /**
     * Method to access reflection on object.
     * 
     * @param object
     *            Object to be used.
     * @return A object that allows you to access reflection on object.
     * @throws IllegalArgumentException
     *             if object is null.
     */
    public static AccessorsController on(final Object object) {
        return mirror.on(object);
    }

    /**
     * Convenience method for {@link Mirror#on(Class)}.
     * 
     * @see Mirror#on(Class)
     * @see Mirror#reflectClass(String)
     */
    public static ClassController<?> on(final String className) {
        return mirror.on(className);
    }

    /**
     * Method to access reflection on any AccessibleObject
     * 
     * @see AccessibleObject
     * @param object
     *            AccessibleObject to be used.
     * @return An object that allows you to access reflection on an
     *         AccessibleObject.
     */
    public static MemberController on(final AnnotatedElement object) {
        return mirror.on(object);
    }

    /**
     * Method to access reflection on any Field.
     * 
     * @param field
     *            to be used
     * @return An object that allows you to access reflection on a Field.
     * @throws IllegalArgumentException
     *             if field is null.
     */
    public static FieldController on(final Field field) {
        return mirror.on(field);
    }

}
