package model;

public class User {
    private int id;   // set automatically by database
    private String username;  // user's database
    private String password;  //user's password

    //constructor for new user
    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    //constructor for fetching user from database
    public User(int id, String username, String password) {
        this.id = id;
        this.username  = username;
        this.password = password;
    }

    //getters
    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    //setters
    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
