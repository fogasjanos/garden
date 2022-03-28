package eu.fogas.orchard;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PointTest {
    private static final int NO_TOKEN = 0;
    private static final int TOKEN = 2;

    @Test
    public void pointConstructor() {
        int x = 1;
        int y = 2;
        int apples = 3;

        Point p = new Point(x, y, apples);

        assertEquals(x, p.getX());
        assertEquals(y, p.getY());
        assertEquals(apples, p.getSum(NO_TOKEN));
    }

    @Test
    public void getSum_noInitArray() {
        Point p = new Point(1, 2, 9);

        assertEquals(9, p.getSum(NO_TOKEN));
    }

    @Test
    public void getSum_initArray() {
        int[] init = new int[]{1, 2};

        Point p = new Point(0, 0, 0, init);

        assertEquals(3, p.getSum(NO_TOKEN));
    }

    @Test
    public void getSum() {
        int[] init = new int[]{1, 2};

        Point p = new Point(0, 0, 3, init);

        assertEquals(6, p.getSum(NO_TOKEN));
    }

    @Test
    public void getSum_withTokensNoInitArray() {
        Point p = new Point(1, 2, 9);

        assertEquals(18, p.getSum(TOKEN));
    }

    @Test
    public void getSum_withTokensInitArray() {
        int[] init = new int[]{1, 2, 3};

        Point p = new Point(0, 0, 0, init);

        assertEquals(11, p.getSum(TOKEN));
    }

    @Test
    public void getSum_withTokens() {
        int[] init = new int[]{1, 2, 3};

        Point p = new Point(0, 0, 4, init);

        assertEquals(17, p.getSum(TOKEN));
    }
}
