package util;

import model.Board;
import model.Piece;

public class PGNTranslator {

    public static String convertToCoordinates(String move, Board board, boolean whiteToMove) {
        if (move == null || move.isEmpty()) return null;

        move = move.replaceAll("[+#]", ""); // remove check/mate symbols

        if (move.equals("O-O")) return whiteToMove ? "e1g1" : "e8g8";
        if (move.equals("O-O-O")) return whiteToMove ? "e1c1" : "e8c8";

        char pieceChar = 'P'; // default is pawn
        int i = 0;
        if (Character.isUpperCase(move.charAt(0)) && move.charAt(0) != 'O') {
            pieceChar = move.charAt(0);
            i++;
        }

        // Handle disambiguation (e.g., Nbd2, R1e1)
        // We'll skip that complexity for now and just try matching pieces

        char destCol = move.charAt(move.length() - 2);
        char destRow = move.charAt(move.length() - 1);

        int toCol = destCol - 'a';
        int toRow = 8 - Character.getNumericValue(destRow);

        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                Piece piece = board.getPiece(r, c);
                if (piece != null &&
                        piece.getClass().getSimpleName().charAt(0) == pieceChar &&
                        piece.isWhite() == whiteToMove &&
                        piece.isValidMove(r, c, toRow, toCol, board.getBoard())) {

                    // Return move in coordinate format: e2e4
                    return "" + (char) ('a' + c) + (8 - r) + destCol + destRow;
                }
            }
        }

        return null; // No valid matching piece found
    }
}
