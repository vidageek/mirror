package net.vidageek.mirror.fixtures;

/**
 * 
 * @author jonasabreu
 * 
 */
public class MethodFixture {

    private static Integer argNum;

    public MethodFixture() {
        argNum = -1;
    }

    @AnnotationFixture
    @AnotherAnnotationFixture
    public String methodWithNoArgs() {
        argNum = 0;
        return "";
    }

    @AnnotationFixture
    @AnotherAnnotationFixture
    public int methodWithOneArg(final String string) {
        argNum = 1;
        return 0;
    }

    public void methodWithFourArgs(final String string, final Boolean b, final Class<?> c, final Long l) {
        argNum = 4;
    }

    public void methodWithTwoPrimitives(final int a, final boolean b) {
        argNum = 2;
    }

    @SuppressWarnings("unused")
    private void privateMethod() {
        argNum = 100;
    }

    public static void staticMethod() {
        argNum = 200;
    }

    public static void staticMethodWithOneArg(final String s) {
        argNum = 300;
    }

    public void methodWithArray(final int[] i) {
        argNum = 400;
    }

    public void methodWithVarArgs(final int... i) {
        argNum = 500;
    }

    public void overloadedMethod() {

    }

    public void overloadedMethod(final int i) {

    }

    public static Integer getArgNum() {
        return argNum;
    }

}
