package db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String url = "jdbc:mysql://localhost:3306/interview_master";
    private static final String username = "root";
    private static final String password = "Admin123";

    private static Connection connection;

    public static void main(String[] args){
    getConnection();
    closeConnection();

    }

    public static Connection getConnection(){
        if(connection == null){
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(url, username, password);
               // System.out.println("Connection Created ✅");
            } catch (ClassNotFoundException  | SQLException e) {
                e.printStackTrace();
                System.out.println("Connection Error! ❌");
            }
        }
        return connection;
    }
    //method to close connection
    public static void closeConnection(){
        if(connection !=null){
            try {
                connection.close();
                connection = null;
                //System.out.println("Connection Closed🔒");
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
