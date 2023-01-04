package com.example.aiproject;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Random;

public class MainController extends Application {

    public TextField h1Result;
    public TextField h2Result;
    public TextField bfsResult;
    public TextField ida1Result;
    public TextField ida2Result;
    public TextField inputN;
    public GridPane gridButtons;
    private Scene matrixScene;

    private Integer[] list;
    private boolean canSolve = false;

    @Override
    public void start(Stage stage) throws Exception {
        GridPane root = FXMLLoader.load(getClass().getResource("main.fxml"));
        Scene scene = new Scene(root, 500, 500);
        stage.setTitle("Matrix");
        stage.setScene(scene);
        stage.show();
    }

    // check string is an integer
    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // check if number given can be generated
    public static boolean checkGeneration(int d) {
        double checkGeneration = Math.sqrt(d + 1);
        if ((int) checkGeneration == checkGeneration) {
            return true;
        } else {
            return false;
        }
    }

    // display generated board
    public void generate(ActionEvent actionEvent) {
        int input = Integer.parseInt(inputN.getText());

        if (isInteger(inputN.getText())
                && checkGeneration(input)
        ) {
            int n = input + 1;
            Integer[] arr = new Integer[n];
            for (int i = 0; i < n; i++) {
                arr[i] = i;
            }

            // generate solvable board
            boolean solvable = true;
            do {
                list = randomize(arr, n);
                // list = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15,16,17,18,19,20,21,0,22,23 ,24};
                solvable = Check_Solvability.isSolvable(list);
            } while (!solvable);

            int size = ((int) Math.sqrt(input) + 1);

            int counter = 0;
            for (int y = 0; y < size; y++) {
                for (int x = 0; x < size; x++) {
                    int value = list[counter];
                    counter++;
                    TextField tf = new TextField();
                    tf.setPrefHeight(50);
                    tf.setPrefWidth(50);
                    tf.setAlignment(Pos.CENTER);
                    tf.setEditable(false);
                    tf.setText(String.valueOf(value));
                    gridButtons.setRowIndex(tf, y);
                    gridButtons.setColumnIndex(tf, x);
                    gridButtons.getChildren().add(tf);
                }
            }
            h1Result.setText("");
            h2Result.setText("");
            bfsResult.setText("");
            ida1Result.setText("");
            ida2Result.setText("");
            canSolve = true;
            matrixScene = new Scene(gridButtons, 500, 500);
            Stage stage = ((Stage) ((Button) (actionEvent.getSource())).getScene().getWindow());
            stage.setScene(matrixScene);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Invalid N");
            alert.show();
        }
    }

    // shuffle the board
    private static Integer[] randomize(Integer arr[], int n) {
        Random r = new Random();
        for (int i = n - 1; i > 0; i--) {
            int j = r.nextInt(i + 1);
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
        return arr;
    }

    // call all methods and display values
    public void solve(ActionEvent actionEvent) {
        if (!canSolve) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Generate first");
            alert.show();
        } else {
            int input = Integer.parseInt(inputN.getText());
            int size = ((int) Math.sqrt(input) + 1);
            Integer[][] numbers = new Integer[size][size];
            int counter = 0;
            for (int r = 0; r < size; r++) {
                for (int c = 0; c < size; c++) {
                    numbers[r][c] = list[counter++];
                }
            }
            Board board = new Board(numbers);
            AStar a = new AStar(board);
            IDAStar ida = new IDAStar(board);

            h1Result.setText(a.aStarSolver("h1"));
            h2Result.setText(a.aStarSolver("h2"));
            bfsResult.setText(a.aStarSolver("bfs"));
            ida1Result.setText(ida.idaStarSolver("h1"));
            ida2Result.setText(ida.idaStarSolver("h2"));
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    // solve h1
    public void solve1(ActionEvent actionEvent) {
        if (!canSolve) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Generate first");
            alert.show();
        } else {
            int input = Integer.parseInt(inputN.getText());
            int size = ((int) Math.sqrt(input) + 1);
            Integer[][] numbers = new Integer[size][size];
            int counter = 0;
            for (int r = 0; r < size; r++) {
                for (int c = 0; c < size; c++) {
                    numbers[r][c] = list[counter++];
                }
            }
            Board board = new Board(numbers);
            AStar a = new AStar(board);
            h1Result.setText(a.aStarSolver("h1"));
        }
    }

    // solve h2
    public void solve2(ActionEvent actionEvent) {
        if (!canSolve) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Generate first");
            alert.show();
        } else {
            int input = Integer.parseInt(inputN.getText());
            int size = ((int) Math.sqrt(input) + 1);
            Integer[][] numbers = new Integer[size][size];
            int counter = 0;
            for (int r = 0; r < size; r++) {
                for (int c = 0; c < size; c++) {
                    numbers[r][c] = list[counter++];
                }
            }
            Board board = new Board(numbers);
            AStar a = new AStar(board);
            h2Result.setText(a.aStarSolver("h2"));
        }
    }

    // solve BFS
    public void solve3(ActionEvent actionEvent) {
        if (!canSolve) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Generate first");
            alert.show();
        } else {
            int input = Integer.parseInt(inputN.getText());
            int size = ((int) Math.sqrt(input) + 1);
            Integer[][] numbers = new Integer[size][size];
            int counter = 0;
            for (int r = 0; r < size; r++) {
                for (int c = 0; c < size; c++) {
                    numbers[r][c] = list[counter++];
                }
            }
            Board board = new Board(numbers);
            AStar a = new AStar(board);
            bfsResult.setText(a.aStarSolver("bfs"));
        }
    }

    // solve ida* with h1
    public void solve4(ActionEvent actionEvent) {
        if (!canSolve) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Generate first");
            alert.show();
        } else {
            int input = Integer.parseInt(inputN.getText());
            int size = ((int) Math.sqrt(input) + 1);
            Integer[][] numbers = new Integer[size][size];
            int counter = 0;
            for (int r = 0; r < size; r++) {
                for (int c = 0; c < size; c++) {
                    numbers[r][c] = list[counter++];
                }
            }
            Board board = new Board(numbers);
            IDAStar ida = new IDAStar(board);
            ida1Result.setText(ida.idaStarSolver("h1"));
        }
    }

    // solve ida* with h2
    public void solve5(ActionEvent actionEvent) {
        if (!canSolve) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Generate first");
            alert.show();
        } else {
            int input = Integer.parseInt(inputN.getText());
            int size = ((int) Math.sqrt(input) + 1);
            Integer[][] numbers = new Integer[size][size];
            int counter = 0;
            for (int r = 0; r < size; r++) {
                for (int c = 0; c < size; c++) {
                    numbers[r][c] = list[counter++];
                }
            }
            Board board = new Board(numbers);
            IDAStar ida = new IDAStar(board);
            ida2Result.setText(ida.idaStarSolver("h2"));
        }
    }
}
