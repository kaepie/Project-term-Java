package cs211.project.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;

public class StaffController {

    @FXML
    private Circle imgCircle;

    @FXML
    private Label roleLabel;

    @FXML
    private Label statusLabel;

    @FXML
    private Label usernameLabel;


    public void setUsernameLabel(Label username){
        this.usernameLabel = username;
    }

    public Circle getImgCircle() { return imgCircle; }

    public Label getRoleLabel() {
        return roleLabel;
    }

    public Label getStatusLabel() {
        return statusLabel;
    }

    public Label getUsernameLabel() {
        return usernameLabel;
    }
}
