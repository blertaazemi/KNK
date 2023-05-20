package controller;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import java.sql.*;

public class blerta {
    // Declare the @FXML-annotated variables to reference the UI elements in your FXML file
    @FXML
    private ImageView imageView;
    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private RadioButton femaleRadioButton;
    @FXML
    private RadioButton maleRadioButton;
    @FXML
    private TextField facultyTextField;
    @FXML
    private TextField emailTextField;

    // This method will be called after the FXML file has been loaded, you can perform initialization here
    public void initialize() {
        // Retrieve data from the database and populate the UI elements with the fetched values
        retrieveStudentDataFromDatabase();
    }

    // This method retrieves student data from the database and populates the UI elements
    private void retrieveStudentDataFromDatabase() {
        try {
            // Establish a database connection
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projekti_knk", "root", "bbddd23");

            // Create a statement
            Statement statement = connection.createStatement();

            // Execute the SQL query to retrieve data from tbl_students
            ResultSet resultSet = statement.executeQuery("SELECT * FROM tbl_students");

            // Iterate through the result set and populate the UI elements with the fetched values
            while (resultSet.next()) {
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String gender = resultSet.getString("gender");
                String faculty = resultSet.getString("faculty");
                String email = resultSet.getString("email");

                firstNameTextField.setText(firstName);
                lastNameTextField.setText(lastName);

                if (gender.equals("F")) {
                    femaleRadioButton.setSelected(true);
                } else if (gender.equals("M")) {
                    maleRadioButton.setSelected(true);
                }

                facultyTextField.setText(faculty);
                emailTextField.setText(email);
            }

            // Close the resources
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
