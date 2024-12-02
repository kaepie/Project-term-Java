package cs211.project.models;


import java.util.ArrayList;

public class TeamList {
    private ArrayList<Team> teams;
    private int lastId;
    public TeamList(){
        teams = new ArrayList<>();
    }
    public void addNewTeam(String team_id, String name, String maxMember, String openDate, String openTime, String closeDate, String closeTime, String countMember){
        team_id = team_id.trim();
        name = name.trim();
        maxMember = maxMember.trim();
        openDate = openDate.trim();
        openTime = openTime.trim();
        closeDate = closeDate.trim();
        closeTime = closeTime.trim();
        countMember = countMember.trim();

        if (!name.equals("")) {
            teams.add(new Team(team_id, name, maxMember, openDate, openTime, closeDate, closeTime, countMember));
            this.lastId = Integer.parseInt(team_id);
        }
    }

    public void createTeam(String name, String maxMember, String openDate, String openTime, String closeDate, String closeTime, String countMember){
        name = name.trim();
        maxMember = maxMember.trim();
        openDate = openDate.trim();
        openTime = openTime.trim();
        closeDate = closeDate.trim();
        closeTime = closeTime.trim();

        if(!name.equals("")){
            teams.add(new Team(""+(++lastId), name, maxMember, openDate, openTime, closeDate, closeTime, countMember));
        }
    }

    public Team findTeamById(int team_id){
        for(Team team : teams){
            if(team.isTeamId(team_id)){
                return team;
            }
        }
        return null;
    }

    public void addCountMember(int teamId){
        Team exist = findTeamById(teamId);
        exist.addCountMember();
    }
    public ArrayList<Team> getTeams() {
        return teams;
    }
}
