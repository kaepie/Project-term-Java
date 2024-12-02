package cs211.project.controllers;

import cs211.project.models.AccountList;
import cs211.project.models.User;
import cs211.project.repository.AccountRepository;
import cs211.project.services.NPBPRouter;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class SettingController {

    @FXML private TextField oldPasswordField;
    @FXML private TextField newPasswordField;
    @FXML private TextField confirmPasswordField;
    @FXML private Label errorLabel;
    @FXML private MFXButton themeButton;
    private User user;
    private AccountList accounts;
    private AccountRepository repository;

    public void initialize(){
        repository = new AccountRepository();
        accounts = repository.getAccounts();
        user = (User) NPBPRouter.getDataAccount();
        errorLabel.setText("");
        themeButton.setText("Theme "+user.getAccountTheme());
    }

    @FXML
    public void chooseImageButton(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Image");

        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("images", "*.png", "*.jpg", "*.jpeg","*gif"));

        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            String name =file.getName();
            String separator = name.substring(name.lastIndexOf('.'), name.length());
            // Define the destination directory and file name where you want to save the selected file
            File destinationDir = new File("images/User");
            String destinationFileName = user.getUsername() + separator;

            // Create the destination file
            File destinationFile = new File(destinationDir, destinationFileName);

            // Copy the selected file to the destination
            try {
                Files.copy(file.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING,StandardCopyOption.COPY_ATTRIBUTES);
                accounts.changeImage(user.getUsername(), "images/User/" + destinationFileName);
                repository.save(accounts);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void refresh(){
        try {
            NPBPRouter.goTo("home",accounts.findUserByAccountId(user.getAccountId()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void changeTheme() throws IOException {
        accounts.changeTheme(user.getAccountId());
        repository.save(accounts);
        NPBPRouter.setCss("CSS/theme-"+accounts.findUserByAccountId(user.getAccountId()).getAccountTheme()+".css");
        if(accounts.findUserByAccountId(user.getAccountId()).getAccountTheme() == 1)
            themeButton.setText("Theme "+ 1);
        else{
            themeButton.setText("Theme "+ 2);
        }
    }

    public void confirm() throws IOException {
        String oldPass = oldPasswordField.getText().trim();
        String newPass = newPasswordField.getText().trim();
        String conPass = confirmPasswordField.getText().trim();
        if (oldPass.equals("") || newPass.equals("") || conPass.equals("")) {
            errorLabel.setText("Please fill  is required");
            errorLabel.setLayoutX(230);
            return ;
        }
            if (user.validatePassword(oldPass)) {
                if(newPass.length() > 5) {
                    if (newPass.equals(conPass)) {
                        accounts.changePassword(user.getUsername(), newPass);
                        repository.save(accounts);
                        System.out.println("Change password successfully");
                        NPBPRouter.goTo("home", user);
                    } else {
                        errorLabel.setText("Not matching password and confirm password");
                        errorLabel.setLayoutX(170);
                    }
                }
                else{
                    errorLabel.setText("Password must has more than 5 characters");
                    errorLabel.setLayoutX(170);
                }
            }
            else
            {
                errorLabel.setText("Please fill the correct old password");
                errorLabel.setLayoutX(180);
            }
    }
}
