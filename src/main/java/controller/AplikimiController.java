package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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

import java.io.IOException;
import java.sql.SQLException;

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

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void handleOpenFile(ActionEvent actionEvent) {

    }

    public void showImage(javafx.scene.input.MouseEvent mouseEvent) {

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




