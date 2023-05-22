package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
<<<<<<< Updated upstream
import java.io.File;
=======
import models.Student;
import models.dto.CreateAplikimiDto;
import repository.AplikimiRepository;
>>>>>>> Stashed changes

import java.sql.SQLException;

public class AplikimiController {

    @FXML
    private Button btnOpenFile;

    @FXML
    private TextArea textArea;

    @FXML
<<<<<<< Updated upstream

    private void chooseFileClicked(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open PDF File");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("PDF Files", "*.pdf")
        );
=======
    private TextField EmriTextField;
>>>>>>> Stashed changes

    @FXML
    private TextField MbiemriTextField;

<<<<<<< Updated upstream
        if (file != null) {
            // File selected, do something with it
            textArea.setText("Selected PDF file: " + file.getAbsolutePath());
        }
    }
=======
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
>>>>>>> Stashed changes


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
        try {
            if (EmriTextField.getText().isEmpty() || MbiemriTextField.getText().isEmpty() ||
                    idTextField.getText().isEmpty() || NotaMesatareTextField.getText().isEmpty()) {
                alert.errorMessage("Duhet ti plotesoni te gjitha fushat!!");
                return;
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

    }
