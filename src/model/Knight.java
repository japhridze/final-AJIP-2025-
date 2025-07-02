package model;

public class Knight extends Piece {
    public Knight(boolean isWhite) {
        super(isWhite);
    }

    @Override
    public boolean isValidMove(int sx, int sy, int dx, int dy, Piece[][] board) {
        int dxabs = Math.abs(dx - sx), dyabs = Math.abs(dy - sy);
        if (!((dxabs == 1 && dyabs == 2) || (dxabs == 2 && dyabs == 1))) return false;
        return board[dx][dy] == null || board[dx][dy].isWhite() != this.isWhite();
    }
}