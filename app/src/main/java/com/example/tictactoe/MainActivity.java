package com.example.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import static android.graphics.Color.rgb;
import static com.example.tictactoe.GameState.DRAW;
import static com.example.tictactoe.GameState.IN_PROGRESS;
import static com.example.tictactoe.GameState.PLAYER_ONE;
import static com.example.tictactoe.GameState.PLAYER_TWO;

public class MainActivity extends AppCompatActivity {


    Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Try to load a previous instance.
        if (savedInstanceState != null) {
            game = (Game) savedInstanceState.getSerializable("game");
        } else {
            game = new Game();
        }

        GameState gameState = game.won();
        TextView statusBar = findViewById(R.id.textView);

        switch (gameState) {
            case IN_PROGRESS:
                // If the game is in progress, tell the player whose turn it is.
                boolean playerOneTurn = game.playerOneTurn();
                if (playerOneTurn) {
                    statusBar.setText("Player 1's turn...");
                } else {
                    statusBar.setText("Player 2's turn...");
                }
                break;
            case PLAYER_ONE:
                statusBar.setText("Player 1 won!");
                break;
            case PLAYER_TWO:
                statusBar.setText("Player 2 won!");
                break;
            case DRAW:
                statusBar.setText("Draw!");
                break;
        }

        // Iterate over the coordinates of the tiles.
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Button button = findViewById(getResources().getIdentifier("button" + i + j, "id", this.getPackageName()));

                TileState tileState = game.getTileState(i, j);

                // Set the tile's text and color based on their status.
                switch (tileState) {
                    case BLANK:
                        button.setText(" ");
                        if (((i+j) % 2) == 0) {
                            button.setBackgroundColor(rgb(189, 189, 189));
                        } else {
                            button.setBackgroundColor(rgb(224, 224, 224));
                        }
                        break;
                    case CROSS:
                        button.setText("X");
                        button.setBackgroundColor(rgb(255, 202, 40));
                        break;
                    case CIRCLE:
                        button.setText("O");
                        button.setBackgroundColor(rgb(186, 104, 200));
                        break;
                    case INVALID:
                        break;
                }
            }
        }
    }

    public void tileClicked(View view) {

        // Do nothing if the game is not in progress anymore.
        GameState gameState = game.won();
        if (gameState != IN_PROGRESS) {
            return;
        }

        int id = view.getId();

        int row = -1;
        int column = -1;

        // Get the row and column of the clicked tile.
        switch (id) {
            case R.id.button00:
                Log.d("button", "button00 was clicked");
                row = 0;
                column = 0;
                break;
            case R.id.button01:
                Log.d("button", "button01 was clicked");
                row = 0;
                column = 1;
                break;
            case R.id.button02:
                Log.d("button", "button02 was clicked");
                row = 0;
                column = 2;
                break;
            case R.id.button10:
                Log.d("button", "button10 was clicked");
                row = 1;
                column = 0;
                break;
            case R.id.button11:
                Log.d("button", "button11 was clicked");
                row = 1;
                column = 1;
                break;
            case R.id.button12:
                Log.d("button", "button12 was clicked");
                row = 1;
                column = 2;
                break;
            case R.id.button20:
                Log.d("button", "button20 was clicked");
                row = 2;
                column = 0;
                break;
            case R.id.button21:
                Log.d("button", "button21 was clicked");
                row = 2;
                column = 1;
                break;
            case R.id.button22:
                Log.d("button", "button22 was clicked");
                row = 2;
                column = 2;
                break;
        }

        // Get the state of the tile that was clicked.
        TileState state = game.choose(row, column);

        // Get the view of the button that was pressed.
        TextView button = (TextView) view;

        // See what the press of the button means for the game.
        switch (state) {
            case BLANK:
                button.setText(" ");
                Log.d("button", "should be changed to blank");
                break;
            case CROSS:
                button.setText("X");
                button.setBackgroundColor(rgb(255, 202, 40));
                Log.d("button", "should be changed to X");
                break;
            case CIRCLE:
                button.setText("O");
                button.setBackgroundColor(rgb(186, 104, 200));
                Log.d("button", "should be changed to O");
                break;
            case INVALID:
                Toast toast = Toast.makeText(this, "Invalid move!", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER,0,320);
                toast.show();
                break;
        }

        TextView statusBar = findViewById(R.id.textView);

        gameState = game.won();

        // Display the game's state in the statusbar.
        switch (gameState) {
            case IN_PROGRESS:
                boolean playerOneTurn = game.playerOneTurn();
                if (playerOneTurn) {
                    statusBar.setText("Player 1's turn...");
                } else {
                    statusBar.setText("Player 2's turn...");
                }
                return;
            case PLAYER_ONE:
                Log.d("state", "PLAYER ONE WON");
                statusBar.setText("Player 1 won!");
                return;
            case PLAYER_TWO:
                Log.d("state", "PLAYER TWO WON");
                statusBar.setText("Player 2 won!");
                return;
            case DRAW:
                Log.d("state", "DRAW");
                statusBar.setText("Draw!");
        }

    }

    public void resetClicked(View view) {

        // Iterate over the coordinates of the grid, set their text, and base their color on their place in the grid.
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Button button = findViewById(getResources().getIdentifier("button" + i + j, "id", this.getPackageName()));
                button.setText(" ");
                if (((i+j) % 2) == 0) {
                    button.setBackgroundColor(rgb(189, 189, 189));
                } else {
                    button.setBackgroundColor(rgb(224, 224, 224));
                }
            }
        }

        game = new Game();

        // It's always player 1's turn after a reset.
        TextView statusBar = findViewById(R.id.textView);
        statusBar.setText("Player 1's turn...");
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("game", game);
    }
}
