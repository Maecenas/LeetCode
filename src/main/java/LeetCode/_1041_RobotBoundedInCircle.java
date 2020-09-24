package LeetCode;

/*
https://leetcode.com/problems/robot-bounded-in-circle/
Medium. Math.

On an infinite plane, a robot initially stands at (0, 0) and faces north.
The robot can receive one of three instructions:

"G": go straight 1 unit;
"L": turn 90 degrees to the left;
"R": turn 90 degress to the right.

The robot performs the instructions given in order, and repeats them forever.

Return true if and only if there exists a circle in the plane
such that the robot never leaves the circle.

Example 1:

Input: "GGLLGG"
Output: true
Explanation:
The robot moves from (0,0) to (0,2), turns 180 degrees,
and then returns to (0,0).
When repeating these instructions, the robot remains in the circle
of radius 2 centered at the origin.

Example 2:

Input: "GG"
Output: false
Explanation:
The robot moves north indefinitely.

Example 3:

Input: "GL"
Output: true
Explanation:
The robot moves from (0, 0) -> (0, 1) -> (-1, 1) -> (-1, 0) -> (0, 0) -> ...

Note:

1 <= instructions.length <= 100
instructions[i] is in {'G', 'L', 'R'}
*/

class _1041_RobotBoundedInCircle {

    /** Directions of north(0), east(1), south(2), and west(3) */
    private static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public static boolean isRobotBounded(String instructions) {
        if (instructions == null || instructions.length() == 0 || instructions.length() > 100) return true;

        int x = 0, y = 0;
        int direction = 0;
        for (char i : instructions.toCharArray()) {
            if (i == 'L') {
                direction = (direction + 3) % 4;
            } else if (i == 'R') {
                direction = (direction + 1) % 4;
            } else { /* i == 'G */
                x += DIRECTIONS[direction][0];
                y += DIRECTIONS[direction][1];
            }
        }

        // robot returns into initial position or does not face north
        return (x == 0 && y == 0) || (direction != 0);
    }
}
