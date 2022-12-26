package com.example.aiproject;

public class MainTest {
    public static void main(String[] args) {
        int n = 8;
        Integer[] list = {5, 0, 2, 8, 6, 4, 7, 3, 1};
        int size = ((int) Math.sqrt(n) + 1);


        Integer[][] numbers = new Integer[size][size];
        int counter = 0;
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                numbers[r][c] = list[counter++];
            }
        }
        Board board = new Board(numbers);
        AStar a = new AStar(board);

        System.out.println("Your board:");
        board.print();

        a.aStarSolver("h1");
        a.aStarSolver("h2");
        a.aStarSolver("bfs");

    }

}


