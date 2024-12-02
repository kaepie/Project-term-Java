package cs211.project.controllers;

import cs211.project.models.Activity;
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
import java.time.LocalTime;
import java.util.ResourceBundle;

public class EditEventActivityController implements Initializable {
    @FXML private TextField nameTextField;
    @FXML private AnchorPane page;
    @FXML private DatePicker startDatePicker;
    @FXML private  DatePicker endDatePicker;
    @FXML private TextArea detailTextArea;
    @FXML private  TextField timeStart;
    @FXML private  TextField timeEnd;
    @FXML private Label errorLabel;
    private ActivityRepository activityRepository;
    private  ActivityList activityList;
    private  Activity activity;
    private  ActivityTeamEventRepository activityTeamEventRepository;
    private  EventActivityList eventActivityList;
    private  int id;
    private  int eventId;
    private  User user;
    public void initialize(URL url, ResourceBundle resourceBundle){
        activityRepository = new ActivityRepository();
        activityList = activityRepository.getActivityList();
        id = (int)NPBPRouter.getDataActivity();
        eventId = (int)NPBPRouter.getDataEvent();
        user = (User)NPBPRouter.getDataAccount();
        activity = activityList.findActivityById(id);
        activityTeamEventRepository = new ActivityTeamEventRepository();
        eventActivityList = activityTeamEventRepository.getEventActivity();

        errorLabel.setVisible(false);


        // edit event
        nameTextField.setText(activity.getName());
        startDatePicker.setValue(activity.getDateStart());
        endDatePicker.setValue(activity.getDateEnd());
        detailTextArea.setText(activity.getDetail().replace("|","\n"));
        timeStart.setText((""+activity.getTimeStart()));
        timeEnd.setText(""+activity.getTimeEnd());
    }

    @FXML
    public  void  changeActivity(){
        errorLabel.setVisible(false);
        String name = nameTextField.getText();
        LocalDate startDate = startDatePicker.getValue();
        LocalDate endDate = endDatePicker.getValue();
        String detail = detailTextArea.getText().replace("\n","|");
        String startTime = timeStart.getText();
        String endTime =timeEnd.getText();
        try {
            activity.editActivity(name,detail,startDate,endDate,startTime,endTime);
            activityRepository.save(activityList);
            backToEventActivity();
        } catch (Exception e){
            errorLabel.setText("Wrong Format");
            errorLabel.setVisible(true);
        }

    }

    public void delete(){
        eventActivityList.remove(eventId,id);
        activityTeamEventRepository.saveEvent(eventActivityList);

        activityList.remove(activityList.findActivityById(id));
        activityRepository.save(activityList);
        backToEventActivity();
    }

    public void end() {
        activity.setDateStart(String.valueOf(LocalDate.now()));
        activity.setDateEnd(String.valueOf(LocalDate.now()));
        activity.setTimeStart(String.valueOf(LocalTime.now()));
        activity.setTimeEnd(String.valueOf(LocalTime.now()));
        activityRepository.save(activityList);
        backToEventActivity();
    }
    public void backToEventActivity(){
        try {
            NPBPRouter.loadPage("event-activity",page,user,eventId);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
