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
        Grid<Integer> lastWinner = null;
        int lastRemoved = 0;
        for (int i = 0; i < drawnNumbers.length; i++) {
            int remaining = drawnNumbers.length - i;

            // apply next thing
            for (Grid<Integer> grid : _gridList) {
                List<Point> points = grid.exists(drawnNumbers[i]);
                grid.setListOfPoints(points, 0);
            }

            // Test for winning condition
            List<Grid<Integer>> winningGrids = winningGrid();

            if (!winningGrids.isEmpty()){
                lastWinner = winningGrids.get(0);
                lastRemoved = drawnNumbers[i];
                for (Grid<Integer> g : winningGrids){
                    _gridList.remove(g);
                }

            }
        }


        lastWinner.showGrid();
        int score = scoreWinner(lastWinner);
        System.out.println("Score : " + score + " removed " + lastRemoved);

    }


    public static List<Grid<Integer>> winningGrid() {
        List<Grid<Integer>> result = new ArrayList<>();

        for (Grid<Integer> grid : _gridList) {
            if (isWinner(grid))
                result.add(grid);
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
        for (int y = 0; y <= grid.getHeight(); y++) {
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

    }
}
