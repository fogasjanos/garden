package eu.fogas.orchard;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class OrchardTest {
    private static final int NO_TOKEN = 0;
    private static final int[][] DATA = {
            {1, 4, 2},
            {2, 3, 1},
            {0, 3, 0}};

    @InjectMocks
    private Orchard orchard = new Orchard(DATA);

    @Test
    public void getStart() {
        Point start = orchard.getStart();

        assertNotNull(start);
        assertEquals(DATA[DATA.length - 1][0], start.getSum(NO_TOKEN), "The sum should be the same as the start field value.");
    }

    @Test
    public void isUpAvailable_shouldReturnTrue_whenUpIsAvailable() {
        Point p = new Point(0, DATA.length - 1, 0);

        assertTrue(orchard.isUpAvailable(p), "From the left bottom position Up should be allowed.");
    }

    @Test
    public void isUpAvailable_shouldReturnFalse_whenUpIsNotAvailable() {
        Point p = new Point(0, 0, 0);

        assertFalse(orchard.isUpAvailable(p), "From the left top position Up should not be allowed.");
    }

    @Test
    public void moveUp() {
        int x = 0;
        int y = DATA.length - 1;
        Point p = new Point(x, y, 0);

        Point actual = orchard.moveUp(p);

        int newY = --y;
        assertNotNull(actual);
        assertNotEquals(p, actual, "The new point should not be equal with the old one.");
        assertEquals(DATA[newY][x], actual.getSum(NO_TOKEN));
        assertEquals(x, actual.getX());
        assertEquals(newY, actual.getY());
    }

    @Test
    public void isRightAvailable_shouldReturnTrue_whenRightIsAvailable() {
        Point p = new Point(0, DATA.length - 1, 0);

        assertTrue(orchard.isRightAvailable(p), "From the left bottom position Right should be allowed.");
    }

    @Test
    public void isRightAvailable_shouldReturnFalse_whenRightIsNotAvailable() {
        Point p = new Point(DATA.length - 1, 0, 0);

        assertFalse(orchard.isRightAvailable(p), "From the right top position Right should not be allowed.");
    }

    @Test
    public void moveRight() {
        int x = 0;
        int y = DATA.length - 1;
        Point p = new Point(x, y, 0);

        Point actual = orchard.moveRight(p);

        int newX = ++x;
        assertNotNull(actual);
        assertNotEquals(p, actual, "The new point should not be equal with the old one.");
        assertEquals(DATA[y][newX], actual.getSum(NO_TOKEN));
        assertEquals(newX, actual.getX());
        assertEquals(y, actual.getY());
    }

    @Test
    public void isDone_shouldReturnTrue_whenTheExitIsReached() {
        Point p = new Point(DATA.length - 1, 0, 0);

        assertTrue(orchard.isDone(p), "The right top position should be the target.");
    }

    @Test
    public void isDone_shouldReturnFalse_whenTheExistIsNotReached() {
        Point p = new Point(0, DATA.length - 1, 0);

        assertFalse(orchard.isDone(p), "The left bottom position should not be the target.");
    }
}
