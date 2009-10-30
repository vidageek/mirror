package net.vidageek.mirror.provider.sun15;

/**
 * 
 * @author jonasabreu
 * 
 */
public class FieldFixture {

    public volatile int publicField;

    @SuppressWarnings("unused")
    private static final int STATIC_FINAL_FIELD = 1;

    @SuppressWarnings("unused")
    private final int finalField = 1;

    private int field;

    private static int staticField;

    @SuppressWarnings("unused")
    private Object superType;

    public Object referenceField;

    public FieldFixture(final int field) {
        this.field = field;
        FieldFixture.staticField = field;
    }

    public int getField() {
        return field;
    }

    public void setField(final int i) {
        field = i;
    }

    public int getStaticField() {
        return FieldFixture.staticField;
    }

}
