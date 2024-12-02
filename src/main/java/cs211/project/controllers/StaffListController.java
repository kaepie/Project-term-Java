package cs211.project.controllers;

import cs211.project.models.*;
import cs211.project.pivot.TeamAccount;
import cs211.project.pivot.TeamAccountList;
import cs211.project.repository.AccountRepository;
import cs211.project.repository.TeamAccountRepository;
import cs211.project.repository.TeamRepository;
import cs211.project.services.NPBPRouter;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class StaffListController implements  Initializable{
    @FXML private VBox vbox1;
    @FXML private VBox vbox2;
    @FXML private VBox vbox3;
    @FXML private AnchorPane page;
    @FXML
    private Button banButton;

    @FXML
    private Button demoteButton;

    @FXML
    private Button promoteButton;

    @FXML
    private Button unbanButton;
    private User user;
    private Team team;
    private TeamRepository teamRepository;
    private AccountRepository accountRepository;
    private Account account;
    private AccountList accountList;
    private TeamAccount teamAccount;
    private TeamAccountList teamAccountList;
    private TeamAccountRepository teamAccountRepository;
    private ArrayList<Integer> listId;
    private TeamList teamList;
    private Account selectStaff;
    private VBox selectBox;

    public void initialize(URL url, ResourceBundle resourceBundle){
        user = (User) NPBPRouter.getDataAccount();
        int set = NPBPRouter.getData();
        if(set==0){
            promoteButton.setVisible(false);
            demoteButton.setVisible(false);
            banButton.setVisible(false);
            unbanButton.setVisible(false);
        }

        int teamId = (int) NPBPRouter.getDataTeam();
        accountRepository = new AccountRepository();
        accountList = accountRepository.getAccounts();

        teamRepository = new TeamRepository();
        teamList = teamRepository.getTeamList();
        team = teamList.findTeamById(teamId);

        teamAccountRepository = new TeamAccountRepository();
        teamAccountList = teamAccountRepository.getTeamAccountList();
        listId = new ArrayList<>();
        listId.addAll(teamAccountList.findAllAccountsByTeam(teamId));

        int count=0;

        for(Integer id : listId){
            if(count%3==0){vbox1.getChildren().add(createCard(id));}
            if(count%3==1){vbox2.getChildren().add(createCard(id));}
            if(count%3==2){vbox3.getChildren().add(createCard(id));}
            count++;
        }
    }

    public VBox createCard(int id) {
        File file = new File("src/main/resources/cs211/project/views/staff-card.fxml");
        URL url = null;
        try {
            url = file.toURL();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        FXMLLoader fxmlLoader = new FXMLLoader(url);

        VBox vbox = null;
        try {
            vbox = (VBox) fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        StaffController staffController = (StaffController) fxmlLoader.getController();
        Label usernameLabel = staffController.getUsernameLabel();
        Label statusLabel = staffController.getStatusLabel();
        Label roleLabel = staffController.getRoleLabel();
        Circle circle = staffController.getImgCircle();

        account = accountList.findUserByAccountId(id);
        teamAccount = teamAccountList.findAccountInTeam(id, team.getTeamId());
        usernameLabel.setText(account.getUsername());
        roleLabel.setText(teamAccount.getRole());
        statusLabel.setText(teamAccount.getStatus());
        circle.setFill(new ImagePattern(new Image("file:" + account.getImagePath())));

        VBox finalVbox = vbox;
        vbox.setOnMouseClicked(event -> {
            selectStaff = (User)accountList.findUserByAccountId(id);
            selectBox = finalVbox;
        });

        return vbox;
    }

    public void promote(){
        teamAccountList.promote(selectStaff.getAccountId(),team.getTeamId());
        teamAccountRepository.save(teamAccountList);
        teamAccount = teamAccountList.findAccountInTeam(selectStaff.getAccountId(),team.getTeamId());
        refresh(teamAccount);
    }
    public void demote(){
        teamAccountList.demote(selectStaff.getAccountId(),team.getTeamId());
        teamAccountRepository.save(teamAccountList);
        teamAccount = teamAccountList.findAccountInTeam(selectStaff.getAccountId(),team.getTeamId());
        refresh(teamAccount);
    }
    public void ban(){
        teamAccountList.ban(selectStaff.getAccountId(),team.getTeamId());
        teamAccountRepository.save(teamAccountList);
        teamAccount = teamAccountList.findAccountInTeam(selectStaff.getAccountId(),team.getTeamId());
        refresh(teamAccount);
    }
    public void unBan(){
        teamAccountList.unBan(selectStaff.getAccountId(),team.getTeamId());
        teamAccountRepository.save(teamAccountList);
        teamAccount = teamAccountList.findAccountInTeam(selectStaff.getAccountId(),team.getTeamId());
        refresh(teamAccount);
    }

    public void refresh(TeamAccount teamAccount) {
        Label username = (Label) selectBox.getChildren().get(3);
        Label role = (Label) selectBox.getChildren().get(4);
        Label status = (Label) selectBox.getChildren().get(5);

        username.setText(selectStaff.getUsername());
        role.setText(teamAccount.getRole());
        status.setText(teamAccount.getStatus());
        selectBox.setFocusTraversable(true);
    }

    public void backToTeamDetail(){
        try {
            NPBPRouter.loadPage("team-detail",page,user);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}