package cs211.project.repository;

import cs211.project.pivot.EventTeamList;
import cs211.project.services.Datasource;
import cs211.project.services.EventTeamDatasource;

public class EventTeamRepository {
    private EventTeamList eventTeamList;
    private Datasource<EventTeamList> datasource;

    public EventTeamRepository(){
        datasource = new EventTeamDatasource("data","event_team.csv");
        eventTeamList = datasource.readData();
    }

    public void save(EventTeamList eventTeamList){datasource.writeData(eventTeamList);}

    public EventTeamList getEventTeamList(){
        return eventTeamList;
    }
}
