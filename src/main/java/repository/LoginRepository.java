package repository;

import models.Login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginRepository {
    public boolean login(Login loginModel, Connection connection) {
        String sql = "SELECT * FROM tbl_students WHERE username = ? and password = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, loginModel.getUsername());
            preparedStatement.setString(2, loginModel.getPassword());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                // No rows returned, so username is incorrect
                System.out.println("Username or password is incorrect.");
                return false;
            } else {

                // Password is correct, so login is successful
                System.out.println("Login successful!");
                // Open adminDashboard tab here
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }





}