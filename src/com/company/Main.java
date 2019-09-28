package com.company;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        int[][] finishedPuzzle = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 0} };
        Puzzle puzzle = new Puzzle();
        System.out.println(puzzle.printBoard());
//        for (int i = 1; i < 9; i++) {
//        while(!(Arrays.equals(puzzle.getBoard(), finishedPuzzle))) {
            puzzle.positionNumber(1);
            puzzle.positionNumber(2);
            puzzle.positionNumber(3);
            puzzle.positionNumber(4);
            puzzle.positionNumber(5);
            puzzle.positionNumber(9);
            puzzle.positionNumber(13);
            puzzle.positionNumber(6);
            puzzle.positionNumber(7);
            puzzle.positionNumber(8);
            puzzle.positionNumber(10);
            puzzle.positionNumber(11);
            puzzle.positionNumber(12);
            puzzle.positionNumber(14);
            puzzle.positionNumber(15);
//        }

    }

    static void SlidingPuzzle(int[][] puzzle) {

    }

    static int[][] moveZeroToLeftOfNumber(int[][] puzzle, int num) {
        int[] coords = findNumCoordinates(puzzle, num);
        int y = coords[0];
        int x = coords[1];
        int[] zeroCoordinates = findNumCoordinates(puzzle, 0);
        int endX = (num % puzzle.length) - 1;
        int endY = (num - 1) / puzzle.length;
//y != endY && x != endX
        // move zero to the left of the number
        while (zeroCoordinates[0] != y || zeroCoordinates[1] != (x - 1)) {
            System.out.println(zeroCoordinates[0] != y);
            System.out.println(zeroCoordinates[1] != (x - 1));
            //if it is possible and would be helpful to move zero up without moving the target number down then move it
            if (zeroCoordinates[0] < y && (zeroCoordinates[0] - 1) != num) {
                puzzle = moveUp(puzzle, zeroCoordinates);
                zeroCoordinates = findNumCoordinates(puzzle, 0);
                // //if it is possible and would be helpful to move zero down without moving the target number up then move it
            } else if (zeroCoordinates[0] > y && (zeroCoordinates[0] + 1) != num && (zeroCoordinates[0] + 1) >= y ) {
                puzzle = moveDown(puzzle, zeroCoordinates);
                zeroCoordinates = findNumCoordinates(puzzle, 0);
                // if it is possible and would be helpful to move zero left without moving the target number right then move it
            } else if ((zeroCoordinates[1] - 1) >= x) {
                puzzle = moveLeft(puzzle, zeroCoordinates);
                zeroCoordinates = findNumCoordinates(puzzle, 0);
            }
            outputBoardToConsole(puzzle);
        }

        // while zero is not to the left move zero in pattern to the left of the number

        // move the zero up if possible to the y array of the number
//        while (zeroCoordinates[0] - 1 >= 0 && zeroCoordinates[0] != 0 && y != zeroCoordinates[0]) {
//            puzzle = moveUp(puzzle, zeroCoordinates);
//            outputBoardToConsole(puzzle);
//            zeroCoordinates = findNumCoordinates(puzzle, 0);
//        }
//        // move the zero one left of the number if possible
//        while (zeroCoordinates[1] - 1 >= 0 && zeroCoordinates[1] != 0 && x - 1 != zeroCoordinates[1]) {
//                System.out.println(x - 1 >= 0);
//                puzzle = moveLeft(puzzle, zeroCoordinates);
//                outputBoardToConsole(puzzle);
//                zeroCoordinates = findNumCoordinates(puzzle, 0);
//            System.out.println("left left left left");
//        }
        // move the zero down if possible to the y array of the number
//        while (zeroCoordinates[0] + 1 >= 0 && zeroCoordinates[0] != 0 && y != zeroCoordinates[0]) {
//            puzzle = moveDown(puzzle, zeroCoordinates);
//            outputBoardToConsole(puzzle);
//            zeroCoordinates = findNumCoordinates(puzzle, 0);
//        }
//        // move the zero one right of the number if possible
//        while (zeroCoordinates[1] + 1 >= 0 && zeroCoordinates[1] != 0 && x + 1 != zeroCoordinates[1]) {
//            puzzle = moveRight(puzzle, zeroCoordinates);
//            outputBoardToConsole(puzzle);
//            zeroCoordinates = findNumCoordinates(puzzle, 0);
//        }
//        }
//        endX = (num % puzzle.length) - 1;
//        endY = (num - 1) / puzzle.length;
//        System.out.println("endX: " + endX);
//        System.out.println("endY: " + endY);
//        int[] currentCoordinates = findNumCoordinates(puzzle, num);
//        while ((currentCoordinates[1] != endX || currentCoordinates[0] != endY)) {
////
//            // moving num instead of zero. need to move zero  only and verify that zero is left of number.
//            currentCoordinates = findNumCoordinates(puzzle, num);
//            if (currentCoordinates[1] - 1 >= 0) {
//                puzzle = moveLeft(puzzle, findNumCoordinates(puzzle, num));
//                System.out.println("Moved number left");
//                outputBoardToConsole(puzzle);
//                currentCoordinates = findNumCoordinates(puzzle, num);
//            }
//            if (currentCoordinates[0] + 1 < puzzle.length) {
//                puzzle = moveDown(puzzle, findNumCoordinates(puzzle, puzzle[currentCoordinates[0] + 1][currentCoordinates[1] + 1]));
//                System.out.println("Moved arbitrary number down");
//                outputBoardToConsole(puzzle);
//                currentCoordinates = findNumCoordinates(puzzle, num);
//            }
//            if (currentCoordinates[0] + 1 < puzzle.length && currentCoordinates[1] + 1 < puzzle.length) {
//                puzzle = moveRight(puzzle, findNumCoordinates(puzzle, puzzle[currentCoordinates[0] + 1][currentCoordinates[1]]));
//                System.out.println("Moved arbitrary number right");
//                outputBoardToConsole(puzzle);
//                currentCoordinates = findNumCoordinates(puzzle, num);
//            }
//            if (currentCoordinates[0] + 1 < puzzle.length && currentCoordinates[1] + 1 < puzzle.length) {
//                puzzle = moveUp(puzzle, findNumCoordinates(puzzle, num));
//                System.out.println("Moved target number up");
//                outputBoardToConsole(puzzle);
//                currentCoordinates = findNumCoordinates(puzzle, num);
//            }
//
//            System.out.println((currentCoordinates[0] == endY));
//
//        }

        return puzzle;
    }

    static int[][] moveLeft(int[][] puzzle, int[] coords) {
        int y = coords[0];
        int x = coords[1];
        System.out.println("doing leftward stuff " + puzzle[y][x]);
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
        if (y - 1 >= 0) {
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


// while number is not in its place
    // if number needs to move up
        // does zero need to move up, down, or not at all to be above the number
            // if zero needs to go up
                // if zero is immediately below the number determine if it should go left or right before going up
                    // if zero needs to go left
                        // move zero left
                    // else zero needs to go right
                        // move zero right
                // else if zero can move up
                    // move zero up
            // else if zero needs to go down
                // move zero down
            // else if zero needs to go left
                // move zero left
            // else if zero needs to go right
                // move zero right

    // else if number needs to go down
        // does zero need to move up, down, or not at all to be above the number
            // if zero needs to go down
                // if zero is immediately above the number determine if it should go left or right before going down
                    // if zero needs to go left
                        // move zero left
                    // else zero needs to go right
                        // move zero right
                // else if zero can move down
                        // move zero down
                // else if zero needs to go up
                        // move zero up
                // else if zero needs to go left
                        // move zero left
                // else if zero needs to go right
                        // move zero right

    // if number needs to go left
        // does zero need to left up, right, or not at all to be above the number
            // if zero needs to go left
                 // if zero is immediately above the number determine if it should go up or down before going left
                    // if zero needs to go down
                        // move zero down
                    // else zero needs to go up
                        // move zero up
                // else if zero can move left
                    // move zero left
                // else if zero needs to go up
                    // move zero up
                // else if zero needs to go down
                    // move zero down
                // else if zero needs to go right
                    // move zero right


    // if number needs to go right
        // does zero need to left up, right, or not at all to be above the number
            // if zero needs to go right
                // if zero is immediately above the number determine if it should go up or down before going right
                    // if zero needs to go down
                        // move zero down
                    // else zero needs to go up
                        // move zero up
                // else if zero can move right
                    // move zero right
                // else if zero needs to go up
                    // move zero up
                // else if zero needs to go down
                    // move zero down
                // else if zero needs to go left
                    // move zero left


// place 1-3

// to get the 4 in place move the 4 just under where it needs to be.
    // move 2 down
    // move 3 left
    // move random in 4 spot left
    // move 4 up
    // 4 is now in place.
// to fix previous numbers.
    // move random left of empty to the right
    // move random above empty down
    // move 3 right
    // move 2 up
    // 1, 2, 3, and 4 are now in place

// place 5 and 9

// to move 13 into place
    // move 13 to the right of its spot
    // move 5 right
    // move 9 up
    // move random in 13 spot up
    // move 13 left
    // 13 is now in its place
// to fix 5 and 9
    // move random above empty down
    // move random left of empty right
    // move 9 down
    // move 5 left

// place 6 and 7
    // follow formula for 4

// solve 2x3
    // solve for 10 then 11 then 10 followed by formula for 4
    // then 14 and 15 should already be where they are supposed to be. if not then it is unsolvable.