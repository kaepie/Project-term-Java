package cs211.project.repository;

import cs211.project.models.TeamList;
import cs211.project.services.Datasource;
import cs211.project.services.TeamDatasource;

public class TeamRepository {
    private TeamList teamList;
    private Datasource<TeamList> datasource;

    public TeamRepository(){
        datasource = new TeamDatasource("data", "teams.csv");
        teamList = datasource.readData();
    }

    public void save(TeamList teamList){ datasource.writeData(teamList); }

    public TeamList getTeamList(){return teamList;}

}
