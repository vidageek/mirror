package net.vidageek.mirror.list;

import java.lang.reflect.Method;

import net.vidageek.mirror.list.dsl.Matcher;

/**
 * @author jonasabreu
 * 
 */
final public class EqualMethodRemover implements Matcher<Method> {

    private final Method method;

    public EqualMethodRemover(final Method method) {
        this.method = method;
    }

    public boolean accepts(final Method element) {
        return !sameMethodName(element) && !sameArgs(element);
    }

    private boolean sameArgs(final Method element) {
        if (element.getParameterTypes().length != method.getParameterTypes().length) {
            return false;
        }
        int i = 0;
        for (Class<?> type : method.getParameterTypes()) {
            if (element.getParameterTypes()[i] != type) {
                return false;
            }
            i++;
        }
        return true;
    }

    private boolean sameMethodName(final Method element) {
        return element.getName().equals(method);
    }

}
