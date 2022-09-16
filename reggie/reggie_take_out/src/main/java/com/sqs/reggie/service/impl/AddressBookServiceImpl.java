package com.sqs.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sqs.reggie.entity.AddressBook;
import com.sqs.reggie.mapper.AddressBookMapper;
import com.sqs.reggie.service.AddressBookService;
import org.springframework.stereotype.Service;

/**
 * @Author : kaka
 * @Date: 2022-09-16 13:55
 * @Description:
 */
@Service
public class AddressBookServiceImpl extends ServiceImpl<AddressBookMapper, AddressBook> implements AddressBookService {
}
