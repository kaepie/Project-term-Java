package cs211.project.pivot;

public class AccountEvent {
    private int accountId;
    private int eventId;
    private String status = "NotBan";

    public AccountEvent(int accId, int evId){
        accountId = accId;
        eventId = evId;
    }
    public AccountEvent(int accId, int evId, String status){
        accountId = accId;
        eventId = evId;
        this.status = status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isAccountId(int id){
        return this.accountId == id;
    }
    public boolean isEventId(int id){
        return this.eventId == id;
    }

    public int getAccountId() {
        return accountId;
    }

    public int getEventId() {
        return eventId;
    }
    public String getStatus(){
        return status;
    }


}
