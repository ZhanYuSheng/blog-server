package com.eatshit.bolg.controller;

import com.eatshit.bolg.common.JsonResponse;
import com.eatshit.bolg.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
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
     * @param request
     * @return
     */
    @RequestMapping(value = "/phoneRegister")
    public JsonResponse<Void> phoneRegister(
            @RequestParam String phone, @RequestParam String password,
            @RequestParam(value = "verify_code", required = false, defaultValue = "6666") String verifyCode,
            @RequestParam(value = "invitor_id", required = false, defaultValue = "0") int invitorId, @RequestParam String username,
            HttpServletRequest request){
        return userService.phoneRegister(phone, password, verifyCode, invitorId, username, request);
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

    /**
     * 邮箱注册
     *
     * @param email 邮箱账号
     * @param password 密码
     * @param verifyCode 邮箱验证码(保留，非必填)
     * @param invitorId 邀请码(非必填)
     * @param username 用户名
     * @param request
     * @return
     */
    @RequestMapping("/emailRegister")
    public JsonResponse<Void> emailRegister(
            @RequestParam String email, @RequestParam String password,
            @RequestParam(value = "verify_code", required = false, defaultValue = "6666") String verifyCode,
            @RequestParam(value = "invitor_id", required = false, defaultValue = "0") int invitorId, @RequestParam String username,
            HttpServletRequest request){
        return userService.emailRegister(email, password, verifyCode, invitorId, username, request);
    }

    /**
     * 邮箱登录
     *
     * @param email 邮箱
     * @param password 密码
     * @return
     */
    @RequestMapping("/emailLogin")
    public JsonResponse<HashMap<String, Object>> emailLogin(@RequestParam String email, @RequestParam String password){
        return userService.emailLogin(email, password);
    }

    public JsonResponse<Void> forget(@RequestParam String username, @RequestParam String password){
        return userService.forget(username, password);
    }
}
