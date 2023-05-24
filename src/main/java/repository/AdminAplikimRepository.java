package repository;

import models.AdminAplikim;
import models.AdminStudent;
import models.dto.CreateAplikimiDto;
import models.dto.CreateStudentDto;
import service.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        String addSql = "INSERT INTO tbl_aplikimet(student_id,bursa_id,viti_studimit, nota_mesatare) VALUES (?,?,?,?)";
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement statement = connection.prepareStatement(addSql);



        statement.setInt(1,aplikim.getStudent_id());
        statement.setInt(2,aplikim.getBursa_id());
        statement.setInt(3,aplikim.getViti_studimit());
        statement.setDouble(4,aplikim.getNota_mesatare());



        statement.executeUpdate();
        return AdminAplikimRepository.getByBursaId(aplikim.getBursa_id());


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

}
