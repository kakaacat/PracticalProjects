package com.sqs.crm.settings.web.controller;

import com.sqs.crm.commons.contants.Contants;
import com.sqs.crm.commons.domain.ReturnObject;
import com.sqs.crm.commons.utils.DateUtils;
import com.sqs.crm.settings.model.User;
import com.sqs.crm.settings.service.UserService;
import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author : kaka
 * @Date: 2022-03-18 19:56
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/settings/qx/user/toLogin.do")
    public String toLogin(){
        return "settings/qx/user/login";
    }

    @RequestMapping("/settings/qx/user/login.do")
    public  @ResponseBody Object login(String loginAct, String loginPwd, String isRemPwd, HttpServletRequest request,
                                       HttpServletResponse response,
                                       HttpSession session){
        //封装参数
        Map<String, Object> map = new HashMap<>();
        map.put("loginAct", loginAct);
        map.put("loginPwd", loginPwd);
        //调用service层方法，查询用户
        User user = userService.queryUserByLoginActAndPwd(map);

        //根据查询结果生成相应信息
        ReturnObject returnObject = new ReturnObject();
        if (user == null) {
            //登录失败，用户名或密码错误
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("用户名或密码错误");
        } else { //进一步判断账号是否合法
            if (DateUtils.formateDateTime(new Date()).compareTo(user.getExpireTime()) > 0) {
                //登录失败,账号过期
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("账号过期");
            } else if ("0".equals(user.getLockState())) {
                //登录失败，状态被锁定
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("状态被锁定");
            } else if (!user.getAllowIps().contains(request.getRemoteAddr())) {
                //登录失败，ip受限
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("ip受限");
            } else {
                //登录成功
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
                session.setAttribute(Contants.SESSION_USER, user);

                //如果需要记住密码，则往外写cookie
                if ("true".equals(isRemPwd)) {
                    Cookie c1 = new Cookie("loginAct", user.getLoginAct());
                    c1.setMaxAge(3 * 24 * 60 * 60);
                    response.addCookie(c1);
                    Cookie c2 = new Cookie("loginPwd", user.getLoginPwd());
                    c2.setMaxAge(3 * 24 * 60 * 60);
                    response.addCookie(c2);
                } else {
                    //把没有过期的cookie删除
                    Cookie c1 = new Cookie("loginAct", "1");
                    c1.setMaxAge(0);
                    response.addCookie(c1);
                    Cookie c2 = new Cookie("loginPwd", "1");
                    c2.setMaxAge(0);
                    response.addCookie(c2);
                }
            }
        }
        return returnObject;
    }


    //安全退出
    @RequestMapping("/settings/qx/user/logout.do")
    public String logout(HttpServletResponse response, HttpSession session){
        //清空cookie
        Cookie c1 = new Cookie("loginAct", "1");
        c1.setMaxAge(0);
        response.addCookie(c1);
        Cookie c2 = new Cookie("loginPwd", "1");
        c2.setMaxAge(0);
        response.addCookie(c2);
        //销毁session
        session.invalidate();
        //跳转到首页
        return "redirect:/";
    }
}
