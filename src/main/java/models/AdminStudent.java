package models;

public class AdminStudent {
    public int id;
    public String first_name;
    public String last_name;
    public String username;

    public String email;
    public String password;

    public AdminStudent(int id, String first_name, String last_name, String username, String email, String password) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
