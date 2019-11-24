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
     * @param userName 用户昵称
     * @return
     */
    @RequestMapping(value = "/phoneRegister")
    public JsonResponse<Void> phoneRegister(
            @RequestParam String phone, @RequestParam String password,
            @RequestParam(value = "verify_code") String verifyCode,
            @RequestParam(value = "invitor_id", required = false, defaultValue = "0") int invitorId,
            @RequestParam(value = "user_name") String userName){
        return userService.phoneRegister(phone, password, verifyCode, invitorId, userName);
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
     * @param address 邮箱账号
     * @param password 密码
     * @param verifyCode 邮箱验证码(保留，非必填)
     * @param invitorId 邀请码(非必填)
     * @param userName 用户名
     * @return
     */
    @RequestMapping("/mailRegister")
    public JsonResponse<Void> mailRegister(
            @RequestParam String address, @RequestParam String password,
            @RequestParam(value = "verify_code") String verifyCode,
            @RequestParam(value = "invitor_id", required = false, defaultValue = "0") int invitorId,
            @RequestParam(value = "user_name") String userName){
        return userService.mailRegister(address, password, verifyCode, invitorId, userName);
    }

    /**
     * 邮箱登录
     *
     * @param mail 邮箱
     * @param password 密码
     * @return
     */
    @RequestMapping("/mailLogin")
    public JsonResponse<HashMap<String, Object>> mailLogin(@RequestParam String mail, @RequestParam String password){
        return userService.mailLogin(mail, password);
    }

    /**
     * 忘记密码
     *
     * @param username
     * @param password
     * @param verifyCode
     * @param emailCode
     * @return
     */
    public JsonResponse<Void> forget(
            @RequestParam String username, @RequestParam String password,
            @RequestParam(value = "verify_code", required = false) String verifyCode,
            @RequestParam(value = "email_code")String emailCode){
        return userService.forget(username, password, verifyCode, emailCode);
    }
}
