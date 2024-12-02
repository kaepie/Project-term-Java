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

public class SelectTeamToJoinController implements Initializable {
    @FXML
    private VBox vbox;
    @FXML private AnchorPane page;
    private TeamRepository teamRepository;
    private EventTeamRepository eventTeamRepository;
    private User user;
    private Event event;
    private Team team;
    private TeamList teamlist;
    private ArrayList<Integer> listId;
    private ArrayList<Integer> checkTeam;
    private EventTeamList eventTeamList;
    private TeamAccountRepository teamAccountRepository;
    private TeamAccountList teamAccountList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        user = (User) NPBPRouter.getDataAccount();
        event = (Event) NPBPRouter.getDataEvent();
        team = (Team) NPBPRouter.getDataTeam();

        teamRepository = new TeamRepository();
        eventTeamRepository = new EventTeamRepository();

        teamAccountRepository = new TeamAccountRepository();
        teamAccountList = teamAccountRepository.getTeamAccountList();

        checkTeam = new ArrayList<>();
        checkTeam.addAll(teamAccountList.findAllTeamsByAccount(user.getAccountId())); //id ของทีมทั้งหมดที่ account นั้นอยู่
        teamlist = teamRepository.getTeamList();
        eventTeamList = eventTeamRepository.getEventTeamList();
        listId = new ArrayList<>();

        ArrayList<Team> teams = new ArrayList<>();

        listId.addAll(eventTeamList.findTeamByEventId(event.getEventId()));//id ของทีมทั้งหมดในอีเว้น

        for(var i : listId){
            teams.add(teamlist.findTeamById(i));//team ทั้งหมดใน event
        }

        for(var id : checkTeam){
            teams.remove(teamlist.findTeamById(id));//remove team ที่ account นั้นอยู่ออกไป
        }

        for(Team team : teams){
            team.checkTime();//เช็คว่าหมดเวลารับสมัครหรือยัง
            if(team.getTimeCheck()){//ถ้ายังไม่หมดเวลารับสมัคร
                vbox.getChildren().add(createCard(team.getTeamId()));//โชว์ team ที่เหลือที่ account นี้ยังไม่ได้เข้าร่วม
            }
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
                NPBPRouter.loadPageSet("join-team",page,user,event,teamlist.findTeamById(id),0);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        return hbox;
    }

    public void backButton(){
        try {
            NPBPRouter.loadPage("join-event",page);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
