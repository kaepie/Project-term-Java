package cs211.project.controllers;

import cs211.project.models.*;
import cs211.project.pivot.EventTeamList;
import cs211.project.repository.EventTeamRepository;
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

public class TeamListController implements Initializable {
    @FXML private VBox vbox;
    @FXML private AnchorPane page;
    private TeamRepository teamRepository;
    private EventTeamRepository eventTeamRepository;
    private User user;
    private Event event;
    private Team team;
    private TeamList teamlist;
    private ArrayList<Integer> listId;
    private EventTeamList eventTeamList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        user = (User) NPBPRouter.getDataAccount();
        event = (Event) NPBPRouter.getDataEvent();
        team = (Team) NPBPRouter.getDataTeam();

        teamRepository = new TeamRepository();
        eventTeamRepository = new EventTeamRepository();

        teamlist = teamRepository.getTeamList();
        eventTeamList = eventTeamRepository.getEventTeamList();
        listId = new ArrayList<>();

        listId.addAll(eventTeamList.findTeamByEventId(event.getEventId()));


        for(Integer id : listId){
            vbox.getChildren().add(createCard(id));
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
                NPBPRouter.loadPageSet("team-detail",page,user,event.getEventId(),teamlist.findTeamById(id),1);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        return hbox;
    }

    public void backButton(){
        try {
            NPBPRouter.loadPage("my-create-event",page);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void createTeamButton(){
        try {
            NPBPRouter.loadPage("create-staff-team",page,user,event);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
