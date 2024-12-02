package cs211.project.controllers;

import cs211.project.services.NPBPRouter;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.IOException;

public class CreatorController {
    @FXML private AnchorPane page;
    @FXML private Circle bossCir;
    @FXML private Circle nonCir;
    @FXML private Circle palmCir;
    @FXML private Circle peakCir;
    public void initialize(){
        bossCir.setFill(new ImagePattern(new Image("file:"+"images/Creator/Boss.png")));
        nonCir.setFill(new ImagePattern(new Image("file:"+"images/Creator/Non.png")));
        palmCir.setFill(new ImagePattern(new Image("file:"+"images/Creator/Palm.png")));
        peakCir.setFill(new ImagePattern(new Image("file:"+"images/Creator/Peak.png")));
    }
    public void backToApp(){
        try {
            NPBPRouter.loadPage("login",page);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
