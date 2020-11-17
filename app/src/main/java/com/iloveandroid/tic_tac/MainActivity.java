package com.iloveandroid.tic_tac;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
    }

    public void buClick(View view) {
        Button buSelected = (Button) view;
        int cellID = 0;

        switch (buSelected.getId()){
            case R.id.id1:
                cellID = 1;
                break;
            case R.id.id2:
                cellID = 2;
                break;
            case R.id.id3:
                cellID = 3;
                break;
            case R.id.id4:
                cellID = 4;
                break;
            case R.id.id5:
                cellID = 5;
                break;
            case R.id.id6:
                cellID = 6;
                break;
            case R.id.id7:
                cellID = 7;
                break;
            case R.id.id8:
                cellID = 8;
                break;
            case R.id.id9:
                cellID = 9;
                break;
        }
        playGame(cellID, buSelected);
    }

    int ActivePlayer=1; // 1 for 1st 2 for 2nd
    ArrayList<Integer>player1 = new ArrayList<Integer>(); // holds player 1 data
    ArrayList<Integer>player2 = new ArrayList<Integer>(); // holds player 2 data

    void playGame(int cellID, Button button){

        Log.d("Player",String.valueOf(cellID));
        Log.d("Button",button.getText().toString());

        if (ActivePlayer == 1){
            button.setText( "X" );
            button.setBackgroundColor(Color.GREEN);
            player1.add(cellID);
            ActivePlayer = 2;
            AutoPlay();

        } else  if (ActivePlayer == 2){
            button.setText( "0" );
            button.setBackgroundColor(Color.BLUE);
            player2.add(cellID);
            ActivePlayer = 1;

        }
        CheckWinner();
        button.setEnabled(false);

    }
    void CheckWinner(){
        int winner = -1;
        // row 1
        if (player1.contains(1) && player1.contains(2) && player1.contains(3)){
            winner =1;

        }
        if (player2.contains(1) && player2.contains(2) && player2.contains(3)){
            winner =2;

        }

        // row 2
        if (player1.contains(4) && player1.contains(5) && player1.contains(6)){
            winner =1;

        }
        if (player2.contains(4) && player2.contains(5) && player2.contains(6)){
            winner =2;

        }

        // row 3
        if (player1.contains(7) && player1.contains(8) && player1.contains(9)){
            winner =1;

        }
        if (player2.contains(7) && player2.contains(8) && player2.contains(9)){
            winner =2;

        }

        // col 1
        if (player1.contains(1) && player1.contains(4) && player1.contains(7)){
            winner =1;

        }
        if (player2.contains(1) && player2.contains(4) && player2.contains(7)){
            winner =2;

        }

        // col 2
        if (player1.contains(2) && player1.contains(5) && player1.contains(8)){
            winner =1;

        }
        if (player2.contains(2) && player2.contains(5) && player2.contains(8)){
            winner =2;

        }

        // col 3
        if (player1.contains(3) && player1.contains(6) && player1.contains(9)){
            winner =1;

        }
        if (player2.contains(3) && player2.contains(6) && player2.contains(9)){
            winner =2;

        }
        if (winner != -1 ){
            // We have a winner
            if (winner == 1){
                Toast.makeText(this, "Player 1 is the winner", Toast.LENGTH_LONG).show();;
            }

            if (winner == 2){
                Toast.makeText(this, "Player 2 is the winner", Toast.LENGTH_LONG).show();;
            }
        }
    }

    void AutoPlay(){

        ArrayList<Integer>EmptyCells = new ArrayList<>();
        // find empty cells

        for (int cellID = 1; cellID< 10; cellID++) {
            if (!(player1.contains(cellID) || player2.contains(cellID))){
                EmptyCells.add(cellID);
             }
        }

        Random r = new Random();
        int RandIndex = r.nextInt( EmptyCells.size() -1) ; // if size = 3, select (01,2,3
        int cellID = EmptyCells.get(RandIndex);

        Button buSelected;
        switch (cellID){
            case 1:
                buSelected = findViewById(R.id.id1);
                break;
            case 2:
                buSelected = findViewById(R.id.id2);
                break;
            case 3:
                buSelected = findViewById(R.id.id3);
                break;
            case 4:
                buSelected = findViewById(R.id.id4);
                break;
            case 5:
                buSelected = findViewById(R.id.id5);
                break;
            case 6:
                buSelected = findViewById(R.id.id6);
                break;
            case 7:
                buSelected = findViewById(R.id.id7);
                break;
            case 8:
                buSelected = findViewById(R.id.id8);
                break;
            case 9:
                buSelected = findViewById(R.id.id9);
                break;
            default:
                buSelected = findViewById(R.id.id1);
                break;
        }
        playGame( cellID, buSelected);
    }
}