package models;

import repository.AdminRepository;
import repository.StudentRepository;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class AdminLoginModel {
    private String username;
    private String password;

    public AdminLoginModel(String username, String password) throws SQLException, NoSuchAlgorithmException {
        this.username = username;
        this.password = AdminRepository.getPassword(this.username);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
}

