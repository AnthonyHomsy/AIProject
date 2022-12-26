package com.example.aiproject;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;

public class AStar {

    public HashMap<String, AStarBoardDummy> map;
    private PriorityQueue<AStarBoardDummy> pq;

    private Board board;
    private Integer mat[][];
    private Integer finalMatrix[][];
    private int size;


    public AStar(Board board) {
        this.board = board;
        this.mat = board.getBoard();
        this.finalMatrix = board.getGoalState();
        this.size = mat.length;
    }

    public void aStarSolver(String h) {
        long start = System.currentTimeMillis();

        pq = new PriorityQueue<AStarBoardDummy>();
        map = new HashMap<String, AStarBoardDummy>();
        AStarBoardDummy first = new AStarBoardDummy(null, this.board, null);
        pq.add(first);
        map.put(first.getId(), first);

        if (first.getBoard().isSolved()) {
            System.out.println("First board is already solved.");
            return;
        }
        Stack<AStarBoardDummy> moves = new Stack<AStarBoardDummy>();
        while (!pq.isEmpty()) {
            AStarBoardDummy prev = (AStarBoardDummy) pq.poll();

            ArrayList<Board.Direction> dirs = (ArrayList<Board.Direction>) prev.getBoard().getPossibleMoves();
            for (int i = 0; i < dirs.size(); i++) {

                AStarBoardDummy current = new AStarBoardDummy(prev, new Board(prev.getBoard().moveDirection(dirs.get(i))), dirs.get(i));
                current.h = h;


                if (map.containsKey(current.getId())) {
                    if (map.get(current.getId()).getWeight() > current.getWeight()) {
                        map.remove(current.getId());
                        map.put(current.getId(), current);
                        pq.remove(current);
                        pq.add(current);
                    }
                }

                if (!current.getBoard().isSolved()) {
                    if (!map.containsKey(current.getId())) {
                        pq.add(current);
                        map.put(current.getId(), current);
                    }

                } else {
                    long end = System.currentTimeMillis();
                    NumberFormat formatter = new DecimalFormat("#0.00000");
                    System.out.print("Execution time for " + h + " is " + formatter.format((end - start) / 1000d) + " seconds\n");
                    System.out.println("Moves: " + current.getDist());
                    return;
                }
            }
        }
    }
}


