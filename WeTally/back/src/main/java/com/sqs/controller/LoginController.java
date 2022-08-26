package com.sqs.controller;

import com.sqs.pojo.CustomerResult;
import com.sqs.utils.HttpUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author : kaka
 * @Date: 2022-08-25 17:15
 */
@RestController
public class LoginController {

    @RequestMapping("/api/login")
    public CustomerResult login(String code) {
        System.out.println("code==> " + code);
        String urlHead = "https://api.weixin.qq.com/sns/jscode2session";
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put("appid", "wxb24d6b8fe8715408");
        urlParams.put("secret", "592d1457755042327b5e828b4da41b18");
        urlParams.put("js_code", code);
        urlParams.put("grant_type", "authorization_code");

        String httpResult = HttpUtils.getResponse(urlHead, urlParams);
        //System.out.println(httpResult);

        return new CustomerResult(httpResult);
    }
}
