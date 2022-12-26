package com.example.aiproject;

import java.util.*;

public class AStarH1 {

    public HashMap<Board, AStarBoardDummy> map;
    private PriorityQueue<AStarBoardDummy> pq;

    private Board board;
    private Integer mat[][];
    private Integer finalMatrix[][];
    private int size;


    public AStarH1(Board board) {
        this.board = board;
        this.mat = board.getBoard();
        this.finalMatrix = board.getGoalState();
        this.size = mat.length;
    }

    public void aStarSolver() {
        pq = new PriorityQueue<AStarBoardDummy>();
        map = new HashMap<Board, AStarBoardDummy>();
        AStarBoardDummy first = new AStarBoardDummy(null, this.board, null);
        // Populate PriorityQueue and HashMap
        pq.add(first);
        map.put(first.getBoard(), first);
        first.getBoard().print();
        if (first.getBoard().isWon()) {
            System.out.println("First board is already solved.");
            return;
        }
        Stack<AStarBoardDummy> moves = new Stack<AStarBoardDummy>();
        while (!pq.isEmpty()) {
            AStarBoardDummy prev = (AStarBoardDummy) pq.poll();

            ArrayList<Board.Direction> dirs = (ArrayList<Board.Direction>) prev.getBoard().getPossibleMoves();
            for (int i = 0; i < dirs.size(); i++) {

                AStarBoardDummy current = new AStarBoardDummy(prev, new Board(prev.getBoard().moveDirection(dirs.get(i))), dirs.get(i));

                if (map.containsKey(current.getBoard())) {
                    if (map.get(current.getBoard()).getWeight() > current.getWeight()) {
                        map.remove(current.getBoard());
                        map.put(current.getBoard(), current);
                        pq.remove(current);
                        pq.add(current);
                    }
                }

                if (!current.getBoard().isWon()) {
                    if (!map.containsKey(current.getBoard())) {
                        pq.add(current);
                        map.put(current.getBoard(), current);
                    }
                } else {
                    AStarBoardDummy rover = new AStarBoardDummy(current.getPrevious(), current.getBoard(), current.getDirection());
                    while (rover.getPrevious() != null) {
                        moves.push(rover);
                        rover = rover.getPrevious();
                    }

                    while (!(moves.isEmpty())) {
                        AStarBoardDummy popped = moves.pop();
                        System.out.println("Direction: " + popped.getDirection());
                        popped.getBoard().print();
                    }
                    System.out.println("It works! Moves: " + current.getDist());
                    return;
                }
            }
        }
    }
}


