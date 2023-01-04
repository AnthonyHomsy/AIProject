package com.example.aiproject;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

public class IDAStar {

    public HashMap<String, AStarBoardDummy> map;
    private PriorityQueue<AStarBoardDummy> pq;

    private Board board;
    private AStarBoardDummy solutionState;
    private int threshold;

    public IDAStar(Board board) {
        this.board = board;
    }

    public String idaStarSolver(String h) {
        long start = System.currentTimeMillis();
        //
        AStarBoardDummy first = new AStarBoardDummy(null, this.board, null);
        first.h = h;

        //Setting threshold = to weight of the first node
        threshold = first.getWeight();

        //Start of iterations
        while (true) {
            int temp = search(first, h);
            if (temp == -1) {
                // value of -1 -> Found
                long end = System.currentTimeMillis();
                NumberFormat formatter = new DecimalFormat("#0.00000");
                System.out.print("Execution time for IDA* with " + h + " is " + formatter.format((end - start) / 1000d) + " seconds\n");
                System.out.println("Moves : " + solutionState.getDist());

                //Stop the Loop
                return formatter.format((end - start) / 1000d);
            }
            if (temp == Integer.MAX_VALUE) {
                // No result Found
                System.out.println("Time limit exceeded");
                return "Time limit exceeded";
            }
            // No solution found at this threshold :
            //Setting new Threshold for next iteration
            threshold = temp;
        }
    }

    public int search(AStarBoardDummy current, String h) {
        if (current.getWeight() > threshold) {
            //If weight is bigger than threshold : Do not explore -> return weight to re-evaluate threshold
            return current.getWeight();
        }

        if (current.getBoard().isSolved()) {
            //If found return value -1
            solutionState = current;
            return -1;
        }

        Integer min = Integer.MAX_VALUE;

        //Iterate through all possible moves
        ArrayList<Board.Direction> dirs = (ArrayList<Board.Direction>) current.getBoard().getMoves();
        for (int i = 0; i < dirs.size(); i++) {

            AStarBoardDummy next = new AStarBoardDummy(current, new Board(current.getBoard().moveToCertainDirection(dirs.get(i))), dirs.get(i));
            next.h = h;

            /* This code helps to keep progress
            System.out.println("-------------------------");
            System.out.println("Current Threshold : "+threshold);
            System.out.println("Current examined node weight : "+next.getWeight());
            System.out.println("-------------------------");
            */
            int temp = search(next, h);

            if (temp == -1) {
                return -1;
            }
            //Setting min if weight is the lowest
            if (temp < min) {
                min = temp;
            }
        }
        //returning the lowest weight
        return min;
    }
}
