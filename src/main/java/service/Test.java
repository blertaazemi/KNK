package service;

import javafx.application.Application;
import javafx.stage.Stage;
import service.ConnectionUtil;

import java.sql.*;

public class Test {
    public static void main(String[]args) {
        try{
        Connection connection = ConnectionUtil.getConnection();
        Statement statement = connection.createStatement();
        ResultSet results = statement.executeQuery("SELECT * FROM tbl_students");
        while(results.next()){
            System.out.println(results.getString("first_name"));          //emri

        }}catch(Exception e ){
            e.printStackTrace();
        }
    }
}
