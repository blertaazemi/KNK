package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class AplikimiController {

    @FXML
    private Button btnOpenFile;

    @FXML
    private TextArea textArea;

    @FXML
    private void chooseFileClicked(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");

        Stage stage = (Stage) btnOpenFile.getScene().getWindow();
        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            // File selected, do something with it
            textArea.setText("Selected file: " + file.getAbsolutePath());
        }
    }

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void handleOpenFile(ActionEvent actionEvent) {

    }

    public void showImage(javafx.scene.input.MouseEvent mouseEvent) {

    }
}
