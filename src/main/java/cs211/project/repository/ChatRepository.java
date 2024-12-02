package cs211.project.repository;

import cs211.project.models.Chat;
import cs211.project.services.ChatDatasource;
import cs211.project.services.Datasource;

public class ChatRepository {
    private Chat chat;
    private Datasource<Chat> datasource;

    public ChatRepository(){
        datasource = new ChatDatasource("data", "chat.csv");
        chat = datasource.readData();
    }

    public void save(Chat chat){ datasource.writeData(chat); }

    public Chat getChat(){return chat;}

}
