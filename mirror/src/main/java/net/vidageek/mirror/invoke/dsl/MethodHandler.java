package net.vidageek.mirror.invoke.dsl;

/**
 * Interface for classes responsible for invoking methods.
 * 
 * @author jonasabreu
 */
public interface MethodHandler {

    /**
     * Invoke a method without arguments.
     * 
     * @return The value returned by the method or null if the method was void.
     */
    public Object withoutArgs();

    /**
     * Invoke a method using arguments args.
     * 
     * @param args
     *            arguments to be used to invoke method.
     * @return The value returned by the method or null if the method was void.
     */
    public Object withArgs(final Object... args);

}
