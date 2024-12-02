package cs211.project.pivot;

public class TeamActivity {
    private int activityId;
    private int teamId;

    public TeamActivity(int teamId, int activityId){
        this.activityId = activityId;
        this.teamId = teamId;
    }
    public boolean isActivityId(int id){
        return this.activityId == id;
    }
    public boolean isTeamId(int id){
        return this.teamId == id;
    }

    public int getActivityId() {
        return activityId;
    }

    public  int getTeamId(){
        return teamId;
    }

}
