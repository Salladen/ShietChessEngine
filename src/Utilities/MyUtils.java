package Utilities;

import java.util.concurrent.TimeUnit;

public class MyUtils {
    public static void spaceOut(TimeUnit unit, long time) {
        long start = System.nanoTime();
        start = unit.convert(start, TimeUnit.NANOSECONDS);

        while (start - unit.convert(System.nanoTime(), TimeUnit.NANOSECONDS) < time) {
            // TODO: Explore the implications of the cost of the high frequency of the condition check
        }
    }
}
