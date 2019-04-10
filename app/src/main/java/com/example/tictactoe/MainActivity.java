package com.example.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

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
        game = new Game();

        ((TextView)findViewById(R.id.button00)).setText(" ");
        ((TextView)findViewById(R.id.button01)).setText(" ");
        ((TextView)findViewById(R.id.button02)).setText(" ");
        ((TextView)findViewById(R.id.button10)).setText(" ");
        ((TextView)findViewById(R.id.button11)).setText(" ");
        ((TextView)findViewById(R.id.button12)).setText(" ");
        ((TextView)findViewById(R.id.button20)).setText(" ");
        ((TextView)findViewById(R.id.button21)).setText(" ");
        ((TextView)findViewById(R.id.button22)).setText(" ");
    }



    public void tileClicked(View view) {
        GameState gameState = game.won();
        if (gameState != IN_PROGRESS){
            return;
        }

        int id = view.getId();

        int row = 7;
        int column = 7;

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

        TileState state = game.choose(row, column);

        // Get the view of the button that was pressed.
        TextView button = (TextView) findViewById(id);

        switch (state) {
            case BLANK:
                button.setText(" ");
                Log.d("button", "should be changed to blank");
                break;
            case CROSS:
                button.setText("X");
                Log.d("button", "should be changed to X");
                break;
            case CIRCLE:
                button.setText("O");
                Log.d("button", "should be changed to O");
                break;
            case INVALID:
                break;
        }

        gameState = game.won();

        switch (gameState) {
            case PLAYER_ONE:
                Log.d("state", "PLAYER ONE WON");

                break;
            case PLAYER_TWO:
                Log.d("state", "PLAYER TWO WON");
                break;
            case DRAW:
                Log.d("state", "DRAW");
                break;
        }
    }

    public void resetClicked(View view) {
        game = new Game();
        // Make all buttons empty again.
        ((TextView)findViewById(R.id.button00)).setText(" ");
        ((TextView)findViewById(R.id.button01)).setText(" ");
        ((TextView)findViewById(R.id.button02)).setText(" ");
        ((TextView)findViewById(R.id.button10)).setText(" ");
        ((TextView)findViewById(R.id.button11)).setText(" ");
        ((TextView)findViewById(R.id.button12)).setText(" ");
        ((TextView)findViewById(R.id.button20)).setText(" ");
        ((TextView)findViewById(R.id.button21)).setText(" ");
        ((TextView)findViewById(R.id.button22)).setText(" ");
    }

    public void party (int player) {

    }
}
