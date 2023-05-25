package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import models.Bursat;
import repository.BursatRepository;

import java.awt.event.ActionEvent;
import java.sql.SQLException;

public class updateBursaController {
    @FXML
    private AnchorPane Bursa;

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
    public Button ubBtn;
    public int Id;

    public String Name;

    public Double Nota_mesatare;

    public String Description;

    public Double Amount;



    public void setBursaInfo(int id, String name, Double nota_mesatare, String description, Double amount) {
        Id=id;
        Name = name;
        Nota_mesatare = nota_mesatare;
        Description = description;
        Amount = amount;


        this.id.setText
                (String.valueOf(Id));
        this.name.setText(Name);
        this.nota_mesatare.setText(String.valueOf(Nota_mesatare));
        this.description.setText(description);
        this.amount.setText(String.valueOf(Amount));

    }


    public void updateBursa(ActionEvent event){
        Bursat bursat = new Bursat(Integer.parseInt(id.getText()), name.getText(), Double.parseDouble(nota_mesatare.getText()), description.getText(), Double.parseDouble(amount.getText()));
        try {
            BursatRepository.updateBursa(bursat);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Bursa updated successfully");
    }




}
