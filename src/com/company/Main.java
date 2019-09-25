package com.company;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        int[][] puzzle = {{10, 3, 6, 4}, {1, 5, 8, 7}, {2, 13, 0 ,15}, {14, 9, 12, 11} };
        outputBoardToConsole(puzzle);
        System.out.println(Arrays.toString(findNumCoordinates(puzzle, 1)));
//        puzzle = moveRight(puzzle, findNumCoordinates(puzzle, 8));
//        outputBoardToConsole(puzzle);
//        puzzle = moveDown(puzzle, findNumCoordinates(puzzle, 6));
//        outputBoardToConsole(puzzle);
//        puzzle = moveLeft(puzzle, findNumCoordinates(puzzle, 4));
//        outputBoardToConsole(puzzle);
//        puzzle = moveUp(puzzle, findNumCoordinates(puzzle, 8));
//        outputBoardToConsole(puzzle);
        puzzle = moveZeroToLeftOfNumber(puzzle, findNumCoordinates(puzzle, 3), 3);
//        outputBoardToConsole(puzzle);
    }

    static void SlidingPuzzle(int[][] puzzle) {

    }

    static int[][] moveZeroToLeftOfNumber(int[][] puzzle, int[] coords, int num) {
        int y = coords[0];
        int x = coords[1];
        int[] zeroCoordinates = findNumCoordinates(puzzle, 0);

        // while zero is not to the left move zero in pattern to the left of the number
//        if (x - 1 >= 0 && puzzle[y][x - 1] != 0) {

        // move the zero up if possible to the y array of the number
        while (zeroCoordinates[0] - 1 >= 0 && zeroCoordinates[0] != 0 && y != zeroCoordinates[0]) {
            puzzle = moveUp(puzzle, zeroCoordinates);
            outputBoardToConsole(puzzle);
            zeroCoordinates = findNumCoordinates(puzzle, 0);
        }
        // move the zero one left of the number if possible
        while (zeroCoordinates[1] - 1 >= 0 && zeroCoordinates[1] != 0 && x - 1 != zeroCoordinates[1]) {
                System.out.println(x - 1 >= 0);
                puzzle = moveLeft(puzzle, zeroCoordinates);
                outputBoardToConsole(puzzle);
                zeroCoordinates = findNumCoordinates(puzzle, 0);
            }
//        }
        int endX = (num % puzzle.length) - 1;
        int endY = (num - 1) / puzzle.length;
        int[] currentCoordinates = findNumCoordinates(puzzle, num);
        while (!(currentCoordinates[1] == endX || currentCoordinates[0] == endY)) {
//            zeroCoordinates = findNumCoordinates(puzzle, 0);
            currentCoordinates = findNumCoordinates(puzzle, num);
            if (currentCoordinates[1] - 1 >= 0) {
                puzzle = moveLeft(puzzle, findNumCoordinates(puzzle, num));
                System.out.println("Moved number left");
                outputBoardToConsole(puzzle);
                currentCoordinates = findNumCoordinates(puzzle, num);
            }
            if (currentCoordinates[0] + 1 < puzzle.length) {
                puzzle = moveDown(puzzle, findNumCoordinates(puzzle, puzzle[currentCoordinates[0] - 1][currentCoordinates[1] + 1]));
                System.out.println("Moved arbitrary number down");
                outputBoardToConsole(puzzle);
                currentCoordinates = findNumCoordinates(puzzle, num);
            }
            if (currentCoordinates[0] + 1 < puzzle.length && currentCoordinates[1] + 1 < puzzle.length) {
                puzzle = moveRight(puzzle, findNumCoordinates(puzzle, puzzle[currentCoordinates[0] - 1][currentCoordinates[1]]));
                System.out.println("Moved arbitrary number right");
                outputBoardToConsole(puzzle);
                currentCoordinates = findNumCoordinates(puzzle, num);
            }
            if (currentCoordinates[0] + 1 < puzzle.length && currentCoordinates[1] + 1 < puzzle.length) {
                puzzle = moveUp(puzzle, findNumCoordinates(puzzle, num));
                System.out.println("Moved arbitrary number right");
                outputBoardToConsole(puzzle);
                currentCoordinates = findNumCoordinates(puzzle, num);
            }

        }

        return puzzle;
    }

    static int[][] moveLeft(int[][] puzzle, int[] coords) {
        int y = coords[0];
        int x = coords[1];
        System.out.println(puzzle[y][x]);
        if (x - 1 >= 0) {
            int tempNum = puzzle[y][x - 1];
            puzzle[y][x - 1] = puzzle[y][x];
            puzzle[y][x] = tempNum;
            System.out.println("conditional works");
        }
        return puzzle;
    }

    static int[][] moveRight(int[][] puzzle, int[] coords) {
        int y = coords[0];
        int x = coords[1];
        System.out.println(puzzle[y][x]);
        if (x + 1 < puzzle.length) {
            int tempNum = puzzle[y][x + 1];
            puzzle[y][x + 1] = puzzle[y][x];
            puzzle[y][x] = tempNum;
            System.out.println("conditional works");
        }
        return puzzle;
    }

    static int[][] moveUp(int[][] puzzle, int[] coords) {
        int y = coords[0];
        int x = coords[1];
        System.out.println(puzzle[y][x]);
        if (y - 1 < puzzle.length) {
            int tempNum = puzzle[y - 1][x];
            puzzle[y - 1][x] = puzzle[y][x];
            puzzle[y][x] = tempNum;
            System.out.println("conditional works");
        }
        return puzzle;
    }

    static int[][] moveDown(int[][] puzzle, int[] coords) {
        int y = coords[0];
        int x = coords[1];
        if (y + 1 < puzzle.length) {
            int tempNum = puzzle[y + 1][x];
            puzzle[y + 1][x] = puzzle[y][x];
            puzzle[y][x] = tempNum;
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
