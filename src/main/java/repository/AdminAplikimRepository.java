package repository;

import models.AdminAplikim;
import models.AdminStudent;
import models.dto.CreateAplikimiDto;
import models.dto.CreateStudentDto;
import models.dto.UpdateAplikimDto;
import models.dto.UpdateStudentDto;
import service.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.time.LocalDate.now;

public class AdminAplikimRepository {

    // marrja e aplikimeve ekzistuese nga databaza
    public static List<AdminAplikim> getAplikimet(Connection connection) throws SQLException {
        List<AdminAplikim> aplikimList = new ArrayList<>();
        String sql = "Select * from tbl_aplikimet";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            int studentid = resultSet.getInt("student_id");
            int bursaid = resultSet.getInt("bursa_id");
            int vitiStudimit = resultSet.getInt("viti_studimit");
            int nmesatare = resultSet.getInt("nota_mesatare");





            AdminAplikim aplikimModel=new AdminAplikim(id,studentid,bursaid,vitiStudimit,nmesatare);
            aplikimList.add(aplikimModel);
        }
        resultSet.close();
        statement.close();
        return aplikimList;

    }

    // insertimi i aplikimeve

    public static AdminAplikim insert(CreateAplikimiDto aplikim) throws SQLException {
        String addSql = "INSERT INTO tbl_aplikimet(student_id,bursa_id,viti_studimit, nota_mesatare,date_submitted) VALUES (?,?,?,?,NOW())";
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement statement = connection.prepareStatement(addSql);



        statement.setInt(1,aplikim.getStudent_id());
        statement.setInt(2,aplikim.getBursa_id());
        statement.setInt(3,aplikim.getViti_studimit());
        statement.setDouble(4,aplikim.getNota_mesatare());



        statement.executeUpdate();
        return AdminAplikimRepository.getByBursaId(aplikim.getBursa_id());


    }

    // fshirja e nje aplikimi nga databaza

    public static boolean deleteAplikim(CreateAplikimiDto aplikim) throws SQLException {
        String deleteSql = "DELETE FROM tbl_aplikimet WHERE student_id = ?";
        Connection connection = ConnectionUtil.getConnection();

        PreparedStatement deleteStatement = connection.prepareStatement(deleteSql);
        deleteStatement.setInt(1, aplikim.getStudent_id());
        int rowsAffected = deleteStatement.executeUpdate();

        deleteStatement.close();
        connection.close();

        return rowsAffected > 0;


    }

    // perditesimi-editimi i nje studenti


    public static boolean updateAplikim(UpdateAplikimDto aplikimDto) throws SQLException{
        String updatesql = "UPDATE tbl_aplikimet SET bursa_id=?, viti_studimit=?, nota_mesatare=? WHERE id=?";
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement statement = connection.prepareStatement(updatesql) ;

        //statement.setInt(1,aplikimDto.getStudent_id());
        statement.setInt(1,aplikimDto.getBursa_id());
        statement.setInt(2,aplikimDto.getViti_studimit());
        statement.setDouble(3,aplikimDto.getNota_mesatare());
        statement.setInt(4, aplikimDto.getId());

        int rowsAffected = statement.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println("Application updated successfully.");
        } else {
            System.out.println("Failed to update application.");
        }

        return rowsAffected>0;
    }

    public static AdminAplikim getByBursaId(int bursaId) throws SQLException {
        String sql = "SELECT * FROM tbl_aplikimet WHERE bursa_id=?";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, bursaId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                int studentId = resultSet.getInt("student_id");
                int vitiStudimit = resultSet.getInt("viti_studimit");
                double notaMesatare = resultSet.getDouble("nota_mesatare");
                // Add any other properties you need from the resultSet

                return new AdminAplikim(id,studentId, bursaId, vitiStudimit, notaMesatare);
            } else {
                return null;
            }
        }
    }

    // filtrimi i nje aplikimi

    public static List<AdminAplikim> filterTable(Connection connection, CreateAplikimiDto model) throws SQLException {
        List<AdminAplikim>aplikimList = new ArrayList<>();
        String sqlQuery = "SELECT * FROM tbl_aplikimet WHERE 1=1";


        if (model.getStudent_id() != 0) {
            sqlQuery+=(" AND student_id = ?");
        }
        if (model.getBursa_id() !=0) {
            sqlQuery+=(" AND bursa_id= ?");
        }


        PreparedStatement statement = connection.prepareStatement(sqlQuery);
        int parameterIndex = 1;


        if (model.getStudent_id()!=0 ) {
            statement.setInt(parameterIndex++,  model.getStudent_id());
        }
        if (model.getBursa_id()!=0) {
            statement.setInt(parameterIndex++, model.getBursa_id());
        }


        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            int studentid=resultSet.getInt("student_id");
            int bursaid=resultSet.getInt("bursa_id");
            int vitiStudimit=resultSet.getInt("viti_studimit");
            double notames=resultSet.getDouble("nota_mesatare");


            // Create an instance of AdminStudent with retrieved data

            AdminAplikim aplikim = new AdminAplikim(id,studentid,bursaid,vitiStudimit,notames);

            aplikimList.add(aplikim);
        }

        resultSet.close();
        statement.close();

        return aplikimList;
    }

}
