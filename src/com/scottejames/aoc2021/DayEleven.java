package com.scottejames.aoc2021;

import java.util.List;

public class DayEleven {

    private static int[][] startingGrid;
    private static int flashes = 0;

    public static void main(String [] args) {
        FileUtils fh = new FileUtils("2021/DayEleven.txt");

        List<String> data = fh.getFileAsList();
        startingGrid = new int[10][10];
        int x,y = 0;

        for (String line: data){
            x = 0;
            for(char c:line.toCharArray()){
                startingGrid[y][x++]=Character.getNumericValue(c);
            }
            y++;
        }
        int day = 0;
        while(!allZeros(startingGrid)){
            updateGrid(startingGrid);
            day++;
        }
        System.out.println(day);

    }

    public static void updateGrid(int [][] grid){
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                grid[r][c]++;
            }
        }
        checkForTens(grid);
    }
    private static void checkForTens(int[][] grid) {
        boolean flash = false;
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] >= 10) {
                    grid[r][c] = 0;
                    flashes++;
                    flash = true;
                    incrementNeighbors(grid, r, c);
                }
            }
        }
        if (flash == true)
            checkForTens(grid);
    }
    private static void incrementNeighbors(int[][] grid, int r, int c) {
        int[] delta = new int[] { -1, 0, 1 };
        for (int i = 0; i < delta.length; i++) {
            for (int j = 0; j < delta.length; j++) {
                if (i == 1 && j == 1)
                    continue;
                if (isEligibleForFlashIncrement(grid, r + delta[i], c + delta[j]))
                    grid[r + delta[i]][c + delta[j]]++;
            }
        }
    }
    private static boolean isEligibleForFlashIncrement(int[][] grid, int r, int c) {
        return (r >= 0 &&
                c >= 0 &&
                r < grid.length &&
                c < grid[0].length &&
                grid[r][c] != 0);
    }
    private static boolean allZeros(int[][] grid) {
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] != 0)
                    return false;
            }
        }
        return true;
    }
}
