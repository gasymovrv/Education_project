package examples;

import java.math.BigDecimal;
import java.math.RoundingMode;
/**
 * Методы сравнения и округления double
 */
public class DoubleTest extends Test {
    private static Test thisOne = new DoubleTest();

    @Override
    public void contentGo(){
        double x = 0.063451;
        double y = 0.063450+0.000001;
        BigDecimal y1 = new BigDecimal("0.063450");
        BigDecimal y2 = new BigDecimal("0.000001");
        y1 = y1.add(y2);

        System.out.println("x==y="+(x==y));
        System.out.println("x(double)="+x);
        System.out.println("y(double)="+y);

        System.out.println("y1.toString().equals(\"0.063451\") = "+y1.toString().equals("0.063451"));
        System.out.println("y1(BigDecimal)="+y1);

        System.out.println("y(rounding double)=" + new BigDecimal(y).setScale(6, RoundingMode.UP).doubleValue());
    }

    public static void main(String[] args) {
        thisOne.go();
    }
}
