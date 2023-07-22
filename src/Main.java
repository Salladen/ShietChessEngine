import Utilities.MyUtils;

import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        //TODO: Implement some chess engine driver
        // SpaceOut test

        MyUtils.spaceOut(TimeUnit.SECONDS, 5);
        System.out.println("Hello World!");

        // Sleep test
        MyUtils.sleep(TimeUnit.SECONDS, 5);
        System.out.println("Hello World!");
    }
}