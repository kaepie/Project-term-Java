package cs211.project.controllers;

import cs211.project.models.*;
import cs211.project.pivot.AccountEventList;
import cs211.project.repository.AccountEventRepository;
import cs211.project.repository.EventRepository;
import cs211.project.services.NPBPRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;

public class CreateEventController {
    @FXML AnchorPane page;
    @FXML private DatePicker dateEndEvent;
    @FXML private DatePicker dateStartEvent;
    @FXML private TextArea detailsTextArea;
    @FXML private Circle eventCircle;
    @FXML private TextField nameEvent;
    @FXML private TextField timeEndEvent;
    @FXML private TextField timeStartEvent;
    @FXML private Label errorLabel;
    private EventList eventList;
    private EventRepository eventRepository;
    private AccountEventRepository accountEventRepository;
    private AccountEventList accountEventList;
    private User user;
    private ArrayList<Event> event;
    private Image image;

    public void initialize(){
        user = (User)NPBPRouter.getDataAccount();
        eventRepository = new EventRepository();
        eventList = eventRepository.getEvents();
        event = eventRepository.getEvents().getEvents();
        accountEventRepository = new AccountEventRepository();
        user.addMyCreateEventFromFile(accountEventRepository.getListCreate().findEventsByAccount(user.getAccountId()));

        errorLabel.setVisible(false);
    }

    @FXML
    public void handleCreateEventButton() {
        try {
            String nameString = nameEvent.getText().trim();
            String dateStart = dateStartEvent.getValue().toString();
            String dateEnd = dateEndEvent.getValue().toString();
            String timeStart = timeStartEvent.getText().trim();
            String timeEnd = timeEndEvent.getText().trim();


            String[] s = detailsTextArea.getText().split("\n");
            String detailsString = "";
            for (var i : s) {
                detailsString += i.trim();
                detailsString += "|";
            }
            if (image == null) {
                eventList.createEvent(nameString, detailsString, dateStart, dateEnd, timeStart, timeEnd, "0");
            } else {
                eventList.createEvent(nameString, detailsString, dateStart, dateEnd, timeStart, timeEnd, "0",image);
            }
            Event exist = eventList.findEventByName(nameString);
            int event_id = exist.getEventId();
            accountEventList = accountEventRepository.getListCreate();
            accountEventList.addNew(user.getAccountId(), event_id);
            accountEventRepository.saveEventOwner(accountEventList);
            nameEvent.clear();
            timeStartEvent.clear();
            timeEndEvent.clear();
            dateStartEvent.setValue(null);
            dateEndEvent.setValue(null);
            eventRepository.save(eventList);


            try {
                NPBPRouter.loadPage("my-create-event", page, user, event_id);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }catch (Exception e){
            errorLabel.setVisible(true);
            errorLabel.setText("Wrong entry!!!");
        }
    }

    @FXML
    public void handleUploadImageButton(ActionEvent event) {
        FileChooser chooser = new FileChooser();

        // SET FILECHOOSER INITIAL DIRECTORY
        chooser.setInitialDirectory(new File(System.getProperty("user.dir")));


        // DEFINE ACCEPTABLE FILE EXTENSION
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("images PNG JPG", "*.png", "*.jpg", "*.jpeg"));

        // GET FILE FROM FILECHOOSER WITH JAVAFX COMPONENT WINDOW
        File file = chooser.showOpenDialog(null);

        if (file != null) {

            File dir = new File("images/event");

            String locate = dir.getParent();
            File f = new File("images/event");
            if (!f.exists()) {f.mkdirs();}

            Path from = Paths.get(file.toURI());

            String name = file.getName();
            String separator = name.substring(name.lastIndexOf('.'), name.length());
            String fileName = nameEvent.getText().trim();

            image = new Image("file:"+"images/event/"+fileName+separator);

            Path to = Paths.get("images/event/"+fileName+separator);
            CopyOption[] options = new CopyOption[]{
                    StandardCopyOption.REPLACE_EXISTING,
                    StandardCopyOption.COPY_ATTRIBUTES
            };
            try {
                Files.copy(from,to, options);
                eventCircle.setFill(new ImagePattern(new Image("file:"+"images/event/"+fileName+separator)));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
