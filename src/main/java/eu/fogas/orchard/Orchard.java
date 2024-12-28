package eu.fogas.orchard;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
@Data
class Orchard {
    private static final int START_X = 0;
    private static final int MIN_Y = 0;
    private final int lastElementIndex;
    private final int[][] data;

    public Orchard(int[][] data) {
        this.data = Arrays.copyOf(data, data.length);
        lastElementIndex = data.length - 1;
        log.debug("New Orchard created: {}", this);
    }

    public Point getStart() {
        int sum = getApples(START_X, lastElementIndex);
        Point p = new Point(START_X, lastElementIndex, sum);
        log.debug("Starting Point: {}", p);
        return p;
    }

    public boolean isUpAvailable(Point p) {
        boolean up = p.y() > MIN_Y;
        log.debug("Point: {} up: {}", p, up);
        return up;
    }

    public Point moveUp(Point p) {
        int x = p.x();
        int y = p.y() - 1;
        Point newP = new Point(x, y, getApples(x, y), p.getAllApples());
        log.debug("Moving UP from {} to {}", p, newP);
        return newP;
    }

    public boolean isRightAvailable(Point p) {
        boolean right = p.x() < lastElementIndex;
        log.debug("Point: {} right: {}", p, right);
        return right;
    }

    public Point moveRight(Point p) {
        int x = p.x() + 1;
        int y = p.y();
        Point newP = new Point(x, y, getApples(x, y), p.getAllApples());
        log.debug("Moving RIGHT from {} to {}", p, newP);
        return newP;
    }

    public boolean isDone(Point p) {
        boolean done = p.x() == lastElementIndex && p.y() == MIN_Y;
        log.debug("Point: {} done: {}", p, done);
        return done;
    }

    private int getApples(int x, int y) {
        int apples = data[y][x];
        log.debug("Apples at x:{} y:{} are {}", x, y, apples);
        return apples;
    }
}
