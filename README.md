# Slide Puzzle Solver

Slide Puzzle Solver is a program that solves a slide puzzle if it is possible to be solved.

#### By Sam Cook 9-27-2019

## Logic for moving a number into its place
* while number is not in its place
    * if number needs to move up
        * does zero need to move up, down, or not at all to be above the number
            * if zero needs to go up
                * if zero is immediately below the number determine if it should go left or right before going up
                    * if zero needs to go right
                        * move zero right
                    * else zero needs to go left
                        * move zero left
                * else if zero can move up
                    * move zero up
            * else if zero needs to go down
                * move zero down
            * else if zero needs to go left
                * move zero left
            * else if zero needs to go right
                * move zero right

    * else if number needs to go down
        * does zero need to move up, down, or not at all to be above the number
            * if zero needs to go down
                * if zero is immediately above the number determine if it should go left or right before going down
                    * if zero needs to go right
                        * move zero right
                    * else zero needs to go left
                        * move zero left
                * else if zero can move down
                        * move zero down
                * else if zero needs to go up
                        * move zero up
                * else if zero needs to go left
                        * move zero left
                * else if zero needs to go right
                        * move zero right

    * if number needs to go left
        * does zero need to left up, right, or not at all to be above the number
            * if zero needs to go left
                 * if zero is immediately above the number determine if it should go up or down before going left
                    * if zero needs to go down
                        * move zero down
                    * else zero needs to go up
                        * move zero up
                * else if zero can move left
                    * move zero left
                * else if zero needs to go up
                    * move zero up
                * else if zero needs to go down
                    * move zero down
                * else if zero needs to go right
                    * move zero right


    * if number needs to go right
        * does zero need to left up, right, or not at all to be above the number
            * if zero needs to go right
                * if zero is immediately above the number determine if it should go up or down before going right
                    * if zero needs to go down
                        * move zero down
                    * else zero needs to go up
                        * move zero up
                * else if zero can move right
                    * move zero right
                * else if zero needs to go up
                    * move zero up
                * else if zero needs to go down
                    * move zero down
                * else if zero needs to go left
                    * move zero left

## Strategy for solving the puzzle
* place 1-3

* to get the 4 in place move the 4 just under where it needs to be.
    * move 2 down
    * move 3 left
    * move random in 4 spot left
    * move 4 up
    * 4 is now in place.
* to fix previous numbers.
    * move random left of empty to the right
    * move random above empty down
    * move 3 right
    * move 2 up
    * 1, 2, 3, and 4 are now in place

* place 5 and 9

* to move 13 into place
    * move 13 to the right of its spot
    * move 5 right
    * move 9 up
    * move random in 13 spot up
    * move 13 left
    * 13 is now in its place
* to fix 5 and 9
    * move random above empty down
    * move random left of empty right
    * move 9 down
    * move 5 left

* place 6 and 7
    * follow formula for 4

* solve 2x3
    * solve for 10 then 11 then 10 followed by formula for 4
    * then 14 and 15 both need to move left one square. if 15 is left to 14 it is unsolvable.

## Technologies
  * Java

## License
  MIT

_(c) Sam Cook 2019_
