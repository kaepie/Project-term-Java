package cs211.project.controllers;

import cs211.project.models.*;
import cs211.project.repository.AccountRepository;
import cs211.project.services.NPBPKeyPress;
import cs211.project.services.NPBPRouter;
import io.github.palexdev.materialfx.controls.MFXIconWrapper;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable{

    @FXML  AnchorPane loginArea; // right pane
    @FXML  MFXTextField userNameField;
    @FXML MFXPasswordField passwordField;
    @FXML Label errorLabel;
    @FXML Button loginButton;
    private AccountList accounts;
    private AccountRepository repository;


    public void initialize(URL location, ResourceBundle resources) {
        repository = new AccountRepository();
        accounts = repository.getAccounts();
        errorLabel.setVisible(false);
        userNameField.setLeadingIcon(new MFXIconWrapper("fas-user", 16, 16));
        passwordField.setLeadingIcon(new MFXIconWrapper("fas-lock", 16, 16));
        passwordField.addEventFilter(KeyEvent.KEY_PRESSED, click -> {
            if (click.getCode() == KeyCode.ENTER) {
                try {
                    clickLogIn();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        NPBPKeyPress.EscPress(userNameField);

        userNameField.textProperty().addListener((observableValue, old, New) -> {
            if(New != null) {
                errorLabel.setVisible(false);
            }
        });

        passwordField.textProperty().addListener(((observableValue, old, New) -> {
            if(New != null){
                errorLabel.setVisible(false);
            }
        }));
    }

    public void clickSignIn() throws IOException {
        NPBPRouter.loadPage("signup", loginArea);
    }
    public void goToCreator() throws IOException {
        NPBPRouter.loadPage("creator", loginArea);
    }
    public void goToIntro() throws IOException {
        NPBPRouter.loadPage("Introduction",loginArea);
    }

    public void clickLogIn() throws IOException {
        String username = userNameField.getText();
        String password = passwordField.getText();
        Account exist = accounts.findUserByUsername(username);
        if(exist != null){
            if(accounts.login(username, password)){
                if (exist instanceof User) {
                    repository.save(accounts);
                    NPBPRouter.setCss("CSS/theme-"+exist.getAccountTheme()+".css");
                    NPBPRouter.goTo("home", exist);
                } else if (exist instanceof Admin) {
                    repository.save(accounts);
                    NPBPRouter.setCss("CSS/theme-"+exist.getAccountTheme()+".css");
                    NPBPRouter.goTo("admin-sidebar", exist);
                }
            }
            else{
                errorLabel.setText("wrong username or password");
                errorLabel.setVisible(true);
            }
        }
        else{
            errorLabel.setText("wrong username or password");
            errorLabel.setVisible(true);
        }

    }

}
