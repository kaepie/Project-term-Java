package cs211.project.controllers;

import cs211.project.models.Admin;
import cs211.project.services.NPBPRouter;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminHomeController implements Initializable {
    @FXML AnchorPane page;
    @FXML Circle image;
    @FXML Label usernameLabel;
    private Admin admin;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        admin = (Admin) NPBPRouter.getDataAccount();
        usernameLabel.setText(admin.getUsername());
        image.setFill(new ImagePattern(new Image("file:" + admin.getImagePath())));
        try {
            NPBPRouter.loadPage("admin-main",page,admin);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void onHomeButton(){
        try {
            NPBPRouter.loadPage("admin-main",page,admin);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void onSettingButton(){
        try {
            NPBPRouter.loadPage("admin-pass",page,admin);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void onLogout(){
        try {
            NPBPRouter.goTo("app");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
