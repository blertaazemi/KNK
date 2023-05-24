package models.dto;

import service.PasswordHasher;

import java.security.NoSuchAlgorithmException;

public class CreateAdminDto {
    private String first_name;
    private String last_name;
    private String username;
    private String email;
    private String password;
    private String salt;

    public CreateAdminDto(String first_name, String last_name, String username, String email, String password, String salt) throws NoSuchAlgorithmException {
        this.first_name = first_name;
        this.last_name = last_name;
        this.username = username;
        this.email = email;
        this.password = PasswordHasher.generateSaltedHash(password, salt);;
        this.salt = salt;
    }

    public String getFirstName() {
        return first_name;
    }

    public void setFirstName(String firstName) {
        this.first_name = firstName;
    }

    public String getLastName() {
        return last_name;
    }

    public void setLastName(String lastName) {
        this.last_name = lastName;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
