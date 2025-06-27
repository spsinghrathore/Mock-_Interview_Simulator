package dao;

import db.DBConnection;
import model.User;

import java.sql.ResultSet;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SessionDAO {

    public static void viewSessions(Scanner sc) {
        String query = "SELECT s.session_id, u.id AS user_id, u.username, " +
                "s.answer1, s.answer2, s.answer3, s.answer4, s.answer5, s.session_time " +
                "FROM interview_sessions s " +
                "JOIN user u ON s.user_id = u.id " +
                "WHERE s.user_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            System.out.print("🔎 Enter User ID to see Interview Sessions: ");
            int userId = sc.nextInt();
            stmt.setInt(1, userId);

            ResultSet res = stmt.executeQuery();

            boolean found = false;
            int count = 1;

            while (res.next()) {
                found = true;
                System.out.println("\n📝 Session " + count++ + ":");
                System.out.println("────────────────────────────────────────────────────────────");
                System.out.println("Session ID  : " + res.getInt("session_id"));
                System.out.println("User ID     : " + res.getInt("user_id"));
                System.out.println("Username    : " + res.getString("username"));
                System.out.println("Answer 1    : " + res.getString("answer1"));
                System.out.println("Answer 2    : " + res.getString("answer2"));
                System.out.println("Answer 3    : " + res.getString("answer3"));
                System.out.println("Answer 4    : " + res.getString("answer4"));
                System.out.println("Answer 5    : " + res.getString("answer5"));
                System.out.println("Time        : " + res.getTimestamp("session_time").toString());
                System.out.println("────────────────────────────────────────────────────────────");
            }

            if (!found) {
                System.out.println("⚠️  No sessions found for user ID: " + userId);
            }

        } catch (SQLException e) {
            System.out.println("❌ Error: " + e.getMessage());
        } finally {
            try {
                // Closing the connection
                DBConnection.closeConnection();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }




    // Clear all sessions
    public static void clearAllSessions() {
        String sql = "DELETE FROM interview_sessions";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            int rowsDeleted = stmt.executeUpdate();
            System.out.println(" All interview sessions deleted successfully! Total rows affected: " + rowsDeleted);

        } catch (SQLException e) {
            System.out.println("❌ Error while clearing sessions: " + e.getMessage());
        } finally {
            try {
                // Closing the connection
                DBConnection.closeConnection();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}


