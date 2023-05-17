package service;

import javafx.application.Application;
import javafx.stage.Stage;
import service.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Test {
    public static void main(String[]args) throws SQLException {
        String sql="UPDATE tbl_students set first_name=? WHERE id=?";    //kto pikpytje jon per mi ju lon vlera mandej ma poshte
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1,"Deshira");                         //TREGON SE TIPI E PIKPYTJES ESHTE INTEGER DHE E KA VLEREN 3
        statement.setString(2,"Deshira");
        statement.setInt(1,3);

        //1 indeksi i kolones
        //3 vlera
        ResultSet results=statement.executeQuery();
        if(results.next()){
            System.out.println(results.getString(2));          //emri

        }
    }
}
