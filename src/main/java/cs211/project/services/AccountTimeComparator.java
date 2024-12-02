package cs211.project.services;

import cs211.project.models.Account;

import java.util.Comparator;

public class AccountTimeComparator implements Comparator<Account> {

    @Override
    public int compare(Account o1, Account o2) {
        return o2.getTimeLogin().compareTo(o1.getTimeLogin());
    }
}
