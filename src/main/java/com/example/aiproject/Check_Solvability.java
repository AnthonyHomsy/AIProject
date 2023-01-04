package com.example.aiproject;

public class Check_Solvability {

/* To check if a n-puzzle is solvable, we need to verify if the number of inversions in the puzzle
is even.
 */

    public static boolean isSolvable(Integer[] puzzle) {
        int parity = 0;
        int gridWidth = (int) Math.sqrt(puzzle.length);
        int row = 0;
        int blankRow = 0;

        for (int i = 0; i < puzzle.length; i++) {
            if (i % gridWidth == 0) {
                row++;
            }
            if (puzzle[i] == 0) {
                blankRow = row;
                continue;
            }
            for (int j = i + 1; j < puzzle.length; j++) {
                if (puzzle[i] > puzzle[j] && puzzle[j] != 0) {
                    parity++;
                }
            }
        }

        if (gridWidth % 2 == 0) {
            if (blankRow % 2 == 0) {
                return parity % 2 == 0;
            } else {
                return parity % 2 != 0;
            }
        } else {
            return parity % 2 == 0;
        }
    }
}
