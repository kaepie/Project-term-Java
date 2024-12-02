package cs211.project.controllers;

import cs211.project.models.*;
import cs211.project.repository.EventRepository;
import cs211.project.repository.TeamRepository;
import cs211.project.services.NPBPRouter;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TeamDetailController implements Initializable {
    @FXML
    private AnchorPane page;
    @FXML
    private Label eventNameLabel;
    @FXML
    private Label teamNameLabel;
    @FXML
    private Label dateStartLabel;
    @FXML
    private Label timeStartLabel;
    @FXML
    private Label dateEndLabel;
    @FXML
    private Label timeEndLabel;
    @FXML
    private Label countMemberLabel;
    @FXML
    private Label maxMemberLabel;
    @FXML private ImageView eventImageView;

    private User user;
    private Event event;
    private Team team;
    private TeamRepository teamRepository;
    private TeamList teamList;
    private EventRepository eventRepository;
    private EventList eventList;
    private int set;
    private  int eventId;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        user = (User) NPBPRouter.getDataAccount();
        set = NPBPRouter.getData();

        eventRepository = new EventRepository();
        eventList = eventRepository.getEvents();
        eventId = (int) NPBPRouter.getDataEvent();
        event = eventList.findEventById(eventId);

        team = (Team) NPBPRouter.getDataTeam();
        teamRepository = new TeamRepository();
        teamList = teamRepository.getTeamList();

        showTeam(team, event);
    }

    public void showTeam(Team team, Event event) {
        eventNameLabel.setText(event.getName());

        teamNameLabel.setText(team.getTeamName());
        dateStartLabel.setText(team.getOpenDate().toString());
        timeStartLabel.setText(team.getOpenTime().toString());
        dateEndLabel.setText(team.getCloseDate().toString());
        timeEndLabel.setText(team.getCloseTime().toString());
        countMemberLabel.setText("" + team.getCountMember());
        maxMemberLabel.setText("" + team.getMaxMember());
        eventImageView.setImage(new Image(event.getImage().getUrl()));
    }

    public void backToTeamList() {
        if(set==1){
            try {
                NPBPRouter.loadPage("team-list", page, user);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else{
            try {
                NPBPRouter.loadPage("team-list-user", page, user);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void goToTeamMember() {
        try {
            NPBPRouter.loadPageSet("show-member", page, user, event, team.getTeamId(),set);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void goToChat() {
        try {
            NPBPRouter.loadPage("chat", page, user, event, team.getTeamId());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void goToActivity() {
        try {
            NPBPRouter.loadPageSet("team-activity", page, user, eventId, team.getTeamId(),set);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}


