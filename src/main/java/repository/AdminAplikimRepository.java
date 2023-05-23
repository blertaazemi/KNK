package repository;

import models.AdminAplikim;
import models.AdminStudent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AdminAplikimRepository {
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
            //Date date = resultSet.getDate("date_column_name");
            //LocalDate localDate = date.toLocalDate();



            AdminAplikim aplikimModel=new AdminAplikim(id,studentid,bursaid,vitiStudimit,nmesatare);
            aplikimList.add(aplikimModel);
        }
        resultSet.close();
        statement.close();
        return aplikimList;

    }
}
