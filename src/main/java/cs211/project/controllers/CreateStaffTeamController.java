package cs211.project.controllers;

import cs211.project.models.Event;
import cs211.project.models.Team;
import cs211.project.models.TeamList;
import cs211.project.models.User;
import cs211.project.pivot.EventTeamList;
import cs211.project.repository.AccountRepository;
import cs211.project.repository.EventRepository;
import cs211.project.repository.EventTeamRepository;
import cs211.project.repository.TeamRepository;
import cs211.project.services.NPBPRouter;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.ArrayList;

public class CreateStaffTeamController {
    @FXML
    private TextField capacity;

    @FXML
    private DatePicker closeDate;

    @FXML
    private TextField closeTimeText;

    @FXML
    private Label errorLabel;

    @FXML
    private DatePicker openDate;

    @FXML
    private TextField openTimeText;

    @FXML
    private AnchorPane page;

    @FXML
    private TextField teamNameText;
    private User user;
    private EventRepository eventRepository;
    private AccountRepository accountRepository;
    private TeamRepository teamRepository;
    private EventTeamList eventTeamList;
    private EventTeamRepository eventTeamRepository;
    private ArrayList<Team> teams;
    private TeamList teamList;
    private Event event;

    public void initialize(){
        user = (User) NPBPRouter.getDataAccount();
        event = (Event) NPBPRouter.getDataEvent();
        accountRepository = new AccountRepository();
        eventRepository = new EventRepository();
        teamRepository = new TeamRepository();
        teamList = teamRepository.getTeamList();
        teams = teamList.getTeams();
        eventTeamRepository = new EventTeamRepository();



        errorLabel.setVisible(false);
    }

    public void createTeamButton(){
        String teamName = teamNameText.getText().trim();
        String member = capacity.getText().trim();
        String openDateText = openDate.getValue().toString();
        String closeDateText = closeDate.getValue().toString();
        String openTime = openTimeText.getText().trim();
        String closeTime = closeTimeText.getText().trim();

        //---------------------------แก้ให้ชื่อทีมซ้ำใน event ไม่ได้----------------------------//
        eventTeamList = eventTeamRepository.getEventTeamList();
        ArrayList<Integer> teamIdInThisEvent = eventTeamList.findTeamByEventId(event.getEventId());//id ของ ทีมทั้งหมดใน event
        ArrayList<Team> teams = new ArrayList<>();
        for(var id : teamIdInThisEvent){
            teams.add(teamList.findTeamById(id));//team ทั้งหมดใน event
        }

        if(checkTeamName(teams)){
            teamList.createTeam(teamName,member,openDateText,openTime,closeDateText,closeTime,"0");
            Team exist = teamList.getTeams().get(teamList.getTeams().size()-1);
            int teamId = exist.getTeamId();

            eventTeamList.addNew(event.getEventId(),teamId);
            eventTeamRepository.save(eventTeamList);

            teamRepository.save(teamList);

            try {
                NPBPRouter.loadPage("team-list",page,user,event);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else{
            errorLabel.setVisible(true);
            errorLabel.setText("already has this team name");
        }
        //---------------------------แก้ให้ชื่อทีมซ้ำใน event ไม่ได้----------------------------//

    }

    public boolean checkTeamName(ArrayList<Team> teams){
        boolean check=true;
        for(var team : teams){
            if(team.isTeamName(teamNameText.getText().trim())){
                check=false;
            }
        }
        return check;
    }

    public void backButton(){
        try {
            NPBPRouter.loadPage("team-list",page);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
