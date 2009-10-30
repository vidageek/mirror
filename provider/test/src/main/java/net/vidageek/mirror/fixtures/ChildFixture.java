/**
 * 
 */
package net.vidageek.mirror.fixtures;

/**
 * @author jonasabreu
 * 
 */
public class ChildFixture extends SuperClassFixture implements InterfaceFixture {

    public ChildFixture() {
        super("");
    }

    public Integer integer;

    public void childMethod() {

    }

    public void interfaceMethod(final String arg) {

    }

    @SuppressWarnings("unused")
    private void overridenMethod() {

    }

    @SuppressWarnings("unused")
    private void overridenMethodWithTwoArgs(final int i, final String string) {

    }

    @Override
    public boolean equals(final Object obj) {
        // TODO Auto-generated method stub
        return super.equals(obj);
    }

}
