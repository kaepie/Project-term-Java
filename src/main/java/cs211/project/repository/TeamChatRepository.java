package cs211.project.repository;

import cs211.project.pivot.TeamChatList;
import cs211.project.services.Datasource;
import cs211.project.services.TeamChatDatasource;

public class TeamChatRepository {
    private TeamChatList teamChatList;
    private Datasource<TeamChatList> datasource;

    public TeamChatRepository() {
        datasource = new TeamChatDatasource("data", "team_chat.csv");
        teamChatList = datasource.readData();
    }

    public void save(TeamChatList teamChatList){
        datasource.writeData(teamChatList);
    }

    public TeamChatList getTeamChatList() {
        return teamChatList;
    }
}
