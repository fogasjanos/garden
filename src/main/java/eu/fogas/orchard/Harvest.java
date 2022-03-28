package eu.fogas.orchard;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Harvest {
    private static final int TOKENS_COUNT = 2;
    private static final int NO_TOKENS = 0;

    public int harvest(int[][] orchard) {
        return harvest(orchard, NO_TOKENS);
    }

    public int harvestWithTokens(int[][] orchard) {
        return harvest(orchard, TOKENS_COUNT);
    }

    private int harvest(int[][] orchard, int token) {
        Orchard o = new Orchard(orchard);
        log.debug("Collecting apples for orchard: {}, token: {}", o, token);
        List<Point> path = new ArrayList<>();
        walk(o, o.getStart(), token, path);
        return findMax(path, token);
    }

    private void walk(Orchard o, Point p, int token, List<Point> path) {
        log.debug("Walking on sunshine. Orchard: {}, point: {}, token: {}, path: {}", o, p, token, path);
        if (o.isDone(p)) {
            path.add(p);
            log.debug("Possible path: {}", p);
            return;
        }
        walkUpIfPossible(o, p, token, path);
        walkRightIfPossible(o, p, token, path);
    }

    private void walkUpIfPossible(Orchard o, Point p, int token, List<Point> path) {
        if (o.isUpAvailable(p)) {
            Point np = o.moveUp(p);
            walk(o, np, token, path);
        }
    }

    private void walkRightIfPossible(Orchard o, Point p, int token, List<Point> path) {
        if (o.isRightAvailable(p)) {
            Point np = o.moveRight(p);
            walk(o, np, token, path);
        }
    }

    private int findMax(List<Point> path, int token) {
        log.debug("findMax - path: {}, token: {}", path, token);
        int max = Integer.MIN_VALUE;
        for (Point p : path) {
            max = getMax(p, max, token);
            log.debug("findMax - max: {}", max);
        }
        return max;
    }

    private int getMax(Point p, int max, int token) {
        log.debug("getMax - point: {}, max: {}, token: {}", p, max, token);
        int sum = p.getSum(token);
        return Math.max(sum, max);
    }
}
