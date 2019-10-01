package com.company;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Puzzle puzzle = new Puzzle();
        SlidePuzzle slidePuzzle = new SlidePuzzle();
//        puzzle.setBoard();
        System.out.println(slidePuzzle.printBoard());

//        List<Integer> thing = Arrays.asList( new Integer[]{10, 3, 11, 4, 1, 5, 14, 7, 8, 6, 13, 2, 12, 9, 15, 0});

//        slidePuzzle.isSolvable(thing);


        slidePuzzle.solveFor(1);
        slidePuzzle.solveFor(2);
        slidePuzzle.solveFor(3);
        slidePuzzle.solveFor(4);
//        slidePuzzle.solveFor(5);
//        slidePuzzle.solveFor(9);
//        slidePuzzle.solveFor(13);
//        slidePuzzle.solveFor(6);
//        slidePuzzle.solveFor(7);
////        slidePuzzle.solveFor(8);
////        slidePuzzle.solveFor(10);
////        slidePuzzle.solveFor(11);
////        slidePuzzle.solveFor(12);
////        slidePuzzle.solveFor(14);
////        slidePuzzle.solveFor(15);


//        for (int i = 1; i < 9; i++) {
//        while(!(Arrays.equals(puzzle.getBoard(), puzzle.getFinishedPuzzle())) && puzzle.getMoveCount() < 160) {
//            puzzle.positionNumber(1);
//            puzzle.positionNumber(2);
//            puzzle.positionNumber(3);
//            puzzle.positionNumber(4);
//            puzzle.positionNumber(5);
//            puzzle.positionNumber(9);
//            puzzle.positionNumber(13);
//            puzzle.positionNumber(6);
//            puzzle.positionNumber(7);
//            puzzle.positionNumber(8);
//            puzzle.positionNumber(10);
//            puzzle.positionNumber(11);
//            puzzle.positionNumber(12);
//            puzzle.positionNumber(14);
//            puzzle.positionNumber(15);
//            System.out.println("The puzzle has been solved.");
//            System.out.println("It took " + puzzle.getMoveCount() + " moves to solve the puzzle.");

        }
    }
