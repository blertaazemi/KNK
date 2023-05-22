// Login.java
package models;

import repository.StudentRepository;
import service.PasswordHasher;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class Login {
    private String username;
    private String password;

    public Login(String username, String password) throws SQLException, NoSuchAlgorithmException {
        this.username = username;
        this.password = PasswordHasher.generateSaltedHash(password, StudentRepository.getSalt(this.username));
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

//    public void setPassword(String password) {
//        this.password = password;
//    }


}
