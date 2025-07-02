package server;

import java.sql.*;

public class DBManager {

    private static final String URL = "jdbc:sqlite:chess.db";

    // Establishes a connection to the SQLite database
    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    // Saves a single move and whether it's valid into the 'moves' table
    public static void saveMove(String move, boolean valid) {
        String sql = "INSERT INTO moves (move_text, is_valid, timestamp) VALUES (?, ?, CURRENT_TIMESTAMP)";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, move);
            pstmt.setBoolean(2, valid);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // (Optional) You can add setup method here if you want to create the table from code
    public static void createMovesTable() {
        String sql = """
            CREATE TABLE IF NOT EXISTS moves (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                move_text TEXT,
                is_valid BOOLEAN,
                timestamp DATETIME DEFAULT CURRENT_TIMESTAMP
            );
        """;
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

