package com.scottejames.aoc2021;

public class Pair<LHS,RHS>{

    LHS lhs;
    RHS rhs;

    public Pair(LHS lhs, RHS rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "lhs=" + lhs +
                ", rhs=" + rhs +
                '}';
    }

    public LHS getLhs() {
        return lhs;
    }

    public void setLhs(LHS lhs) {
        this.lhs = lhs;
    }

    public RHS getRhs() {
        return rhs;
    }

    public void setRhs(RHS rhs) {
        this.rhs = rhs;
    }

    public static class Points extends Pair<Point, Point> {

        public Points(Point point, Point point2) {
            super(point, point2);
        }
    }
}
