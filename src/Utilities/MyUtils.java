package Utilities;

import javax.management.MXBean;
import java.lang.management.ThreadMXBean;
import java.util.Scanner;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.lang.management.ManagementFactory;

public class MyUtils {
    public static void spaceOut(TimeUnit unit, long time) {
        // Solve busy-waiting problem by passing IO stream to different thread
        Thread ioThread = new Thread(() -> {
            try (Scanner sysIn = new Scanner(System.in)) {
                sysIn.nextLine();
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        });

        ioThread.start();

        // Sleep main thread regardless of whether user input is received
        sleep(unit, time);
    }

    public static void sleep(TimeUnit unit, long time) {
        try {
            Thread.sleep(unit.toMillis(time));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
