package server;

import java.sql.*;

public class DBManager {
    private static final String DB_URL = "jdbc:sqlite:database/chess.db";

    public static void initializeDatabase() {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS games (id INTEGER PRIMARY KEY, player1 TEXT, player2 TEXT, start_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP)");
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS moves (id INTEGER PRIMARY KEY AUTOINCREMENT, game_id INTEGER, move_number INTEGER, notation TEXT, timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP)");
            System.out.println("Database initialized.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int createNewGame(String player1, String player2) {
        String sql = "INSERT INTO games(player1, player2) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, player1);
            pstmt.setString(2, player2);
            pstmt.executeUpdate();
            ResultSet keys = pstmt.getGeneratedKeys();
            if (keys.next()) {
                return keys.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static void recordMove(int gameId, int moveNumber, String notation) {
        String sql = "INSERT INTO moves(game_id, move_number, notation) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, gameId);
            pstmt.setInt(2, moveNumber);
            pstmt.setString(3, notation);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
