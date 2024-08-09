package examples.enums;

public enum Size {
    SMALL(5),
    MIDDLE(10),
    BIG(20),
    LARGE(40);

    private int value;

    Size(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}