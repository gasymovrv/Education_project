package examples.enums;

public class EnumTest {
    public static void main(String[] args) throws Exception {
        Size big = Size.BIG;
        System.out.println(big.getValue());
        big.setValue(24626);
        System.out.println(big.getValue());

        Size big2 = Size.BIG;
        System.out.println(big2.getValue());

        Size middle = Size.MIDDLE;
        System.out.println(middle.getValue());
    }
}
