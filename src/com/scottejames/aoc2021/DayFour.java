package com.scottejames.aoc2021;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class DayFour {
    private static int[] drawnNumbers;
    private static List<Grid<Integer>> _gridList = new ArrayList<>();

    public static void main(String[] args) {
        FileUtils fh = new FileUtils("2021/DayFour.txt");

        List<String> data = fh.getFileAsList();
        drawnNumbers = Stream.of(data.get(0).split(",")).mapToInt(Integer::parseInt).toArray();
        populateGridList(data);
        for (int i = 0; i < drawnNumbers.length; i++) {

            // apply next thing
            for (Grid<Integer> grid : _gridList) {
                List<Point> points = grid.exists(drawnNumbers[i]);
                grid.set(points, 0);
            }

            // Test for winning condition
            Grid<Integer> winningGrid = winningGrid();

            if (winningGrid != null) {
                winningGrid.showGrid();
                int score = scoreWinner(winningGrid);
                System.out.println("Part 1 scores " + score + " x " + drawnNumbers[i] + " = " + score * drawnNumbers[i]);
                break;
            }


        }
    }


    public static Grid<Integer> winningGrid() {
        Grid<Integer> result = null;
        for (Grid<Integer> grid : _gridList) {
            if (isWinner(grid))
                result = grid;
        }
        return result;

    }

    public static boolean isWinner(Grid<Integer> grid) {
        boolean result = false;
        for (int x = 0; x < grid.getWidth(); x++) {
            int colSum = ArrayHelper.sumListOfInteger(grid.getColumn(x));
            if (colSum == 0) {
                result = true;
            }
        }
        for (int y = 0; y < grid.getHeight(); y++) {
            int rowSum = ArrayHelper.sumListOfInteger(grid.getRow(y));
            if (rowSum == 0) {
                result = true;
            }
        }
        return result;
    }

    public static int scoreWinner(Grid<Integer> grid) {
        int result = 0;
        for (int x = 0; x <= grid.getWidth(); x++) {
            List<Integer> thing = grid.getColumn(x);
            int colSum = ArrayHelper.sumListOfInteger(thing);
            result += colSum;
        }

        return result;
    }

    private static void populateGridList(List<String> data) {
        int rowCount = 0;
        Grid<Integer> grid = new Grid<Integer>();

        for (int y = 2; y < data.size(); y++) {

            String line = data.get(y);
            if (line.isEmpty()) {
                _gridList.add(grid);
                grid = new Grid<Integer>();
                rowCount = 0;
            } else {
                String[] temp = line.trim().split("\\s+");
                int[] rowData = Stream.of(temp).mapToInt(Integer::parseInt).toArray();
                for (int x = 0; x < rowData.length; x++) {
                    grid.add(new Point(x, rowCount), rowData[x]);
                }
                rowCount++;
            }
        }
//        for (Grid<Integer> g : _gridList){
//            g.showGrid();
//        }
    }
}
