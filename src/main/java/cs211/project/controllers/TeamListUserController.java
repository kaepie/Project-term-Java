package cs211.project.controllers;

import cs211.project.models.Event;
import cs211.project.models.Team;
import cs211.project.models.TeamList;
import cs211.project.models.User;
import cs211.project.pivot.EventTeamList;
import cs211.project.pivot.TeamAccountList;
import cs211.project.repository.EventTeamRepository;
import cs211.project.repository.TeamAccountRepository;
import cs211.project.repository.TeamRepository;
import cs211.project.services.NPBPRouter;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TeamListUserController implements Initializable {
    @FXML
    private VBox vbox;
    @FXML private AnchorPane page;
    private TeamRepository teamRepository;
    private EventTeamRepository eventTeamRepository;
    private User user;
    private Event event;
    private Team team;
    private TeamList teamlist;
    private TeamAccountRepository teamAccountRepository;
    private TeamAccountList teamAccountList;
    private EventTeamList eventTeamList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        user = (User) NPBPRouter.getDataAccount();
        event = (Event) NPBPRouter.getDataEvent();
        team = (Team) NPBPRouter.getDataTeam();

        teamRepository = new TeamRepository();
        teamlist = teamRepository.getTeamList();

        teamAccountRepository = new TeamAccountRepository();
        teamAccountList = teamAccountRepository.getTeamAccountList();
        ArrayList<Integer> checkTeam = teamAccountList.findAllTeamsByAccount(user.getAccountId());//id ของทีมทั้งหมดที่ user อยู่

        eventTeamRepository = new EventTeamRepository();
        eventTeamList = eventTeamRepository.getEventTeamList();
        ArrayList<Integer> teamId = eventTeamList.findTeamByEventId(event.getEventId());

        ArrayList<Integer> listTeamId = eventTeamList.checkEventIdInEventId(teamId, checkTeam);

        for(var i : listTeamId){
            vbox.getChildren().add(createCard(i));
        }
    }

    public HBox createCard(int id){
        File file = new File("src/main/resources/cs211/project/views/team-card.fxml");
        URL url = null;
        try {
            url = file.toURL();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        FXMLLoader fxmlLoader = new FXMLLoader(url);

        HBox hbox = null;
        try {
            hbox = (HBox) fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        TeamCardController teamCardController = (TeamCardController) fxmlLoader.getController();
        Label teamNameLabel = teamCardController.getTeamNameLabel();
        Label countMemberLabel = teamCardController.getCountMemberLabel();
        Label maxMemberLabel = teamCardController.getMaxMemberLabel();

        teamNameLabel.setText(teamlist.findTeamById(id).getTeamName());
        countMemberLabel.setText(""+teamlist.findTeamById(id).getCountMember());
        maxMemberLabel.setText(""+teamlist.findTeamById(id).getMaxMember());

        hbox.setOnMouseClicked(click ->{
            try {
                NPBPRouter.loadPageSet("team-detail",page,user,event.getEventId(),teamlist.findTeamById(id),0);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        return hbox;
    }

    public void backButton(){
        try {
            NPBPRouter.loadPage("my-event",page);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
