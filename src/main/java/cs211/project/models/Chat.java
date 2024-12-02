package cs211.project.models;

import java.util.ArrayList;

public class Chat {
    private ArrayList<ChatText> texts;
    private int lastId = 0;
    public Chat(){
        texts = new ArrayList<>();
    }
    public void addNewText(String chatId, String date, String time, String username, String text){
        chatId.trim();
        date.trim();
        time.trim();
        username.trim();
        if(!text.equals("")){
            ChatText exist = findTextByChatId(Integer.parseInt(chatId));
            if(exist == null){
                texts.add(new ChatText(chatId,date,time,username,text));
                this.lastId = Integer.parseInt(chatId);
            }
        }
    }

    public void sendNewText(String date, String time, String username, String text){
        date.trim();
        time.trim();
        username.trim();

        if(!text.equals("")){
            texts.add(new ChatText(""+(++lastId),date,time,username,text));
        }
    }
    public ChatText findTextByChatId(int chatId){
        for(ChatText text : texts){
            if(text.isChatId(chatId)){
                return text;
            }
        }
        return null;
    }

    public int getLastId() {
        return lastId;
    }

    public ArrayList<ChatText> getChat() {
        return texts;
    }

}
