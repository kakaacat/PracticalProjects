package com.sqs.dao;

import com.sqs.pojo.Account;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author : kaka
 * @Date: 2022-09-10 20:41
 */
public interface AccountDao extends JpaRepository<Account, Integer> {
}
