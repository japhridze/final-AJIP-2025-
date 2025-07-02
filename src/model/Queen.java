package model;

public class Queen extends Piece {
    public Queen(boolean isWhite) {
        super(isWhite);
    }

    @Override
    public boolean isValidMove(int sx, int sy, int dx, int dy, Piece[][] board) {
        Rook r = new Rook(isWhite());
        Bishop b = new Bishop(isWhite());
        return r.isValidMove(sx, sy, dx, dy, board) || b.isValidMove(sx, sy, dx, dy, board);
    }
}
