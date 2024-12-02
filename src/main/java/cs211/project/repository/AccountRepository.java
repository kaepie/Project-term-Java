package cs211.project.repository;

import cs211.project.models.AccountList;
import cs211.project.models.User;
import cs211.project.services.AccountDatasource;
import cs211.project.services.Datasource;

public class AccountRepository {
    private AccountList accounts;
    private Datasource<AccountList> data;

    public AccountRepository(){
        data = new AccountDatasource("data","account.csv");
        accounts = data.readData();
    }

    public void save(AccountList accounts){
        data.writeData(accounts);
    }

    public AccountList getAccounts(){
        return accounts;
    }
}
