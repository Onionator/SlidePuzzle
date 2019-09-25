package com.company;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        int[][] puzzle = {{10, 3, 6, 4}, {1, 5, 8, 0}, {2, 13, 7 ,15}, {14, 9, 12, 11} };
        outputBoardToConsole(puzzle);
        System.out.println(Arrays.toString(findNumCoordinates(puzzle, 1)));
        puzzle = moveRight(puzzle, findNumCoordinates(puzzle, 8));
        outputBoardToConsole(puzzle);
        puzzle = moveDown(puzzle, findNumCoordinates(puzzle, 6));
        outputBoardToConsole(puzzle);
        puzzle = moveLeft(puzzle, findNumCoordinates(puzzle, 4));
        outputBoardToConsole(puzzle);
        puzzle = moveUp(puzzle, findNumCoordinates(puzzle, 8));
        outputBoardToConsole(puzzle);
    }

    static void SlidingPuzzle(int[][] puzzle) {

    }

    static void moveNumberLeft(int[][] puzzle, int[] coords) {
        int y = coords[0];
        int x = coords[1];
        int[] zeroCoordinates = findNumCoordinates(puzzle, 0);
        if (puzzle[y][x-1] != puzzle[y][(puzzle.length - 1)]) {
            moveLeft(puzzle, coords);
        } else {
            // while zero is not to the left move zero in pattern to the left of the number

        }
    }

    static int[][] moveLeft(int[][] puzzle, int[] coords) {
        int y = coords[0];
        int x = coords[1];
        if (puzzle[y][x-1] == 0) {
            puzzle[y][x-1] = puzzle[y][x];
            puzzle[y][x] = 0;
            System.out.println("conditional works");
        }
        return puzzle;
    }

    static int[][] moveRight(int[][] puzzle, int[] coords) {
        int y = coords[0];
        int x = coords[1];
        if (puzzle[y][x+1] == 0) {
            puzzle[y][x+1] = puzzle[y][x];
            puzzle[y][x] = 0;
            System.out.println("conditional works");
        }
        return puzzle;
    }

    static int[][] moveUp(int[][] puzzle, int[] coords) {
        int y = coords[0];
        int x = coords[1];
        if (puzzle[y-1][x] == 0) {
            puzzle[y-1][x] = puzzle[y][x];
            puzzle[y][x] = 0;
            System.out.println("conditional works");
        }
        return puzzle;
    }

    static int[][] moveDown(int[][] puzzle, int[] coords) {
        int y = coords[0];
        int x = coords[1];
        if (puzzle[y+1][x] == 0) {
            puzzle[y+1][x] = puzzle[y][x];
            puzzle[y][x] = 0;
            System.out.println("conditional works");
        }
        return puzzle;
    }

    static void outputBoardToConsole(int[][] puzzle) {
        for (int[] i: puzzle) {
            System.out.println(Arrays.toString(i));
        }
    }

    // return an array with the array it's in and the index of that array
    static int[] findNumCoordinates(int[][] puzzle, int num) {
        for (int x = 0; x < puzzle.length; x++) {
            for (int y = 0; y < puzzle[x].length; y++) {
                if (puzzle[x][y] == num) {
                    return new int[]{x, y};
                }
            }
        }
        return null;
    }

}
