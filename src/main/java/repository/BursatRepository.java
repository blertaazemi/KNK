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


    // fshirja e nje burse nga databaza

    public static void deleteBursa(int id) throws SQLException {
        String deleteSql = "DELETE FROM tbl_bursa WHERE id = ?";
        Connection connection = ConnectionUtil.getConnection();

        PreparedStatement deleteStatement = connection.prepareStatement(deleteSql);
        deleteStatement.setInt(1, id);
        int rowsAffected = deleteStatement.executeUpdate();

        deleteStatement.close();
        connection.close();


    }



    public static void updateBursa(Bursat bursa) throws SQLException {
        String updateSql = "UPDATE tbl_bursa SET name=?, nota_mesatare=?, description=?, amount=? WHERE id=?";
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement statement = connection.prepareStatement(updateSql);

        statement.setString(1, bursa.getname());
        statement.setDouble(2, bursa.getnota_mesatare());
        statement.setString(3, bursa.getdescription());
        statement.setDouble(4, bursa.getamount());
        statement.setInt(5, bursa.getId());

        int rowsAffected = statement.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println("Bursa updated successfully.");
        } else {
            System.out.println("Failed to update bursa.");
        }

        statement.close();
        connection.close();
    }


}



