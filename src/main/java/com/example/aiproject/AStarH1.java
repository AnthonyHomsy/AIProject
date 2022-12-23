package com.example.aiproject;

import java.util.HashMap;
import java.util.PriorityQueue;

public class AStarH1 {

//    public HashMap<Board, X> states;
//    private PriorityQueue<X> pq;

    public int misplacedTiles(Board board) {
        Integer mat[][] = board.getBoard();
        int c = 0;
        Integer finalMatrix[][] = board.getGoalState();
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat.length; j++) {
                if (mat[i][j] > 0 && mat[i][j] != finalMatrix[i][j])
                    c++;
            }
        }
        return c;
    }
}
