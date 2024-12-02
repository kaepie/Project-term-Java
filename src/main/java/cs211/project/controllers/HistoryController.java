package cs211.project.controllers;

import cs211.project.models.Event;
import cs211.project.models.EventList;
import cs211.project.models.User;
import cs211.project.pivot.AccountEventList;
import cs211.project.repository.AccountEventRepository;
import cs211.project.repository.EventRepository;
import cs211.project.services.NPBPRouter;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HistoryController implements Initializable {

    @FXML private MFXTableView<Event> table1;
    @FXML private MFXTableView<Event> table2;
    private EventList eventList;
    private ArrayList<Event> eventNotEnd;
    private ArrayList<Event> eventEnd;
    private EventRepository eventRepository;
    private AccountEventRepository accountEventRepository;
    private AccountEventList accountEventList;
    private User user;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        eventRepository = new EventRepository();
        eventList = eventRepository.getEvents();
        user = (User)NPBPRouter.getDataAccount();
        accountEventRepository = new AccountEventRepository();
        accountEventList = accountEventRepository.getListJoin();
        ArrayList<Integer> listId = new ArrayList<>();
        eventNotEnd = new ArrayList<>();
        eventEnd = new ArrayList<>();
        listId.addAll(accountEventList.findEventsByAccount(user.getAccountId()));
        ArrayList<Event> events = new ArrayList<>();
        for (var i : listId){
            events.add(eventList.findEventById(i));
        }
        for(var i : events){
            i.checkTimeEvent();
            if(i.getStatusEvent()){
                eventNotEnd.add(i);
            }
            else{
               eventEnd.add(i);
            }
        }
        setTable(eventNotEnd,table1);
        setTable(eventEnd,table2);
    }
    private void setTable(ArrayList<Event> events, MFXTableView<Event> tableView) {
        MFXTableColumn<Event> nameColumn = new MFXTableColumn<>("Name", false);
        MFXTableColumn<Event> countMemberColumn = new MFXTableColumn<>("Count member", false);
        MFXTableColumn<Event> dateStartColumn = new MFXTableColumn<>("Date Start", false);
        MFXTableColumn<Event> dateEndColumn = new MFXTableColumn<>("Date End", false);

        nameColumn.setRowCellFactory(event -> new MFXTableRowCell<>(Event::getName));
        countMemberColumn.setRowCellFactory(event -> new MFXTableRowCell<>(Event::getCountMember));
        dateStartColumn.setRowCellFactory(event -> new MFXTableRowCell<>(Event::getDateStartEvent));
        dateEndColumn.setRowCellFactory(event -> new MFXTableRowCell<>(Event::getDateEndEvent));
        tableView.getTableColumns().clear();
        tableView.getTableColumns().addAll(nameColumn, countMemberColumn , dateStartColumn , dateEndColumn);

        for(Event event: events){
            tableView.getItems().add(event);
        }
    }
}
