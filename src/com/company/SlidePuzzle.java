package com.company;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SlidePuzzle {
    int[][] board = {{1,  6,  9,  0},
                     {14, 13, 2,  4},
                     {15, 5,  3,  7},
                     {11, 8,  12, 10}};
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

    public void isSolvable(List possibleBoard) {
        int[] zeroCoordinates = find(0);
        System.out.println(zeroCoordinates[0]);
        if (zeroCoordinates[0] % 2 == 0) {
            System.out.println("even");
        } else {
            System.out.println("odd");
        }
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

    public void solve(int num) {
        int[] numCoordinates = find(num);
        int[] zeroCoordinates = find(0);
        int[] numEndCoordinates = {(num - 1) / boardLength, (num % boardLength) - 1};
        int yMoves = numEndCoordinates[0] - numCoordinates[0];
        int xMoves = numEndCoordinates[1] - numCoordinates[1];



        // Rules of movement
        // 1. The number can only move up if it the zero is above it.
        // 2. The number can only move left if the zero is left of it.
        // 3. The number can only move down if it the zero is below it.
        // 4. The number can only move right if the zero is right of it.
        // 5. The zero can only move up if it doesn't affect a number that is already in its place above it.
        // 6. The zero can only move left if it doesn't affect a number this is already in its place left of it.
        // 7. There are some exceptions to rules 3 and 4 such as the furthest right number in a row or the furthest down number in a column require zero to move numbers that have already been placed.
        // 8. The top row should be solved first. It is solved going left to right while not affecting numbers already in place unless otherwise specified by rule 5.
        // 9. The left column should be solved next. It is solved going top to bottom while not affecting numbers already in place unless otherwise specified by rule 5.
        // 10. Repeat 6 followed by 7 until there is a 3X3 unsolved square in the bottom right corner.
        // 11. Do step 6 twice. Then finish the puzzle.

//      Can the number move the direction it needs to move?
        if (numCoordinates[0] - 1 == zeroCoordinates[0] && numCoordinates[1] == zeroCoordinates[1]) {
//          1. The number can only move up if it the zero is above it.
            moveUp(num);
        }
        if (numCoordinates[0] == zeroCoordinates[0] && numCoordinates[1] - 1 == zeroCoordinates[1]) {
//          2. The number can only move left if the zero is left of it.
            moveLeft(num);
        }
        if (numCoordinates[0] + 1 == zeroCoordinates[0] && numCoordinates[1] == zeroCoordinates[1]) {
//          3. The number can only move down if it the zero is below it.
            moveDown(num);
        }
        if (numCoordinates[0] == zeroCoordinates[0] && numCoordinates[1] + 1 == zeroCoordinates[1]) {
//          4. The number can only move right if the zero is right of it.
            moveRight(num);
        }
        // Loop through the moves for each number until it is in its place.
        // First determine if moving the zero the least amount of times to be able to move the number is possible while following the rules.
        // If it is possible then proceed with zero movement followed by number movement when available.
        // If it is not possible then determine the next shortest distance


    }

    public void positionZero(int num) {
        // Determine the best path for zero to take to move the number into its spot.

        int[] numCoordinates = find(num);
        int[] zeroCoordinates = find(0);
        int[] numEndCoordinates = {(num - 1) / boardLength, (num % boardLength) - 1};
        int yMoves = numEndCoordinates[0] - numCoordinates[0];
        int xMoves = numEndCoordinates[1] - numCoordinates[1];
        boolean leftColumnNumber = false;
        int upMoves = 0;
        int leftMoves = 0;
        int downMoves = 0;
        int rightMoves = 0;
        List<Integer> listOfMoves = null;
        List<String> listOfUpMoves = null;
        List<String> listOfLeftMoves = null;
        List<String> listOfDownMoves = null;
        List<String> listOfRightMoves = null;
        int[][] tempBoard = board;
        int routeOne = 0;
        int routeTwo = 0;

    // UP UP UP UP UP UP UP UP UP UP UP UP UP UP UP UP UP UP UP UP UP UP UP UP UP UP UP UP UP UP UP UP
        if (yMoves < 0) {
            int[] zeroEndCoordinates = {numCoordinates[0] - 1, numCoordinates[1]};
            // num needs to go up
            if (zeroEndCoordinates[0] - zeroCoordinates[0] < 0) {
                // if zero needs to go up
                if (board[zeroCoordinates[0] - 1][zeroCoordinates[1]] > num || board[zeroCoordinates[0] - 1][zeroCoordinates[1]] > num - boardLength && leftColumnNumber) {
                    // Zero can move up
                    moveUp(0);
                } else if (zeroCoordinates[1] < boardLength - 1) {
                    // Zero will stay within the boundaries
                    if (board[zeroCoordinates[0]][zeroCoordinates[1] + 1] > num || board[zeroCoordinates[0]][zeroCoordinates[1] + 1] > num - boardLength && leftColumnNumber) {
                        // Zero can move right
                        moveRight(0);
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
                    moveLeft(0);
                } else {
                    // move zero down
                    moveDown(0);
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
            int[] zeroEndCoordinates = {numCoordinates[0], numCoordinates[1] - 1};
            // if num needs to go left
            if (zeroEndCoordinates[1] - zeroCoordinates[1] < 0) {
                // if zero needs to go left
                if (board[zeroCoordinates[0]][zeroCoordinates[1] - 1] > num || board[zeroCoordinates[0]][zeroCoordinates[1] - 1] > num - boardLength && leftColumnNumber) {
                    // Zero can move left
                    moveLeft(0);
                } else if (zeroCoordinates[0] < boardLength - 1) {
                    // if zero is not at the bottom
                    if (board[zeroCoordinates[0] + i][zeroCoordinates[1]] > num || board[zeroCoordinates[0] + i][zeroCoordinates[1]] > num - boardLength && leftColumnNumber) {
                        // Zero can move down
                        moveDown(0);
                        moveLeft(0);
                        if (yMoves > 0) {
                            moveDown(num);
                        }
                    }
                } else if (zeroCoordinates[0] == boardLength - 1){
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
            }
        } else if (yMoves > 0) {
            int[] zeroEndCoordinates = {numCoordinates[0] + 1, numCoordinates[1]};
            // if num needs to go down
            if (zeroEndCoordinates[0] - zeroCoordinates[0] > 0) {
                // if zero needs to go down

            }
        }

        // if num needs to go left and zero is above it and zero can't go left...
        moveRight(0);
        moveDown(0);
        moveDown(0);
        moveLeft(0);
        moveLeft(0);
        moveUp(0);

        // if num needs to go up and zero is left of it and zero can't go up...
        moveDown(0);
        moveRight(0);
        moveRight(0);
        moveUp(0);
        moveUp(0);
        moveLeft(0);

        // if num is the last in a row and is directly below its spot...
            // if zero is below num
            moveLeft(0);
            moveUp(0);
        moveLeft(0);
        moveUp(0);
        moveRight(0);
        moveRight(0);
        moveDown(0);
        moveLeft(0);
        moveUp(0);
        moveLeft(0);
        moveDown(0);

        // if num is the last (bottom) in a column and is directly right of where it needs to be
            // if zero is right of the number
            moveUp(0);
            moveLeft(0);
        moveUp(0);
        moveLeft(0);
        moveDown(0);
        moveDown(0);
        moveRight(0);
        moveUp(0);
        moveLeft(0);
        moveUp(0);
        moveRight(0);


        // Assuming the number is 1 and it needs to move up and left determine if it should move up or left first.
        if (yMoves < 0) {
            // The number needs to move up
            // How many moves would it take to get zero in position to move the number up?
            int[] zeroEndCoordinates = {numCoordinates[0] - 1, numCoordinates[1]};
            int zeroYMoves = zeroEndCoordinates[0] - numCoordinates[0];
            int zeroXMoves = zeroEndCoordinates[1] - numCoordinates[1];
            // Can zero make this move without affecting an already placed number?
            if (zeroYMoves < 0) {
                // If zero needs to move up
                int zeroYMovesPositive = zeroYMoves * -1;
                int zeroXMovesPositive = zeroXMoves < 0 ? zeroXMoves * -1 : zeroXMoves;


                // see if zero can move up as fas as it needs to. then add move sure it would end up in its spot. then add the moves together and see if one route is short than another to determine which direction it should go first.
                for (int i = 1; i <= zeroYMovesPositive; i++) {
                    if (board[zeroCoordinates[0] - i][zeroCoordinates[1]] > num || board[zeroCoordinates[0] - i][zeroCoordinates[1]] > num - boardLength && leftColumnNumber) {
                        // Zero can move up
                        upMoves++;
                    }
                }
                if (upMoves == zeroYMovesPositive) {
                    if (zeroXMoves < 0) {
                        for (int i = 1; i <= zeroXMovesPositive; i++) {
                            if (board[zeroCoordinates[0] + zeroYMovesPositive][zeroCoordinates[1] - i] > num || board[zeroCoordinates[0] + zeroYMovesPositive][zeroCoordinates[1] - i] > num - boardLength && leftColumnNumber) {
                                // Zero can move left
                                leftMoves++;
                            }
                        }
                        if (leftMoves == zeroXMovesPositive) {
                            routeOne = upMoves + leftMoves;
                        }
                    }
                }

                     else if (zeroCoordinates[1] > 0) {
                        // Zero will stay within the boundaries

                    } else if (zeroCoordinates[1] < boardLength - 1) {
                        // Zero will stay within the boundaries
                        if (board[zeroCoordinates[0] + i][zeroCoordinates[1]] > num || board[zeroCoordinates[0] + i][zeroCoordinates[1]] > num - boardLength && leftColumnNumber) {
                            // Zero can move down
                            upMoves++;
                            moveDown(0);
                        }
                    }
                }




        }
        if (xMoves < 0) {
            // The number needs to move left
            // How many moves would it take to get zero in position to move the number left?

            // Can zero make this move without affecting an already placed number?
            // If the num is greater than the number zero is trying to swap with then it is true.
            // The leftColumnNumbers have some exceptions to the first conditional because they are not moved in numerical order.
            int[] zeroEndCoordinates = {numCoordinates[0], numCoordinates[1] - 1};
            int zeroYMoves = zeroEndCoordinates[0] - numCoordinates[0];
            int ZeroXMoves = zeroEndCoordinates[1] - numCoordinates[1];

            System.out.println(zeroOptions(num));
        }
        if (yMoves > 0) {
            // The number needs to move down
            // How many moves would it take to get zero in position to move the number down?

            // Can zero make this move without affecting an already placed number?
            // If the num is greater than the number zero is trying to swap with then it is true.
            // The leftColumnNumbers have some exceptions to the first conditional because they are not moved in numerical order.
            int[] zeroEndCoordinates = {numCoordinates[0] + 1, numCoordinates[1]};
            int zeroYMoves = zeroEndCoordinates[0] - numCoordinates[0];
            int ZeroXMoves = zeroEndCoordinates[1] - numCoordinates[1];

            System.out.println(zeroOptions(num));
        }
        if (xMoves > 0) {
            // The number needs to move right
            // How many moves would it take to get zero in position to move the number right?

            // Can zero make this move without affecting an already placed number?
            // If the num is greater than the number zero is trying to swap with then it is true.
            // The leftColumnNumbers have some exceptions to the first conditional because they are not moved in numerical order.
            int[] zeroEndCoordinates = {numCoordinates[0], numCoordinates[1] + 1};
            int zeroYMoves = zeroEndCoordinates[0] - numCoordinates[0];
            int ZeroXMoves = zeroEndCoordinates[1] - numCoordinates[1];

            System.out.println(zeroOptions(num));
        }

        // Determine the proper order then move to zero into position


    }

    public List<String> zeroOptions(int num) {
        int[] numCoordinates = find(num);
        int[] zeroCoordinates = find(0);
        int[] zeroEndCoordinates = {numCoordinates[0], numCoordinates[1] + 1};
        int zeroYMoves = zeroEndCoordinates[0] - numCoordinates[0];
        int zeroXMoves = zeroEndCoordinates[1] - numCoordinates[1];
        List<String> listOfMoves = null;
        boolean leftColumnNumber = false;


        if (zeroYMoves < 0) {
            // If zero needs to move up
            if (board[zeroCoordinates[0] - 1][zeroCoordinates[1]] > num || board[zeroCoordinates[0] - 1][zeroCoordinates[1]] > num - boardLength && leftColumnNumber) {
                // Zero can move up
                listOfMoves.add("Move zero up.");
                moveUp(0);
            } else if (board[zeroCoordinates[0]][zeroCoordinates[1] - 1] > num || board[zeroCoordinates[0]][zeroCoordinates[1] - 1] > num - boardLength && leftColumnNumber) {
                // Zero can move left
                listOfMoves.add("Move zero left.");
                moveLeft(0);
            } else if (board[zeroCoordinates[0]][zeroCoordinates[1] + 1] > num || board[zeroCoordinates[0]][zeroCoordinates[1] + 1] > num - boardLength && leftColumnNumber) {
                // Zero can move right
                listOfMoves.add("Move zero right.");
                moveRight(0);
            } else if (board[zeroCoordinates[0] + 1][zeroCoordinates[1]] > num || board[zeroCoordinates[0] + 1][zeroCoordinates[1]] > num - boardLength && leftColumnNumber) {
                // Zero can move down
                listOfMoves.add("Move zero down.");
                moveDown(0);
            }
        }

        if (zeroXMoves < 0) {
            // If zero needs to move left
            if (board[zeroCoordinates[0]][zeroCoordinates[1] - 1] > num || board[zeroCoordinates[0]][zeroCoordinates[1] - 1] > num - boardLength && leftColumnNumber) {
                // Zero can move left
                listOfMoves.add("Move zero left.");
            } else if (board[zeroCoordinates[0] - 1][zeroCoordinates[1]] > num || board[zeroCoordinates[0] - 1][zeroCoordinates[1]] > num - boardLength && leftColumnNumber) {
                // Zero can move up
                listOfMoves.add("Move zero up.");
            } else if (board[zeroCoordinates[0] + 1][zeroCoordinates[1]] > num || board[zeroCoordinates[0] + 1][zeroCoordinates[1]] > num - boardLength && leftColumnNumber) {
                // Zero can move down
                listOfMoves.add("Move zero down.");
            } else if (board[zeroCoordinates[0]][zeroCoordinates[1] + 1] > num || board[zeroCoordinates[0]][zeroCoordinates[1] + 1] > num - boardLength && leftColumnNumber) {
                // Zero can move right
                listOfMoves.add("Move zero right.");
            }
        }

        if (zeroYMoves > 0) {
            // If zero needs to move down
            if (board[zeroCoordinates[0] + 1][zeroCoordinates[1]] > num || board[zeroCoordinates[0] + 1][zeroCoordinates[1]] > num - boardLength && leftColumnNumber) {
                // Zero can move down
                listOfMoves.add("Move zero down.");
                moveDown(0);
            } else if (board[zeroCoordinates[0]][zeroCoordinates[1] - 1] > num || board[zeroCoordinates[0]][zeroCoordinates[1] - 1] > num - boardLength && leftColumnNumber) {
                // Zero can move left
                listOfMoves.add("Move zero left.");
                moveLeft(0);
            } else if (board[zeroCoordinates[0]][zeroCoordinates[1] + 1] > num || board[zeroCoordinates[0]][zeroCoordinates[1] + 1] > num - boardLength && leftColumnNumber) {
                // Zero can move right
                listOfMoves.add("Move zero right.");
                moveRight(0);
            } else if (board[zeroCoordinates[0] - 1][zeroCoordinates[1]] > num || board[zeroCoordinates[0] - 1][zeroCoordinates[1]] > num - boardLength && leftColumnNumber) {
                // Zero can move up
                listOfMoves.add("Move zero up.");
                moveUp(0);
            }
        }

        if (zeroXMoves > 0) {
            // If zero needs to move right
            if (board[zeroCoordinates[0]][zeroCoordinates[1] + 1] > num || board[zeroCoordinates[0]][zeroCoordinates[1] + 1] > num - boardLength && leftColumnNumber) {
                // Zero can move right
                listOfMoves.add("Move zero right.");
            } else if (board[zeroCoordinates[0] - 1][zeroCoordinates[1]] > num || board[zeroCoordinates[0] - 1][zeroCoordinates[1]] > num - boardLength && leftColumnNumber) {
                // Zero can move up
                listOfMoves.add("Move zero up.");
            } else if (board[zeroCoordinates[0] + 1][zeroCoordinates[1]] > num || board[zeroCoordinates[0] + 1][zeroCoordinates[1]] > num - boardLength && leftColumnNumber) {
                // Zero can move down
                listOfMoves.add("Move zero down.");
            } else if (board[zeroCoordinates[0]][zeroCoordinates[1] - 1] > num || board[zeroCoordinates[0]][zeroCoordinates[1] - 1] > num - boardLength && leftColumnNumber) {
                // Zero can move left
                listOfMoves.add("Move zero left.");
            }
        }
        return listOfMoves;
    }
}

// if zero needs to go up it will never make sense for it to go down. no down conditional required.
// if zero is left of the number it should go up the left side.
// if zero is right of the number it should go up the right side.