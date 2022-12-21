package com.example.aiproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class MainController  implements Initializable {

    @FXML
    private Button btn00;
    @FXML
    private Button btn01;
    @FXML
    private Button btn02;
    @FXML
    private Button btn03;
    @FXML
    private Button btn10;
    @FXML
    private Button btn11;
    @FXML
    private Button btn12;
    @FXML
    private Button btn13;
    @FXML
    private Button btn20;
    @FXML
    private Button btn21;
    @FXML
    private Button btn22;
    @FXML
    private Button btn23;
    @FXML
    private Button btn30;
    @FXML
    private Button btn31;
    @FXML
    private Button btn32;
    @FXML
    private Button btn33;

    int[][] currentState ;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


            scramble();
            printState();
            displayGameBoard();


    }

    @FXML
    protected void scramble() {

        //Temporary array to scramble numbers
        List<Integer> numbers = new ArrayList<Integer>();

        //Storing numbers in Array
        for (int i = 0 ; i<16; i++) {
            numbers.add(i);
        }
        // Scrambling numbers
        Collections.shuffle(numbers);

        //Adding scrambled numbers to currentState
        currentState = new int[4][4];
        int counter = 0;
        for(int i = 0;i<4;i++) {

            int[] row = new int[4];

            for (int j = 0;j<4;j++){
                row[j]=numbers.get(counter);
                counter++;
            }
            currentState[i] = row;
        }
    }


    //Print current  State in console;
    public void printState() {

        for (int i = 0; i < 4; i++) {
            System.out.println(currentState[i].toString());
            for (int j = 0;j<4;j++){
                System.out.println(currentState[i][j]);
            }
        }
    }

    //Display State on the board
    public void displayGameBoard() {

        //Setting text to the buttons, if value is 0 => display nothing in button
        btn00.setText(currentState[0][0] != 0 ? currentState[0][0] + "" : "");
        btn01.setText(currentState[0][1] != 0 ? currentState[0][1] + "" : "");
        btn02.setText(currentState[0][2] != 0 ? currentState[0][2] + "" : "");
        btn03.setText(currentState[0][3] != 0 ? currentState[0][3] + "" : "");
        btn10.setText(currentState[1][0] != 0 ? currentState[1][0] + "" : "");
        btn11.setText(currentState[1][1] != 0 ? currentState[1][1] + "" : "");
        btn12.setText(currentState[1][2] != 0 ? currentState[1][2] + "" : "");
        btn13.setText(currentState[1][3] != 0 ? currentState[1][3] + "" : "");
        btn20.setText(currentState[2][0] != 0 ? currentState[2][0] + "" : "");
        btn21.setText(currentState[2][1] != 0 ? currentState[2][1] + "" : "");
        btn22.setText(currentState[2][2] != 0 ? currentState[2][2] + "" : "");
        btn23.setText(currentState[2][3] != 0 ? currentState[2][3] + "" : "");
        btn30.setText(currentState[3][0] != 0 ? currentState[3][0] + "" : "");
        btn31.setText(currentState[3][1] != 0 ? currentState[3][1] + "" : "");
        btn32.setText(currentState[3][2] != 0 ? currentState[3][2] + "" : "");
        btn33.setText(currentState[3][3] != 0 ? currentState[3][3] + "" : "");


        }


    public void move(ActionEvent actionEvent) {

        System.out.println("Triggered");

        //Get Button Value
        Button btn = (Button)actionEvent.getTarget();
        System.out.println(btn.getText());
        int value = Integer.parseInt(btn.getText());

        System.out.println(value);



        //Search position in current state
        int iValue = -1,jValue = -1;

        for (int i = 0;i<4;i++) {
            for(int j = 0;j<4;j++){
                if(currentState[i][j] == value){
                    iValue = i;
                    jValue = j;
                }
            }
        }

        System.out.println(iValue);
        System.out.println(jValue);

        if(iValue != -1 && jValue != -1){

            int temp = 0;

            //Check up
                if( iValue !=0 && currentState[iValue - 1][jValue] == 0 ){

                    temp = currentState[iValue][jValue];
                    currentState[iValue][jValue] = 0;

                    currentState[iValue - 1][jValue] = temp;
                }
            //Check down
                else if(iValue !=3 && currentState[iValue + 1][jValue] == 0 ){

                    temp = currentState[iValue][jValue];
                    currentState[iValue][jValue] = 0;

                    currentState[iValue + 1][jValue] = temp;
                }

            //Check left
                else if(jValue !=0 && currentState[iValue][jValue - 1] == 0 ){

                    temp = currentState[iValue][jValue];
                    currentState[iValue][jValue] = 0;

                    currentState[iValue][jValue - 1] = temp;

                }

            //Check right
                else if(jValue !=3 && currentState[iValue][jValue + 1] == 0 ){

                    temp = currentState[iValue][jValue];
                    currentState[iValue][jValue] = 0;

                    currentState[iValue][jValue + 1] = temp;
                }

            displayGameBoard();

        }


    }
}
