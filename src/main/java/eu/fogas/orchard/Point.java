package eu.fogas.orchard;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
@Data
public class Point {
    private final int x;
    private final int y;
    private final int[] allApples;

    public Point(int x, int y, int apples) {
        this(x, y, apples, null);
    }

    public Point(int x, int y, int apples, int[] initialApples) {
        log.debug("Creating new Point. x: {}, y: {}, apples: {}, initialApples: {}", x, y, apples, initialApples);
        this.x = x;
        this.y = y;
        allApples = getApples(initialApples, apples);
        log.debug("New Point created: {}", this);
    }

    public int[] getAllApples() {
        return Arrays.copyOf(allApples, allApples.length);
    }

    public int getSum(int tokens) {
        int[] sorted = getAllApples();
        Arrays.sort(sorted);
        return getSum(sorted, tokens);
    }

    private int getSum(int[] a, int tokens) {
        int sum = 0;
        int tokenStartIndex = a.length - tokens;
        for (int i = 0; i < a.length; i++) {
            int c = a[i];
            if (i >= tokenStartIndex) {
                c *= 2;
            }
            sum += c;
        }
        log.debug("Sum: {} for array: {} with tokens count: {}.", sum, a, tokens);
        return sum;
    }

    private int[] getApples(int[] initialApples, int apples) {
        if (initialApples == null) {
            log.debug("InitialApples was null. Apples: {}", apples);
            return handleNullArray(apples);
        }
        return copyAndExtendArray(initialApples, apples);
    }

    private int[] handleNullArray(int value) {
        return new int[]{
                value
        };
    }

    private int[] copyAndExtendArray(int[] initialApples, int apples) {
        log.debug("InitialApples: {}. Apples: {}", initialApples, apples);
        int[] a = Arrays.copyOf(initialApples, initialApples.length + 1);
        a[a.length - 1] = apples;
        return a;
    }
}
