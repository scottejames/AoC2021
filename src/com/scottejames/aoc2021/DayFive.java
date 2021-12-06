package com.scottejames.aoc2021;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

public class DayFive {
    private static Grid<Integer> grid = new Grid<>();
    private static ArrayList<Pair.Points> lines = new ArrayList<>();

    public static void main(String[] args) {
        FileUtils fh = new FileUtils("2021/DayFive.txt");
        var data = fh.getFileAsList();
        for (String line : data) {
            String[] pair = line.split("->");
            String[] from = pair[0].trim().split(",");
            String[] to = pair[1].trim().split(",");
            Point fromPoint = new Point(Integer.parseInt(from[0].trim()),Integer.parseInt(from[1].trim()));
            Point toPoint = new Point(Integer.parseInt(to[0].trim()),Integer.parseInt(to[1].trim()));
            lines.add(new Pair.Points(fromPoint,toPoint));
        }

        for (Pair.Points line: lines){
            // Kick out diags
//            if ((line.lhs.x != line.rhs.x) && (line.lhs.y != line.rhs.y)){
//   //             System.out.println("Skipping diag " + line);
//                continue;
//            }
     //       System.out.println("Plotting line " + line);
            var plot = line.lhs.trail(line.rhs);
            for (Point p: plot){


       //         System.out.println("Plotting point " +p );
                var current = grid.get(p);
                if (current == null) current=0;
                grid.add(p,++current);
            }
        }
        var listOfValues = grid.getAllData();
        int count = 0;

        for (int value: listOfValues){
            if (value >= 2) count++;
        }
        grid.showGrid();

        System.out.println("Part 1 value : " + count);

    }
}
