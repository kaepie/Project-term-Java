package cs211.project.models;

import javafx.scene.image.Image;

import java.util.ArrayList;

public class EventList {
    private ArrayList<Event> events;
    private int lastId = 0;

    public EventList() {
        events = new ArrayList<>();
    }

    public void addNewEvent(String name, String id, String details,
                            String dateStartEvent, String dateEndEvent, String timeStartEvent, String timeEndEvent,
                            String openDateStart, String openDateEnd, String openTimeStart, String openTimeEnd,
                            String countMember, String maxMember, Image image){
        name = name.trim();
        details = details.trim();
        dateStartEvent = dateStartEvent.trim();
        dateEndEvent = dateEndEvent.trim();
        timeStartEvent = timeStartEvent.trim();
        timeEndEvent = timeEndEvent.trim();
        maxMember = maxMember.trim();
        if (!name.equals("")) {
            Event exist = findEventByName(name);
            if (exist == null) {
                events.add(new Event(name,id, details, dateStartEvent, dateEndEvent, timeStartEvent, timeEndEvent,openDateStart, openDateEnd,openTimeStart, openTimeEnd,countMember, maxMember,image));
                this.lastId = Integer.parseInt(id);
            }
        }
    }

    public boolean checkTeamInEvent(ArrayList<Integer> arrayList, ArrayList<Integer> arrayList2){
        for(var i : arrayList){
            if(isTeam(i, arrayList2)){
                return true;
            }
        }
        return false;
    }

    public boolean isTeam(int teamId,ArrayList<Integer> arrayList){
        for(var i : arrayList){
            if(teamId == i){
                return true;
            }
        }
        return false;
    }

    public Event findEventById(int event_id){
        for (Event event : events) {
            if (event.isEventId(event_id)) {
                return event;
            }
        }
        return null;
    }

    public void createEvent(String name, String details,
                            String dateStartEvent, String dateEndEvent, String timeStartEvent, String timeEndEvent,
                            String countMember, Image image){
        name = name.trim();
        details = details.trim();
        dateStartEvent = dateStartEvent.trim();
        dateEndEvent = dateEndEvent.trim();
        timeStartEvent = timeStartEvent.trim();
        timeEndEvent = timeEndEvent.trim();
        countMember = countMember.trim();
        Event exist = findEventByName(name);
        if(!name.equals("")){
            if(exist == null){
                events.add(new Event(name,""+ (++lastId),details,dateStartEvent,dateEndEvent,timeStartEvent,timeEndEvent,countMember,image));
            }
        }
    }

    public void createEvent(String name, String details,
                            String dateStartEvent, String dateEndEvent, String timeStartEvent, String timeEndEvent,
                            String countMember){
        name = name.trim();
        details = details.trim();
        dateStartEvent = dateStartEvent.trim();
        dateEndEvent = dateEndEvent.trim();
        timeStartEvent = timeStartEvent.trim();
        timeEndEvent = timeEndEvent.trim();
        countMember = countMember.trim();
        Event exist = findEventByName(name);
        if(!name.equals("")){
            if(exist == null){
                events.add(new Event(name,""+ (++lastId),details,dateStartEvent,dateEndEvent,timeStartEvent,timeEndEvent,countMember));
            }
        }
    }

    public Event findEventByName(String name) {
        for (Event event : events) {
            if (event.isName(name)) {
                return event;
            }
        }
        return null;
    }

    public ArrayList<Event> getEvents() {
        return events;
    }
}
