package controller;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;


public class ClientController {
    public Label lblClientName;
    public JFXTextField txtType;
    public ScrollPane txtField;



    public void initialize(){
        lblClientName.setText(LoginFormController.name);
    }

    public void EmojiOnAction(MouseEvent mouseEvent) {
    }

    public void CamOnAction(MouseEvent mouseEvent) {
    }

    public void sendOnAction(ActionEvent actionEvent) {
    }
}

