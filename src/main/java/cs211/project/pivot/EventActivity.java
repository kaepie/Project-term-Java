package cs211.project.pivot;

public class EventActivity {
    private int activityId;
    private int eventId;

    public EventActivity(int eventId, int activityId)
    {
        this.eventId = eventId;
        this.activityId = activityId;
    }

    public boolean isActivityId(int id){
        return this.activityId == id;
    }
    public boolean isEventId(int id){
        return this.eventId == id;
    }

    public  int getActivityId(){
        return activityId;
    }
    public int getEventId()
    {
        return eventId;
    }


}
