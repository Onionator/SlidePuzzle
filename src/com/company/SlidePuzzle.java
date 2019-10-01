package com.company;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SlidePuzzle {
    int[][] board = {{1,  2,  3,  4},
            {5,  0,  14, 10},
            {8,  9,  13, 7},
            {6,  15, 11, 12 }};
    int boardLength = board.length;
    int moveCount = 0;
    int[][] finishedPuzzle = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 0}};

    protected SlidePuzzle() {

    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

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
        int inversions = 0;
        for (int i = 0; i < listOfInts.size() - 1; i++) {
            for (int n = i + 1; n < listOfInts.size(); n++) {
                if (listOfInts.get(i) > listOfInts.get(n)) {
                    inversions++;
                }
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

    public void isSolvable(List<Integer> listOfInts) {
        // Can the puzzle be solved?
        int zeroY = listOfInts.indexOf(0) / boardLength;
        int inversions = 0;
        // Can the puzzle be solved?
        for (int i = 0; i < listOfInts.size() - 1; i++) {
            for (int n = i + 1; n < listOfInts.size(); n++) {
                if (listOfInts.get(i) > listOfInts.get(n)) {
                    inversions++;
                }
            }
        }

        if (zeroY % 2 == 0 && inversions % 2 == 1) {
            System.out.println("Can be solved");
        } else if (zeroY % 2 == 1 && inversions % 2 == 0) {
            System.out.println("Can be solved");
        } else {
            System.out.println("Can't be solved.");
        }
        System.out.println("Inversions: " + inversions);
        System.out.println("zeroY: " + zeroY);
    }

    public String printBoard() {
        String output = "";
        for (int[] y : board) {
            for (int x : y) {
                output += Integer.toString(x);
                output += (x < 10) ? ",  " : ", ";
            }
            output += "\n";
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


    public void moveUp(int num) {
        int[] numCoordinates = find(num);
        int tempNum = board[numCoordinates[0] - 1][numCoordinates[1]];
        if (tempNum == 0 || num == 0) {
            board[numCoordinates[0] - 1][numCoordinates[1]] = board[numCoordinates[0]][numCoordinates[1]];
            board[numCoordinates[0]][numCoordinates[1]] = tempNum;
            setMoveCount(getMoveCount() + 1);
        }
        System.out.println(printBoard());
    }

    public void moveLeft(int num) {
        int[] numCoordinates = find(num);
        int tempNum = board[numCoordinates[0]][numCoordinates[1] - 1];
        if (tempNum == 0 || num == 0) {
            board[numCoordinates[0]][numCoordinates[1] - 1] = board[numCoordinates[0]][numCoordinates[1]];
            board[numCoordinates[0]][numCoordinates[1]] = tempNum;
            setMoveCount(getMoveCount() + 1);
        }
        System.out.println(printBoard());
    }

    public void moveDown(int num) {
        int[] numCoordinates = find(num);
        int tempNum = board[numCoordinates[0] + 1][numCoordinates[1]];
        if (tempNum == 0 || num == 0) {
            board[numCoordinates[0] + 1][numCoordinates[1]] = board[numCoordinates[0]][numCoordinates[1]];
            board[numCoordinates[0]][numCoordinates[1]] = tempNum;
            setMoveCount(getMoveCount() + 1);
        }
        System.out.println(printBoard());
    }

    public void moveRight(int num) {
        int[] numCoordinates = find(num);
        int tempNum = board[numCoordinates[0]][numCoordinates[1] + 1];
        if (tempNum == 0 || num == 0) {
            board[numCoordinates[0]][numCoordinates[1] + 1] = board[numCoordinates[0]][numCoordinates[1]];
            board[numCoordinates[0]][numCoordinates[1]] = tempNum;
            setMoveCount(getMoveCount() + 1);
        }
        System.out.println(printBoard());
    }

    public void solveFor(int num) {
        // Determine the best path for zero to take to move the number into its spot.
            int count = 0;
        while (count < 16) {
            count++;
            System.out.println("count: " + count);
            int[] numCoordinates = find(num);
            int[] zeroCoordinates = find(0);
            int[] numEndCoordinates = {(num - 1) / boardLength, (num % boardLength) - 1};
            boolean leftColumnNumber = false;
            boolean numIsFarthestRight = false;
            boolean lastLeftColumnNumber = false;

            if (numEndCoordinates[1] == -1) {
                // if the number belongs at the far right of the puzzle then change its end coordinates to be at the far right and one below where it should be
                numEndCoordinates[1] = boardLength - 1;
                if (!(Arrays.equals(numCoordinates, numEndCoordinates))) {
                    numIsFarthestRight = true;
                    numEndCoordinates[0] = numEndCoordinates[0] + 1;
                }
            } else if (numEndCoordinates[1] + 3 < boardLength) {
                // number belongs in the farthest left unsolved column
                leftColumnNumber = true;
                System.out.println();
                if (numEndCoordinates[0] == (boardLength - 1)) {
                    // if it is the last number adjust its end coordinates
                    numEndCoordinates[1] = numEndCoordinates[1] + 1;
                    lastLeftColumnNumber = true;
                }
            }
            System.out.println("lastLeftColumnNumber: " + lastLeftColumnNumber + " " + numCoordinates[0] + " " + (boardLength - 1));
            System.out.println("num is: " + num);
            System.out.println("numCoordinates: " + Arrays.toString(numCoordinates));
            System.out.println("numEndCoordinates: " + Arrays.toString(numEndCoordinates));

            if (Arrays.equals(numCoordinates, numEndCoordinates) && numIsFarthestRight) {
                // if the number is below where it needs to be and it is the last number in a row start the move sequence
                int[] zeroEndCoordinates = {numCoordinates[0] - 1, numCoordinates[1]};
                // num needs to go up
                if (Arrays.equals(zeroCoordinates, zeroEndCoordinates)) {
                    // num is ready to move up
                    moveUp(num);
                } else {
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
                }
            } else if (Arrays.equals(numCoordinates, numEndCoordinates) && lastLeftColumnNumber) {
                // number belongs on the bottom left corner and is currently one space to the right
                if (zeroCoordinates[1] == numEndCoordinates[1] - 1) {
                    // if zero is where num needs to be then move num left
                    moveLeft(num);
                } else {
                    if (Arrays.equals(zeroCoordinates, new int[]{numCoordinates[0], numCoordinates[1] + 1})) {
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
            } else if (Arrays.equals(numCoordinates, numEndCoordinates)) {
                System.out.println("It's over now.");
                break;
            }

            int yMoves = numEndCoordinates[0] - numCoordinates[0];
            int xMoves = numEndCoordinates[1] - numCoordinates[1];

            // UP UP UP UP UP UP UP UP UP UP UP UP UP UP UP UP UP UP UP UP UP UP UP UP UP UP UP UP UP UP UP UP
            if (yMoves < 0) {
                System.out.println("num needs to go up");
                int[] zeroEndCoordinates = {numCoordinates[0] - 1, numCoordinates[1]};
                System.out.println("zeroCoordinates: " + Arrays.toString(zeroCoordinates));
                System.out.println("zeroEndCoordinates: " + Arrays.toString(zeroEndCoordinates));
                // num needs to go up
                if (Arrays.equals(zeroCoordinates, zeroEndCoordinates)) {
                    // num is ready to move up
                    moveUp(num);
                } else if (zeroEndCoordinates[0] - zeroCoordinates[0] < 0) {
                    // if zero needs to go up
                    if (board[zeroCoordinates[0] - 1][zeroCoordinates[1]] > num || board[zeroCoordinates[0] - 1][zeroCoordinates[1]] > num - boardLength && board[zeroCoordinates[0] - 1][zeroCoordinates[1]] != num && leftColumnNumber) {
                        // Zero can move up
                        moveUp(0);
                        if (xMoves > 0) {
                            // num needs to move right
                        }
                    } else if (zeroCoordinates[1] < boardLength - 1) {
                        // Zero will stay within the boundaries
                        if (xMoves > 0 && board[numCoordinates[0]][numCoordinates[1] + 1] == 0) {
                            // num can move right and needs to move right then move it right
                            moveRight(num);
                        } else if (board[zeroCoordinates[0]][zeroCoordinates[1] + 1] > num || board[zeroCoordinates[0]][zeroCoordinates[1] + 1] > num - boardLength && leftColumnNumber) {
                            // Zero can move right
                            moveRight(0);
                        } else {
                            // if num needs to go up and zero is left of it and zero can 't go up...
                            moveDown(0);
                            moveRight(0);
                            moveRight(0);
                            moveUp(0);
                            if (board[numCoordinates[0] -1][numCoordinates[1]] > num) {
                                moveUp(0);
                                moveLeft(0);
                            } else {
                                moveRight(num);
                            }
                        }
                    } else if (zeroCoordinates[1] == boardLength - 1) {
                        moveLeft(0);
                        moveUp(0);
                        if (xMoves < 0) {
                            // if the number also needs to go left move it left
                            moveLeft(num);
                        } else {
                            moveUp(0);
                            moveRight(0);
                        }
                    } else {
                        // if num needs to go up and zero is left of it and zero can't go up...
                        moveDown(0);
                        moveRight(0);
                        moveRight(0);
                        moveUp(0);
                        moveUp(0);
                        moveLeft(0);
                    }
                } else if (zeroEndCoordinates[1] - zeroCoordinates[1] < 0) {
                    // zero needs to go left
                    if (board[zeroCoordinates[0]][zeroCoordinates[1] - 1] > num || board[zeroCoordinates[0]][zeroCoordinates[1] - 1] > num - boardLength && leftColumnNumber) {
                        // if zero can follow the rules and go left
                        // move zero left
                        moveLeft(0);
                    } else {
                        // move zero down
                        moveDown(0);
                        moveRight(num);
                    }
                } else if (zeroEndCoordinates[1] - zeroCoordinates[1] > 0) {
                    // zero needs to go right
                    moveRight(0);
                } else if (zeroEndCoordinates[0] - zeroCoordinates[0] > 0) {
                    // zero needs to go down
                    moveDown(0);
                }
                // LEFT LEFT LEFT LEFT LEFT LEFT LEFT LEFT LEFT LEFT LEFT LEFT LEFT LEFT LEFT LEFT LEFT LEFT LEFT LEFT LEFT LEFT
            } else if (xMoves < 0) {
                System.out.println("Num needs to go left");
                int[] zeroEndCoordinates = {numCoordinates[0], numCoordinates[1] - 1};
                // if num needs to go left
                if (Arrays.equals(zeroCoordinates, zeroEndCoordinates)) {
                    // num is ready to move up
                    moveLeft(num);
                } else if (zeroEndCoordinates[1] - zeroCoordinates[1] < 0) {
                    // if zero needs to go left
                    if (board[zeroCoordinates[0]][zeroCoordinates[1] - 1] > num || board[zeroCoordinates[0]][zeroCoordinates[1] - 1] != num - boardLength && board[zeroCoordinates[0]][zeroCoordinates[1] - 1] != num && leftColumnNumber) {
                        // Zero can move left
                        moveLeft(0);
                    } else if (zeroCoordinates[0] < boardLength - 1) {
                        System.out.println("Here!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                        // if zero is not at the bottom
                        if (board[zeroCoordinates[0] + 1][zeroCoordinates[1]] > num && board[zeroCoordinates[0] + 1][zeroCoordinates[1]] != num || board[zeroCoordinates[0] + 1][zeroCoordinates[1]] > num - boardLength && board[zeroCoordinates[0] + 1][zeroCoordinates[1]] != num && leftColumnNumber) {
                            // Zero can move down
                            moveDown(0);
                            moveLeft(0);
                            if (yMoves > 0) {
                                moveDown(num);
                            }
                        } else {
                            // move all the way around the number
                            moveRight(0);
                            moveDown(0);
                            moveDown(0);
                            moveLeft(0);
                            moveLeft(0);
                            moveUp(0);
                        }
                    } else if (zeroCoordinates[0] == boardLength - 1) {
                        // Zero is at the bottom therefore it can move up
                        moveUp(0);
                        moveLeft(0);
                    } else {
                        // if num needs to go left and zero is above it and zero can't go left...
                        moveRight(0);
                        moveDown(0);
                        moveDown(0);
                        moveLeft(0);
                        moveLeft(0);
                        moveUp(0);
                    }
                } else if (zeroEndCoordinates[0] - zeroCoordinates[0] < 0) {
                    // zero needs to go up
                    System.out.println("board[zeroCoordinates[0] - 1][zeroCoordinates[1]] > num - boardLength: " + (board[zeroCoordinates[0] - 1][zeroCoordinates[1]] > num - boardLength));
                    System.out.println(board[zeroCoordinates[0] - 1][zeroCoordinates[1]]);
                    System.out.println(num - boardLength);
                    if (board[zeroCoordinates[0] - 1][zeroCoordinates[1]] > num || board[zeroCoordinates[0] - 1][zeroCoordinates[1]] > num - boardLength && leftColumnNumber) {
                        // Zero can move up
                        moveUp(0);
                    } else {
                        // move zero right then up
                        moveRight(0);
                        moveUp(0);
                    }
                } else if (zeroEndCoordinates[0] - zeroCoordinates[0] > 0) {
                    // zero needs to go down
                    // move zero down
                    moveDown(0);
                } else if (zeroEndCoordinates[1] - zeroCoordinates[1] > 0) {
                    // zero needs to go right
                    // move zero right
                    moveRight(0);
                }
            // RIGHT RIGHT RIGHT RIGHT RIGHT RIGHT RIGHT RIGHT RIGHT RIGHT RIGHT RIGHT RIGHT RIGHT RIGHT RIGHT RIGHT RIGHT RIGHT RIGHT RIGHT RIGHT
            } else if (xMoves > 0) {
                System.out.println("num needs to go right");
                // errors here with num going right
                int[] zeroEndCoordinates = {numCoordinates[0], numCoordinates[1] + 1};
                // if num needs to go right
                if (board[numCoordinates[0]][numCoordinates[1] + 1] == 0 && numIsFarthestRight) {
                    // Num can move right
                    moveRight(num);
                } else if (zeroEndCoordinates[1] - zeroCoordinates[1] > 0) {
                    // if zero needs to move right
                    if (board[zeroCoordinates[0]][zeroCoordinates[1] + 1] > num || board[zeroCoordinates[0]][zeroCoordinates[1] + 1] > num - boardLength && leftColumnNumber) {
                        // Zero can move right
                        moveRight(0);
                    } else if (zeroCoordinates[0] + 1 == boardLength) {
                        moveUp(0);
                        moveRight(0);
                        moveDown(0);
                        moveRight(0);
                        moveUp(0);
                        moveLeft(0);
                        moveLeft(0);
                        moveDown(0);
                        moveRight(0);
                        moveRight(0);
                    } else {
                        // move zero down then right first
                        moveDown(0);
                        moveRight(0);
                        moveRight(0);
                        moveUp(0);
                        moveRight(num);
                    }
                } else if (zeroEndCoordinates[0] - zeroCoordinates[0] > 0) {
                    // if zero needs to go down
                    moveDown(0);
                } else if (zeroEndCoordinates[1] - zeroCoordinates[1] < 0) {
                    // if zero needs to go left
                    if (board[zeroCoordinates[0]][zeroCoordinates[1] - 1] > num) {
                        // Zero can move left
                        moveLeft(0);
                    } else {
                        // move zero down then left
                        moveDown(0);
                        moveLeft(0);
                    }
                } else if (zeroEndCoordinates[0] - zeroCoordinates[0] < 0) {
                    // if zero needs to move up
                    moveUp(0);
                }
            } else if (yMoves > 0) {
                System.out.println("num needs to move down");
                // if num needs to move down
                int[] zeroEndCoordinates = {numCoordinates[0] + 1, numCoordinates[1]};
                if (board[numCoordinates[0] + 1][numCoordinates[1]] == 0) {
                    // if num can move down move it down
                    moveDown(num);
                } else if (zeroEndCoordinates[0] - zeroCoordinates[0] > 0) {
                    // if zero needs to move down
                    if (board[zeroCoordinates[0] + 1][zeroCoordinates[1]] != num) {
                        // if zero can move down move it down
                        moveDown(0);
                    } else {
                        // move zero right
                        moveRight(0);
                    }
                } else if (zeroEndCoordinates[1] - zeroCoordinates[1] < 0) {
                    // if zero needs to move left
                    if (board[zeroCoordinates[0]][zeroCoordinates[1] + 1] != num) {
                        // if zero can move left move it left
                        moveLeft(0);
                    } else {
                        // move zero down
                        moveDown(0);
                    }
                } else if (zeroEndCoordinates[1] - zeroCoordinates[1] > 0) {
                    // if zero needs to move right
                    moveRight(0);
                }
            }
        }
    }
}
//
//        // if num needs to go left and zero is above it and zero can't go left...
//        moveRight(0);
//        moveDown(0);
//        moveDown(0);
//        moveLeft(0);
//        moveLeft(0);
//        moveUp(0);
//
//        // if num needs to go up and zero is left of it and zero can't go up...
//        moveDown(0);
//        moveRight(0);
//        moveRight(0);
//        moveUp(0);
//        moveUp(0);
//        moveLeft(0);
//
//        // if num is the last in a row and is directly below its spot...
//            // if zero is below num
//            moveLeft(0);
//            moveUp(0);
//        moveLeft(0);
//        moveUp(0);
//        moveRight(0);
//        moveRight(0);
//        moveDown(0);
//        moveLeft(0);
//        moveUp(0);
//        moveLeft(0);
//        moveDown(0);
//
//        // if num is the last (bottom) in a column and is directly right of where it needs to be
//            // if zero is right of the number
//            moveUp(0);
//            moveLeft(0);
//        moveUp(0);
//        moveLeft(0);
//        moveDown(0);
//        moveDown(0);
//        moveRight(0);
//        moveUp(0);
//        moveLeft(0);
//        moveUp(0);
//        moveRight(0);
//
//
//        // Assuming the number is 1 and it needs to move up and left determine if it should move up or left first.
//        if (yMoves < 0) {
//            // The number needs to move up
//            // How many moves would it take to get zero in position to move the number up?
//            int[] zeroEndCoordinates = {numCoordinates[0] - 1, numCoordinates[1]};
//            int zeroYMoves = zeroEndCoordinates[0] - numCoordinates[0];
//            int zeroXMoves = zeroEndCoordinates[1] - numCoordinates[1];
//            // Can zero make this move without affecting an already placed number?
//            if (zeroYMoves < 0) {
//                // If zero needs to move up
//                int zeroYMovesPositive = zeroYMoves * -1;
//                int zeroXMovesPositive = zeroXMoves < 0 ? zeroXMoves * -1 : zeroXMoves;
//
//
//                // see if zero can move up as fas as it needs to. then add move sure it would end up in its spot. then add the moves together and see if one route is short than another to determine which direction it should go first.
//                for (int i = 1; i <= zeroYMovesPositive; i++) {
//                    if (board[zeroCoordinates[0] - i][zeroCoordinates[1]] > num || board[zeroCoordinates[0] - i][zeroCoordinates[1]] > num - boardLength && leftColumnNumber) {
//                        // Zero can move up
//                        upMoves++;
//                    }
//                }
//                if (upMoves == zeroYMovesPositive) {
//                    if (zeroXMoves < 0) {
//                        for (int i = 1; i <= zeroXMovesPositive; i++) {
//                            if (board[zeroCoordinates[0] + zeroYMovesPositive][zeroCoordinates[1] - i] > num || board[zeroCoordinates[0] + zeroYMovesPositive][zeroCoordinates[1] - i] > num - boardLength && leftColumnNumber) {
//                                // Zero can move left
//                                leftMoves++;
//                            }
//                        }
//                        if (leftMoves == zeroXMovesPositive) {
//                            routeOne = upMoves + leftMoves;
//                        }
//                    }
//                }
//
//                     else if (zeroCoordinates[1] > 0) {
//                        // Zero will stay within the boundaries
//
//                    } else if (zeroCoordinates[1] < boardLength - 1) {
//                        // Zero will stay within the boundaries
//                        if (board[zeroCoordinates[0] + i][zeroCoordinates[1]] > num || board[zeroCoordinates[0] + i][zeroCoordinates[1]] > num - boardLength && leftColumnNumber) {
//                            // Zero can move down
//                            upMoves++;
//                            moveDown(0);
//                        }
//                    }
//                }
//
//
//
//
//        }
//        if (xMoves < 0) {
//            // The number needs to move left
//            // How many moves would it take to get zero in position to move the number left?
//
//            // Can zero make this move without affecting an already placed number?
//            // If the num is greater than the number zero is trying to swap with then it is true.
//            // The leftColumnNumbers have some exceptions to the first conditional because they are not moved in numerical order.
//            int[] zeroEndCoordinates = {numCoordinates[0], numCoordinates[1] - 1};
//            int zeroYMoves = zeroEndCoordinates[0] - numCoordinates[0];
//            int ZeroXMoves = zeroEndCoordinates[1] - numCoordinates[1];
//
//            System.out.println(zeroOptions(num));
//        }
//        if (yMoves > 0) {
//            // The number needs to move down
//            // How many moves would it take to get zero in position to move the number down?
//
//            // Can zero make this move without affecting an already placed number?
//            // If the num is greater than the number zero is trying to swap with then it is true.
//            // The leftColumnNumbers have some exceptions to the first conditional because they are not moved in numerical order.
//            int[] zeroEndCoordinates = {numCoordinates[0] + 1, numCoordinates[1]};
//            int zeroYMoves = zeroEndCoordinates[0] - numCoordinates[0];
//            int ZeroXMoves = zeroEndCoordinates[1] - numCoordinates[1];
//
//            System.out.println(zeroOptions(num));
//        }
//        if (xMoves > 0) {
//            // The number needs to move right
//            // How many moves would it take to get zero in position to move the number right?
//
//            // Can zero make this move without affecting an already placed number?
//            // If the num is greater than the number zero is trying to swap with then it is true.
//            // The leftColumnNumbers have some exceptions to the first conditional because they are not moved in numerical order.
//            int[] zeroEndCoordinates = {numCoordinates[0], numCoordinates[1] + 1};
//            int zeroYMoves = zeroEndCoordinates[0] - numCoordinates[0];
//            int ZeroXMoves = zeroEndCoordinates[1] - numCoordinates[1];
//
//            System.out.println(zeroOptions(num));
//        }
//
//        // Determine the proper order then move to zero into position
//
//
//    }
//
//    public List<String> zeroOptions(int num) {
//        int[] numCoordinates = find(num);
//        int[] zeroCoordinates = find(0);
//        int[] zeroEndCoordinates = {numCoordinates[0], numCoordinates[1] + 1};
//        int zeroYMoves = zeroEndCoordinates[0] - numCoordinates[0];
//        int zeroXMoves = zeroEndCoordinates[1] - numCoordinates[1];
//        List<String> listOfMoves = null;
//        boolean leftColumnNumber = false;
//
//
//        if (zeroYMoves < 0) {
//            // If zero needs to move up
//            if (board[zeroCoordinates[0] - 1][zeroCoordinates[1]] > num || board[zeroCoordinates[0] - 1][zeroCoordinates[1]] > num - boardLength && leftColumnNumber) {
//                // Zero can move up
//                listOfMoves.add("Move zero up.");
//                moveUp(0);
//            } else if (board[zeroCoordinates[0]][zeroCoordinates[1] - 1] > num || board[zeroCoordinates[0]][zeroCoordinates[1] - 1] > num - boardLength && leftColumnNumber) {
//                // Zero can move left
//                listOfMoves.add("Move zero left.");
//                moveLeft(0);
//            } else if (board[zeroCoordinates[0]][zeroCoordinates[1] + 1] > num || board[zeroCoordinates[0]][zeroCoordinates[1] + 1] > num - boardLength && leftColumnNumber) {
//                // Zero can move right
//                listOfMoves.add("Move zero right.");
//                moveRight(0);
//            } else if (board[zeroCoordinates[0] + 1][zeroCoordinates[1]] > num || board[zeroCoordinates[0] + 1][zeroCoordinates[1]] > num - boardLength && leftColumnNumber) {
//                // Zero can move down
//                listOfMoves.add("Move zero down.");
//                moveDown(0);
//            }
//        }
//
//        if (zeroXMoves < 0) {
//            // If zero needs to move left
//            if (board[zeroCoordinates[0]][zeroCoordinates[1] - 1] > num || board[zeroCoordinates[0]][zeroCoordinates[1] - 1] > num - boardLength && leftColumnNumber) {
//                // Zero can move left
//                listOfMoves.add("Move zero left.");
//            } else if (board[zeroCoordinates[0] - 1][zeroCoordinates[1]] > num || board[zeroCoordinates[0] - 1][zeroCoordinates[1]] > num - boardLength && leftColumnNumber) {
//                // Zero can move up
//                listOfMoves.add("Move zero up.");
//            } else if (board[zeroCoordinates[0] + 1][zeroCoordinates[1]] > num || board[zeroCoordinates[0] + 1][zeroCoordinates[1]] > num - boardLength && leftColumnNumber) {
//                // Zero can move down
//                listOfMoves.add("Move zero down.");
//            } else if (board[zeroCoordinates[0]][zeroCoordinates[1] + 1] > num || board[zeroCoordinates[0]][zeroCoordinates[1] + 1] > num - boardLength && leftColumnNumber) {
//                // Zero can move right
//                listOfMoves.add("Move zero right.");
//            }
//        }
//
//        if (zeroYMoves > 0) {
//            // If zero needs to move down
//            if (board[zeroCoordinates[0] + 1][zeroCoordinates[1]] > num || board[zeroCoordinates[0] + 1][zeroCoordinates[1]] > num - boardLength && leftColumnNumber) {
//                // Zero can move down
//                listOfMoves.add("Move zero down.");
//                moveDown(0);
//            } else if (board[zeroCoordinates[0]][zeroCoordinates[1] - 1] > num || board[zeroCoordinates[0]][zeroCoordinates[1] - 1] > num - boardLength && leftColumnNumber) {
//                // Zero can move left
//                listOfMoves.add("Move zero left.");
//                moveLeft(0);
//            } else if (board[zeroCoordinates[0]][zeroCoordinates[1] + 1] > num || board[zeroCoordinates[0]][zeroCoordinates[1] + 1] > num - boardLength && leftColumnNumber) {
//                // Zero can move right
//                listOfMoves.add("Move zero right.");
//                moveRight(0);
//            } else if (board[zeroCoordinates[0] - 1][zeroCoordinates[1]] > num || board[zeroCoordinates[0] - 1][zeroCoordinates[1]] > num - boardLength && leftColumnNumber) {
//                // Zero can move up
//                listOfMoves.add("Move zero up.");
//                moveUp(0);
//            }
//        }
//
//        if (zeroXMoves > 0) {
//            // If zero needs to move right
//            if (board[zeroCoordinates[0]][zeroCoordinates[1] + 1] > num || board[zeroCoordinates[0]][zeroCoordinates[1] + 1] > num - boardLength && leftColumnNumber) {
//                // Zero can move right
//                listOfMoves.add("Move zero right.");
//            } else if (board[zeroCoordinates[0] - 1][zeroCoordinates[1]] > num || board[zeroCoordinates[0] - 1][zeroCoordinates[1]] > num - boardLength && leftColumnNumber) {
//                // Zero can move up
//                listOfMoves.add("Move zero up.");
//            } else if (board[zeroCoordinates[0] + 1][zeroCoordinates[1]] > num || board[zeroCoordinates[0] + 1][zeroCoordinates[1]] > num - boardLength && leftColumnNumber) {
//                // Zero can move down
//                listOfMoves.add("Move zero down.");
//            } else if (board[zeroCoordinates[0]][zeroCoordinates[1] - 1] > num || board[zeroCoordinates[0]][zeroCoordinates[1] - 1] > num - boardLength && leftColumnNumber) {
//                // Zero can move left
//                listOfMoves.add("Move zero left.");
//            }
//        }
//        return listOfMoves;
//    }
//}

// if zero needs to go up it will never make sense for it to go down. no down conditional required.
// if zero is left of the number it should go up the left side.
// if zero is right of the number it should go up the right side.

//else if (yMoves > 0) {
//        int[] zeroEndCoordinates = {numCoordinates[0] + 1, numCoordinates[1]};
//        // if num needs to go down
//        if (zeroEndCoordinates[0] - zeroCoordinates[0] > 0) {
//        // if zero needs to go down
//
//        } else if (zeroEndCoordinates[1] - zeroCoordinates[1] < 0) {
//        // zero needs to go left
//        } else if (zeroEndCoordinates[1] - zeroCoordinates[1] > 0) {
//        // zero needs to go right
//        } else if (zeroEndCoordinates[0] - zeroCoordinates[0] < 0) {
//        // zero needs to go left
//        }
//        }