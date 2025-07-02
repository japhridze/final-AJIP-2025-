package model;

public class Board {
    private Piece[][] board = new Piece[8][8];
    private boolean whiteToMove = true;

    public void initialize() {
        for (int i = 0; i < 8; i++) {
            board[1][i] = new Pawn(false);
            board[6][i] = new Pawn(true);
        }
        board[0][0] = new Rook(false);  board[0][7] = new Rook(false);
        board[7][0] = new Rook(true);   board[7][7] = new Rook(true);
        board[0][1] = new Knight(false);board[0][6] = new Knight(false);
        board[7][1] = new Knight(true); board[7][6] = new Knight(true);
        board[0][2] = new Bishop(false);board[0][5] = new Bishop(false);
        board[7][2] = new Bishop(true); board[7][5] = new Bishop(true);
        board[0][3] = new Queen(false); board[0][4] = new King(false);
        board[7][3] = new Queen(true);  board[7][4] = new King(true);
    }

    public boolean applyMove(String move) {
        if (move.length() != 4) return false;

        int fromCol = move.charAt(0) - 'a';
        int fromRow = 8 - (move.charAt(1) - '0');
        int toCol   = move.charAt(2) - 'a';
        int toRow   = 8 - (move.charAt(3) - '0');

        if (!isOnBoard(fromRow, fromCol) || !isOnBoard(toRow, toCol)) return false;

        Piece piece = board[fromRow][fromCol];
        if (piece == null) return false;
        if (piece.isWhite() != whiteToMove) return false;

        if (!piece.isValidMove(fromRow, fromCol, toRow, toCol, this.getBoard())) return false;

        board[toRow][toCol] = piece;
        board[fromRow][fromCol] = null;
        whiteToMove = !whiteToMove;
        return true;
    }

    private boolean isOnBoard(int row, int col) {
        return row >= 0 && row < 8 && col >= 0 && col < 8;
    }

    public boolean isWhiteToMove() {
        return whiteToMove;
    }

    public Piece[][] getBoard() {
        return board;
    }

    public Piece getPiece(int row, int col) {
        return board[row][col];
    }

    public boolean makeMove(String coordinates) {
        return false;
    }
}
