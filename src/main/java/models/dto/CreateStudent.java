package models.dto;

public class CreateStudent {
    public int studentId;
    public String emri;
    public String mbiemri;
    public String username;
    public String email;

    public String password;

    public CreateStudent(int studentId, String emri, String mbiemri, String username, String email,String password) {
        this.studentId = studentId;
        this.emri = emri;
        this.mbiemri = mbiemri;
        this.username = username;
        this.email = email;
        this.password=password;
    }
}

