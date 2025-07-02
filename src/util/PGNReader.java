package util;

import java.io.*;
import java.util.*;

public class PGNReader {

    public static List<String> extractMoves(String filePath) {
        List<String> moves = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            StringBuilder pgnText = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                // Ignore header lines like [Event "..."]
                if (!line.startsWith("[")) {
                    pgnText.append(line).append(" ");
                }
            }

            // Split by move numbers (e.g., "1.", "2." etc.)
            String[] tokens = pgnText.toString().split("\\d+\\.");
            for (String token : tokens) {
                String[] movePair = token.trim().split("\\s+");
                for (String move : movePair) {
                    if (!move.isEmpty() && !move.matches("1-0|0-1|1/2-1/2|\\*")) {
                        moves.add(move);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return moves;
    }
}
