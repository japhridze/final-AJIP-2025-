package model;

import util.PGNTranslator;

public class PGNValidator {
    public static boolean validateMove(String move, Board board) {
        try {
            String coordinates = PGNTranslator.convertToCoordinates(move, board, board.isWhiteToMove());
            return board.makeMove(coordinates);
        } catch (Exception e) {
            return false;
        }
    }
}
