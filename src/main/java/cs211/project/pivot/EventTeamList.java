package cs211.project.pivot;

import java.util.ArrayList;

public class EventTeamList {
    private ArrayList<EventTeam> list;
    public EventTeamList(){list = new ArrayList<>();}
    public void addNew(int eventId, int teamId){
        list.add(new EventTeam(eventId, teamId));
    }
    public ArrayList<Integer> findTeamByEventId(int eventId){
        ArrayList<Integer> result = new ArrayList<>();
        for(EventTeam eventTeam : list){
            if(eventTeam.isEventId(eventId)){
                result.add(eventTeam.getTeamId());
            }
        }
        return result;
    }
    public Integer findEventByTeamId(int teamId){
        for(EventTeam eventTeam : list){
            if(eventTeam.isTeamId(teamId)){
                return eventTeam.getEventId();
            }
        }
        return null;
    }

    public ArrayList<Integer> checkEventIdInEventId(ArrayList<Integer> arrayList, ArrayList<Integer> arrayList2){
        ArrayList<Integer> result = new ArrayList<>();
        for(var i : arrayList2){
            if(isEvent(i, arrayList)){
                result.add(i);
            }
        }
        return result;
    }

    public boolean isEvent(int eventId, ArrayList<Integer> arrayList){
        for(var i : arrayList){
            if(eventId == i){
                return true;
            }
        }
        return false;
    }

    public ArrayList<Integer> checkDuplicateEventId(ArrayList<Integer> arrayList){
        ArrayList<Integer> result = new ArrayList<>();
        for (var i : arrayList){
            if (!checkEventIdInArrayList(i, result)){
                result.add(i);
            }
        }
        return result;
    }

    public boolean checkEventIdInArrayList(int eventId, ArrayList<Integer> arrayList){
        for(var i : arrayList){
            if(eventId == i){
                return true;
            }
        }
        return false;
    }

    public ArrayList<Integer> getListEventId(){
        ArrayList<Integer> listId = new ArrayList<>();
        for (var i : list){
            listId.add(i.getEventId());
        }
        return listId;
    }
    public ArrayList<EventTeam> getList() {
        return list;
    }
}
