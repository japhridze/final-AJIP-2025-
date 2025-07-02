package model;

public class King extends Piece {
    public King(boolean isWhite) {
        super(isWhite);
    }

    @Override
    public boolean isValidMove(int sx, int sy, int dx, int dy, Piece[][] board) {
        int dxabs = Math.abs(dx - sx), dyabs = Math.abs(dy - sy);
        return (dxabs <= 1 && dyabs <= 1) && (board[dx][dy] == null || board[dx][dy].isWhite() != isWhite());
    }
}