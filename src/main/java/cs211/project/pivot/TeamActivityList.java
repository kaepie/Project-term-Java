package cs211.project.pivot;

import java.util.ArrayList;
import java.util.Iterator;

public class TeamActivityList {
    private ArrayList<TeamActivity> list;
    public TeamActivityList(){
        list = new ArrayList<>();
    }

    public void addNew(int teamId, int activityId) {
        list.add(new TeamActivity(teamId,activityId));
    }

    public void remove(int teamId, int acId) {
        Iterator<TeamActivity> iterator = list.iterator();
        while (iterator.hasNext()) {
            TeamActivity teamActivity = iterator.next();
            if (teamActivity.isTeamId(teamId) && teamActivity.isActivityId(acId)) {
                iterator.remove(); // Remove the element using the iterator
            }
        }
    }

    public ArrayList<TeamActivity> getList() {
        return list;
    }
}
