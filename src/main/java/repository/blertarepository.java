package repository;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import service.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class blertarepository {
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private RadioButton genderFField;
    @FXML
    private RadioButton genderMField;
    @FXML
    private TextField facultyField;
    @FXML
    private TextField emailField;

    public void initialize() {
        fetchDataFromDatabase();
    }

    private void fetchDataFromDatabase() {
        // Fetch data from the database and populate the text fields
        try (Connection connection = ConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM tbl_students";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                firstNameField.setText(resultSet.getString("first_name"));
                lastNameField.setText(resultSet.getString("last_name"));
                String gender = resultSet.getString("gender");
                if (gender.equals("F")) {
                    genderFField.setSelected(true);
                } else if (gender.equals("M")) {
                    genderMField.setSelected(true);
                }
                facultyField.setText(resultSet.getString("faculty"));
                emailField.setText(resultSet.getString("email"));
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void saveDataToDatabase() {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String gender = genderFField.isSelected() ? "F" : "M";
        String faculty = facultyField.getText();
        String email = emailField.getText();

        try (Connection connection = ConnectionUtil.getConnection()) {
            String sql = "INSERT INTO tbl_students (first_name, last_name, gender, faculty, email) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, gender);
            statement.setString(4, faculty);
            statement.setString(5, email);

            statement.executeUpdate();
            statement.close();
            connection.close();

            // Optional: Show a success message or perform any additional actions after the insert.
        } catch (SQLException e) {
            e.printStackTrace();
            // Optional: Show an error message or handle the exception appropriately.
        }
    }
}
