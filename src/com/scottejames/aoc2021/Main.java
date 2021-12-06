package com.scottejames.aoc2021;

public class Main {

    public static void main(String[] args) {

        var result = ArrayHelper.getRange(1,10);
        System.out.println(result);
        result = ArrayHelper.getRange(10,1);
        System.out.println(result);
        result = ArrayHelper.getRange(0,10);
        System.out.println(result);
        result = ArrayHelper.getRange(10,0);
        System.out.println(result);
        result = ArrayHelper.getRange(-5,5);
        System.out.println(result);



        Point p1 = new Point(0,9);
        var pointRange = p1.trail(new Point(5,9));
        System.out.println(pointRange);

        p1 = new Point(1,1);
        pointRange = p1.trail(new Point(3,3));
        System.out.println(pointRange);

        p1 = new Point(1,1);
        pointRange = p1.trail(new Point(3,3));
        System.out.println(pointRange);

    }
}
