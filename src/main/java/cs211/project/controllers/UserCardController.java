package cs211.project.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;

public class UserCardController {
    @FXML private Circle imgCircle;
    @FXML private Label usernameLabel;
    @FXML private Label statusLabel;

    public Circle getImgCircle() {
        return imgCircle;
    }
    public Label getUsernameLabel() {
        return usernameLabel;
    }
    public Label getStatusLabel(){
        return statusLabel;
    }

    public void setUsernameLabel(Label usernameLabel) {
        this.usernameLabel = usernameLabel;
    }
}
