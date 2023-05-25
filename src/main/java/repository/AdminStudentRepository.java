package repository;

import models.AdminStudent;
import models.dto.CreateAdminStudentDto;
import models.dto.CreateStudentDto;
import models.dto.UpdateStudentDto;
import service.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminStudentRepository {
    //marrja e te dhenave nga databaza
    public static List<AdminStudent> getStudents(Connection connection) throws SQLException {
        List<AdminStudent> studentList = new ArrayList<>();
        String sql = "Select * from tbl_students";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("Id");
            String first_name = resultSet.getString("first_name");
            String last_name = resultSet.getString("last_name");
            String username = resultSet.getString("username");
            String email = resultSet.getString("email");
            String password = resultSet.getString("password");
            String salt=resultSet.getString("salt");

            AdminStudent studentModel = new AdminStudent(id, first_name, last_name, username, email, password,salt);
            studentList.add(studentModel);
        }
        resultSet.close();
        statement.close();
        return studentList;

    }
  // vendosja e nje studenti te ri ne databaze(add)
    public static AdminStudent insert(CreateStudentDto student) throws SQLException {
        String addSql = "INSERT INTO tbl_students(first_name,last_name,username,email,password,salt) VALUES (?,?,?,?,?,?)";
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement statement = connection.prepareStatement(addSql);



        statement.setString(1, student.getFirstName());
        statement.setString(2, student.getLastName());
        statement.setString(3, student.getUsername());
        statement.setString(4, student.getEmail());
        statement.setString(5, student.getPassword());
        statement.setString(6,student.getSalt());


        statement.executeUpdate();
        return AdminStudentRepository.getByUsername(student.getUsername());

    }

    // fshirja e nje studenti nga databaza

    public static boolean deleteStudent(CreateStudentDto student) throws SQLException {
        String deleteSql = "DELETE FROM tbl_students WHERE username = ?";
        Connection connection = ConnectionUtil.getConnection();

        PreparedStatement deleteStatement = connection.prepareStatement(deleteSql);
        deleteStatement.setString(1, student.getUsername());
        int rowsAffected = deleteStatement.executeUpdate();

        deleteStatement.close();
        connection.close();

        return rowsAffected > 0;


    }

    // perditesimi-editimi i nje studenti


        public static boolean updateStudent(UpdateStudentDto studentDto) throws SQLException{
            String updatesql = "UPDATE tbl_students SET first_name=?, last_name=?, username=?, email=?, password=?, salt=? WHERE id=?";
             Connection connection = ConnectionUtil.getConnection();
                 PreparedStatement statement = connection.prepareStatement(updatesql) ;

                statement.setString(1, studentDto.getFirst_name());
                statement.setString(2, studentDto.getLast_name());
                statement.setString(3, studentDto.getUsername());
                statement.setString(4, studentDto.getEmail());
                statement.setString(5, studentDto.getPassword());
                statement.setString(6, studentDto.getSalt());
                statement.setInt(7, studentDto.getId());

                int rowsAffected = statement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Student updated successfully.");
                } else {
                    System.out.println("Failed to update student.");
                }

            return rowsAffected>0;
        }

        // filtrimi i nje studenti
        public static List<AdminStudent> filterTable(Connection connection, CreateAdminStudentDto model) throws SQLException {
            List<AdminStudent> studentList = new ArrayList<>();
            StringBuilder sqlBuilder = new StringBuilder("SELECT * FROM tbl_students WHERE 1=1");

            if (model.getId() != 0) {
                sqlBuilder.append(" AND id= ?");
            }
            if (model.getFirst_name() != null && !model.getFirst_name().isEmpty()) {
                sqlBuilder.append(" AND first_name LIKE ?");
            }
            if (model.getLast_name() != null && !model.getLast_name().isEmpty()) {
                sqlBuilder.append(" AND last_name LIKE ?");
            }
            if (model.getUsername() != null && !model.getUsername().isEmpty()) {
                sqlBuilder.append(" AND username LIKE ?");
            }

            PreparedStatement statement = connection.prepareStatement(sqlBuilder.toString());
            int parameterIndex = 1;

            if (model.getId()!=0 ) {
                statement.setInt(parameterIndex++,  model.getId());
            }

            if (model.getFirst_name() != null && !model.getFirst_name().isEmpty()) {
                statement.setString(parameterIndex++, "%" + model.getFirst_name() + "%");
            }
            if (model.getLast_name() != null && !model.getLast_name().isEmpty()) {
                statement.setString(parameterIndex++, "%" + model.getLast_name() + "%");
            }
            if (model.getUsername() != null && !model.getUsername().isEmpty()) {
                statement.setString(parameterIndex++, "%" + model.getUsername() + "%");
            }

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String username = resultSet.getString("username");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String salt = resultSet.getString("salt");

                // Create an instance of AdminStudent with retrieved data
                AdminStudent student = new AdminStudent(id, firstName, lastName, username, email, password, salt);
                studentList.add(student);
            }

            resultSet.close();
            statement.close();

            return studentList;
        }






    public static AdminStudent getByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM tbl_students WHERE username=?";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String email=resultSet.getString("email");
                String password=resultSet.getString("password");
                String salt=resultSet.getString("salt");
                return new AdminStudent(id,firstName,lastName,username,email,password,salt);
            } else {
                return null;
            }
        }
    }
}



