/**
 * 
 */
package net.vidageek.mirror.reflect;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import net.vidageek.mirror.dsl.Mirror;
import net.vidageek.mirror.exception.MirrorException;
import net.vidageek.mirror.list.EqualMethodRemover;
import net.vidageek.mirror.list.SameNameMatcher;
import net.vidageek.mirror.list.dsl.MirrorList;
import net.vidageek.mirror.provider.ReflectionProvider;
import net.vidageek.mirror.reflect.dsl.MethodReflector;

/**
 * This class is responsible for reflecting methods.
 * 
 * @author jonasabreu
 */
public final class DefaultMethodReflector implements MethodReflector {

    private final String methodName;

    private final Class<?> clazz;

    private final ReflectionProvider provider;

    public DefaultMethodReflector(final ReflectionProvider provider, final String methodName, final Class<?> clazz) {
        if ((methodName == null) || (methodName.trim().length() == 0)) {
            throw new IllegalArgumentException("methodName cannot be null or empty");
        }
        if (clazz == null) {
            throw new IllegalArgumentException("clazz cannnot be null");
        }
        this.provider = provider;
        this.methodName = methodName.trim();
        this.clazz = clazz;
    }

    public Method withoutArgs() {
        return withArgs(new Class<?>[0]);
    }

    public Method withArgs(final Class<?>... classes) {
        if (classes == null) {
            throw new IllegalArgumentException("classes cannot be null");
        }
        return provider.getClassReflectionProvider(clazz).reflectMethod(methodName, classes);
    }

    public Method withAnyArgs() {
        MirrorList<Method> sameNameList = new Mirror(provider).on(clazz).reflectAll().methods().matching(
                new SameNameMatcher(methodName));

        if (sameNameList.size() == 0) {
            return null;
        }

        List<Method> list = new ArrayList<Method>(sameNameList.matching(new EqualMethodRemover(sameNameList.get(0))));

        list.add(sameNameList.get(0));

        if (list.size() == 1) {
            return list.get(0);
        }
        throw new MirrorException("more than one method named " + methodName + " was found on class " + clazz.getName()
                + " while attempting to find a uniquely named method. Methods are: " + list);
    }
}
