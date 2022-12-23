package com.example.aiproject;

public class H2_Manathann {
    private static Integer [][] goalState = Board.getGoalState();


    public int manathann (Board board){
        Integer [][]mat=board.getBoard();
        int count = 0;
        // Going across each value of our matrix
        for (int r=0; r<mat.length; r++){
            for(int c=0; c<mat.length; c++){

                //We compare with each value of the goal state
                for(int i=0; i< goalState.length; i++){
                    for(int j=0; j< goalState.length; j++){

                        //if they're the same, we compute manathann distance and increment counter
                        if (mat[r][c] == goalState[i][j]) {
                            count += Math.abs(r - i) + Math.abs(c - j);
                        }
                    }
                }

            }
        }
        return count;
    }
}
