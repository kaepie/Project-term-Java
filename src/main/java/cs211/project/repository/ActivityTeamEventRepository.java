package cs211.project.repository;

import cs211.project.pivot.*;
import cs211.project.services.Datasource;
import cs211.project.services.EventActivityDatasource;
import cs211.project.services.TeamActivityDatasource;

public class ActivityTeamEventRepository {
    private EventActivityList eventActivity;
    private Datasource<EventActivityList> evenData;
    private  TeamActivityList teamActivity;
    private Datasource<TeamActivityList> teamData;

    public ActivityTeamEventRepository(){
        evenData = new EventActivityDatasource("data", "event_activity.csv");
        teamData = new TeamActivityDatasource("data", "team_activity.csv");
        eventActivity = evenData.readData();
        teamActivity = teamData.readData();
    }
    public void saveTeam(TeamActivityList teamList) {
        teamData.writeData(teamList);
    }

    public  void saveEvent(EventActivityList eventList){
        evenData.writeData(eventList);
    }

    public EventActivityList getEventActivity(){
        return  eventActivity;
    }

    public TeamActivityList getTeamActivity(){
        return  teamActivity;
    }
}
