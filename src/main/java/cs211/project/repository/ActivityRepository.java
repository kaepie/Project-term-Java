package cs211.project.repository;

import cs211.project.models.Activity;
import cs211.project.models.ActivityList;
import cs211.project.services.ActivityDatasource;
import cs211.project.services.Datasource;

public class ActivityRepository {
    private ActivityList activitys;
    private Datasource<ActivityList>  datasource;

    public  ActivityRepository() {
        datasource = new ActivityDatasource("data","activity.csv");
        activitys = datasource.readData();
    }

    public void save(ActivityList activityList){
        datasource.writeData(activityList);
    }

    public Activity findById(int id) {
        Activity activity = activitys.findActivityById(id);
        return activity;
    }



    public  ActivityList getActivityList() {
        return activitys;
    }
}
