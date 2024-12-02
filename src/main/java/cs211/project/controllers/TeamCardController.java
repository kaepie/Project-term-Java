package cs211.project.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class TeamCardController {
    @FXML private Label teamNameLabel;
    @FXML private Label countMemberLabel;
    @FXML private Label maxMemberLabel;

    public Label getTeamNameLabel() {
        return teamNameLabel;
    }

    public void setTeamNameLabel(Label teamNameLabel) {
        this.teamNameLabel = teamNameLabel;
    }

    public Label getCountMemberLabel() {
        return countMemberLabel;
    }

    public void setCountMemberLabel(Label countMemberLabel) {
        this.countMemberLabel = countMemberLabel;
    }

    public Label getMaxMemberLabel() {
        return maxMemberLabel;
    }

    public void setMaxMemberLabel(Label maxMemberLabel) {
        this.maxMemberLabel = maxMemberLabel;
    }
}
