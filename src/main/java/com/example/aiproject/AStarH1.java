package com.example.aiproject;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

public class AStarH1 {

    HashSet<String> set = new HashSet<String>();
    PriorityQueue<Board> pq = new PriorityQueue<Board>();

    private Board board;
    private Integer mat[][];
    private Integer finalMatrix[][];
    private int size;

    //test
    int row[] = {0,0,1,-1};
    int col[] = {1,-1,0,0};

    public AStarH1(Board board) {
        this.board = board;
        this.mat = board.getBoard();
        this.finalMatrix = board.getGoalState();
        this.size = mat.length;
    }

    public String getId(Integer[][] matrice) {
        String id = "";
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                id += matrice[r][c] + "";
            }
        }
        return id;
    }

    public int misplacedTiles(Integer[][] matrice) {
        int c = 0;
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                if (matrice[i][j] > 0 && matrice[i][j] != this.finalMatrix[i][j])
                    c++;
            }
        }
        return c;
    }

    boolean inLimit(int x, int y) {
        if (x >= 0 && x < this.size && y >= 0 && y < this.size)
            return true;
        return false;
    }

    public void aStarSolver() {
        set = new HashSet<String>();
        pq = new PriorityQueue<Board>();
        this.board.heuristic = this.misplacedTiles(this.board.getBoard());
        this.board.id = getId(this.board.getBoard());
        pq.add(this.board);
        set.add(this.board.id);

        while (!pq.isEmpty()) {
            Board currentBoard = pq.poll();
            Integer [][] currentMat = currentBoard.getBoard();
            if (currentBoard.heuristic == 0) {
                System.out.println("Fini");
                return;
            }
          // else do the algo

        }
    }

}
