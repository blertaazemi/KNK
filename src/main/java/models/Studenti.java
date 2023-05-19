package models;

public class Studenti {
    private String first_name;
    private String last_name;
    private String email;
    private String gender;
    private String faculty;



    public Studenti(String name, String username, String email, String gender,String faculty) {

        this.first_name=first_name;
        this.last_name=last_name;
        this.email=email;
        this.gender=gender;
        this.faculty=faculty;
    }

    public Studenti(int id, String first_name) {
    }

    //GETTERS

    public String getfirst_name() {
        return first_name;
    }

    public String getlast_name() {
        return last_name;
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
    public void setfirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setlast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setemail(String email) {
        this.email = email;
    }
    public void setgender(String gender) {
        this.gender = gender;
    }
    public void setfaculty(String faculty) {
        this.faculty = faculty;
    }
}
