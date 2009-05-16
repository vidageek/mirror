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

}
