package cs211.project.controllers;

import cs211.project.models.AccountList;
import cs211.project.models.Admin;
import cs211.project.repository.AccountRepository;
import cs211.project.services.NPBPRouter;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;


public class AdminPassController {

    private Admin admin;
    @FXML private MFXPasswordField oldPasswordField;
    @FXML private MFXPasswordField newPasswordField;
    @FXML private MFXPasswordField confirmPasswordField;
    @FXML private Label errorLabel;
    private AccountList accounts;
    private AccountRepository repository;

    public void initialize(){
        repository = new AccountRepository();
        accounts = repository.getAccounts();
        admin = (Admin) NPBPRouter.getDataAccount();
        errorLabel.setText("");
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
            File destinationDir = new File("images/Admin");
            String destinationFileName = admin.getUsername() + separator;

            // Create the destination file
            File destinationFile = new File(destinationDir, destinationFileName);

            // Copy the selected file to the destination
            try {
                Files.copy(file.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING,StandardCopyOption.COPY_ATTRIBUTES);
                accounts.changeImage(admin.getUsername(), "images/Admin/" + destinationFileName);
                repository.save(accounts);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void confirm() throws IOException {
        String oldPass = oldPasswordField.getText().trim();
        String newPass = newPasswordField.getText().trim();
        String conPass = confirmPasswordField.getText().trim();
        if (oldPass.equals("") || newPass.equals("") || conPass.equals("")) {
            errorLabel.setText("Please fill  is required");
            return ;
        }
        if (admin.validatePassword(oldPass)) {
            if(newPass.length() > 5) {
                if (newPass.equals(conPass)) {
                    accounts.changePassword(admin.getUsername(), newPass);
                    repository.save(accounts);
                    NPBPRouter.goTo("admin-sidebar",admin);
                } else
                    errorLabel.setText("Not matching password and confirm password");
                errorLabel.setLayoutX(170);
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
    public void refresh(){
        try {
            NPBPRouter.goTo("admin-sidebar",accounts.findUserByAccountId(admin.getAccountId()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
