package maintasks.task3;


import org.junit.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class Task3Test {

    @Test
    public void rightTriangle() {
        ITriangle triangle = ITriangleProvider.getRightTriangle();
        checkCoordinates(triangle);
        checkTriangle(triangle);
        checkPifagor(triangle);

    }


    @Test(expected = AssertionError.class)
    public void notRightTriangle() {
        ITriangle triangle = ITriangleProvider.getTriangle();
        checkCoordinates(triangle);
        checkTriangle(triangle);
        checkPifagor(triangle);

    }

    @Test(expected = AssertionError.class)
    public void theSameCoordinates() {
        ITriangle triangle = ITriangleProvider.getTheSameCoordinates();
        checkCoordinates(triangle);
        checkTriangle(triangle);

    }

    @Test(expected = AssertionError.class)
    public void line() {
        ITriangle triangle = ITriangleProvider.getLine();
        checkCoordinates(triangle);
        checkTriangle(triangle);

    }

    public void checkCoordinates(ITriangle triangle) {
        assertFalse("Координаты вершин треугольника не должны совпадать", (triangle.getX2() - triangle.getX1() == 0) &&
                (triangle.getY2() - triangle.getY1() == 0));

        assertFalse("Координаты вершин треугольника не должны совпадать", (triangle.getX3() - triangle.getX2() == 0) &&
                (triangle.getY3() - triangle.getY2() == 0));

        assertFalse("Координаты вершин треугольника не должны совпадать", (triangle.getX3() - triangle.getX1() == 0) &&
                (triangle.getY3() - triangle.getY1() == 0));

    }

    public void checkTriangle(ITriangle triangle) {
        assertNotEquals(0, ((triangle.getX2() - triangle.getX1()) * (triangle.getY3() - triangle.getY1())
                - (triangle.getX3() - triangle.getX1()) * (triangle.getY2() - triangle.getY1())) / 2);

    }

    public void checkPifagor(ITriangle triangle) {
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

        assertTrue("Сумма квадратов сторон не равна длине самой длинной стороны", toSort.get(2).equals(toSort.get(0).add(toSort.get(1))));

    }
}
