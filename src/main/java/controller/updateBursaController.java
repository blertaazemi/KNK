package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import models.Bursat;
import repository.BursatRepository;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

public class updateBursaController {
    @FXML
    private AnchorPane Bursa;

    @FXML
    private SplitPane child;

    @FXML
    private AnchorPane sidebar;

    @FXML
    private Button homeButton2;

    @FXML
    private Button studentetButton;

    @FXML
    private Button bursa;

    @FXML
    private Button aplikimetbtn1;

    @FXML
    private AnchorPane updateBursa1;

    @FXML
    private TextField id;

    @FXML
    private TextField name;

    @FXML
    private TextField nota_mesatare;

    @FXML
    private TextArea description;

    @FXML
    private TextField amount;

    @FXML
    private Button ubBtn;

    public int Id;
    public String Name;
    public Double Nota_mesatare;
    public String Description;
    public Double Amount;
    @FXML
    private Button translateAL;
    @FXML
    private Button translateEN;

    private ResourceBundle bundle;
    private Connection connection;


    private void translateElements() {

        Locale locale = Locale.getDefault();
        ResourceBundle translate = ResourceBundle.getBundle("translations.content", locale);

        homeButton2.setText(bundle.getString("homeButton2"));
        studentetButton.setText(bundle.getString("studentetButton"));
        bursa.setText(bundle.getString("bursabtn"));
        aplikimetbtn1.setText(bundle.getString("aplikimetbtn1"));
        id.setText(bundle.getString("id"));
        name.setText(bundle.getString("name"));
        nota_mesatare.setText(bundle.getString("nota_mesatare"));
        description.setText(bundle.getString("description"));
        amount.setText(bundle.getString("amount"));
        ubBtn.setText(bundle.getString("ubBtn"));


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


    public void setBursaInfo(int id, String name, Double nota_mesatare, String description, Double amount) {
        Id = id;
        Name = name;
        Nota_mesatare = nota_mesatare;
        Description = description;
        Amount = amount;

        this.id.setText(String.valueOf(Id));
        this.name.setText(Name);
        this.nota_mesatare.setText(String.valueOf(Nota_mesatare));
        this.description.setText(description);
        this.amount.setText(String.valueOf(Amount));
    }

    public void updateBursa(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("bursat.fxml"));
        Bursat bursat = new Bursat(Integer.parseInt(id.getText()), name.getText(), Double.parseDouble(nota_mesatare.getText()), description.getText(), Double.parseDouble(amount.getText()));
        try {
            BursatRepository.updateBursa(bursat);
            System.out.println("Bursa updated successfully");

            // Retrieve the current stage
            Stage currentStage = (Stage) ubBtn.getScene().getWindow();

            // Load the new scene
            Parent root = loader.load();

            // Create a new stage for the new scene
            Stage newStage = new Stage();
            newStage.setScene(new Scene(root));

            // Close the current stage and show the new stage
            currentStage.close();
            newStage.show();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
