package com.scottejames.aoc2021;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Point {
    public int x;
    public int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public Point(String x, String y) {
        this.x = Integer.parseInt(x);
        this.y = Integer.parseInt(y);
    }
    public Point(Point p) {
        this.x = p.x;
        this.y = p.y;
    }
    public List<Point> trail(Point destination){
        List<Point> result = new ArrayList<>();
        var xRange = ArrayHelper.getRange(x, destination.x);
        var yRange = ArrayHelper.getRange(y, destination.y);

        if ((x==destination.x) ||(y==destination.y)) {
            // Straight line
            for (int x : xRange) {
                for (int y : yRange) {
                    result.add(new Point(x, y));
                }
            }
        } else {
            // diaginal
            for (int i = 0 ; i < xRange.size();i++)
                result.add(new Point(xRange.get(i), yRange.get(i)));
        }
       return result;
    }
    public Point add(Point p) {
        return new Point(x + p.x, y + p.y);
    }
    public Point multiply(int i) {
        return new Point (x*i , y*i);
    }
    public int manHattenDistance(){
        return Math.abs(x) + Math.abs(y);
    }
    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x &&
                y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }


}