package model;

public class Rook extends Piece {
    public Rook(boolean isWhite) {
        super(isWhite);
    }

    @Override
    public boolean isValidMove(int fromRow, int fromCol, int toRow, int toCol, Piece[][] board) {
        if (fromRow != toRow && fromCol != toCol) return false;

        int stepRow = Integer.compare(toRow, fromRow);
        int stepCol = Integer.compare(toCol, fromCol);
        int r = fromRow + stepRow;
        int c = fromCol + stepCol;

        while (r != toRow || c != toCol) {
            if (board[r][c] != null) return false;
            r += stepRow;
            c += stepCol;
        }

        return board[toRow][toCol] == null || board[toRow][toCol].isWhite() != this.isWhite();
    }
}
