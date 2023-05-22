package controller;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import models.AdminStudent;
import models.dto.CreateStudentDto;
import models.dto.UpdateStudentDto;
import repository.AdminStudentRepository;
import service.ConnectionUtil;
import service.PasswordHasher;

import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class AdminStudentController implements Initializable {
    @FXML
    public TableColumn<AdminStudent, Integer> col_studentId;

    @FXML
    public TableColumn<AdminStudent, String> col_firstName;

    @FXML
    public TableColumn<AdminStudent, String> col_lastName;

    @FXML
    public TableColumn<AdminStudent, String> col_username;

    @FXML
    public TableColumn<AdminStudent, String> col_email;

    @FXML
    public TableColumn<AdminStudent, String> col_password;


    @FXML
    private TableView<AdminStudent> studentTableView;
    @FXML
    private TextField StudentIdField;
    @FXML
    private TextField FirstNameField;
    @FXML
    private TextField LastNameField;

    @FXML
    private TextField UsernameField;

    @FXML
    private TextField EmailField;




    @FXML
    private TextField PasswordField;

    @FXML
    private Button addStudentBtn;

    @FXML
    private Button deleteStudentBtn;

    @FXML
    private Button updateStudentBtn;



    // Logjika e buttonit add
    @FXML
    public void addStudentClick() {
        try {
            // Gather student information from input fields or other sources
            String firstName = FirstNameField.getText();
            String lastName = LastNameField.getText();
            String username = UsernameField.getText();
            String email = EmailField.getText();
            String password = PasswordField.getText();
            String salt = PasswordHasher.generateSalt();
            String saltedHash = PasswordHasher.generateSaltedHash(password, salt);

            CreateStudentDto student = new CreateStudentDto(firstName, lastName, username, email, saltedHash, salt);


            try {
                AdminStudent insertedStudent = AdminStudentRepository.insert(student);
                if (insertedStudent != null) {
                    studentTableView.getItems().add(insertedStudent);

                }
            } catch (SQLException e) {
                e.printStackTrace();

            }
        }catch(NoSuchAlgorithmException e){
            e.printStackTrace();
        }
    }
 // logjika e buttonit delete
    public void deleteStudentClick() {

            AdminStudent selectedStudent = studentTableView.getSelectionModel().getSelectedItem();
            try {
                if (selectedStudent != null) {
                    CreateStudentDto studentToDelete = new CreateStudentDto(
                            selectedStudent.getFirst_name(),
                            selectedStudent.getLast_name(),
                            selectedStudent.getUsername(),
                            selectedStudent.getEmail(),
                            selectedStudent.getPassword(),
                            selectedStudent.getSalt()
                    );

                    try {
                        boolean isDeleted = AdminStudentRepository.deleteStudent(studentToDelete);

                        if (isDeleted) {
                            // Remove the deleted student from the TableView
                            studentTableView.getItems().remove(selectedStudent);

                            System.out.println("User with username '" + studentToDelete.getUsername() + "' has been deleted successfully.");
                        } else {
                            System.out.println("Failed to delete user with username '" + studentToDelete.getUsername() + "'.");
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("No student is selected.");
                }
            }catch(NoSuchAlgorithmException e){
                e.printStackTrace();
            }
        }



// logjika e buttonit update
public void updateStudentClick() {
    // Get the selected student ID and updated student information from input fields or other sources
    int studentId = Integer.parseInt(StudentIdField.getText());
    String firstName = FirstNameField.getText();
    String lastName = LastNameField.getText();
    String username = UsernameField.getText();
    String email = EmailField.getText();
    String password = PasswordField.getText();
    String salt = PasswordHasher.generateSalt();
    String saltedHash = PasswordHasher.generateSaltedHash(password, salt);

    UpdateStudentDto updatedStudent = new UpdateStudentDto(studentId, firstName, lastName, username, email, saltedHash, salt);

    AdminStudent selectedStudent = null;
    for (AdminStudent student : studentTableView.getItems()) {
        if (student.getId() == studentId) {
            selectedStudent = student;
            break;
        }
    }

    if (selectedStudent != null) {
        // Update the AdminStudent object with the updated information
        selectedStudent.setFirst_name(firstName);
        selectedStudent.setLast_name(lastName);
        selectedStudent.setUsername(username);
        selectedStudent.setEmail(email);
        // Update the password only if a new password is provided
        if (!password.isEmpty()) {
            selectedStudent.setPassword(saltedHash);
        }



        try {

            boolean isUpdated = AdminStudentRepository.updateStudent(updatedStudent);
            if (isUpdated) {

                System.out.println("Student updated successfully.");
                studentTableView.refresh();
            } else {

                System.out.println("Failed to update student.");
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }

    }
}

// logjika e buttonit filter

    @FXML
    void filterStudentClick(ActionEvent event) throws SQLException {
        String firstNameFilter = FirstNameField.getText();
        String lastNameFilter = LastNameField.getText();
        String usernameFilter = UsernameField.getText();

        Connection connection = null;
        try{
        try {
            connection = ConnectionUtil.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        CreateStudentDto studentDto = new CreateStudentDto(firstNameFilter, lastNameFilter, usernameFilter, "", "", "");
        List<AdminStudent> studentModelList = null;

        try {
            studentModelList = AdminStudentRepository.filterTable(connection, studentDto);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Update the table with the filtered data
        ObservableList<AdminStudent> filteredList = FXCollections.observableList(studentModelList);
        studentTableView.setItems(filteredList);
    }catch(NoSuchAlgorithmException e){
            e.printStackTrace();
        }
    }



    // Selektimi nga tableview qe ka te dhenat e databazes
    public void SelectStudent() {
        AdminStudent student = studentTableView.getSelectionModel().getSelectedItem();
        int num = studentTableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }
       StudentIdField.setText(String.valueOf(student.getId()));
        FirstNameField.setText(student.getFirst_name());
        LastNameField.setText(student.getLast_name());
        UsernameField.setText(student.getUsername());
        EmailField.setText(student.getEmail());



    }

    public void initialize(URL location, ResourceBundle resources) {
        col_studentId.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        col_firstName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFirst_name()));
        col_lastName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLast_name()));
        col_username.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUsername()));
        col_email.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
        col_password.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPassword()));

        List<AdminStudent> studentModelList = null;
        Connection connection = null;
        try {
            connection = ConnectionUtil.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            studentModelList = AdminStudentRepository.getStudents(connection);

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception as needed
        }

        ObservableList<AdminStudent> studentObservableList = FXCollections.observableList(studentModelList);
        studentTableView.setItems(studentObservableList);

    }



}


