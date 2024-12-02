package cs211.project.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Team {
    private int teamId;
    private String teamName;
    private int countMember;
    private int maxMember;
    private LocalDate openDate;
    private LocalTime openTime;
    private LocalDate closeDate;
    private LocalTime closeTime;
    private Boolean timeCheck;

    public Team(String teamId, String name, String maxMember, String openDate, String openTime, String closeDate, String closeTime){
        this.teamId = Integer.parseInt(teamId);
        this.teamName = name;
        this.maxMember = Integer.parseInt(maxMember);
        this.openDate = LocalDate.parse(openDate);
        this.openTime = LocalTime.parse(openTime);
        this.closeDate = LocalDate.parse(closeDate);
        this.closeTime = LocalTime.parse(closeTime);
        this.countMember = 0;
    }
    
    public Team(String teamId, String name, String maxMember, String openDate, String openTime, String closeDate, String closeTime, String countMember){
        this.teamId = Integer.parseInt(teamId);
        this.teamName = name;
        this.maxMember = Integer.parseInt(maxMember);
        this.openDate = LocalDate.parse(openDate);
        this.openTime = LocalTime.parse(openTime);
        this.closeDate = LocalDate.parse(closeDate);
        this.closeTime = LocalTime.parse(closeTime);
        this.countMember = Integer.parseInt(countMember);
    }

    public int getTeamId() {return teamId;}

    public String getTeamName() {return teamName;}

    public int getCountMember(){return countMember;}

    public int getMaxMember() {return maxMember;}

    public LocalDate getOpenDate() {return openDate;}

    public LocalTime getOpenTime() {return openTime;}

    public LocalDate getCloseDate() {return closeDate;}

    public LocalTime getCloseTime() {return closeTime;}


    public boolean isTeamName(String name) {
        return this.teamName.equals(name);
    }

    public boolean isTeamId(int team_id){
        return this.teamId == team_id;
    }

    public void addCountMember(){
        countMember++;
    }

    public boolean checkMember(){
        if (countMember < maxMember){
            return true;
        }
        return false;
    }

    public void checkTime(){
        LocalDateTime localDateTimeEnd =LocalDateTime.of(closeDate,closeTime);
        if(localDateTimeEnd.isAfter(LocalDateTime.now())){
            timeCheck = true;
        }
        else {
            timeCheck = false;
        }
    }

    public Boolean getTimeCheck() {
        return timeCheck;
    }
}
