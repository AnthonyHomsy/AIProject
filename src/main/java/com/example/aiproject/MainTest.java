package com.example.aiproject;

public class MainTest {
    public static void main(String[] args) {
        int n = 8;
        Integer[] list = {1, 2, 3, 0, 4, 6, 7, 5, 8};
        int size = ((int) Math.sqrt(n) + 1);


        Integer[][] numbers = new Integer[size][size];
        int counter = 0;
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                numbers[r][c] = list[counter++];
            }
        }
        Board board = new Board(numbers);
        AStarH1 h1 = new AStarH1(board);
        h1.aStarSolver();
    }

}


