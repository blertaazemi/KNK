package models;

public class AdminStudent {
    private int id;
    private String firstname;
    private String lastname;
    private String susername;

    private String email;
    private String password;


    public AdminStudent(int id, String firstname, String lastname, String susername, String email,String password) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.susername = susername;
        this.email = email;
        this.password=password;
    }

    //GETTERS


    public int getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getSusername() {
        return susername;
    }

    public String getEmail() {
        return email;
    }

    //SETTER

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setSusername(String susername) {
        this.susername = susername;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
