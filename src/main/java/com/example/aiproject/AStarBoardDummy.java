package com.example.aiproject;

public class AStarBoardDummy implements Comparable<AStarBoardDummy> {
    private Board board;
    private Board.Direction direction;
    private int distanceTo;
    private AStarBoardDummy previous;
    private static Integer[][] goalState = Board.getGoalState();
    public String h;

    // create a unique id for each board
    public String getId() {
        Integer[][] matrice = board.getBoard();
        String id = "";
        for (int r = 0; r < matrice.length; r++) {
            for (int c = 0; c < matrice.length; c++) {
                id += matrice[r][c] + "";
            }
        }
        return id;
    }

    public AStarBoardDummy(AStarBoardDummy previous, Board board, Board.Direction d) {
        this.board = board;
        if (previous != null) {
            this.previous = previous;
            this.distanceTo = previous.getDist() + 1;
            this.direction = d;
        } else {
            this.distanceTo = 0;
        }
    }

    public Board getBoard() {
        return board;
    }

    public int getDist() {
        return distanceTo;
    }

    public Board.Direction getDirection() {
        return this.direction;
    }

    public AStarBoardDummy getPrevious() {
        return previous;
    }

    public int getWeight() {
        // if we are using h1 we call misplacedTitles to get the heuristic and add the distance to it
        if (h == "h1") {
            return misplacedTiles(board) + getDist();
            // if we are using h2 we call manathann to get the heuristic and add the distance to it
        } else if (h == "h2") {
            return manathann(board) + getDist();
            // we only return distance in BFS we don't use heuristic
        } else {
            return getDist();
        }
    }

    // function that returns the heuristic h1 of each misplaced square
    public int misplacedTiles(Board b) {
        Integer[][] matrice = b.getBoard();
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

    // function that returns the heuristic h2 while using the manathann distance
    public int manathann(Board board) {
        Integer[][] mat = board.getBoard();
        int count = 0;
        // Going across each value of our matrix
        for (int r = 0; r < mat.length; r++) {
            for (int c = 0; c < mat.length; c++) {

                //We compare with each value of the goal state
                for (int i = 0; i < goalState.length; i++) {
                    for (int j = 0; j < goalState.length; j++) {

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

    // we are implementing and using comparable to compare the weight in the priority queue

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
