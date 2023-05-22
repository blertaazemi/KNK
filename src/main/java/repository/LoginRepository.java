package repository;

import controller.alertMessage;
import models.AdminLogin;
import models.Login;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import models.Login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginRepository {
    public boolean login(Login loginModel, Connection connection) {
        String sql = "SELECT * FROM tbl_students WHERE username = ? and password = ?";
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

//    public String adminLogin(AdminLogin adminLogin, Connection connection) {
//        alertMessage alertMessage = new alertMessage();
//        String adminSql = "SELECT role FROM tbl_admin WHERE username = ? AND password = ?";
//        try {
//            PreparedStatement adminStatement = connection.prepareStatement(adminSql);
//            adminStatement.setString(1, adminLogin.getUsername());
//            adminStatement.setString(2, adminLogin.getPassword());
//            ResultSet adminResult = adminStatement.executeQuery();
//            if (adminResult.next()) {
//                return adminResult.getString("role");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//