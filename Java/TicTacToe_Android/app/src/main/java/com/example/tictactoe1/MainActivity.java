package com.example.tictactoe1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button[][] buttons = new Button[3][3];
    private boolean player1Turn = true;
    private int roundCount;
    private int player1Points;
    private int player2Points;
    private TextView textViewPlayer1;
    private TextView textViewPlayer2;
    private Button btnHistoryPage;
    Intent goToNextAC_H;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(this, "Welcome To TicTacToe Game Page!", Toast.LENGTH_SHORT).show();

        goToNextAC_H = new Intent(this,NextActivity.class);

        //Tic Tav Toe:
        textViewPlayer1 = findViewById(R.id.text_view_p1);
        textViewPlayer2 = findViewById(R.id.text_view_p2);

        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                String buttonID = "button_" + i + j;
                // an alternative way for R.id.something:
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }
        }

        Button buttonReset = findViewById(R.id.button_reset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetGame();
            }
        });

        //listView
        //in=new Intent(MainActivity.this,NextActivity.class);
        btnHistoryPage = findViewById(R.id.btn_historyPage);
        btnHistoryPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToNextActivity();
            }
        });

    }

    @Override
    public void onClick(View view) {

        if (!((Button) view).getText().toString().equals("")) {
            return;
        }

        if (player1Turn) {
            ((Button) view).setText("X");
        }else {
            ((Button) view).setText("O");
        }

        roundCount++;

        if (checkForWin()) {
            if (player1Turn) {
                player1Wins();
            }else {
                player2Wins();
            }
        }else if (roundCount == 9) {
            draw();
        }else {
            player1Turn = (!player1Turn);
        }
    }

    private boolean checkForWin() {
        String[][] field = new String[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = buttons[i][j].getText().toString();
            }
        }

        for (int i = 0; i < 3; i++) {
            if ((field[i][0].equals(field[i][1])) && ((field[i][0].equals(field[i][2])))
                    && (!(field[i][0].equals("")))) {
                return true;
            }
        }

        for (int i = 0; i < 3; i++) {
            if ((field[0][i].equals(field[1][i])) && ((field[0][i].equals(field[2][i])))
                    && (!(field[0][i].equals("")))) {
                return true;
            }
        }

        if ((field[0][0].equals(field[1][1])) && ((field[0][0].equals(field[2][2])))
                && (!(field[0][0].equals("")))) {
            return true;
        }

        if ((field[0][2].equals(field[1][1])) && ((field[0][2].equals(field[2][0])))
                && (!(field[0][2].equals("")))) {
            return true;
        }
        return false;
    }

    private void player1Wins(){
        player1Points++;
        Toast.makeText(this, "Player 1 Wins!", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }

    private void player2Wins(){
        player2Points++;
        Toast.makeText(this, "Player 2 Wins!", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }

    private void draw(){
        Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show();
        resetBoard();
    }

    private void updatePointsText() {
        textViewPlayer1.setText("Player 1: " + player1Points);
        textViewPlayer2.setText("Player 2: " + player2Points);
    }

    private void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }

        roundCount = 0;
        player1Turn = true;
    }

    private void resetGame() {
        Toast.makeText(this, "Game Was Reset", Toast.LENGTH_SHORT).show();
        String score = "Player 1: " + String.valueOf(player1Points) + "    -    Player 2: " + String.valueOf(player2Points);
        goToNextAC_H.putExtra("HISTORY_SCORE",score);
        player1Points = 0;
        player2Points = 0;
        updatePointsText();
        resetBoard();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("roundCount", roundCount);
        outState.putInt("player1Points", player1Points);
        outState.putInt("player2Points", player2Points);
        outState.putBoolean("player1Turn", player1Turn);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        roundCount = savedInstanceState.getInt("roundCount");
        player1Points = savedInstanceState.getInt("player1Points");
        player2Points = savedInstanceState.getInt("player2Points");
        player1Turn = savedInstanceState.getBoolean("player1Turn");
    }

    public void goToNextActivity(){
        startActivity(goToNextAC_H);
        finish();
    }

}
