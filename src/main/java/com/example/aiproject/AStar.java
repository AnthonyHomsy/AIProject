package com.example.aiproject;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;

public class AStar {

    // map to store all Tests
    public HashMap<String, AStarBoardDummy> map;
    // PriorityQueue to take the puzzles in order
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

    public String aStarSolver(String h) {
        long start = System.currentTimeMillis();

        pq = new PriorityQueue<AStarBoardDummy>();
        map = new HashMap<String, AStarBoardDummy>();

        // adding initial board to the map and queue
        AStarBoardDummy first = new AStarBoardDummy(null, this.board, null);
        pq.add(first);
        map.put(first.getId(), first);

        // check if initial puzzle is solved
        if (first.getBoard().isSolved()) {
            System.out.println("Puzzle is already solved.");
            return h;
        }

        while (!pq.isEmpty()) {
            // take the first element of the queue
            AStarBoardDummy prev = (AStarBoardDummy) pq.poll();

            // get different directions of the board
            ArrayList<Board.Direction> dirs = (ArrayList<Board.Direction>) prev.getBoard().getMoves();

            // create a dummy for each direction possible
            for (int i = 0; i < dirs.size(); i++) {
                AStarBoardDummy current = new AStarBoardDummy(prev, new Board(prev.getBoard().moveToCertainDirection(dirs.get(i))), dirs.get(i));
                current.h = h;

                // if this board is already in our map and the new h + g is lower than the one we remove the old one and put the new board
                if (map.containsKey(current.getId())) {
                    if (map.get(current.getId()).getWeight() > current.getWeight()) {
                        map.remove(current.getId());
                        map.put(current.getId(), current);
                        pq.remove(current);
                        pq.add(current);
                    }
                }

                // if it's not the solution we add it to the map and queue and stay in the while loop
                if (!current.getBoard().isSolved()) {
                    if (!map.containsKey(current.getId())) {
                        pq.add(current);
                        map.put(current.getId(), current);
                    }
                    // else we exit the program and return results
                } else {
                    long end = System.currentTimeMillis();
                    NumberFormat formatter = new DecimalFormat("#0.00000");
                    System.out.print("Execution time for " + h + " is " + formatter.format((end - start) / 1000d) + " seconds\n");
                    System.out.println("Moves: " + current.getDist());
                    return formatter.format((end - start) / 1000d);
                }
            }
        }
        return h;
    }
}


