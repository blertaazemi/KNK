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
import models.dto.UpdateAplikimDto;
import models.dto.UpdateStudentDto;
import repository.AdminAplikimRepository;
import repository.AdminStudentRepository;
import service.ConnectionUtil;
import service.PasswordHasher;

import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import static java.time.LocalDate.now;

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

   @FXML
   private Button filterAplikimBtn;

   @FXML
   private Button addAplikimBtn;

   @FXML
   private Button deleteAplikimBtn;
   @FXML
   private Button updateAplikimBtn;

   @FXML
   private Label AplikimIdLabel;
   @FXML
   private Label StudentIdLabel;
   @FXML
   private Label BursaIdLabel;
   @FXML
   private Label VitiStudimeveLabel;
   @FXML
   private Label NotaMesatareLabel;

   private ResourceBundle bundle;

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
        Locale locale = Locale.getDefault();
        ResourceBundle translate = ResourceBundle.getBundle("translations.content", locale);

    }
    private void translateElements() {
        filterAplikimBtn.setText(bundle.getString("filterAplikimButton"));
        deleteAplikimBtn.setText(bundle.getString("deleteAplikimButton"));
        updateAplikimBtn.setText(bundle.getString("updateAplikimButton"));
        addAplikimBtn.setText(bundle.getString("addAplikimButton"));
        AplikimIdLabel.setText(bundle.getString("AplikimIdLabel"));
        StudentIdLabel.setText(bundle.getString("StudentiIdLabel"));
        BursaIdLabel.setText(bundle.getString("BursaIdLabel"));
        VitiStudimeveLabel.setText(bundle.getString("VitiStudimitLabel"));
        NotaMesatareLabel.setText(bundle.getString("NotaMesatareLabel"));

    }

    public void translateEn(ActionEvent event){
        Locale.setDefault(new Locale("en"));
        bundle = ResourceBundle.getBundle("translations.content", Locale.getDefault());
        this.translateElements();
    }

    public void translateAl(ActionEvent event){
        Locale.setDefault(new Locale("sq"));
        bundle = ResourceBundle.getBundle("translations.content", Locale.getDefault());
        this.translateElements();
    }



    public void addAplikimClick(ActionEvent event) {
      try{
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

        int aplikimId = Integer.parseInt(AplikimIdField.getText());
        int studentId=Integer.parseInt(StudentIdField.getText());
        int bursaId = Integer.parseInt(BursaField.getText());
        int vitiStudimi = Integer.parseInt(VstudimeveField.getText());
        double NotaMesatare = Double.parseDouble(NmesField.getText());

        UpdateAplikimDto updatedAplikim=new UpdateAplikimDto(aplikimId,studentId,bursaId,vitiStudimi,NotaMesatare);

        AdminAplikim selectedAplikim = null;
        for (AdminAplikim aplikim : aplikimTableView.getItems()) {
            if (aplikim.getId() == aplikimId) {
                selectedAplikim = aplikim;
                break;
            }
        }

        if (selectedAplikim != null) {

            selectedAplikim.setId(aplikimId);
            selectedAplikim.setStudent_id(studentId);
            selectedAplikim.setBursa_id(bursaId);
            selectedAplikim.setViti_studimit(vitiStudimi);
            selectedAplikim.setNota_mesatare(NotaMesatare);




            try {

                boolean isUpdated = AdminAplikimRepository.updateAplikim(updatedAplikim);
                if (isUpdated) {

                    System.out.println("Application updated successfully.");

                    int index = aplikimTableView.getItems().indexOf(selectedAplikim);


                    aplikimTableView.getItems().set(index, selectedAplikim);
                    aplikimTableView.refresh();

                } else {

                    System.out.println("Failed to update application.");
                }
            } catch (SQLException e) {
                e.printStackTrace();

            }

        }
    }

    public void deleteAplikimClick(ActionEvent event) {
        AdminAplikim selectedAplikim = aplikimTableView.getSelectionModel().getSelectedItem();
        if (selectedAplikim != null) {
            CreateAplikimiDto aplikimToDelete = new CreateAplikimiDto(
                    selectedAplikim.getStudent_id(),
                    selectedAplikim.getBursa_id(),
                    selectedAplikim.getViti_studimit(),
                    selectedAplikim.getNota_mesatare()

            );

            try {
                boolean isDeleted = AdminAplikimRepository.deleteAplikim(aplikimToDelete);

                if (isDeleted) {

                    aplikimTableView.getItems().remove(selectedAplikim);

                    System.out.println("User with id '" + aplikimToDelete.getStudent_id()+ "' has been deleted successfully.");
                } else {
                    System.out.println("Failed to delete user with id '" + aplikimToDelete.getStudent_id() + "'.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No student is selected.");
        }
    }

    public void filterAplikimClick(ActionEvent event) {
        String studentIdText = StudentIdField.getText();
        String bursaIdText = BursaField.getText();

        // Validate and parse the input values
        int studentidFilter = 0;
        int bursaidFilter = 0;

        if (!studentIdText.isEmpty()) {
            try {
                studentidFilter = Integer.parseInt(studentIdText);
            } catch (NumberFormatException e) {

                return;
            }
        }

        if (!bursaIdText.isEmpty()) {
            try {
                bursaidFilter = Integer.parseInt(bursaIdText);
            } catch (NumberFormatException e) {

                return;
            }
        }

        Connection connection = null;
        try {
            connection = ConnectionUtil.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        CreateAplikimiDto aplikimiDto = new CreateAplikimiDto(studentidFilter, bursaidFilter, 0, 0);
        List<AdminAplikim> aplikimModelList = null;

        try {
            aplikimModelList = AdminAplikimRepository.filterTable(connection, aplikimiDto);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Update the table with the filtered data
        ObservableList<AdminAplikim> filteredList = FXCollections.observableList(aplikimModelList);
        aplikimTableView.setItems(filteredList);
    }
}
