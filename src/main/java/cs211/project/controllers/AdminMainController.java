package cs211.project.controllers;

import cs211.project.models.Account;
import cs211.project.models.AccountList;
import cs211.project.models.Admin;
import cs211.project.repository.AccountRepository;
import cs211.project.services.AccountTimeComparator;
import cs211.project.services.NPBPRouter;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class AdminMainController implements Initializable {
    @FXML MFXTableView mfxTableView;
    @FXML Label nameLabel;
    @FXML Label userNameLabel;
    @FXML Label timeLabel;
    @FXML Circle image;
    private AccountList accountList;
    private AccountRepository accountRepository;
    private ArrayList<Account> accountArrayList;
    private Admin admin;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        admin = (Admin) NPBPRouter.getDataAccount();
        accountRepository = new AccountRepository();
        accountList = accountRepository.getAccounts();
        accountArrayList = accountList.getAccounts();
        accountArrayList.removeIf(account -> account.getRoleAccount().equals("Admin"));
        admin.sort(accountArrayList,new AccountTimeComparator());
        setData(accountArrayList.get(0));
        setTable(accountArrayList, mfxTableView);
    }
    private void setTable(ArrayList<Account> accounts, MFXTableView<Account> tableView) {
        MFXTableColumn<Account> nameColumn = new MFXTableColumn<>("Name",true);
        MFXTableColumn<Account> userNameColumn = new MFXTableColumn<>("Username",true);
        MFXTableColumn<Account> date = new MFXTableColumn<>("Date");


        nameColumn.setRowCellFactory(account -> {
            MFXTableRowCell mfxTableRowCell = new MFXTableRowCell<>(Account::getName);
            mfxTableRowCell.setAlignment(Pos.CENTER);
            return mfxTableRowCell;
        });
        userNameColumn.setRowCellFactory(account -> {
            MFXTableRowCell mfxTableRowCell = new MFXTableRowCell<>(Account::getUsername);
            mfxTableRowCell.setAlignment(Pos.CENTER);
            return mfxTableRowCell;
        });
        date.setRowCellFactory(account -> {
            MFXTableRowCell mfxTableRowCell = new MFXTableRowCell<>(Account::getTime);
            mfxTableRowCell.setAlignment(Pos.CENTER);
            return mfxTableRowCell;
        });
        nameColumn.setAlignment(Pos.CENTER);
        userNameColumn.setAlignment(Pos.CENTER);
        date.setPrefSize(150,10);
        date.setAlignment(Pos.CENTER);
        tableView.getTableColumns().clear();
        tableView.getTableColumns().addAll(userNameColumn , nameColumn , date);

        for(Account account: accounts){
            tableView.getItems().add(account);
        }
        tableView.getSelectionModel().selectionProperty().addListener((observableValue, Old, New) -> {
            if(New != null){
                setData(tableView.getSelectionModel().getSelectedValue());
            }
        });
    }
    private void setData(Account account){
        nameLabel.setText(account.getName());
        userNameLabel.setText(account.getUsername());
        timeLabel.setText(account.getTime());
        image.setFill(new ImagePattern(new Image("file:" + account.getImagePath())));
    }
}


