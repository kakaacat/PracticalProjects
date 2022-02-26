package com.sqs.listener;

import com.sqs.pojo.ProductType;
import com.sqs.service.ProductTypeService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.List;

/**
 * @Author : kaka
 * @Date: 2022-02-26 20:11
 */

//@WebListener
public class ProductTypeListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext_*.xml");
        ProductTypeService tservice = (ProductTypeService) context.getBean("ProductTypeServiceImpl");
        List<ProductType> list = tservice.getAllType();
        servletContextEvent.getServletContext().setAttribute("ptlist",list);

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
