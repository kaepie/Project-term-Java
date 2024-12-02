package cs211.project.controllers;

import cs211.project.models.Event;
import cs211.project.models.EventList;
import cs211.project.models.User;
import cs211.project.pivot.AccountEventList;
import cs211.project.repository.AccountEventRepository;
import cs211.project.repository.EventRepository;
import cs211.project.repository.EventTeamRepository;
import cs211.project.repository.TeamAccountRepository;
import cs211.project.services.NPBPRouter;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.ArrayList;

public class JoinEventController {

    @FXML
    private Label countEmpty;

    @FXML
    private Label countTotal;

    @FXML
    private Label dateEndEventLabel;

    @FXML
    private Label dateStartEventLabel;

    @FXML
    private Label nameEventLabel;

    @FXML
    private AnchorPane page;

    @FXML
    private Label timeEndEventLabel;

    @FXML
    private Label timeStartEventLabel;
    @FXML
    private ImageView eventImageView;

    @FXML
    private TextArea detailsEvent;
    @FXML
    private Button joinEventButton;
    @FXML
    private Button joinTeamButton;
    @FXML
    private Label errorLabel;
    private EventList eventList;
    private Event event;

    private EventRepository eventRepository;
    private AccountEventRepository accountEventRepository;
    private TeamAccountRepository accountTeamRepository;
    private AccountEventList accountEventList;
    private EventTeamRepository eventTeamRepository;
    private User user;
    public void initialize(){
        eventRepository = new EventRepository();
        eventList = eventRepository.getEvents();
        int eventId = (Integer) NPBPRouter.getDataEvent();
        event = eventList.findEventById(eventId);

        user = (User) NPBPRouter.getDataAccount();
        accountEventRepository = new AccountEventRepository();
        user.addMyEventFromFile(accountEventRepository.getListJoin().findEventsByAccount(user.getAccountId()));

        eventTeamRepository = new EventTeamRepository();
        ArrayList<Integer> teamIdEvent = eventTeamRepository.getEventTeamList().findTeamByEventId(eventId);

        accountTeamRepository = new TeamAccountRepository();
        ArrayList<Integer> teamIdUser = accountTeamRepository.getTeamAccountList().findAllTeamsByAccount(user.getAccountId());

        if (eventList.checkTeamInEvent(teamIdEvent, teamIdUser)){
            joinEventButton.setVisible(false);
        }
        ArrayList<Integer> teamIdEventNew = new ArrayList<>();
        teamIdEventNew.addAll(teamIdEvent);
        teamIdEvent.removeAll(teamIdUser);
        if (teamIdEvent.isEmpty() && !(teamIdEventNew.isEmpty())){
            joinTeamButton.setVisible(false);
        }

        showEvent(event);
        detailsEvent.setEditable(false);
        errorLabel.setVisible(false);
    }

    public void showEvent(Event event){
        nameEventLabel.setText(event.getName());
        String details = event.getDetail();
        String []details_new = details.split("\\|");
        details = "";
        for (var i : details_new){
            details += i.trim();
            details += "\n";
        }
        detailsEvent.setText(details);
        dateStartEventLabel.setText(""+event.getDateStartEvent());
        dateEndEventLabel.setText(""+event.getDateEndEvent());
        timeStartEventLabel.setText(""+event.getTimeStartEvent());
        timeEndEventLabel.setText(""+event.getTimeEndEvent());
        countEmpty.setText(""+event.getCountMember());
        countTotal.setText(""+event.getMaxMember());
        eventImageView.setImage(new Image(event.getImage().getUrl()));
    }

    public void onJointEventButton(){
        if(event.checkMember()) {
            event.addCountMember();
            eventRepository.save(eventList);
            accountEventList = accountEventRepository.getListJoin();
            user.addMyEvent(event.getEventId());
            accountEventList.addNew(user.getAccountId(), event.getEventId());
            accountEventRepository.saveEventJoin(accountEventList);

            try {
                NPBPRouter.loadPage("home-page", page, user);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else{
            errorLabel.setVisible(true);
            errorLabel.setText("Event Full!!!");
        }
    }

    public void goToSelectTeam() {
        try {
            NPBPRouter.loadPage("select-team-to-join", page, user, event);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

