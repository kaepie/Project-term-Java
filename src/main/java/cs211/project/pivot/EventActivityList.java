package cs211.project.pivot;

import java.util.ArrayList;
import java.util.Iterator;

public class EventActivityList {
    private ArrayList<EventActivity> list;
    public EventActivityList(){
        list = new ArrayList<>();
    }

    public void addNew(int eventId, int activityId) {
        list.add(new EventActivity(eventId,activityId));
    }

    public ArrayList<Integer> findEventActivityByActivityId(int activity_id){
        ArrayList<Integer> result = new ArrayList<>();
        for(EventActivity eventActivity: list){
            if (eventActivity.isActivityId(activity_id)){
                result.add(eventActivity.getActivityId());
            }
        }
        return result;
    }



    public void remove(int eventId, int acId) {
        Iterator<EventActivity> iterator = list.iterator();
        while (iterator.hasNext()) {
            EventActivity eventActivity = iterator.next();
            if (eventActivity.isEventId(eventId) && eventActivity.isActivityId(acId)) {
                iterator.remove(); // Remove the element using the iterator
            }
        }
    }

    public ArrayList<EventActivity> getList() {
        return list;
    }
}
