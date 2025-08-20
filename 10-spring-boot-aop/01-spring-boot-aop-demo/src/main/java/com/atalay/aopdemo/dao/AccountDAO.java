package com.atalay.aopdemo.dao;

import com.atalay.aopdemo.Account;

public interface AccountDAO {

    void addAccount(Account account, boolean vipFlag);

    boolean doWork();
}
