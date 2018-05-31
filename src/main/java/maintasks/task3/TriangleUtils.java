package maintasks.task3;


import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TriangleUtils {

    public static boolean isTriangle(ITriangle triangle) {
        return ((triangle.getX2() - triangle.getX1()) * (triangle.getY3() - triangle.getY1())
                        - (triangle.getX3() - triangle.getX1()) * (triangle.getY2() - triangle.getY1())) / 2 != 0;
    }


    public static void validateCoordinates(ITriangle triangle) {
        if (((triangle.getX2() - triangle.getX1() == 0) &&
                (triangle.getY2() - triangle.getY1() == 0))

                || ((triangle.getX3() - triangle.getX2() == 0) &&
                (triangle.getY3() - triangle.getY2() == 0))

                || ((triangle.getX3() - triangle.getX1() == 0) &&
                (triangle.getY3() - triangle.getY1() == 0))
                ) throw new IllegalStateException("Координаты вершин треугольника не должны совпадать");

    }


    public static boolean isRihtTriangle(ITriangle triangle) {
        BigInteger powa = BigInteger.valueOf(triangle.getX2() - triangle.getX1()).pow(2)
                .add(BigInteger.valueOf(triangle.getY2() - triangle.getY1()).pow(2));

        BigInteger powb = BigInteger.valueOf(triangle.getX3() - triangle.getX2()).pow(2)
                .add(BigInteger.valueOf(triangle.getY3() - triangle.getY2()).pow(2));

        BigInteger powc = BigInteger.valueOf(triangle.getX3() - triangle.getX1()).pow(2)
                .add(BigInteger.valueOf(triangle.getY3() - triangle.getY1()).pow(2));

        List<BigInteger> toSort = new ArrayList<>();
        toSort.add(powa);
        toSort.add(powb);
        toSort.add(powc);

        Collections.sort(toSort);
        return toSort.get(2).equals(toSort.get(0).add(toSort.get(1)));
    }
}
