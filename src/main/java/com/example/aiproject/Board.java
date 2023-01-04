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
        createBoard();
    }

    private void createBoard() {
        size = board.length;
        goalState = new Integer[size][size];

        // locate the 0 in our board
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                if (board[r][c] == 0) {
                    rZero = r;
                    cZero = c;
                }
            }
        }

        // create the final state
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

    // return a copy of our board
    public Board copyBoard() {
        Integer[][] copy = new Integer[size][size];
        for (int r = 0; r < Board.size; r++) {
            for (int c = 0; c < Board.size; c++) {
                copy[r][c] = this.board[r][c];
            }
        }
        return new Board(copy);
    }

    // set value to a certain square
    public void setSquare(int r, int c, int value) {
        if (value == 0) {
            rZero = r;
            cZero = c;
        }
        board[r][c] = value;
    }

    // give in parameter the direction and move the 0 to that direction
    public Integer[][] moveToCertainDirection(Direction d) {
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
            newBoard.board = newBoard.switchSquare(r, c);
        } catch (ExceptionErrorMove e) {
            e.printStackTrace();
        }
        return newBoard.getBoard();
    }

    // switch to squares
    public Integer[][] switchSquare(int r, int c) throws ExceptionErrorMove {
        Board newBoard = copyBoard();
        int rDiff = Math.abs(r - rZero);
        int cDiff = Math.abs(c - cZero);
        if (rDiff > 1 || cDiff > 1) {
            throw new ExceptionErrorMove("Error");
        }
        switch (rDiff) {
            case 1: //up ou down
                if (cDiff == 0) {
                    newBoard.setSquare(rZero, cZero, getValueOfaSquare(r, c));
                    newBoard.setSquare(r, c, 0);
                } else {
                    throw new ExceptionErrorMove("rDiff == " + rDiff + ", cDiff == " + cDiff);
                }
                break;
            case 0: //droite ou gauche
                if (cDiff == 1) {
                    newBoard.setSquare(rZero, cZero, getValueOfaSquare(r, c));
                    newBoard.setSquare(r, c, 0);
                } else {
                    throw new ExceptionErrorMove("rDiff == " + rDiff + ", cDiff == " + cDiff);
                }
            default:
                if (rDiff == 0 && cDiff == 0) {
                    throw new ExceptionErrorMove("Error switching 0 with 0");
                }
        }
        return newBoard.getBoard();
    }

    // useful in the terminal to visualise the board
    public void print() {
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                System.out.print(board[r][c] + " ");
            }
            System.out.println();
        }
    }

    // check if our board reached the final state
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

    private int getValueOfaSquare(int r, int c) {
        return board[r][c];
    }

    public Integer[][] getBoard() {
        return board;
    }

    // get possible directions for each 0 position
    public List<Direction> getMoves() {
        int cZero = this.getcZero();
        int rZero = this.getrZero();
        List<Direction> result = new ArrayList<>();
        if (cZero == 0) {
            if (rZero == 0) {
                result.add(Direction.DOWN);
                result.add(Direction.RIGHT);
                return result;
            } else if (rZero == this.getSize() - 1) {
                result.add(Direction.UP);
                result.add(Direction.RIGHT);
                return result;
            } else {
                result.add(Direction.UP);
                result.add(Direction.DOWN);
                result.add(Direction.RIGHT);
                return result;
            }
        } else if (cZero == this.getSize() - 1) {
            if (rZero == 0) {
                result.add(Direction.DOWN);
                result.add(Direction.LEFT);
                return result;
            } else if (rZero == this.getSize() - 1) {
                result.add(Direction.UP);
                result.add(Direction.LEFT);
                return result;
            } else {
                result.add(Direction.UP);
                result.add(Direction.DOWN);
                result.add(Direction.LEFT);
                return result;
            }
        } else {
            if (rZero == 0) { // top
                result.add(Direction.DOWN);
                result.add(Direction.LEFT);
                result.add(Direction.RIGHT);
                return result;
            } else if (rZero == this.getSize() - 1) {
                result.add(Direction.UP);
                result.add(Direction.LEFT);
                result.add(Direction.RIGHT);
                return result;
            } else { // tout est possible
                result.add(Direction.UP);
                result.add(Direction.DOWN);
                result.add(Direction.LEFT);
                result.add(Direction.RIGHT);
                return result;
            }
        }
    }

    // enum for the possible directions
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

    class ExceptionErrorMove extends Exception {
        public ExceptionErrorMove(String message) {
            super(message);
        }
    }

    public static Integer[][] getGoalState() {
        return goalState;
    }

}

