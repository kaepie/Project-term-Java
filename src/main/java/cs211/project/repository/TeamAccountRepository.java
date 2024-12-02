package cs211.project.repository;

import cs211.project.pivot.TeamAccountList;
import cs211.project.services.TeamAccountDatasource;
import cs211.project.services.Datasource;

public class TeamAccountRepository {
    private TeamAccountList teamAccountList;
    private Datasource<TeamAccountList> datasource;

    public TeamAccountRepository(){
        datasource = new TeamAccountDatasource("data","team_account.csv");
        teamAccountList = datasource.readData();
    }

    public void save(TeamAccountList teamAccountList){datasource.writeData(teamAccountList);}

    public TeamAccountList getTeamAccountList(){
        return teamAccountList;
    }
}
