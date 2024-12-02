package cs211.project.models;

import java.util.ArrayList;

public class ActivityList {
    private ArrayList<Activity> activities;
    private  int lastId = 0;
    public  ActivityList(){activities = new ArrayList<>();}
    public void  addNewActivityFromFile(String name,String detail,String id,String dateStart,String dateEnd, String timeStart,String timeEnd,String status){
        name = name.trim();
        detail = detail.trim();
        id = id.trim();
        if (!name.equals("") & !detail.equals("")){
                activities.add(new Activity(name,detail,Integer.parseInt(id),dateStart,dateEnd,timeStart,timeEnd,status));
                this.lastId = Integer.parseInt(id);
        }
    }

    public void  addNewActivity(String name,String detail,String dateStart,String dateEnd, String timeStart,String timeEnd){
        name = name.trim();
        detail = detail.trim();
        if (!name.equals("") & !detail.equals("")){
                activities.add(new Activity(name,detail,++lastId,dateStart,dateEnd,timeStart,timeEnd,"available"));
        }
    }
    public void remove(Activity activity){
        activities.remove(activity);
    }
    public int getLastId()
    {
        return  lastId;
    }
    public void  addNewActivity(Activity activity)
    {
        activities.add(activity);
    }
    public Activity findActivityById(int id){
        for (Activity activity : activities) {
            if (activity.isId(id)){
                return activity;
            }
        }
        return null;
    }

    public  ArrayList<Activity> getActivity(){
        return activities;
    }
}

