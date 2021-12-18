package com.scottejames.aoc2021;

public class DaySeventeen {
    static int  minX = 48;
    static int maxX = 70;
    static int minY = -189;
    static int maxY = -148;
    public static void main(String args[]){

        int maxYPosition = (Math.abs(-189) - 1) * (Math.abs(-189)) / 2;
        System.out.println(maxYPosition);

        int minXVelocity, maxXVelocity, minYVelocity, maxYVelocity;

        maxXVelocity = maxX;
        minYVelocity = minY;
        minXVelocity = (int) Math.ceil((-1 + Math.sqrt(1 + 8 * minX)) / 2);
        maxYVelocity = Math.abs(minY) - 1;

        int count = 0;
        for (int x = minXVelocity; x <= maxXVelocity; x++) {
            for (int y = minYVelocity; y <= maxYVelocity; y++) {
                if (runSimul(x, y)) {
                    count++;
                }
            }
        }
        System.out.println(count);
    }


    private static boolean runSimul(int xV, int yV) {
        int xP = 0, yP = 0;
        while (xP <= maxX && yP >= minY) {
            xP += xV;
            yP += yV;
            if (xV > 0)
                xV--;
            yV--;
            if (xP >= minX && xP <= maxX && yP >= minY && yP <= maxY) {
                return true;
            }
        }
        return false;
    }
}
