package controller;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import models.AdminAplikim;
import models.AdminStudent;
import models.dto.CreateAplikimiDto;
import models.dto.CreateStudentDto;
import repository.AdminAplikimRepository;
import repository.AdminStudentRepository;
import service.ConnectionUtil;
import service.PasswordHasher;

import java.net.URL;
import java.security.NoSuchAlgorithmException;
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
    public TableColumn<AdminAplikim, Double> col_Nmes;

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
   private Pagination pagination;


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
        VstudimeveField.setText(String.valueOf(aplikim.getViti_studimit()));
        NmesField.setText(String.valueOf(aplikim.getNota_mesatare()));





    }

    public void initialize(URL location, ResourceBundle resources) {

        col_aplikimId.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        col_StudentID.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getStudent_id()).asObject());
        col_bursaId.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getBursa_id()).asObject());
        col_vstudimeve.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getViti_studimit()).asObject());
        col_Nmes.setCellValueFactory(cellData -> Bindings.createObjectBinding(() -> cellData.getValue().getNota_mesatare()));







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
        int itemsPerPage = 10;
        int pageCount = (aplikimObservableList.size() + itemsPerPage - 1) / itemsPerPage;
        pagination.setPageCount(pageCount);
        pagination.setPageFactory(pageIndex->{
            int fromIndex = pageIndex * itemsPerPage;
            int toIndex = Math.min(fromIndex + itemsPerPage,aplikimObservableList.size());
            aplikimTableView.setItems(FXCollections.observableArrayList(aplikimObservableList.subList(fromIndex,toIndex)));
            aplikimTableView.setVisible(true);
            pagination.setVisible(true);
            return new Pane();
        });






    }

    public void addAplikimClick(ActionEvent event) {
        try {
            // Gather student information from input fields or other sources
            // int aplikimId = Integer.parseInt(AplikimIdField.getText());
            int studentId = Integer.parseInt(StudentIdField.getText());
            int bursaId = Integer.parseInt(BursaField.getText());
            int vitiStudimit = Integer.parseInt(VstudimeveField.getText());
            double notaMesatare = Double.parseDouble(NmesField.getText());


            CreateAplikimiDto aplikim = new CreateAplikimiDto(studentId, bursaId, vitiStudimit, notaMesatare);

            try {
                AdminAplikim insertedAplikim = AdminAplikimRepository.insert(aplikim);
                if (insertedAplikim != null) {
                    aplikimTableView.getItems().add(insertedAplikim);

                }
            } catch (SQLException e) {
                e.printStackTrace();

            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void updateAplikimClick(ActionEvent event) {
    }

    public void deleteAplikimClick(ActionEvent event) {
    }

    public void filterAplikimClick(ActionEvent event) {
    }
}
