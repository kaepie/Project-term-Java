package cs211.project.controllers;

import cs211.project.models.Event;
import cs211.project.models.EventList;
import cs211.project.models.User;
import cs211.project.pivot.AccountEventList;
import cs211.project.repository.AccountEventRepository;
import cs211.project.repository.EventRepository;
import cs211.project.services.NPBPAnimation;
import cs211.project.services.NPBPRouter;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ShowMyCreateEventController implements Initializable {
    @FXML
    private AnchorPane page;


    @FXML
    private TextField searchTextField;

    @FXML
    private VBox vbox;
    @FXML
    private ListView showNameEventListView;
    private User user;
    private EventRepository eventRepository;
    private AccountEventRepository accountEventRepository;
    private EventList eventList;
    private ArrayList<Event> events;
    private int LOAD = 250;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        user = (User)NPBPRouter.getDataAccount();
        eventRepository = new EventRepository();
        eventList = eventRepository.getEvents();
        events = eventList.getEvents();
        accountEventRepository = new AccountEventRepository();
        AccountEventList listCreate = accountEventRepository.getListCreate();
        ArrayList<Integer> listId = new ArrayList<>();
        listId.addAll(listCreate.findEventsByAccount(user.getAccountId()));
        ArrayList<Event> eventCreate = new ArrayList<>();
        for (var i : listId){
            eventCreate.add(eventList.findEventById(i));
        }
        showEvent(eventCreate);
        showNameEventListView.setVisible(false);

        //----------------------* searchTextField *----------------------
        ObservableList<String> observableList = FXCollections.observableArrayList();
        searchTextField.textProperty().addListener((observableValue, old, New) -> {
            if(New.equals("")) {
                showNameEventListView.getItems().clear();
                showNameEventListView.setVisible(false);
                vbox.getChildren().clear();
                showEvent(eventCreate);
            }
            else if(New != null) {
                showNameEventListView.setVisible(true);
                showNameEventListView.getItems().clear();
                ArrayList<Event> list = new ArrayList<>();
                for(var i : eventCreate){
                    if(i.getName().toLowerCase().contains(New.toLowerCase())) {
                        if (i.getStatusEvent() && i.getStatusJoin()){
                            observableList.add(i.getName());
                            list.add(eventList.findEventByName(i.getName()));
                        }
                    }
                }
                showNameEventListView.setItems(observableList);
                vbox.getChildren().clear();
                showEvent(list);
            }
        });
        showNameEventListView.setOnMouseClicked(click -> {
            if (!showNameEventListView.getSelectionModel().getSelectedItems().isEmpty()) {
                String nameSelectEvent = showNameEventListView.getSelectionModel().getSelectedItems().get(0).toString();
                Event selectEvent = eventList.findEventByName(nameSelectEvent);
                if (nameSelectEvent != null) {
                    vbox.getChildren().clear();
                    vbox.getChildren().add(createCard(selectEvent));
                    searchTextField.setText(nameSelectEvent);
                    showNameEventListView.setVisible(false);
                }
            }
        });
        //------------------------------------------------------------------
    }

    public void showEvent(ArrayList<Event> eventArrayList){
        LOAD = 250;
        eventArrayList.forEach(data -> {
            data.checkTimeEvent();
            if(data.getStatusEvent()) {
                VBox vBox = createCard(data);
                vbox.getChildren().add(vBox);
                vBox.setOpacity(0);
                FadeTransition fadeTransition = new FadeTransition(Duration.millis(LOAD),vBox);
                LOAD += 250;
                fadeTransition.setToValue(100);
                fadeTransition.play();
            }

        });
    }

    public VBox createCard(Event newEvent){
        File file = new File("src/main/resources/cs211/project/views/event-card.fxml");
        URL url = null;
        try {
            url = file.toURL();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        FXMLLoader fxmlLoader = new FXMLLoader(url);

        VBox vbox = null;
        try {
            vbox = (VBox) fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        EventController eventController = (EventController) fxmlLoader.getController();
        Label namelabel = eventController.getNameEventLabel();
        Label countMember = eventController.getCountMemberLabel();
        Label maxMember = eventController.getMaxMemberLabel();
        Circle img = eventController.getImgCircle();

        namelabel.setText(newEvent.getName());
        countMember.setText(""+newEvent.getCountMember());
        maxMember.setText(""+newEvent.getMaxMember());
        img.setFill(new ImagePattern(new Image(newEvent.getImage().getUrl())));

//        img.setFill(new ImagePattern(new Image("file:"+"images/"+"default.png")));
        vbox.setOnMouseClicked(event ->{
            try {
                NPBPRouter.loadPage("my-create-event",page,user,newEvent.getEventId());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        VBox finalVbox = vbox;
        vbox.setOnMouseEntered(event1 ->{
            NPBPAnimation.scaleTransition(finalVbox, 1.05);
        });

        vbox.setOnMouseExited(event2 ->{
            NPBPAnimation.reverseScale(finalVbox);
        });

        return vbox;
    }
}
