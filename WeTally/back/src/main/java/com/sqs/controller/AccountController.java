package com.sqs.controller;

import com.sqs.pojo.Account;
import com.sqs.pojo.CustomerResult;
import com.sqs.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author : kaka
 * @Date: 2022-09-10 20:45
 */
@RestController
@RequestMapping("/api/account")
public class AccountController {
    @Autowired
    AccountService accountService;

    @GetMapping("/all")
    public List<Account> getAllRecord() {
        return accountService.getAll();
    }

    @PostMapping("/add")
    public CustomerResult add(@RequestBody Account requestAccount) {
        Account account = new Account();
        account.setCategory(requestAccount.getCategory());
        account.setSubCategory(requestAccount.getSubCategory());
        account.setDate(requestAccount.getDate());
        account.setDesc(requestAccount.getDesc());
        account.setPay(requestAccount.getPay());
        account.setType(requestAccount.getType());
        account.setUserid(requestAccount.getUserid());
        account.setValue(requestAccount.getValue());
        accountService.updateAccount(account);
        return new CustomerResult("ok");
    }
}
