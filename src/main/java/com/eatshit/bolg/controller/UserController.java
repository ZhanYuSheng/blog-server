package com.eatshit.bolg.controller;

import com.eatshit.bolg.common.JsonResponse;
import com.eatshit.bolg.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    /**
     * 手机号注册
     *
     * @param phone 手机号
     * @param password 密码(MD5加密后)
     * @param verifyCode 短信验证码(非必填)
     * @param invitorId 邀请码(非必填)
     * @param username 用户昵称
     * @return
     */
    @RequestMapping(value = "/phoneRegister")
    public JsonResponse<Void> phoneRegister(
            @RequestParam String phone, @RequestParam String password,
            @RequestParam(value = "verify_code", required = false, defaultValue = "6666") String verifyCode,
            @RequestParam(value = "invitor_id", required = false, defaultValue = "0") int invitorId, @RequestParam String username){
        return userService.phoneRegister(phone, password, verifyCode, invitorId, username);
    }

    /**
     * 手机号登录
     *
     * @param phone
     * @param password
     * @return
     */
    @RequestMapping(value = "/phoneLogin")
    public JsonResponse<HashMap<String, Object>> phoneLogin(@RequestParam String phone, @RequestParam String password){
        return userService.phoneLogin(phone, password);
    }
}
