package com.scottejames.aoc2021;

import java.nio.charset.StandardCharsets;
import java.util.*;

public class DayFifteen {
    public static Grid<Integer> grid = new Grid<>();
    public static Grid<Long> dist = new Grid<>();

    record Cell (Point point, long distance) {}

    public static void main(String [] args){
        FileUtils fu = new FileUtils("2021/DayFifteen.txt");
        List<String> data = fu.getFileAsList();

        int row = 0;

        for (String line: data){
            for (int column = 0; column < line.length();column++){
                Point p = new Point(column,row);
                Integer i = Character.getNumericValue(line.charAt(column));
                grid.add(p,i);
                dist.add(p,Long.MAX_VALUE);
            }
            row++;
        }
        final Point start = new Point(0,0);
        final Point end = new Point(grid.getWidth(),grid.getHeight());

        dist.add(start,0L);

        PriorityQueue<Cell> pq =
                new PriorityQueue<>(grid.getAllPoints().size(), Comparator.comparing(Cell::distance));
        var done = new HashSet<Point>();

        pq.add(new Cell(start,dist.get(start)));

        while (!pq.isEmpty()) {
            Cell currCell = pq.poll();
            System.out.println("Looking at " + currCell.point);
            if (currCell.point.equals(end)) {
                System.out.println("COST " + currCell.distance);
                System.exit(0);
            }
            for (Direction dir: Direction.fourDirections()){
                Point newPoint = dir.move(currCell.point,1);
                if (grid.getAllPoints().contains(newPoint)){
                    long cost = currCell.distance + grid.get(newPoint);
                    if (dist.get(newPoint) > cost){
                        dist.add(newPoint,cost);
                        pq.add(new Cell(newPoint,cost));
                    }
                }
            }
        }

    System.out.println("ENDED!");

    }



}
