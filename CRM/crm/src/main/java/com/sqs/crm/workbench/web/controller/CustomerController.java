package com.sqs.crm.workbench.web.controller;

import com.sqs.crm.commons.contants.Contants;
import com.sqs.crm.commons.domain.ReturnObject;
import com.sqs.crm.commons.utils.DateUtils;
import com.sqs.crm.commons.utils.UUIDUtils;
import com.sqs.crm.settings.model.User;
import com.sqs.crm.settings.service.UserService;
import com.sqs.crm.workbench.mapper.ContactsMapper;
import com.sqs.crm.workbench.model.Contacts;
import com.sqs.crm.workbench.model.Customer;
import com.sqs.crm.workbench.service.ContactsService;
import com.sqs.crm.workbench.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author : kaka
 * @Date: 2022-05-29 15:43
 */
@Controller
public class CustomerController {

    @Autowired
    private UserService userService;
    @Autowired
    private CustomerService customerService;

    @RequestMapping("/workbench/customer/index.do")
    public String index(HttpServletRequest request){
        List<User> userList = userService.queryAllUsers();
        request.setAttribute("userList", userList);
        return "workbench/customer/index";
    }

    @RequestMapping("/workbench/customer/saveCustomers.do")
    public @ResponseBody Object saveCustomers(Customer customer, HttpSession session) {
        User user = (User) session.getAttribute(Contants.SESSION_USER);
        //封装参数
        customer.setId(UUIDUtils.getUUID());
        customer.setCreateBy(user.getId());
        customer.setCreateTime(DateUtils.formateDateTime(new Date()));

        ReturnObject returnObject = new ReturnObject();
        try {
            int ret = customerService.saveCustomer(customer);
            if (ret > 0) {
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
            } else {
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage(Contants.RETURN_OBJECT_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage(Contants.RETURN_OBJECT_MESSAGE);
        }

        return returnObject;
    }

    @RequestMapping("/workbench/customer/queryCustomerForPage.do")
    public @ResponseBody Object queryCustomerForPage(String name, String owner, String phone,
                                                     String website, int pageNo, int pageSize) {
        //封装参数
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("owner", owner);
        map.put("phone", phone);
        map.put("website", website);
        map.put("beginNo", (pageNo - 1) * pageSize);
        map.put("pageSize", pageSize);

        //调用service层方法
        List<Customer> customerList = customerService.queryCustomerByConditionForPage(map);
        int totalRows = customerService.queryCountOfCustomerByCondition(map);

        //生产响应信息
        Map<String, Object> retMap = new HashMap<>();
        retMap.put("customerList", customerList);
        retMap.put("totalRows", totalRows);

        return retMap;
    }
}
