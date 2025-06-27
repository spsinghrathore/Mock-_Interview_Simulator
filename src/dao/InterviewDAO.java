package dao;

import java.util.List;
import java.util.Random;
import db.DBConnection;
import model.User;
import java.sql.*;
import java.util.ArrayList;

public class InterviewDAO {
    // Get Questions from interview_questions table
// First and last question is fixed with id 1 and 2!
    public static String getQuestion() {
        String maxIdQuery = "SELECT MAX(id) FROM interview_questions";
        int randomId = 0;

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(maxIdQuery);
            ResultSet res = stmt.executeQuery();

            if (res.next()) {
                int maxId = res.getInt(1);
                if (maxId > 0) {
                    randomId = (int) (Math.random() * maxId) + 1;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return "Can't fetch max id";
        }

        String query = "SELECT * FROM interview_questions WHERE id = ?";

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, randomId);
            ResultSet res = stmt.executeQuery();

            if (res.next()) {
                return res.getString(2);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return "Can't get the question";
        } finally {
            try {
                DBConnection.closeConnection();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    // Submit user responses
    public static boolean submit(User user, String[] answers) {
        String submitQuery = "INSERT INTO interview_sessions (user_id, answer1, answer2, answer3, answer4, answer5) VALUES (?, ?, ?, ?, ?, ?) ";
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(submitQuery);
            stmt.setInt(1, user.getId());
            for (int i = 0; i < answers.length; i++) {
                stmt.setString(i + 2, answers[i]);
            }
            int res = stmt.executeUpdate();
            if (res > 0) {
                System.out.println("\n📝 Submitting Your Responses...");
                for (int i = 0; i < 10; i++) {
                    System.out.print("📀");
                    Thread.sleep(200);
                }
                System.out.println("✅ Done!");
                System.out.println("\n🙏 Thank you for participating!");
            }
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    // Add a new interview question
    public static boolean addQuestion(String question) {
        String query = "INSERT INTO interview_questions (question_text) VALUES (?)";
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DBConnection.getConnection();
            stmt = conn.prepareStatement(query);
            stmt.setString(1, question);
            int res = stmt.executeUpdate();
            return res > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Delete a question by ID
    public static boolean deleteQuestion(int id) {
        String query = "DELETE FROM interview_questions WHERE id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DBConnection.getConnection();
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            int res = stmt.executeUpdate();
            return res > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // View all interview questions
    public static void getAllQuestions() {
        String query = "SELECT * FROM interview_questions";
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            System.out.println("\n📋 List of Interview Questions:");
            while (rs.next()) {
                System.out.println(rs.getInt(1) + ". " + rs.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("❌ Error fetching questions!");
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}


