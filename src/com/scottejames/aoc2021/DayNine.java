package com.scottejames.aoc2021;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DayNine {
    public static void main(String [] args) {
        FileUtils fh = new FileUtils("2021/DayNine.txt");

        List<String> data = fh.getFileAsList();
        Grid<Integer> grid = new Grid<Integer>();
        Direction[] directions = {Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST};
        int row = 0;
        int column = 0;

        for (String rowData : data) {
            for (char c : rowData.toCharArray()) {
                grid.add(new Point(row, column), Character.getNumericValue(c));
                row++;
            }
            column++;
            row = 0;
        }

        grid.showGrid();
        List<Point> minimal = new ArrayList<>();
        for(Point p: grid.getAllPoints()){
//            Point p = new Point(1,0);
            int value = grid.get(p);
            boolean lowest = true;
            for (Direction d: directions){

                Point newPoint = d.move(p,1);
                if (grid.getAllPoints().contains(newPoint)){
                    if (grid.get(newPoint) < value){
                        lowest = false;
                    }
                }
            }
            if (lowest == true){
                minimal.add(p);
            }
        }
        int result = 0;
        for (Point i : minimal){
            System.out.println(i +" => " + grid.get(i));
            result += grid.get(i) + 1;
        }
        System.out.println("Part 1: " + result);


    }
}
