package com.sqs.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sqs.reggie.common.R;
import com.sqs.reggie.entity.User;
import com.sqs.reggie.service.UserService;
import com.sqs.reggie.utils.SMSUtils;
import com.sqs.reggie.utils.ValidateCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @Author : kaka
 * @Date: 2022-09-15 16:56
 * @Description:
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 发送手机验证码
     * @param user
     * @param session
     * @return
     */
    @PostMapping("/sendMsg")
    public R<String> sendMsg(@RequestBody User user, HttpSession session) {
        //获取手机号
        String phone = user.getPhone();

        if (StringUtils.isNotEmpty(phone)) {
            //生成4为验证码
            String code = ValidateCodeUtils.generateValidateCode(4).toString();
            log.info("code --> {}", code);

            //调用阿里云api发送短信
            //SMSUtils.sendMessage("signName", "templateCode", phone, code);

            //保存验证码以备校验
            session.setAttribute(phone, code);

            return R.success("短信发送成功");
        }

        return R.error("短信发送失败");
    }

    /**
     * 用户登录
     * @param map
     * @param session
     * @return
     */
    @PostMapping("/login")
    public R<User> login(@RequestBody Map map, HttpSession session) {
        String phone = map.get("phone").toString();
        String code = map.get("code").toString();

        String sessionCode = session.getAttribute(phone).toString();

        if (sessionCode != null && sessionCode.equals(code)) {
            LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(User::getPhone, phone);

            User user = userService.getOne(queryWrapper);
            //如果是新用户，就自动注册
            if (user == null) {
                user = new User();
                user.setPhone(phone);
                user.setStatus(1);
                userService.save(user);
            }

            session.setAttribute("user", user.getId());

            return R.success(user);
        }

        return R.error("登录失败");
    }


}
