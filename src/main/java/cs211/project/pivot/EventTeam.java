package cs211.project.pivot;


public class EventTeam {
    private int eventId;
    private int teamId;

    public EventTeam(int eventId, int teamId){
        this.eventId = eventId;
        this.teamId = teamId;
    }

    public boolean isEventId(int id){return this.eventId==id;}
    public boolean isTeamId(int id){return this.teamId==id;}

    public int getEventId() {
        return eventId;
    }

    public int getTeamId() {
        return teamId;
    }
}
