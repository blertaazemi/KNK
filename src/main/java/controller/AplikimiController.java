package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import  javafx.scene.image.ImageView;
import org.w3c.dom.events.MouseEvent;

import java.io.File;
import java.nio.channels.FileChannel;

public class AplikimiController {
    private ImageView image;
    void showImage(MouseEvent event){
        image.setVisible(true);

    }
}
