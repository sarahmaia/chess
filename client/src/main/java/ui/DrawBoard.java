package ui;

import chess.ChessGame;

public class DrawBoard {
    private String[][] chessBoard = new String[10][10];

    private final String borderSquare = EscapeSequences.SET_BG_COLOR_LIGHT_GREY + EscapeSequences.SET_TEXT_COLOR_BLACK;

    private final ChessGame chessGame;

    public DrawBoard() {
        chessGame = new ChessGame();
    }