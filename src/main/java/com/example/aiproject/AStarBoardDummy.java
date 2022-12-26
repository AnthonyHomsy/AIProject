package com.example.aiproject;

public class AStarBoardDummy implements Comparable<AStarBoardDummy> {
    private Board board;
    private Board.Direction dirToHere;
    private int distTo;
    private AStarBoardDummy previous;
    private static Integer [][] goalState = Board.getGoalState();

    public AStarBoardDummy(AStarBoardDummy previous, Board board, Board.Direction d) {
        this.board = board;
        if (previous != null) {
            this.previous = previous;
            this.distTo = previous.getDist() + 1;
            this.dirToHere = d;
        } else {
            this.distTo = 0;
        }
    }

    public Board getBoard() {
        return board;
    }

    public int getDist() {
        return distTo;
    }

    public Board.Direction getDirection() {
        return this.dirToHere;
    }

    public AStarBoardDummy getPrevious() {
        return previous;
    }

    public int getWeight() {
        return misplacedTiles(board) + getDist();
        // return manathann(board) + getDist();
    }

    public int misplacedTiles(Board b) {
        Integer [][] matrice=b.getBoard();
        int size = matrice.length;
        int c = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (matrice[i][j] > 0 && matrice[i][j] != this.goalState[i][j])
                    c++;
            }
        }
        return c;
    }
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

    @Override
    public boolean equals(Object other) {
        if (other instanceof AStarBoardDummy) {
            return this.getBoard().equals(((AStarBoardDummy) other).getBoard());
        }
        return false;
    }

    @Override
    public int compareTo(AStarBoardDummy o) {
        int a = this.getWeight();
        int b = o.getWeight();
        return a - b;
    }
}
