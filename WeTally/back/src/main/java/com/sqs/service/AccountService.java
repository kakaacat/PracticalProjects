package com.sqs.service;

import com.sqs.dao.AccountDao;
import com.sqs.pojo.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author : kaka
 * @Date: 2022-09-10 20:40
 */
@Service
public class AccountService {
    @Autowired
    AccountDao accountDao;

    public List<Account> getAll() {
        List<Account> accountList = accountDao.findAll();
        return accountList;
    }

    public void updateAccount(Account account) {
        accountDao.save(account);
    }
}
