package maintasks.task3;


import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static maintasks.task3.TriangleUtils.isRihtTriangle;
import static maintasks.task3.TriangleUtils.isTriangle;
import static maintasks.task3.TriangleUtils.validateCoordinates;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

public class Task3Test {

    @Mock
    ITriangleProvider provider;


    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Test
    public void rightTriangle() {
        when(provider.getTriangle()).thenReturn(rightTriangle);
        ITriangle triangle = provider.getTriangle();
        validateCoordinates(triangle);
        assertTrue(isTriangle(triangle));
        assertTrue("Сумма квадратов сторон равна длине самой длинной стороны", isRihtTriangle(triangle));

    }


    @Test
    public void notRightTriangle() {
        when(provider.getTriangle()).thenReturn(notRightTriangle);

        ITriangle triangle = provider.getTriangle();
        validateCoordinates(triangle);
        assertTrue(isTriangle(triangle));
        assertFalse("Сумма квадратов сторон не равна длине самой длинной стороны", isRihtTriangle(triangle));
    }


    @Test(expected = IllegalStateException.class)
    public void theSameCoordinatesTest() {
        when(provider.getTriangle()).thenReturn(theSameCoordinates);
        validateCoordinates(provider.getTriangle());
    }

    @Test
    public void isTriangleLineTest() {
        when(provider.getTriangle()).thenReturn(line);

        assertFalse(isTriangle(provider.getTriangle()));
    }

    @Test
    public void isTriangleTriangleTest() {
        when(provider.getTriangle()).thenReturn(notRightTriangle);
        assertTrue(isTriangle(provider.getTriangle()));
    }


    ITriangle rightTriangle = new ITriangle(){
        @Override
        public int getX1() {
            return 1;
        }

        @Override
        public int getY1() {
            return 1;
        }

        @Override
        public int getX2() {
            return 1;
        }

        @Override
        public int getY2() {
            return 3;
        }

        @Override
        public int getX3() {
            return 3;
        }

        @Override
        public int getY3() {
            return 1;
        }
    };

    ITriangle notRightTriangle = new ITriangle(){

        @Override
            public int getX1() {
                return 1;
            }

            @Override
            public int getY1() {
                return 1;
            }

            @Override
            public int getX2() {
                return 1;
            }

            @Override
            public int getY2() {
                return 3;
            }

            @Override
            public int getX3() {
                return 3;
            }

            @Override
            public int getY3() {
                return 2;
            }
        };

        ITriangle theSameCoordinates = new ITriangle(){

            @Override
            public int getX1() {
                return 1;
            }

            @Override
            public int getY1() {
                return 1;
            }

            @Override
            public int getX2() {
                return 1;
            }

            @Override
            public int getY2() {
                return 1;
            }

            @Override
            public int getX3() {
                return 3;
            }

            @Override
            public int getY3() {
                return 2;
            }
        };


    ITriangle line = new ITriangle(){


        @Override
            public int getX1() {
                return 1;
            }

            @Override
            public int getY1() {
                return 1;
            }

            @Override
            public int getX2() {
                return 2;
            }

            @Override
            public int getY2() {
                return 2;
            }

            @Override
            public int getX3() {
                return 3;
            }

            @Override
            public int getY3() {
                return 3;
            }
        };

}
