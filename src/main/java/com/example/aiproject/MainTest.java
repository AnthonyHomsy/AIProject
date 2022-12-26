package com.example.aiproject;

public class MainTest {
    public static void main(String[] args) {
        int n = 3;
        Integer[] list = {0,3,2,1};
        int size = ((int) Math.sqrt(n) + 1);


        Integer[][] numbers = new Integer[size][size];
        int counter = 0;
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                numbers[r][c] = list[counter++];
            }
        }
        Board board = new Board(numbers);
        AStar h1 = new AStar(board);
        h1.aStarSolver("h2");
    }

}


