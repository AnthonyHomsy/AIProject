package com.example.aiproject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {


    private Integer[][] board;
    private static Integer[][] goalState;

    private int rZero, cZero;

    public int getcZero() {
        return cZero;
    }

    public int getrZero() {
        return rZero;
    }

    private static int size;

    public Board(Integer[][] board) {
        super();
        this.board = board;
        setupBoard();
    }

    private void setupBoard() {
        size = board.length;
        goalState = new Integer[size][size];

        if (!isValidBoard()) {
            try {
                throw new InvalidBoardException("Error board validity");
            } catch (InvalidBoardException e) {
                e.printStackTrace();
            }
        }

        boolean foundZero = false;
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                if (board[r][c] == 0) {
                    foundZero = true;
                    rZero = r;
                    cZero = c;
                }
            }
        }

        if (!foundZero) {
            try {
                throw new InvalidBoardException("No zero found");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        int counter = 1;
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                goalState[r][c] = counter++;
            }
        }
        goalState[size - 1][size - 1] = 0;

    }

    public int getSize() {
        return size;
    }

    public Board copyBoard() {
        Integer[][] copy = new Integer[size][size];
        for (int r = 0; r < Board.size; r++) {
            for (int c = 0; c < Board.size; c++) {
                copy[r][c] = this.board[r][c];
            }
        }
        return new Board(copy);
    }

    public void setSquare(int r, int c, int value) {
        if (value == 0) {
            rZero = r;
            cZero = c;
        }
        board[r][c] = value;
    }

    public Integer[][] moveDirection(Direction d) {
        Board newBoard = copyBoard();
        int r = this.rZero;
        int c = this.cZero;
        switch (d) {
            case UP:
                r = r - 1;
                break;
            case DOWN:
                r = r + 1;
                break;
            case LEFT:
                c = c - 1;
                break;
            case RIGHT:
                c = c + 1;
                break;
            default:
                System.out.println("x");
                break;
        }
        try {
            newBoard.board = newBoard.swapSquare(r, c);
        } catch (InvalidMoveException e) {
            e.printStackTrace();
        }
        return newBoard.getBoard();
    }

    public Integer[][] swapSquare(int r, int c) throws InvalidMoveException {
        Board newBoard = copyBoard();
        int rDiff = Math.abs(r - rZero);
        int cDiff = Math.abs(c - cZero);
        if (rDiff > 1 || cDiff > 1) {
            throw new InvalidMoveException("Tried to swap with non-adjacent square");
        }
        switch (rDiff) {
            case 1: //going up or down
                if (cDiff == 0) {
                    newBoard.setSquare(rZero, cZero, getValueAtSquare(r, c));
                    newBoard.setSquare(r, c, 0);
                } else {
                    throw new InvalidMoveException("rDiff == " + rDiff + ", cDiff == " + cDiff);
                }
                break;
            case 0: //going left or right
                if (cDiff == 1) {
                    newBoard.setSquare(rZero, cZero, getValueAtSquare(r, c));
                    newBoard.setSquare(r, c, 0);
                } else {
                    throw new InvalidMoveException("rDiff == " + rDiff + ", cDiff == " + cDiff);
                }
            default:
                if (rDiff == 0 && cDiff == 0) {
                    throw new InvalidMoveException("Tried to swap 0 with 0");
                }
        }
        return newBoard.getBoard();
    }

    public void print() {
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                System.out.print(board[r][c] + " ");
            }
            System.out.println();
        }
    }

    public boolean isSolved() {
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                if (board[r][c] != goalState[r][c]) {
                    return false;
                }
            }
        }
        return true;
    }

    private int getValueAtSquare(int r, int c) {
        return board[r][c];
    }


    private boolean isValidBoard() {
        if (board.length != board[0].length) {
            System.out.println("Board is not valid due to mismatch in lengths");

        }
        boolean flag = false;

        for (int i = 0; i < size * size - 1; i++) {
            flag = false;

            for (int r = 0; r < size; r++) {
                for (int c = 0; c < size; c++) {

                    if (board[r][c] == i) {
                        flag = true;
                        r = c = size; // breaks out of both loops
                    }
                }

            }
            if (flag == false) { // didn't find that number
                System.out.println("Board is not valid due to duplicate input value.");
                this.print();
            }
        }
        return true;
    }

    public Integer[][] getBoard() {
        return board;
    }

    public List<Direction> getPossibleMoves() {
        int cZero = this.getcZero();
        int rZero = this.getrZero();
        List<Direction> result = new ArrayList<>();
        if (cZero == 0) { // left column
            if (rZero == 0) { //top left corner
                result.add(Direction.DOWN);
                result.add(Direction.RIGHT);
                return result;
            } else if (rZero == this.getSize() - 1) { // bottom left corner
                result.add(Direction.UP);
                result.add(Direction.RIGHT);
                return result;
            } else { // middle rows, left column
                result.add(Direction.UP);
                result.add(Direction.DOWN);
                result.add(Direction.RIGHT);
                return result;
            }
        } else if (cZero == this.getSize() - 1) { // right column
            if (rZero == 0) { // top right corner
                result.add(Direction.DOWN);
                result.add(Direction.LEFT);
                return result;
            } else if (rZero == this.getSize() - 1) { //bottom right corner
                result.add(Direction.UP);
                result.add(Direction.LEFT);
                return result;
            } else { // middle rows of right column
                result.add(Direction.UP);
                result.add(Direction.DOWN);
                result.add(Direction.LEFT);
                return result;
            }
        } else { // not left or right
            if (rZero == 0) { // top
                result.add(Direction.DOWN);
                result.add(Direction.LEFT);
                result.add(Direction.RIGHT);
                return result;
            } else if (rZero == this.getSize() - 1) { // bottom
                result.add(Direction.UP);
                result.add(Direction.LEFT);
                result.add(Direction.RIGHT);
                return result;
            } else { // all possible
                result.add(Direction.UP);
                result.add(Direction.DOWN);
                result.add(Direction.LEFT);
                result.add(Direction.RIGHT);
                return result;
            }
        }
    }


    public enum Direction {
        UP, DOWN, LEFT, RIGHT;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Board) {
            return Arrays.deepEquals(this.getBoard(), ((Board) other).getBoard());
        }
        return false;
    }

    class InvalidMoveException extends Exception {
        public InvalidMoveException(String message) {
            super(message);
        }
    }

    class InvalidBoardException extends Exception {
        public InvalidBoardException(String message) {
            super(message);
        }
    }

    public static Integer[][] getGoalState() {
        return goalState;
    }
}

