package repository;

import models.dto.CreateAplikimiDto;
import service.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AplikimiRepository {
    public void createapplication(CreateAplikimiDto aplikimi) throws SQLException {
        Connection connection = null;
        PreparedStatement prepareStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionUtil.getConnection();

            String checkId = "SELECT * FROM tbl_aplikimet WHERE student_id= ?";
            prepareStatement = connection.prepareStatement(checkId);
            prepareStatement.setInt(1, aplikimi.getStudent_id());
            resultSet = prepareStatement.executeQuery();

            if (resultSet.next()) {
                throw new SQLException("Perdoruesi " + aplikimi.getStudent_id() + " nuk eshte i lire per perdorim.");
            } else {

                String insertData = "INSERT INTO tbl_aplikimet "
                        + "(student_id,bursa_id, viti_studimit, nota_mesatare,date_submitted)"
                        + "VALUES(?, ?, ?, ?, Now())";
                prepareStatement = connection.prepareStatement(insertData);
                prepareStatement.setInt(1, aplikimi.getStudent_id());
                prepareStatement.setInt(2, aplikimi.getBursa_id());
                prepareStatement.setInt(3, aplikimi.getViti_studimit());
                prepareStatement.setDouble(4, aplikimi.getNota_mesatare());
                prepareStatement.executeUpdate();
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        finally {
            // Close the resources in the reverse order of their creation
            if (resultSet != null) {
                resultSet.close();
            }
            if (prepareStatement != null) {
                prepareStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
}

