package cs211.project.controllers;

import cs211.project.models.Event;
import cs211.project.models.EventList;
import cs211.project.models.User;
import cs211.project.repository.AccountRepository;
import cs211.project.repository.EventRepository;
import cs211.project.services.NPBPRouter;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MyCreateEventController implements Initializable {
    @FXML
    private DatePicker dateEnd;

    @FXML
    private DatePicker dateStart;
    @FXML
    private TextField timeEnd;

    @FXML
    private TextField timeStart;
    @FXML
    AnchorPane page;

    @FXML
    private ImageView imageView;

    @FXML
    private Label nameEvent;
    @FXML
    private TextField maxMember;
    @FXML
    private Label errorLabel;
    private EventRepository eventRepository;
    private EventList eventList;
    private ArrayList<Event> events;
    private AccountRepository accountRepository;
    private User user;
    private Event event;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        eventRepository = new EventRepository();
        eventList = eventRepository.getEvents();
        user = (User) NPBPRouter.getDataAccount();
        int eventId = (Integer) NPBPRouter.getDataEvent();
        event = eventList.findEventById(eventId);

        showEvent(event);
        errorLabel.setVisible(false);
    }

    public void showEvent(Event event){
        imageView.setImage(new Image(event.getImage().getUrl()));
        nameEvent.setText(event.getName());
    }

    public void setDateTimeJoin(){
        try {
            errorLabel.setVisible(false);
            event.setOpenDateStart(LocalDate.parse(dateStart.getValue().toString()));
            event.setOpenDateEnd(LocalDate.parse(dateEnd.getValue().toString()));
            event.setOpenTimeStart(LocalTime.parse(timeStart.getText()));
            event.setOpenTimeEnd(LocalTime.parse(timeEnd.getText()));
            event.setCapacity(Integer.parseInt(maxMember.getText()));
            eventRepository.save(eventList);
            dateStart.setValue(null);
            dateEnd.setValue(null);
            timeStart.clear();
            timeEnd.clear();
            maxMember.clear();
        } catch (Exception e) {
            errorLabel.setVisible(true);
            errorLabel.setText("Wrong entry!!!");
        }
    }
    public void goToStaffList(){
        try {
            NPBPRouter.loadPage("team-list",page,user,event);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void goToEditUser(){
        try {
            NPBPRouter.loadPage("edit-user",page,user,event);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void goToEditEvent(){
        try {
            NPBPRouter.loadPage("edit-event",page,user,event.getEventId());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void goToEventActivity(){
        try {
            NPBPRouter.loadPage("event-activity",page,user,event.getEventId());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
