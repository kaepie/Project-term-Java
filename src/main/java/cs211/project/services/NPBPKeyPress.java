package cs211.project.services;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class NPBPKeyPress {

    public static void EscPress(Control control){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Scene scene = control.getScene();
                Stage stage = (Stage) control.getScene().getWindow();
                scene.addEventFilter(KeyEvent.KEY_PRESSED, click -> {
                    if(click.getCode() == KeyCode.ESCAPE){
                        stage.close();
                    }
                });
            }
        });
    }
}
