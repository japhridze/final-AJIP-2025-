package model;

public class Pawn extends Piece {
    public Pawn(boolean isWhite) {
        super(isWhite);
    }

    @Override
    public boolean isValidMove(int sx, int sy, int dx, int dy, Piece[][] board) {
        int direction = isWhite() ? -1 : 1;
        int startRow = isWhite() ? 6 : 1;

        if (sy == dy && board[dx][dy] == null) {
            if (dx - sx == direction) return true;
            if (sx == startRow && dx - sx == 2 * direction && board[sx + direction][sy] == null) return true;
        }

        if (Math.abs(dy - sy) == 1 && dx - sx == direction && board[dx][dy] != null
                && board[dx][dy].isWhite() != this.isWhite()) {
            return true;
        }

        return false;
    }
}