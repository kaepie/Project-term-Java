package cs211.project.controllers;

import cs211.project.models.ActivityList;
import cs211.project.models.User;
import cs211.project.pivot.EventActivityList;
import cs211.project.repository.ActivityRepository;
import cs211.project.repository.ActivityTeamEventRepository;
import cs211.project.services.NPBPRouter;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class CreateEventActivityController implements Initializable {

    @FXML private TextField nameTextField;
    @FXML private AnchorPane page;
    @FXML private DatePicker startDatePicker;
    @FXML private  DatePicker endDatePicker;
    @FXML private TextArea detailTextArea;
    @FXML private  TextField timeStart;
    @FXML private  TextField timeEnd;
    @FXML private Label errorLabel;
    private ActivityRepository activityRepository;
    private ActivityTeamEventRepository activityTeamEventRepository;
    private EventActivityList eventActivityList;
    private ActivityList activityList;
    private  int eventId;
    private User user;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        activityRepository = new ActivityRepository();
        activityList = activityRepository.getActivityList();
        activityTeamEventRepository = new ActivityTeamEventRepository();
        eventActivityList = activityTeamEventRepository.getEventActivity();
        user = (User)NPBPRouter.getDataAccount();
        eventId = (int)NPBPRouter.getDataEvent();
        errorLabel.setVisible(false);
    }


    @FXML
    public  void  createActivity(){
        errorLabel.setVisible(false);
        String name = nameTextField.getText();
        LocalDate startDate = startDatePicker.getValue();
        LocalDate endDate = endDatePicker.getValue();
        String detail = detailTextArea.getText().replace("\n","|");
        String startTime = timeStart.getText();
        String endTime =timeEnd.getText();
        try {
            activityList.addNewActivity(name, detail, startDate.toString(), endDate.toString(), startTime, endTime);
            activityRepository.save(activityList);
            eventActivityList.addNew(eventId, activityList.getLastId());
            activityTeamEventRepository.saveEvent(eventActivityList);
            backToEventActivity();
        }catch(Exception e){
            errorLabel.setVisible(true);
            errorLabel.setText("Wrong Format");
        }
    }


    public void backToEventActivity(){
        try {
            NPBPRouter.loadPage("event-activity",page,user,eventId);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
