package com.vsoltys.demo.sandbox.recursion.gridrobot;

import java.util.ArrayList;
import java.util.List;

public class GridRobot {

    private static int operationsCounter = 0;

    public static void main(String[] args) {

        // robot can move right or down
        // grid has blocked cells (as false)
        // find a path from the top left cell to the bottom right cell

        final int[][] grid = {
                {1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 0, 1, 1, 1, 1, 0},
                {1, 0, 1, 0, 1, 1, 0, 1},
                {1, 1, 1, 1, 1, 0, 1, 1},
                {1, 1, 0, 1, 0, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 0, 1, 1, 1, 1},
                {1, 0, 1, 1, 1, 1, 1, 1}
        };

        final List<Cell> path = buildPath(grid);

        System.out.println("-------");
        path.forEach(System.out::println);

        System.out.println(operationsCounter);
    }

    private static List<Cell> buildPath(final int[][] grid) {
        final List<Cell> path = new ArrayList<>();

        buildPath(grid, path, grid.length - 1, grid[0].length - 1);  // start from the end, navigate back

        return path;
    }

    // O(2^(row * column))
    private static boolean buildPath(final int[][] grid, final List<Cell> path, final int row, final int column) {

        if (row < 0 || column < 0 || grid[row][column] == 0) {
            return false;
        }

        System.out.println(String.format("[%s, %s]", row, column));
        ++operationsCounter;

        boolean startReached = row == 0 && column == 0;
        if (startReached || buildPath(grid, path, row - 1, column) || buildPath(grid, path, row, column - 1)) {
            path.add(new Cell(row, column));
            return true;
        }
        return false;
    }

    private static class Cell {
        final int row, column;

        Cell(final int row, final int column) {
            this.row = row;
            this.column = column;
        }

        @Override
        public String toString() {
            return String.format("[%s, %s]", row, column);
        }
    }
}
