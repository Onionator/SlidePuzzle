package com.company;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Puzzle {
    int[][] board = {{10, 3, 11, 4}, {1, 5, 14, 7}, {8, 6, 13, 2}, {12, 9, 15, 0}};
    int boardLength = board.length;

    protected Puzzle() {

    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard() {
        this.board = newBoard();
    }

    // find the coordinates of a number and return an int array with y, x coordinates
    public int[] find(int num) {
        for (int y = 0; y < boardLength; y++) {
            for (int x = 0; x < board[y].length; x++) {
                if (board[y][x] == num) {
                    return new int[]{y, x};
                }
            }
        }
        return null;
    }



    int moveCount = 0;
    int[][] finishedPuzzle = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 0}};

    public int[][] newBoard() {
        // The puzzle should always be solvable.
        // If the board length is even and the zero is in in an even row (to determine even row start counting at 1 from the bottom) and the number of inversions is odd then it is solvable.
        // If the board length is even and the zero is in in an odd row (to determine odd row start counting at 1 from the bottom) and the number of inversions is even then it is solvable.
        // If the board length is odd and the number of inversions is even then it is solvable.

        int num = 4;
        int[][] arrayOfArrays = new int[num][num];
        int numSquared = num * num;
        Random randomNum = new Random();
        List<Integer> listOfInts = IntStream.range(0, numSquared).boxed().collect(Collectors.toList());
        Collections.shuffle(listOfInts);
        int count = 0;
        for (int y = 0; y < num; y++) {
            for (int x = 0; x < num; x++) {
                arrayOfArrays[y][x] = listOfInts.get(count);
                count++;
            }
        }

        // Can the puzzle be solved?
        int zeroY = listOfInts.indexOf(0) / num;
        System.out.println(zeroY);
        int inversions = 0;
        for (int i = 0; i < listOfInts.size(); i++) {
            while (listOfInts.indexOf(i) != i) {
                int index = listOfInts.indexOf(i);
                int temp;
                if (index > i) {
                    temp = listOfInts.get(index - 1);
                    listOfInts.set(index - 1, i);
                } else {
                    temp = listOfInts.get(index + 1);
                    listOfInts.set(index + 1, i);
                }
                listOfInts.set(index, temp);
                inversions++;
            }
        }

        if (zeroY % 2 == 0 && inversions % 2 == 1) {
            System.out.println("Can be solved");
        } else if (zeroY % 2 == 1 && inversions % 2 == 0) {
            System.out.println("Can be solved");
        } else {
            System.out.println("Can't be solved.");
            newBoard();
        }

        return arrayOfArrays;
    }

    public String printBoard() {
        String output = "";
        for (int[] y: board) {
            for (int x: y) {
                output += Integer.toString(x);
                output += (x < 10) ? ",  ": ", ";
            }
            output +=  "\n";
        }
        return output;
    }

    public int getMoveCount() {
        return moveCount;
    }

    public void setMoveCount(int moveCount) {
        this.moveCount = moveCount;
    }

    public int[][] getFinishedPuzzle() {
        return finishedPuzzle;
    }

    public void moveUp(int num) {
        int[] numCoordinates = find(num);
        int tempNum = board[numCoordinates[0] - 1][numCoordinates[1]];
        if (tempNum == 0 || num == 0) {
            board[numCoordinates[0] - 1][numCoordinates[1]] = board[numCoordinates[0]][numCoordinates[1]];
            board[numCoordinates[0]][numCoordinates[1]] = tempNum;
            setMoveCount(getMoveCount() + 1);
            System.out.println(printBoard());
        }
    }

    public void moveLeft(int num) {
        int[] numCoordinates = find(num);
        int tempNum = board[numCoordinates[0]][numCoordinates[1] - 1];
        if (tempNum == 0 || num == 0) {
            board[numCoordinates[0]][numCoordinates[1] - 1] = board[numCoordinates[0]][numCoordinates[1]];
            board[numCoordinates[0]][numCoordinates[1]] = tempNum;
            setMoveCount(getMoveCount() + 1);
            System.out.println(printBoard());
        }
    }

    public void moveDown(int num) {
        int[] numCoordinates = find(num);
        int tempNum = board[numCoordinates[0] + 1][numCoordinates[1]];
        if (tempNum == 0 || num == 0) {
            board[numCoordinates[0] + 1][numCoordinates[1]] = board[numCoordinates[0]][numCoordinates[1]];
            board[numCoordinates[0]][numCoordinates[1]] = tempNum;
            setMoveCount(getMoveCount() + 1);
            System.out.println(printBoard());
        }
    }

    public void moveRight(int num) {
        int[] numCoordinates = find(num);
        int tempNum = board[numCoordinates[0]][numCoordinates[1] + 1];
        if (tempNum == 0 || num == 0) {
            board[numCoordinates[0]][numCoordinates[1] + 1] = board[numCoordinates[0]][numCoordinates[1]];
            board[numCoordinates[0]][numCoordinates[1]] = tempNum;
            setMoveCount(getMoveCount() + 1);
            System.out.println(printBoard());
        }
    }

    public boolean positionNumber(int num) {
        int[] numCoordinates = find(num);
        int[] zeroCoordinates = find(0);
        int[] numEndCoordinates = {(num - 1) / boardLength, (num % boardLength) - 1};
        boolean numIsFarthestRight = false;
        boolean numIsFarthestDown = false;
        if ((boardLength * boardLength) - 2 == num && Arrays.equals(find((boardLength * boardLength) - 1), numEndCoordinates)) {
            // if it is the second to last number and the last number is in the place of the where the second to last should be then it is unsolvable
            // 13, 15, 14 == unsolvable
            return false;
        }
        if (numEndCoordinates[1] == -1) {
            // if the number belongs at the far right of the puzzle then change its end coordinates to be at the far right and one below where it should be
            numEndCoordinates[1] = boardLength - 1;
            if (!(Arrays.equals(numCoordinates, numEndCoordinates))) {
                numIsFarthestRight = true;
                numEndCoordinates[0] = numEndCoordinates[0] + 1;
            }
        } else if (numEndCoordinates[0] == boardLength - 1 && numEndCoordinates[1] < boardLength - 3) {
            // if the number belongs at the bottom of the puzzle and is not within the bottom right 3x3 squares change its coordinates to the one right of where it is supposed to go
            numEndCoordinates[1] += 1;
            numIsFarthestDown = true;
        }
        while (!(Arrays.equals(numCoordinates, numEndCoordinates))) {
            // while number is not in its place
            if (numCoordinates[0] - numEndCoordinates[0] > 0) {
                System.out.println("Num needs to go up.");
                // if number needs to move up
                // does zero need to move up, down, or not at all to be above the number
                if (zeroCoordinates[0] - numCoordinates[0] >= 0) {
                    // if zero needs to go up
                    if (board[zeroCoordinates[0] - 1][zeroCoordinates[1]] == num) {
                        System.out.println("Target number is directly above zero");
                        // if zero is immediately below the number determine if it should go left or right before going up
                        if (zeroCoordinates[1] < boardLength - 1) {
                            // if zero needs to go right
                            // move zero right
                            moveRight(0);
                        } else {
                            // else zero needs to go left
                            // move zero left
                            moveLeft(0);
                        }
                    } else if (board[zeroCoordinates[0] - 1][zeroCoordinates[1]] > num) {
                        // else if zero can move up
                        // move zero up
                        moveUp(0);
                    } else if (zeroCoordinates[1] < boardLength - 1) {
                        // if zero needs to go right
                        // move zero right
                        moveRight(0);
                    } else {
                        // else zero needs to go left
                        // move zero left
                        moveLeft(0);
                    }
                } else if (zeroCoordinates[0] - numCoordinates[0] < -1) {
                    // else if zero needs to go down
                    // move zero down
                    moveDown(0);
                } else if (zeroCoordinates[1] - numCoordinates[1] > 0) {
                    // else if zero needs to go left
                    // move zero left
                    moveLeft(0);
                } else if (zeroCoordinates[1] - numCoordinates[1] < 0) {
                    // else if zero needs to go right
                    // move zero right
                    moveRight(0);
                }
                zeroCoordinates = find(0);
                if (numCoordinates[0] - 1 == zeroCoordinates[0] && numCoordinates[1] == zeroCoordinates[1]) {
                    // if zero is directly above num move num up
                    moveUp(num);
                }
            } else if (numCoordinates[0] - numEndCoordinates[0] < 0) {
                System.out.println("Num needs to go down.");
                // else if number needs to go down
                // does zero need to move up, down, or not at all to be above the number
                if (zeroCoordinates[0] - numCoordinates[0] <= 0) {
                    // if zero needs to go down
                    if (board[zeroCoordinates[0] + 1][zeroCoordinates[1]] == num) {
                        // if zero is immediately above the number determine if it should go left or right before going down
                        if (zeroCoordinates[1] < boardLength - 1) {
                            // if zero needs to go right
                            // move zero right
                            moveRight(0);
                        } else {
                            // else zero needs to go left
                            // move zero left
                            moveLeft(0);
                        }
                    } else {
                        // else if zero can move down
                        // move zero down
                        moveDown(0);
                    }
                } else if (zeroCoordinates[1] - numCoordinates[1] > 0) {
                    // else if zero needs to go left
                    // move zero left
                    moveLeft(0);
                } else if (zeroCoordinates[1] - numCoordinates[1] < 0) {
                    // else if zero needs to go right
                    // move zero right
                    moveRight(0);
                } else if (zeroCoordinates[0] - numCoordinates[0] > 0) {
                    // else if zero needs to go up
                    // move zero up
                    moveUp(0);
                }
                zeroCoordinates = find(0);
                if (numCoordinates[0] + 1 == zeroCoordinates[0] && numCoordinates[1] == zeroCoordinates[1]) {
                    // if zero is directly below num move num down
                    moveDown(num);
                }
            } else if (numCoordinates[1] - numEndCoordinates[1] > 0) {
                System.out.println("Num needs to go left.");
                // if number needs to go left
                // does zero need to left up, right, or not at all to be above the number
                if (zeroCoordinates[1] - numCoordinates[1] >= 0) {
                    // if zero needs to go left
                    if (board[zeroCoordinates[0]][zeroCoordinates[1] - 1] == num) {
                        // if zero is right of the number determine if it should go up or down before going left
                        if (zeroCoordinates[0] < boardLength - 1) {
                            // if zero needs to go down
                            // move zero down
                            moveDown(0);
                        } else {
                            // else zero needs to go up
                            // move zero up
                            moveUp(0);
                        }
                    } else {
                        // else if zero can move left
                        // move zero left
                        moveLeft(0);
                    }
                } else if (zeroCoordinates[0] - numCoordinates[0] > 0) {
                    // else if zero needs to go up
                    // move zero up
                    moveUp(0);
                } else if (zeroCoordinates[0] - numCoordinates[0] < 0) {
                    // else if zero needs to go down
                    // move zero down
                    moveDown(0);
                } else if (zeroCoordinates[1] - numCoordinates[1] < 0) {
                    // else if zero needs to go right
                    // move zero right
                    moveRight(0);
                }
                zeroCoordinates = find(0);
                if (numCoordinates[0] == zeroCoordinates[0] && numCoordinates[1] - 1 == zeroCoordinates[1]) {
                    // if zero is directly left of num move num left
                    moveLeft(num);
                }
            } else if ((numCoordinates[1] - numEndCoordinates[1] < 0)) {
                System.out.println("Num needs to go right.");
                // if number needs to go right
                // does zero need to the right of the number
                if (zeroCoordinates[1] - numCoordinates[1] <= 0) {
                    // if zero needs to go right
                    if (board[zeroCoordinates[0]][zeroCoordinates[1] + 1] == num) {
                        // if zero is immediately to the left of the number determine if it should go up or down before going right
                        if (zeroCoordinates[0] < boardLength - 1) {
                            // if zero needs to go down
                            // move zero down
                            moveDown(0);
                        } else {
                            // else zero needs to go up
                            // move zero up
                            moveUp(0);
                        }
                    } else {
                        // else if zero can move right
                        // move zero right
                        moveRight(0);
                    }
                } else if (zeroCoordinates[0] - numCoordinates[0] > 0) {
                    // else if zero needs to go up
                    // move zero up
                    moveUp(0);
                } else if (zeroCoordinates[0] - numCoordinates[0] < 0) {
                    // else if zero needs to go down
                    // move zero down
                    moveDown(0);
                } else if (zeroCoordinates[1] - numCoordinates[1] > 0) {
                    // else if zero needs to go left
                    // move zero left
                    moveLeft(0);
                }
                zeroCoordinates = find(0);
                if (numCoordinates[0] == zeroCoordinates[0] && numCoordinates[1] + 1 == zeroCoordinates[1]) {
                    // if zero is directly right of num move run right
                    moveRight(num);
                }
            }
            zeroCoordinates = find(0);
            numCoordinates = find(num);
            if (Arrays.equals(numCoordinates, numEndCoordinates) && numIsFarthestRight) {
                // if the number is below where it needs to be and it is the last number in a row start the move sequence
                if (Arrays.equals(zeroCoordinates, new int[]{numCoordinates[0] + 1, numCoordinates[1]})) {
                    // if the zero is beneath the number move it left 1 and up 1 first.
                    moveLeft(0);
                    moveUp(0);
                }
                moveLeft(0);
                moveUp(0);
                moveRight(0);
                moveRight(0);
                moveDown(0);
                moveLeft(0);
                moveUp(0);
                moveLeft(0);
                moveDown(0);
                break;
            } else if (Arrays.equals(numCoordinates, numEndCoordinates) && numIsFarthestDown) {
                System.out.println("num is: " + num);
                // number belongs on the bottom left corner and is currently one space to the right
                if (Arrays.equals(zeroCoordinates, new int[] {numCoordinates[0], numCoordinates[1] + 1})) {
                    // if the 0 is to the right of the number then move it above
                    moveUp(0);
                    moveLeft(0);
                }
                moveUp(0);
                moveLeft(0);
                moveDown(0);
                moveDown(0);
                moveRight(0);
                moveUp(0);
                moveLeft(0);
                moveUp(0);
                moveRight(0);
                break;
            }
            numCoordinates = find(num);
        }
        if (numCoordinates[0] > zeroCoordinates[0]) {
            moveDown(0);
        }
        return true;
    }
}