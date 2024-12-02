package cs211.project.pivot;

public class TeamAccount {
    private int accountId;
    private int teamId;
    private String status = "NotBan";
    private String role = "Member";

    public TeamAccount(int accId, int teamId){
        this.accountId = accId;
        this.teamId = teamId;
    }
    public TeamAccount(int accId, int teamId, String status, String role){
        this.accountId = accId;
        this.teamId = teamId;
        this.status = status;
        this.role = role;
    }

    public boolean isAccountId(int id){
        return this.accountId == id;
    }
    public boolean isTeamId(int id){
        return this.teamId == id;
    }

    public int getAccountId() {
        return accountId;
    }
    public int getTeamId() {
        return teamId;
    }
    public String getStatus(){
        return status;
    }
    public String getRole() {
        return role;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public void setRole(String role) {
        this.role = role;
    }

}
