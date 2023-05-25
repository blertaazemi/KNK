package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import repository.BursatRepository;
import service.ConnectionUtil;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;


import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import models.Bursat;



public class BursatController implements Initializable {


    @FXML
    private AnchorPane deshira;

    @FXML
    private SplitPane child;

    @FXML
    private AnchorPane sidebar;

    @FXML
    private Button homeButton;

    @FXML
    private Button studentetButton;

    @FXML
    private Button bursa;

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




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bursatTableView.getColumns().addAll(col_id, col_name, col_nota_mesatare, col_description, col_amount);

        try {
            Connection connection = ConnectionUtil.getConnection();
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


