package controller;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import models.AdminAplikim;
import models.AdminStudent;
import repository.AdminAplikimRepository;
import repository.AdminStudentRepository;
import service.ConnectionUtil;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class AdminAplikimetController implements Initializable {
    @FXML
    public TableColumn<AdminAplikim, Integer> col_aplikimId;

    @FXML
    public TableColumn<AdminAplikim, Integer> col_StudentID;

    @FXML
    public TableColumn<AdminAplikim, Integer> col_bursaId;

    @FXML
    public TableColumn<AdminAplikim, Integer> col_vstudimeve;

    @FXML
    public TableColumn<AdminAplikim, Integer> col_Nmes;

    @FXML
    public TableColumn<AdminAplikim, Integer> col_Data;

    @FXML
    private TableView<AdminAplikim> aplikimTableView;
    @FXML
    private TextField AplikimIdField;
    @FXML
    private TextField StudentIdField;
    @FXML
    private TextField BursaField;

    @FXML
    private TextField VstudimeveField;

    @FXML
    private TextField NmesField;

    @FXML
    private DatePicker DataField;


    // Selektimi nga tableview qe ka te dhenat e databazes
    public void SelectAplikim() {
        AdminAplikim aplikim= aplikimTableView.getSelectionModel().getSelectedItem();
        int num = aplikimTableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }
        AplikimIdField.setText(String.valueOf(aplikim.getId()));
        StudentIdField.setText(String.valueOf(aplikim.getStudent_id()));
        BursaField.setText(String.valueOf(aplikim.getBursa_id()));
        VstudimeveField.setText(String.valueOf(aplikim.getStudent_id()));
        NmesField.setText(String.valueOf(aplikim.getStudent_id()));
      //  LocalDate selectedDate = LocalDate.ofEpochDay(aplikim.getStudent_id()); // Assuming getStudent_id() returns a long value representing the date
        //DataField.setValue(selectedDate);




    }

    public void initialize(URL location, ResourceBundle resources) {

        col_aplikimId.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        col_StudentID.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        col_bursaId.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        col_vstudimeve.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        col_Nmes.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());





        List<AdminAplikim> aplikimModelList = null;
        Connection connection = null;
        try {
            connection = ConnectionUtil.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            aplikimModelList = AdminAplikimRepository.getAplikimet(connection);

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception as needed
        }

        ObservableList<AdminAplikim> aplikimObservableList = FXCollections.observableList(aplikimModelList);
        aplikimTableView.setItems(aplikimObservableList);





    }

}
