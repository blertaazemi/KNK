package models;

public class Studenti {
    private String name;
    private String username;
    private String email;
    private String gender;
    private String faculty;


    public Studenti(String name, String username, String email, String gender,String faculty) {

        this.name=name;
        this.username = username;
        this.email=email;
        this.gender=gender;
        this.faculty=faculty;
    }

    //GETTERS

    public String getname() {
        return name;
    }

    public String getusername() {
        return username;
    }

    public String getemail() {
        return email;
    }
    public String getgender() {
        return gender;
    }
    public String getfaculty() {
        return faculty;
    }


    //SETTERS
    public void setname(String username) {
        this.name = name;
    }

    public void setusername(String username) {
        this.username = username;
    }

    public void setemail(String username) {
        this.email = email;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setfaculty(String faculty) {
        this.faculty = faculty;
    }
}
