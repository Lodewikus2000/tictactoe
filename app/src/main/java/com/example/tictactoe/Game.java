package com.example.tictactoe;


public class Game {
    final private int BOARD_SIZE = 3;
    private TileState[][] board;

    private Boolean playerOneTurn;  // true if player 1's turn, false if player 2's turn
    private int movesPlayed;
    private Boolean gameOver;

    // Constructor
    public Game() {
        board = new TileState[BOARD_SIZE][BOARD_SIZE];
        for(int i=0; i<BOARD_SIZE; i++)
            for(int j=0; j<BOARD_SIZE; j++)
                board[i][j] = TileState.BLANK;

        playerOneTurn = true;
        gameOver = false;
    }

    public TileState choose(int row, int column) {
        TileState currentState = board[row][column];
        if (currentState == TileState.BLANK){
            if (playerOneTurn) {
                board[row][column] = TileState.CROSS;
                playerOneTurn = false;
                return TileState.CROSS;
            }
            else {
                board[row][column] = TileState.CIRCLE;
                playerOneTurn = true;
                return TileState.CIRCLE;
            }
        }
        else {
            return TileState.INVALID;
        }
    }

    public GameState won() {

        // Check rows and columns.
        for(int i=0; i<BOARD_SIZE; i++) {

            // Check if someone won a row.
            if (board[i][0] == board[i][1] && board[i][0] == board[i][2]) {
                if (board[i][0] == TileState.CROSS) {
                    return GameState.PLAYER_ONE;
                }
                if (board[i][0] == TileState.CIRCLE) {
                    return GameState.PLAYER_TWO;
                }
            }

            // Check if someone won a column.
            if ((board[0][i] == board[1][i]) && (board[0][i] == board[2][i])) {
                if (board[0][i] == TileState.CROSS) {
                    return GameState.PLAYER_ONE;
                }
                if (board[0][i] == TileState.CIRCLE) {
                    return GameState.PLAYER_TWO;
                }
            }
        }

        // Check diagonal.
        if ((board[0][0] == board[1][1]) && (board[0][0] == board[2][2])){
            if (board[0][0] == TileState.CROSS) {
                return GameState.PLAYER_ONE;
            }
            if (board[0][0] == TileState.CIRCLE) {
                return GameState.PLAYER_TWO;
            }
        }
        // Check other diagonal.
        if ((board[0][2] == board[1][1]) && (board[0][2] == board[2][0])){
            if (board[0][2] == TileState.CROSS) {
                return GameState.PLAYER_ONE;
            }
            if (board[0][2] == TileState.CIRCLE) {
                return GameState.PLAYER_TWO;
            }
        }


        boolean empty_found = false;

        for(int i=0; i<BOARD_SIZE; i++)
            for(int j=0; j<BOARD_SIZE; j++) {
                if (board[i][j] == TileState.BLANK) {
                    empty_found = true;
                }
            }

        if (empty_found == true) {
            return GameState.IN_PROGRESS;
        }

        return GameState.DRAW;
    }


}
