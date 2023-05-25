package controller;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import repository.BursatRepository;
import service.ConnectionUtil;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import models.Bursat;

import static service.ConnectionUtil.getConnection;


public class BursatController implements Initializable {


    @FXML
    private AnchorPane deshira;

    @FXML
    private SplitPane child;

    @FXML
    private AnchorPane sidebar;

    @FXML
    private Button homeButton1;

    @FXML
    private Button studentetButton;

    @FXML
    private Button bursabtn;

    @FXML
    private Button aplikimetbtn;

    @FXML
    private AnchorPane bursat;


    @FXML
    private TableView<Bursat> bursatTableView;
    @FXML
    private TableColumn<Bursat, Integer> col_id;

    @FXML
    private TableColumn<Bursat, String> col_name;

    @FXML
    private TableColumn<Bursat, Double> col_nota_mesatare;

    @FXML
    private TableColumn<Bursat, String> col_description;

    @FXML
    private TableColumn<Bursat, Double> col_amount;

    @FXML
    private Button translateAL;
    @FXML
    private Button translateEN;

    private ResourceBundle bundle;
    private Connection connection;


    private void translateElements() {

        Locale locale = Locale.getDefault();
        ResourceBundle translate = ResourceBundle.getBundle("translations.content", locale);

        homeButton1.setText(bundle.getString("homeButton1"));
        studentetButton.setText(bundle.getString("studentetButton"));
        bursabtn.setText(bundle.getString("bursabtn"));
        aplikimetbtn.setText(bundle.getString("aplikimetbtn"));
        col_id.setText(bundle.getString("col_id"));
        col_name.setText(bundle.getString("col_name"));
        col_nota_mesatare.setText(bundle.getString("col_nota_mesatare"));
        col_description.setText(bundle.getString("col_description"));
        col_amount.setText(bundle.getString("col_amount"));

    }


    public void translateEn(ActionEvent event) {
        Locale.setDefault(new Locale("en"));
        bundle = ResourceBundle.getBundle("translations.content", Locale.getDefault());
        this.translateElements();
    }

    public void translateAl(ActionEvent event) {
        Locale.setDefault(new Locale("sq"));
        bundle = ResourceBundle.getBundle("translations.content", Locale.getDefault());
        this.translateElements();
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Set the cell value factories for other columns
        col_id.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        col_name.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getname()));
        col_nota_mesatare.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getnota_mesatare()).asObject());
        col_description.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getdescription()));
        col_amount.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getamount()).asObject());

        // Create the "Actions" column
        TableColumn<Bursat, Void> colActions = new TableColumn<>("Actions");
        colActions.setPrefWidth(100);

        colActions.setCellFactory(param -> new TableCell<Bursat, Void>() {
            private final Button deleteButton = new Button("Delete");
            private final Button updateButton = new Button("Update");
            private final HBox buttonContainer = new HBox(deleteButton, updateButton);

            {
                deleteButton.setOnAction(event -> {
                    Bursat bursa = getTableRow().getItem();
                    // Handle delete button action for the specific Bursa object
                    try {
                        BursatRepository.deleteBursa(bursa.getId());
                        // Refresh the table data
                        bursatTableView.getItems().remove(bursa);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });

                updateButton.setOnAction(event -> {

                    // Handle update button action for the specific Bursa object
                    Bursat model = getTableRow().getItem();
                    if (model != null) {
                        try {
                            FXMLLoader fxmlLoader = new FXMLLoader(updateBursaController.class.getResource("updateBursa.fxml"));
                            Pane pane = fxmlLoader.load();
                            updateBursaController updatebursaController = fxmlLoader.getController();
                            updatebursaController.setBursaInfo(model.getId(), model.getname(), model.getnota_mesatare(), model.getdescription(), model.getamount());
                            Scene scene = new Scene(pane);
                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(scene);
                            stage.show();
                        } catch (IOException e) {
                            System.err.println("Error loading FXML file: " + e.getMessage());
                        }
                        System.out.println("Button clicked for item:");
                    }
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(buttonContainer);
                }
            }
        });

        // Add the "Actions" column to the table
        bursatTableView.getColumns().add(colActions);

        try {
            Connection connection = getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM tbl_bursa");

            ObservableList<Bursat> bursatList = FXCollections.observableArrayList();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double notaMesatare = resultSet.getDouble("nota_mesatare");
                String description = resultSet.getString("description");
                double amount = resultSet.getDouble("amount");

                Bursat bursa = new Bursat(id, name, notaMesatare, description, amount);
                bursatList.add(bursa);
            }

            bursatTableView.setItems(bursatList);

            resultSet.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




}


