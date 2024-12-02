package cs211.project.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;

public class EventController {

    @FXML
    private Label countMemberLabel;

    @FXML
    private Circle imgCircle;

    @FXML
    private Label maxMemberLabel;

    @FXML
    private Label nameEventLabel;

    public Label getCountMemberLabel() {
        return countMemberLabel;
    }

    public Circle getImgCircle() {
        return imgCircle;
    }

    public Label getMaxMemberLabel() {
        return maxMemberLabel;
    }

    public Label getNameEventLabel() {
        return nameEventLabel;
    }

    public void setCountMemberLabel(Label countMemberLabel) {
        this.countMemberLabel = countMemberLabel;
    }

    public void setMaxMemberLabel(Label maxMemberLabel) {
        this.maxMemberLabel = maxMemberLabel;
    }

}
