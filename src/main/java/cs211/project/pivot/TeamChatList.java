package cs211.project.pivot;

import java.util.ArrayList;

public class TeamChatList {
    private ArrayList<TeamChat> list;
    public TeamChatList(){list = new ArrayList<>();}
    public void addNew(int teamId, int chatId){
        list.add(new TeamChat(teamId, chatId));
    }
    public ArrayList<Integer> findChatByTeamId(int teamId){
        ArrayList<Integer> result = new ArrayList<>();
        for(TeamChat teamChat : list){
            if(teamChat.isTeamId(teamId)){
                result.add(teamChat.getChatId());
            }
        }
        return result;
    }

    public ArrayList<TeamChat> getList() {
        return list;
    }
}
