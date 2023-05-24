package repository;

import models.Bursat;

import service.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BursatRepository {
    //marrja e te dhenave nga databaza
    public static List<Bursat> getStudents(Connection connection) throws SQLException {
        List<Bursat> bursatList = new ArrayList<>();
        String sql = "Select * from tbl_bursa";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            Double nota_mesatare = resultSet.getDouble("nota_mesatare");
            String description = resultSet.getString("description");
            Double amount = resultSet.getDouble("amount");


            Bursat bursatModel = new Bursat(id, name, nota_mesatare, description, amount);
            bursatList.add(bursatModel);
        }
        resultSet.close();
        statement.close();
        return bursatList;

    }


    // fshirja e nje studenti nga databaza

    public static boolean deleteStudent(int id) throws SQLException {
        String deleteSql = "DELETE FROM tbl_bursa WHERE id = ?";
        Connection connection = ConnectionUtil.getConnection();

        PreparedStatement deleteStatement = connection.prepareStatement(deleteSql);
        deleteStatement.setInt(1, id);
        int rowsAffected = deleteStatement.executeUpdate();

        deleteStatement.close();
        connection.close();

        return rowsAffected > 0;


    }

    // perditesimi-editimi i nje studenti


//    public static boolean updateBursa(Bursat bursat) throws SQLException{
//        String updatesql = "UPDATE tbl_students SET first_name=?, last_name=?, username=?, email=?, password=?, salt=? WHERE id=?";
//        Connection connection = ConnectionUtil.getConnection();
//        PreparedStatement statement = connection.prepareStatement(updatesql) ;
//
//        statement.setString(1, studentDto.getFirst_name());
//        statement.setString(2, studentDto.getLast_name());
//        statement.setString(3, studentDto.getUsername());
//        statement.setString(4, studentDto.getEmail());
//        statement.setString(5, studentDto.getPassword());
//        statement.setString(6, studentDto.getSalt());
//        statement.setInt(7, studentDto.getId());
//
//        int rowsAffected = statement.executeUpdate();
//
//        if (rowsAffected > 0) {
//            System.out.println("Student updated successfully.");
//        } else {
//            System.out.println("Failed to update student.");
//        }
//
//        return rowsAffected>0;
//    }








//    public static AdminStudent getByUsername(String username) throws SQLException {
//        String sql = "SELECT * FROM tbl_students WHERE username=?";
//        try (Connection connection = ConnectionUtil.getConnection();
//             PreparedStatement statement = connection.prepareStatement(sql)) {
//            statement.setString(1, username);
//            ResultSet resultSet = statement.executeQuery();
//            if (resultSet.next()) {
//                int id = resultSet.getInt("id");
//                String firstName = resultSet.getString("first_name");
//                String lastName = resultSet.getString("last_name");
//                String email=resultSet.getString("email");
//                String password=resultSet.getString("password");
//                String salt=resultSet.getString("salt");
//                return new AdminStudent(id,firstName,lastName,username,email,password,salt);
//            } else {
//                return null;
//            }
//        }
//    }
}



