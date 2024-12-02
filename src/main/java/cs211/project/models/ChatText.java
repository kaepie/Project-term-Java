package cs211.project.models;

import java.time.LocalDate;
import java.time.LocalTime;

public class ChatText {
    private int chatId;
    private LocalDate date;
    private LocalTime time;
    private String username;
    private String text;

    public ChatText(String chatId, String date, String time, String username, String text){
        this.chatId = Integer.parseInt(chatId);
        this.date = LocalDate.parse(date);
        this.time = LocalTime.parse(time);
        this.username = username;
        this.text = text;
    }

    public int getChatId() {
        return chatId;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public String getUsername() {
        return username;
    }

    public String getText() {
        return text;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isChatId(int chatId){return this.chatId == chatId;}

}
