package com.scottejames.aoc2021;

import java.util.*;

public class DayThirteen {
    private static Set<Point> points;
    private static ArrayList<String> folds;

    public static void main(String [] args) {
        points = new HashSet<Point>();
        folds = new ArrayList<>();

        FileUtils fh = new FileUtils("2021/DayThirteen.txt");
        List<String> data = fh.getFileAsList();
        for (String line: data) {
            if (line.equals(""))
                continue;
            if (line.contains(",")) {
                String[] sp = line.split(",");
                points.add(new Point(sp[0], sp[1]));
            } else {
                folds.add(line);
            }
        }
        partOne();
        partTwo();
    }

    private static void partOne(){
        String fold = folds.get(0);
        if (fold.contains("x="))
            foldLeft(fold);
        else
            foldUp(fold);
        boolean grid[][] = makeGrid();

        int count = countPoints(grid);
        System.out.println("Part 1:" + count);

    }
    public static void partTwo() {
        for (int f = 1; f < folds.size(); f++) {
            String fold = folds.get(f);
            if (fold.contains("x="))
                foldLeft(fold);
            else
                foldUp(fold);
        }
        boolean[][] grid = makeGrid();
        printGrid(grid);
    }

    private static void foldUp(String fold) {
        int y = Integer.parseInt(fold.substring(fold.indexOf("=") + 1));
        for (Point point : points)
            if (point.y > y)
                point.y = y - (point.y - y);
    }

    private static void foldLeft(String fold) {
        int x = Integer.parseInt(fold.substring(fold.indexOf("=") + 1));
        for (Point point : points)
            if (point.x > x)
                point.x = x - (point.x - x);
    }
    private static int countPoints(boolean[][] grid) {
        int count = 0;
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c])
                    count++;
            }
        }
        return count;
    }
    private static boolean[][] makeGrid() {
        int maxX = points.stream().max(Comparator.comparingInt(a -> a.x)).get().x;
        int maxY = points.stream().max(Comparator.comparingInt(a -> a.y)).get().y;

        boolean[][] grid = new boolean[maxY + 1][maxX + 1];

        for (Point point : points) {
            int c = point.x;
            int r = point.y;
            grid[r][c] = true;
        }

        return grid;
    }
    private static void printGrid(boolean[][] grid) {
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++)
                System.out.print(grid[r][c] ? "#" : ".");
            System.out.println("");
        }
    }


}
