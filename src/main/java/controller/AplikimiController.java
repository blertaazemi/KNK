package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import models.Student;
import models.dto.CreateAplikimiDto;
import repository.AplikimiRepository;
import service.ConnectionUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

public class AplikimiController {

    @FXML
    private Button btnOpenFile;

    @FXML
    private TextArea textArea;

    @FXML
    private TextField EmriTextField;

    @FXML
    private TextField MbiemriTextField;

    @FXML
    private TextField idTextField;

    @FXML
    private TextField NotaMesatareTextField;

    @FXML
    private TextField VitiStudimiTextField;

    @FXML
    private RadioButton STEM;

    @FXML
    private RadioButton Universitare;

    @FXML
    private Label idid;

    @FXML
    private Label emriid;

    @FXML
    private Label mbiemriid;

    @FXML
    private Label notamesatareid;

    @FXML
    private Label vitistudimitid;


    @FXML
    private Label zgjedhbursenid;
    @FXML
    private Button translateAL;
    @FXML
    private Button translateEN;

    @FXML
    private Button aplikoid;

    private ResourceBundle bundle;


    private Stage stage;
    private Connection connection;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void handleOpenFile(ActionEvent actionEvent) {

    }

    public void showImage(javafx.scene.input.MouseEvent mouseEvent) {

    }




    private void translateElements() {

        Locale locale = Locale.getDefault();
        ResourceBundle translate = ResourceBundle.getBundle("translations.content", locale);

        idid.setText(bundle.getString("idid"));
        emriid.setText(bundle.getString("emriid"));
        mbiemriid.setText(bundle.getString("mbiemriid"));
        notamesatareid.setText(bundle.getString("notamesatareid"));
        zgjedhbursenid.setText(bundle.getString("zgjedhbursenid"));
        vitistudimitid.setText(bundle.getString("vitistudimitid"));
        idTextField.setText(bundle.getString("idTextField"));
        EmriTextField.setText(bundle.getString("EmriTextField"));
        MbiemriTextField.setText(bundle.getString("MbiemriTextField"));
        NotaMesatareTextField.setText(bundle.getString("NotaMesatareTextField"));
        Universitare.setText(bundle.getString("Universitare"));
        VitiStudimiTextField.setText(bundle.getString("VitiStudimiTextField"));
        aplikoid.setText(bundle.getString("aplikoid"));


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



    @FXML
    void register(ActionEvent event) {
        alertMessage alert = new alertMessage();
        double notaMesatare = Double.parseDouble(NotaMesatareTextField.getText());
        int vitiStd = Integer.parseInt(VitiStudimiTextField.getText());
        try {
            if (EmriTextField.getText().isEmpty() || MbiemriTextField.getText().isEmpty() ||
                    idTextField.getText().isEmpty() || NotaMesatareTextField.getText().isEmpty()) {
                alert.errorMessage("Duhet ti plotesoni te gjitha fushat!!");
                return;
            }
            else if(notaMesatare>10.00 || notaMesatare<8.00){
                alert.errorMessage("Nuk mund te aplikoni nese nota mesatare nuk eshte mes 8.00 dhe 10.00");

            }else if (vitiStd<1 || vitiStd>3) {
                alert.errorMessage("Viti i gabuar i studimeve");
            } else {
                String student_id = idTextField.getText();
                int bursa_id = 1;
                if (STEM.isSelected()) {
                    bursa_id = 2;
                }
                if (Universitare.isSelected()) {
                    bursa_id = 1;
                }

                String nota_mesatare = NotaMesatareTextField.getText();
                String viti_studimit = VitiStudimiTextField.getText();

                CreateAplikimiDto createAplikimiDto = new CreateAplikimiDto(Integer.parseInt(student_id), bursa_id, Integer.parseInt(viti_studimit), Double.parseDouble(nota_mesatare));
                AplikimiRepository aplikimiRepository = new AplikimiRepository();
                aplikimiRepository.createapplication(createAplikimiDto);

            }
        } catch (SQLException e) {
            // Handle the SQLException here
            System.out.println("Error occurred while creating the application: " + e.getMessage());
        } catch (NumberFormatException e) {
            // Handle the NumberFormatException here
            System.out.println("Invalid input format: " + e.getMessage());
        } catch (Exception e) {
            // Handle other exceptions here
            System.out.println("An error occurred: " + e.getMessage());
        }


    }
    @FXML
    private void navigateToStudenti(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(Studenti.class.getResource("studenti.fxml"));
        AnchorPane studentiPane = loader.load();

        Scene scene = new Scene(studentiPane, 650, 564);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }



    }




