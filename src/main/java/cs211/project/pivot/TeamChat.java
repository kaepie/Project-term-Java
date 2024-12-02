package cs211.project.pivot;

public class TeamChat {
    private int teamId;
    private int chatId;

    public TeamChat(int teamId, int chatId){
        this.teamId = teamId;
        this.chatId = chatId;
    }
    public boolean isTeamId(int id){
        return this.teamId == id;
    }
    public int getTeamId() {
        return teamId;
    }
    public int getChatId() {
        return chatId;
    }


}
