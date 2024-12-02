package cs211.project.pivot;

import java.util.ArrayList;

public class AccountEventList {
    private ArrayList<AccountEvent> list;

    public AccountEventList(){
        list = new ArrayList<>();
    }

    public void addNew(int accId, int evId){
        list.add(new AccountEvent(accId,evId));
    }
    public void addNew(int accId, int evId, String status){
        list.add(new AccountEvent(accId, evId, status));
    }

    public ArrayList<Integer> findEventsByAccount(int accId){
        ArrayList<Integer> result = new ArrayList<>();
        for(AccountEvent accountEvent: list){
            if(accountEvent.isAccountId(accId) && accountEvent.getStatus().equals("NotBan")){
                result.add(accountEvent.getEventId());
            }
        }
        return result;
    }
    public String findStatusByEventId(int eventId, int accId){
        for (AccountEvent accountEvent : list){
            if (accountEvent.isEventId(eventId) && accountEvent.isAccountId(accId)) {
                return accountEvent.getStatus();
            }
        }
        return null;
    }
    public ArrayList<Integer> findAllEventsByAccount(int accId){
        ArrayList<Integer> result = new ArrayList<>();
        for(AccountEvent accountEvent: list){
            if(accountEvent.isAccountId(accId)){
                result.add(accountEvent.getEventId());
            }
        }
        return result;
    }
    public ArrayList<Integer> findAllAccountsByEvent(int evId){
        ArrayList<Integer> result = new ArrayList<>();
        for(AccountEvent accountEvent: list){
            if(accountEvent.isEventId(evId)){
                result.add(accountEvent.getAccountId());
            }
        }
        return result;
    }
    public AccountEvent findAccountInEvent(int accId, int evId){
        for(AccountEvent accountEvent: list){
            if(accountEvent.getAccountId() == accId && accountEvent.getEventId() == evId){
                return accountEvent;
            }
        }
        return null;
    }
    public void ban(int accId, int evId){
        for(AccountEvent accountEvent: list){
            if(accountEvent.getAccountId() == accId && accountEvent.getEventId() == evId){
                accountEvent.setStatus("Ban");
            }
        }
    }
    public void unBan(int accId, int evId){
        for(AccountEvent accountEvent: list){
            if(accountEvent.getAccountId() == accId && accountEvent.getEventId() == evId){
                accountEvent.setStatus("NotBan");
            }
        }
    }

    public ArrayList<AccountEvent> getList() {
        return list;
    }
}
