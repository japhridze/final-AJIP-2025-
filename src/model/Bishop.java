package model;

public class Bishop extends Piece {
    public Bishop(boolean isWhite) {
        super(isWhite);
    }

    @Override
    public boolean isValidMove(int sx, int sy, int dx, int dy, Piece[][] board) {
        if (Math.abs(dx - sx) != Math.abs(dy - sy)) return false;

        int stepX = Integer.compare(dx, sx);
        int stepY = Integer.compare(dy, sy);
        int x = sx + stepX, y = sy + stepY;

        while (x != dx && y != dy) {
            if (board[x][y] != null) return false;
            x += stepX;
            y += stepY;
        }

        return board[dx][dy] == null || board[dx][dy].isWhite() != isWhite();
    }
}