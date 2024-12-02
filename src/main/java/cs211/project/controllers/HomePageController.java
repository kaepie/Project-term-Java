package cs211.project.controllers;

import cs211.project.models.*;
import cs211.project.pivot.AccountEventList;
import cs211.project.repository.AccountEventRepository;
import cs211.project.repository.AccountRepository;
import cs211.project.repository.EventRepository;
import cs211.project.services.NPBPAnimation;
import cs211.project.services.NPBPRouter;
import javafx.animation.*;
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

public class HomePageController implements Initializable {
    @FXML
    private AnchorPane page;

    @FXML
    private TextField searchTextField;

    @FXML
    private VBox vbox;

    @FXML
    private ListView listView;

    private ArrayList<Event> events;
    private EventList eventList;
    private EventRepository eventRepository;
    private AccountRepository accountRepository;
    private AccountEventRepository accountEventRepository;
    private User user;
    private int LOAD = 400;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        user = (User)NPBPRouter.getDataAccount();
        eventRepository = new EventRepository();
        accountRepository = new AccountRepository();
        eventList = eventRepository.getEvents();
        events = eventList.getEvents();
        accountEventRepository = new AccountEventRepository();
        AccountEventList listJoin = accountEventRepository.getListJoin();
        AccountEventList listCreate = accountEventRepository.getListCreate();
        ArrayList<Integer> listId = new ArrayList<>();
        listId.addAll(listJoin.findAllEventsByAccount(user.getAccountId()));
        listId.addAll(listCreate.findEventsByAccount(user.getAccountId()));

        for (var i : listId){
            events.remove(eventList.findEventById(i));
        }
        showEvent(events);
        listView.setVisible(false);

        //----------------------* searchTextField *----------------------
        ObservableList<String> observableList = FXCollections.observableArrayList();
        searchTextField.textProperty().addListener((observableValue, old, New) -> {
            if(New.equals("")) {
                listView.getItems().clear();
                listView.setVisible(false);
                vbox.getChildren().clear();
                showEvent(events);
            }
            else if(New != null) {
                listView.setVisible(true);
                listView.getItems().clear();
                ArrayList<Event> list = new ArrayList<>();
                for(var i : events){
                    if(i.getName().toLowerCase().contains(New.toLowerCase())) {
                        if (i.getStatusEvent() && i.getStatusJoin()){
                            observableList.add(i.getName());
                            list.add(eventList.findEventByName(i.getName()));
                        }
                    }
                }
                listView.setItems(observableList);
                vbox.getChildren().clear();
                showEvent(list);
            }
        });
        listView.setOnMouseClicked(click -> {
            if (!listView.getSelectionModel().getSelectedItems().isEmpty()) {
                String nameSelectEvent = listView.getSelectionModel().getSelectedItems().get(0).toString();
                Event selectEvent = eventList.findEventByName(nameSelectEvent);
                if (nameSelectEvent != null) {
                    vbox.getChildren().clear();
                    vbox.getChildren().add(createCard(selectEvent));
                    searchTextField.setText(nameSelectEvent);
                    listView.setVisible(false);
                }
            }
        });
        //------------------------------------------------------------------
    }
    public void showEvent(ArrayList<Event> eventArrayList){
        LOAD = 400;
        eventArrayList.forEach(data -> {
            data.checkTimeEvent();
            data.checkTimeJoin();
            if(data.getStatusEvent() && data.getStatusJoin()) {
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

        vbox.setOnMouseClicked(event ->{
            try {
                NPBPRouter.loadPage("join-event",page,user,newEvent.getEventId());
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

    public void onCreateEventButton(){
        try {
            NPBPRouter.loadPage("create-event",page,user);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
