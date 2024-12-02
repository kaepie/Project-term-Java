package cs211.project.controllers;

import cs211.project.models.Account;
import cs211.project.models.AccountList;
import cs211.project.repository.AccountRepository;
import cs211.project.services.NPBPRouter;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements Initializable{
    @FXML  AnchorPane signUpArea;
    @FXML  MFXTextField userNameField;
    @FXML  MFXTextField nameField;
    @FXML  MFXPasswordField passwordField;
    @FXML  MFXPasswordField confirmPasswordField;
    @FXML  Label errorLabel;
    private AccountList accounts;
    private AccountRepository repository;

    public void initialize(URL location, ResourceBundle resources){
        repository = new AccountRepository();
        accounts = repository.getAccounts();
        confirmPasswordField.addEventFilter(KeyEvent.KEY_PRESSED, click -> {
            if (click.getCode() == KeyCode.ENTER) {
                onButtonRegister();
            }
        });
        errorLabel.setVisible(false);
    }
    public void onButtonRegister() {
        String name = nameField.getText();
        String username = userNameField.getText();
        String password = passwordField.getText();
        String confirmPassWord = confirmPasswordField.getText();
        if(!name.equals("") && !username.equals("") && !password.equals("") && !confirmPassWord.equals("")) {
            if (accounts.checkUserByUsername(username)) {
                if (password.length() > 5) {
                    if (Account.confirmPassword(password, confirmPassWord)) {
                        accounts.signUp(username, name, password);
                        errorLabel.setText("success");
                        repository.save(accounts);
                        try {
                            NPBPRouter.loadPage("login", signUpArea);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        errorLabel.setText("Please make sure your passwords match.");
                        errorLabel.setLayoutX(150);
                        errorLabel.setVisible(true);
                    }
                }
                else{
                    errorLabel.setText("Password must has more than 5 characters");
                    errorLabel.setLayoutX(140);
                    errorLabel.setVisible(true);
                }
            }
            else {
                errorLabel.setText("This username is already use.");
                errorLabel.setLayoutX(175);
                errorLabel.setVisible(true);
            }
        }
        else {
            errorLabel.setText("Please enter all information");
            errorLabel.setLayoutX(180);
            errorLabel.setVisible(true);
        }
    }
    public void clickBack(MouseEvent event) throws IOException {
        NPBPRouter.loadPage("login", signUpArea);
    }
}