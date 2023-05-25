package models;

public class Studenti {
    private int id;
    private String first_name;
    private String last_name;
    private String username;
    private String email;
    //private String faculty;



    public Studenti(int id,String first_name, String last_name, String username, String email) {

        this.id=id;
        this.first_name=first_name;
        this.last_name=last_name;
        this.username=username;
        this.email=email;

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

