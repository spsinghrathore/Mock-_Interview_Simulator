package dao;

import db.DBConnection;
import model.User;
import java.sql.*;
import java.util.Scanner;

public class UserDAO {

  //Register a new user in database
    public boolean createUser(User user) {

        //first we check username is already present or not
        boolean isPresent = checkUser(user);
        if(isPresent) {
            System.out.println("⚠️ Username already exists. Try a different one.");
            DBConnection.closeConnection();
            return false;
        }


        //Here we register
        String query = "INSERT INTO user (username, password) VALUES (?,?) ";

        //creating connection
        try(Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {


            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());

            int rowsInserted = stmt.executeUpdate();
            DBConnection.closeConnection();
            return rowsInserted > 0;
        } catch(SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //Check the user in database!
    public boolean checkUser(User user) {
        String query = "SELECT * FROM user WHERE username = ? AND password = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            // Set the username and password parameters for the query
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());

            // Execute the query and get the result
            ResultSet res = stmt.executeQuery();

            // Check if any record was returned
            if (res.next()) {
                // If a record is found, user credentials are correct
                return true;


            } else {
                // If no record is found, credentials are incorrect
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                // Closing the connection
                DBConnection.closeConnection();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public  void viewAllUsers() {
        try {
            Connection conn = DBConnection.getConnection();
            String query = "SELECT * FROM user";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet res = stmt.executeQuery();

            System.out.println("+--------+----------------+");
            System.out.println("|  ID    |   Username     |");
            System.out.println("+--------+----------------+");

            while (res.next()) {
                int userId = res.getInt(1);
                String userName = res.getString(2);

                System.out.printf("| %-6d | %-14s |\n", userId, userName);
            }

            System.out.println("+--------+----------------+");

        } catch(SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                DBConnection.closeConnection();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //function to delete user
    //function to delete user
    public void deleteUser(int userId) {
        String deleteSessionsQuery = "DELETE FROM interview_sessions WHERE user_id = ?";
        String deleteUserQuery = "DELETE FROM user WHERE id = ?";

        try {
            Connection conn = DBConnection.getConnection();

            // First, delete interview sessions if any exist
            PreparedStatement deleteSessionsStmt = conn.prepareStatement(deleteSessionsQuery);
            deleteSessionsStmt.setInt(1, userId);
            int sessionDeleted = deleteSessionsStmt.executeUpdate();

            if (sessionDeleted > 0) {
                System.out.println("Deleted " + sessionDeleted + " interview session(s) for this user.");
            }

            // Then, delete the user
            PreparedStatement deleteUserStmt = conn.prepareStatement(deleteUserQuery);
            deleteUserStmt.setInt(1, userId);
            int userDeleted = deleteUserStmt.executeUpdate();

            if (userDeleted > 0) {
                System.out.println("✅ User Deleted Successfully!");
            } else {
                System.out.println("⚠️ User not found or already deleted.");
            }

        } catch (SQLException e) {
            System.out.println("❌ Error while deleting user: " + e.getMessage());
        } finally {
            try {
                DBConnection.closeConnection();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //function to get user it
    public static int getUserId(User user) {
        String sql = "SELECT id FROM user WHERE username = ?";
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, user.getUsername());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                System.out.println("User not found: " + user.getUsername());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // return -1 if user not found or error
    }

    //method to update user in the database
    public static void updateUserById(Scanner sc) {
        String sql = "UPDATE user SET username = ?, password = ? WHERE id = ?";

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            System.out.println("Enter id of user to update: ");
            int userId = sc.nextInt();

            System.out.println("Enter New Username: ");
            String newUsername = sc.next();
            System.out.println("Enter New Password: ");
            String newPassword = sc.next();
            stmt.setString(1, newUsername);
            stmt.setString(2, newPassword);
            stmt.setInt(3, userId);

            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("✅ User updated successfully!");
            } else {
                System.out.println("⚠️ No user found with ID: " + userId);
            }

        } catch (SQLException e) {
            System.out.println("❌ Error updating user: " + e.getMessage());
        }
    }

}
