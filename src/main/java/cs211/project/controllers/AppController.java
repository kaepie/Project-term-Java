package cs211.project.controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import cs211.project.services.NPBPRouter;
import java.io.IOException;

public class AppController {
    @FXML private AnchorPane pageArea;

    @FXML
    public void initialize(){
        try {
            NPBPRouter.loadPage("login", pageArea);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
