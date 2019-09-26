package com.company;

import java.util.Arrays;

public class Puzzle {
    int[][] board = {{10, 3, 6, 4}, {1, 5, 2, 7}, {8, 13, 0 ,15}, {14, 9, 12, 11} };
    int boardLength = board.length;

    protected Puzzle() {

    }

    public int[][] getBoard() {
        return board;
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

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public void topRow(int num) {
        int[] numCoordinates = find(num);
        int[] zeroCoordinates = find(0);
        int[] numEndCoordinates = {(num - 1) / boardLength, (num % boardLength) - 1};
        boolean numberNotInPosition = true;

        while (numberNotInPosition) {
            if (numCoordinates[0] == numEndCoordinates[0] && numCoordinates[1] == numEndCoordinates[1]) {
                // if the number is where it should be then exit the loop
                numberNotInPosition = false;
            } else if (numCoordinates[0] > numEndCoordinates[0]) {
                // if the number is below where it needs to be move it up
                System.out.println("first");
                moveZeroAboveNum(num);
                numCoordinates = find(num);
                zeroCoordinates = find(0);
                if (numCoordinates[0] - 1 == zeroCoordinates[0]) {
                    moveUp(num);
                    numCoordinates = find(num);
                    System.out.println(this.printBoard());
                }
            } else if (numCoordinates[1] > numEndCoordinates[1]) {
                // if the number is right of where it needs to be then move it left
                moveZeroLeftOfNum(num);
                numCoordinates = find(num);
                zeroCoordinates = find(0);
                if (numCoordinates[1] - 1 == zeroCoordinates[0]) {
                    moveLeft(num);
                }
            }
//            if (numCoordinates[0] > numEndCoordinates[0]) {
//
//                moveLeft(num);
//                numCoordinates = find(num);
//                break;
//            }
        }

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

    public void moveZeroAboveNum(int num) {
        System.out.println("Trying to move zero above number");
        int[] numCoordinates = find(num);
        int[] zeroCoordinates = find(0);
        boolean inBounds = false;
        if (zeroCoordinates[0] - 1 > 0) {
            inBounds = (board[zeroCoordinates[0] - 1][zeroCoordinates[1]] != board[numCoordinates[0]][numCoordinates[1]]);
        }
        if (inBounds) {
            // if zero is not directly below the target number move it above the number
            System.out.println("there");
            while (zeroCoordinates[0] >= numCoordinates[0]) {
                // if zero is not directly above the number move it up
                moveUp(0);
                zeroCoordinates = find(0);
                numCoordinates = find(num);
                System.out.println(printBoard());
                System.out.println("Move zero up.");
            }
            while (zeroCoordinates[1] > numCoordinates[1]) {
                // if zero is not directly above the number move it left
                moveLeft(0);
                zeroCoordinates = find(0);
                numCoordinates = find(num);
                System.out.println(printBoard());
                System.out.println("Move zero left.");

            }
            while (zeroCoordinates[0] > numCoordinates[0] - 1) {
                // if zero is not directly above the number move it down
                moveDown(0);
                zeroCoordinates = find(0);
                numCoordinates = find(num);
                System.out.println(printBoard());
                System.out.println("Move zero down.");
            }

            while (zeroCoordinates[1] < numCoordinates[1]) {
                // if zero is not above the number move it right
                moveRight(0);
                zeroCoordinates = find(0);
                numCoordinates = find(num);
                System.out.println(printBoard());
                System.out.println("Move zero right.");
            }
        } else if (zeroCoordinates[1] > 0) {
            // if zero is not at the left edge of the board move it left
            moveLeft(0);
            System.out.println(printBoard());
            System.out.println("Move zero left.");
        } else if (zeroCoordinates[1] < boardLength) {
            // if zero is not at the right edge of the board move it right
            moveRight(0);
            System.out.println(printBoard());
            System.out.println("Move zero right.");
        }

    }

    public void moveZeroLeftOfNum(int num) {
        System.out.println("Trying to move zero left of number.");
        int[] numCoordinates = find(num);
        int[] zeroCoordinates = find(0);
        System.out.println(board[zeroCoordinates[0] - 1][zeroCoordinates[1]]);
        System.out.println(board[numCoordinates[0]][numCoordinates[1]]);
        if (board[zeroCoordinates[0] - 1][zeroCoordinates[1]] != board[numCoordinates[0]][numCoordinates[1]]) {
            // if zero is not directly right the target number move it left the number
            System.out.println("there");
            while (zeroCoordinates[1] > numCoordinates[1] - 1) {
                // if zero is not directly inline the number move it left
                moveLeft(0);
                zeroCoordinates = find(0);
                numCoordinates = find(num);
                System.out.println(printBoard());
                System.out.println("Move zero left.");

            }
            while (zeroCoordinates[0] != numCoordinates[0]) {
                // if zero is not directly inline the number move it up
                moveUp(0);
                zeroCoordinates = find(0);
                numCoordinates = find(num);
                System.out.println(printBoard());
                System.out.println("Move zero up.");
            }
            while (zeroCoordinates[1] < numCoordinates[1] + 1) {
                // if zero is not above the number move it right
                moveRight(0);
                zeroCoordinates = find(0);
                numCoordinates = find(num);
                System.out.println(printBoard());
                System.out.println("Move zero right.");
            }
            while (zeroCoordinates[0] != numCoordinates[0]) {
                // if zero is not directly inline the number move it down
                moveDown(0);
                zeroCoordinates = find(0);
                numCoordinates = find(num);
                System.out.println(printBoard());
                System.out.println("Move zero down.");
            }
        } else if (zeroCoordinates[1] > 0) {
            // if zero is not at the left edge of the board move it left
            moveLeft(0);
            System.out.println(printBoard());
            System.out.println("Move zero left.");
        } else if (zeroCoordinates[1] < boardLength) {
            // if zero is not at the right edge of the board move it right
            moveRight(0);
            System.out.println(printBoard());
            System.out.println("Move zero right.");
        }
    }

    public void moveUp(int num) {
        int[] numCoordinates = find(num);
        int tempNum = board[numCoordinates[0] - 1][numCoordinates[1]];
        if (tempNum == 0 || num == 0) {
            board[numCoordinates[0] - 1][numCoordinates[1]] = board[numCoordinates[0]][numCoordinates[1]];
            board[numCoordinates[0]][numCoordinates[1]] = tempNum;
        }
    }

    public void moveLeft(int num) {
        int[] numCoordinates = find(num);
        int tempNum = board[numCoordinates[0]][numCoordinates[1] - 1];
        if (tempNum == 0 || num == 0) {
            board[numCoordinates[0]][numCoordinates[1] - 1] = board[numCoordinates[0]][numCoordinates[1]];
            board[numCoordinates[0]][numCoordinates[1]] = tempNum;
        }
    }

    public void moveDown(int num) {
        int[] numCoordinates = find(num);
        int tempNum = board[numCoordinates[0] + 1][numCoordinates[1]];
        if (tempNum == 0 || num == 0) {
            board[numCoordinates[0] + 1][numCoordinates[1]] = board[numCoordinates[0]][numCoordinates[1]];
            board[numCoordinates[0]][numCoordinates[1]] = tempNum;
        }
    }

    public void moveRight(int num) {
        int[] numCoordinates = find(num);
        int tempNum = board[numCoordinates[0]][numCoordinates[1] + 1];
        if (tempNum == 0 || num == 0) {
            board[numCoordinates[0]][numCoordinates[1] + 1] = board[numCoordinates[0]][numCoordinates[1]];
            board[numCoordinates[0]][numCoordinates[1]] = tempNum;
        }
    }

    public void move(int num) {
        boolean keepMoving = true;
        while (keepMoving) {
            int[] numCoordinates = find(num);
            int[] zeroCoordinates = find(0);
            int[] numEndCoordinates = {(num - 1) / boardLength, (num % boardLength) - 1};
            int endY = numCoordinates[0] - numEndCoordinates[0];
            int endX = numCoordinates[1] - numEndCoordinates[1];
            int zeroMovesY = numCoordinates[0] - zeroCoordinates[0];
            int zeroMovesX = numCoordinates[1] - zeroCoordinates[1];

            if (board[numCoordinates[0]-1][numCoordinates[1]] == 0) {
                // if the number is where it should be then exit the loop
                System.out.println("Zero in in position over the target number. Moving num up one.");
                moveUp(num);
                keepMoving = false;
            } else if (endY > 0) {
                // num needs to move up
                // where does zero need to go
                if (zeroCoordinates[0]-1 >= 0) {
                    if (board[zeroCoordinates[0] - 1][zeroCoordinates[1]] == num) {
                        // if the target number is directly above zero
                        if (zeroCoordinates[1] < boardLength) {
                            // move zero left
                            moveRight(0);
                        } else if (zeroCoordinates[1] > 0) {
                            // move zero right
                            moveLeft(0);
                        }
                    } else if (zeroMovesY <= 0) {
                        System.out.println("zero is moving up.");
                        // zero needs to move up
                        moveUp(0);
                    }
                } else if (zeroCoordinates[1]-1 >= 0) {
                    if (zeroMovesX > 0) {
                        if (board[zeroCoordinates[0]][zeroCoordinates[1] - 1] == num) {
                            // if the target number is directly right of zero
                            if (zeroCoordinates[0] < boardLength) {
                                // move zero down
                                moveDown(0);
                            } else if (zeroCoordinates[0] > 0) {
                                // move zero up
                                moveUp(0);
                            }
                        }
                    } else if (zeroMovesX < 0) {
                        System.out.println("zero is moving left.");
                        // zero needs to move left
                        moveLeft(0);
                    }
                }
            } else if (endY < 0) {
                // num needs to move down
            }
            System.out.println(printBoard());
        }
    }

    public void loopThroughMoves(int num) {
        move(num);
    }


}


//else if (zeroMovesY > -1 && zeroMovesY <= 0) {
//        System.out.println("zero is moving down.");
//        // zero need to move down
//        moveDown(0);
//        } else if (zeroMovesX < 0) {
//        System.out.println("zero is moving left.");
//        // zero needs to move left
//        moveLeft(0);
//        } else if (zeroMovesX > 0) {
//        System.out.println("zero is moving right.");
//        // zero need to move right
//        moveRight(0);
//        }


    public void positionNumber(int num) {
        int[] numCoordinates = find(num);
        int[] zeroCoordinates = find(0);
        int[] numEndCoordinates = {(num - 1) / boardLength, (num % boardLength) - 1};
        while (!(Arrays.equals(numCoordinates, numEndCoordinates))) {
            // while number is not in its place
            if (numCoordinates[0] - numEndCoordinates[0] > 0) {
                // if number needs to move up
                // does zero need to move up, down, or not at all to be above the number
                if (zeroCoordinates[0] - numCoordinates[0] > 0) {
                    // if zero needs to go up
                    // if zero is immediately below the number determine if it should go left or right before going up
                    // if zero needs to go left
                    // move zero left
                    // else zero needs to go right
                    // move zero right
                    // else if zero can move up
                    // move zero up
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
            }
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
            numCoordinates = find(num);
        }
    }