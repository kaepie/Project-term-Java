package cs211.project.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Activity {
    private String name;
    private String detail;
    private LocalDate dateStart;
    private LocalDate dateEnd;
    private LocalTime timeStart;
    private LocalTime timeEnd;
    private String status;

    private int id;

    public Activity(String name, String detail,int id,String dateStart,String dateEnd, String timeStart,String timeEnd,String status) {
        this.name = name;
        this.detail = detail;
        this.id = id;
        this.dateStart = LocalDate.parse(dateStart);
        this.dateEnd = LocalDate.parse(dateEnd);
        this.timeStart = LocalTime.parse(timeStart);
        this.timeEnd = LocalTime.parse(timeEnd);
        this.status = status;
    }

    public void editActivity(String name, String detail, LocalDate dateStart, LocalDate dateEnd, String timeStart, String timeEnd) {
        this.name = name;
        this.detail = detail;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.timeStart = LocalTime.parse(timeStart);
        this.timeEnd = LocalTime.parse(timeEnd);
    }

    public void checkTimeActivity(){
        LocalDateTime localDateTime = LocalDateTime.of(dateEnd, timeEnd);
        if (localDateTime.isAfter(LocalDateTime.now())){
            status = "available";
        }
        else{
            status = "Ended";
        }
    }
    public String getName() {
        return name;
    }
    public String getStatus(){
        return  status;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId(){
        return id;
    }
    public String getDetail() {
        return detail;
    }
    public void setDetail(String detail) {
        this.detail = detail;
    }
    public boolean isId(int id){
        return  this.id == id;
    }
    public LocalDate getDateStart() {
        return dateStart;
    }
    public LocalDate getDateEnd() {
        return dateEnd;
    }
    public LocalTime getTimeEnd() {
        return timeEnd;
    }
    public LocalTime getTimeStart() {
        return timeStart;
    }


    public void setTimeStart(String timeStart) {
        this.timeStart = LocalTime.parse(timeStart);
    }
    public void setTimeEnd(String timeEnd) {
        this.timeEnd = LocalTime.parse(timeEnd);
    }

    public void setDateStart(String dateStart) {
        this.dateStart = LocalDate.parse(dateStart);
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = LocalDate.parse(dateEnd);
    }
}
