/**
 * 
 */
package net.vidageek.mirror.fixtures;

/**
 * @author jonasabreu
 * 
 */
public class SuperClassFixture {

    public String superClassString;

    public static String staticSuperClassField;

    @SuppressWarnings("unused")
    private final String superClassPrivateField;

    public SuperClassFixture(final String value) {
        superClassPrivateField = value;
    }

    public int superClassMethod() {
        return 0;
    }

    @SuppressWarnings("unused")
    private int superClassPrivateMethod() {
        return 1;
    }

    @SuppressWarnings("unused")
    private void overridenMethod() {

    }

    @SuppressWarnings("unused")
    private void overridenMethodWithTwoArgs(final int i, final String string) {

    }

}
