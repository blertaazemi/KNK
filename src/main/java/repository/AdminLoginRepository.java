package repository;

import models.AdminLoginModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class AdminLoginRepository {
    public boolean login(AdminLoginModel loginModel, Connection connection) {
        String sql = "SELECT * FROM tbl_admin WHERE username = ? and password = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, loginModel.getUsername());
            statement.setString(2, loginModel.getPassword());
            ResultSet resultSet = statement.executeQuery();
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
