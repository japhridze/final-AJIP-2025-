package model;

public abstract class Piece {
    private boolean white;

    public Piece(boolean white) {
        this.white = white;
    }

    public boolean isWhite() {
        return white;
    }

    public abstract boolean isValidMove(int fromRow, int fromCol, int toRow, int toCol, Piece[][] board);
}