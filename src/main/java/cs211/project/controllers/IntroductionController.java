package cs211.project.controllers;

import cs211.project.services.NPBPRouter;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class IntroductionController {
    @FXML private AnchorPane page;

    public void initialize(){
    }
    public void backToApp(){
        try {
            NPBPRouter.loadPage("login",page);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
