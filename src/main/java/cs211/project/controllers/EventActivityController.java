package cs211.project.controllers;

import cs211.project.models.Activity;
import cs211.project.models.ActivityList;
import cs211.project.models.User;
import cs211.project.pivot.EventActivity;
import cs211.project.pivot.EventActivityList;
import cs211.project.repository.ActivityRepository;
import cs211.project.repository.ActivityTeamEventRepository;
import cs211.project.services.NPBPRouter;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class EventActivityController {

    @FXML private TableView<Activity> activityTableView;

    @FXML
    private AnchorPane page;

    private ActivityTeamEventRepository teamEventRepository;

    private  ActivityRepository repository;

    private ActivityList activitys;
    private  ActivityList showActivitys;

    private  Activity activity;

    private EventActivityList eventActivity;

    private User user;

    private  int eventId;

    @FXML
    public void initialize() {
        teamEventRepository = new ActivityTeamEventRepository();
        showActivitys = new ActivityList();
        eventActivity  = teamEventRepository.getEventActivity();
        repository = new ActivityRepository();
        activitys = repository.getActivityList();
        user = (User)NPBPRouter.getDataAccount();
        eventId = (int) NPBPRouter.getDataEvent();

        for(EventActivity event :  eventActivity.getList()) {
            if (event.isEventId(eventId)) {
                activity = activitys.findActivityById(event.getActivityId());
                activity.checkTimeActivity();
                showActivitys.addNewActivityFromFile(activity.getName(), activity.getDetail(), "" + activity.getId(), "" + activity.getDateStart(), "" + activity.getDateEnd(), "" + activity.getTimeStart(), "" + activity.getTimeEnd(), "" + activity.getStatus());
            }
        }
        showTable(showActivitys);

        activityTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Activity>() {
            @Override
            public void changed(ObservableValue observable, Activity oldValue, Activity newValue) {
                if (newValue != null) {
                    try {
                        NPBPRouter.loadPageEditEvent("edit-event-activity",page,user,eventId,newValue.getId());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }

    private void showTable(ActivityList activityList) {
        TableColumn<Activity, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setResizable(false);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Activity, String> startDateColumn = new TableColumn<>("dateStart");
        startDateColumn.setCellValueFactory(new PropertyValueFactory<>("dateStart"));
        startDateColumn.setResizable(false);


        TableColumn<Activity, String> startTimeColumn = new TableColumn<>("timeStart");
        startTimeColumn.setCellValueFactory(new PropertyValueFactory<>("timeStart"));
        startTimeColumn.setResizable(false);

        TableColumn<Activity, String> endDateColumn = new TableColumn<>("dateEnd");
        endDateColumn.setCellValueFactory(new PropertyValueFactory<>("dateEnd"));
        endDateColumn.setResizable(false);

        TableColumn<Activity, String> endTimeColumn = new TableColumn<>("timeEnd");
        endTimeColumn.setCellValueFactory(new PropertyValueFactory<>("timeEnd"));
        endTimeColumn.setResizable(false);

        TableColumn<Activity, String> statusColumn = new TableColumn<>("status");
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        statusColumn.setResizable(false);

        activityTableView.getColumns().clear();
        activityTableView.getColumns().add(nameColumn);
        activityTableView.getColumns().add(startDateColumn);
        activityTableView.getColumns().add(startTimeColumn);
        activityTableView.getColumns().add(endDateColumn);
        activityTableView.getColumns().add(endTimeColumn);
        activityTableView.getColumns().add(statusColumn);
        activityTableView.getItems().clear();

        for (Activity activity: activityList.getActivity()) {
            activityTableView.getItems().add(activity);
        }
    }
    public void create(){
        try {
            NPBPRouter.loadPage("create-event-activity",page,user,eventId);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void back(){
        try {
            NPBPRouter.loadPage("my-create-event",page,user,eventId);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}