package cs211.project.services;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class NPBPAnimation {

    public static void scaleTransition(Pane pane,double scale){
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(250), pane);
        scaleTransition.setToX(scale);
        scaleTransition.setToY(scale);
        scaleTransition.play();
    }

    public static void reverseScale(Pane pane) {
        ScaleTransition scale = new ScaleTransition(Duration.millis(250), pane);
        scale.setToX(1);
        scale.setToY(1);
        scale.play();
    }

}
