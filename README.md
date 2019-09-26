# Slide Puzzle Solver

## Logic for moving a number into its place
* while number is not in its place
    * if number needs to move up
        * does zero need to move up, down, or not at all to be above the number
            * if zero needs to go up
                * if zero is immediately below the number determine if it should go left or right before going up
                    * if zero needs to go left
                        * move zero left
                    * else zero needs to go right
                        * move zero right
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
                    * if zero needs to go left
                        * move zero left
                    * else zero needs to go right
                        * move zero right
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
