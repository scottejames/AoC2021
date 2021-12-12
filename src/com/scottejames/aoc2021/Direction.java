package com.scottejames.aoc2021;
import java.util.Arrays;

public enum Direction {
    NORTH(1, 'U'), EAST(4, 'R'), SOUTH(2, 'D'), WEST(3, 'L'),
    NORTHEAST(4, 'E'), EASTSOUTH(5, 'E'), SOUTHWEST(6, 'E'), WESTNORTH(7, 'E');

    public final int num;
    public final int code;

    private Direction(int num, char code) {
        this.num = num;
        this.code = code;
    }

    public static Direction getByDirCode(char code) {
        return Arrays.stream(values()).filter(e -> e.code == code).findAny().get();
    }
    public static Direction getByDir(char code) {
        return Arrays.stream(values()).filter(e -> e.name().charAt(0) == code).findAny().get();
    }

    public Direction turnNintyDegrees(boolean right) {
        if (right){
            switch (this){
                case NORTH:
                    return EAST;
                case EAST:
                    return SOUTH;
                case SOUTH:
                    return WEST;
                case WEST:
                    return NORTH;
            }
        } else {
            switch (this) {
                case NORTH:
                    return WEST;
                case WEST:
                    return SOUTH;
                case SOUTH:
                    return EAST;
                case EAST:
                    return NORTH;
            }
        }
        System.out.println("ERROR");
        return null;
    }

    public Point move(Point currentLocation, int amount) {
        switch (this) {
            case SOUTH:
                return new Point(currentLocation.x, currentLocation.y + amount);
            case NORTH:
                return new Point(currentLocation.x, currentLocation.y - amount);
            case EAST:
                return new Point(currentLocation.x + amount, currentLocation.y);
            case WEST:
                return new Point(currentLocation.x - amount, currentLocation.y);
            case SOUTHWEST:
                return new Point(currentLocation.x - amount, currentLocation.y + amount);
            case NORTHEAST:
                return new Point(currentLocation.x + amount, currentLocation.y - amount);
            case EASTSOUTH:
                return new Point(currentLocation.x + amount, currentLocation.y + amount);
            case WESTNORTH:
                return new Point(currentLocation.x - amount, currentLocation.y - amount);
        }
        throw new IllegalStateException("Non-existent Direction: " + this);
    }


    public Point move(Point currentLocation) {
        return move(currentLocation, 1);
    }

    public Direction opposite() {
        switch (this) {
            case NORTH:
                return SOUTH;
            case SOUTH:
                return NORTH;
            case EAST:
                return WEST;
            case WEST:
                return EAST;
            case NORTHEAST:
                return SOUTHWEST;
            case SOUTHWEST:
                return NORTHEAST;
            case EASTSOUTH:
                return WESTNORTH;
            case WESTNORTH:
                return EASTSOUTH;
        }
        throw new IllegalStateException("Non-existent Direction: " + this);
    }

    public static Direction getByMove(Point from, Point to) {
        if (to.x > from.x) return EAST;
        else if (to.x < from.x) return WEST;
        else if (to.y > from.y) return SOUTH;
        else if (to.y < from.y) return NORTH;
        throw new IllegalStateException("From and to location are the same: " + from + ", " + to);
    }

    public boolean leftOf(Direction robotDir) {
        int n = this.ordinal() - 1;
        if (n == -1) n = values().length - 1;
        return robotDir.ordinal() == n;
    }

    public static Direction[] fourDirections() {
        return new Direction[]{NORTH, EAST, SOUTH, WEST};
    }

    public static Direction[] eightDirections() {
        return new Direction[]{NORTH, NORTHEAST, EAST, EASTSOUTH, SOUTH, SOUTHWEST, WEST, WESTNORTH};
    }
}
