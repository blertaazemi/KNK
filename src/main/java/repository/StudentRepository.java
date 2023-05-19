package repository;

<<<<<<< Updated upstream
public class StudentRepository {
}
=======
<<<<<<< HEAD
import models.Studenti;
import service.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository {
    public static  Studenti insert(Studenti studenti) throws SQLException {
        String sql = "INSERT INTO tbl_students (first_name, last_name, emai, gender, faculty) VALUES (?, ?, ?, ?, ?)";
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, studenti.getfirst_name());
        statement.setString(2, studenti.getlast_name());
        statement.setString(2, studenti.getemail());
        statement.setString(2, studenti.getgender());
        statement.setString(2, studenti.getfaculty());
        statement.executeUpdate();

        return UserRepository.getByfirst_name(studenti.getfirst_name());
    }

    //
    public static Studenti getByfirst_name(String first_name) throws SQLException {
        String sql = "SELECT * FROM tbl_students WHERE first_name=?";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, first_name);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                int id = resultSet.getInt("id");
                String last_name = resultSet.getString("last_name");
                String email = resultSet.getString("email");
                String gender = resultSet.getString("gender");
                String faculty = resultSet.getString("faculty");
                return new Studenti( first_name, last_name, email, gender, faculty);
            } else {
                return null;
            }
            }
        }

    }

=======
public class StudentRepository {
}
>>>>>>> bdea95102abd424313338d7b1f996da57fc1ff5b
>>>>>>> Stashed changes
