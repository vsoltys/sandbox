package com.vsoltys.demo.sandbox.recursion.gridrobot;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class GridRobotFast {

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

        System.out.println();
        path.forEach(System.out::println);

        System.out.println(operationsCounter);
    }

    private static List<Cell> buildPath(final int[][] grid) {
        final List<Cell> path = new ArrayList<>();
        final Set<Cell> failedCells = new HashSet<>();

        buildPath(grid, path, failedCells, grid.length - 1, grid[0].length - 1);  // start from the end, navigate back

        System.out.println("-------");
        failedCells.forEach(System.out::println);
        System.out.println("-------");
        return path;
    }

    // O(row * column)
    private static boolean buildPath(final int[][] grid, final List<Cell> path, final Set<Cell> failedCells, final int row, final int column) {

        if (row < 0 || column < 0 || grid[row][column] == 0) {
            return false;
        }

        final Cell cell = new Cell(row, column);
        if (failedCells.contains(cell)) {
            return false;
        }

        System.out.println(String.format("[%s, %s]", row, column));
        ++operationsCounter;

        boolean startReached = row == 0 && column == 0;
        if (startReached || buildPath(grid, path, failedCells, row - 1, column) || buildPath(grid, path, failedCells, row, column - 1)) {
            path.add(cell);
            return true;
        }

        failedCells.add(cell);
        return false;
    }

    private static class Cell {
        final int row, column;

        Cell(final int row, final int column) {
            this.row = row;
            this.column = column;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Cell cell = (Cell) o;
            return row == cell.row &&
                    column == cell.column;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, column);
        }

        @Override
        public String toString() {
            return String.format("[%s, %s]", row, column);
        }
    }
}
