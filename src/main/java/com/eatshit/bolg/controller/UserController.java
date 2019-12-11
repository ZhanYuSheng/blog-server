package com.eatshit.bolg.controller;

import com.eatshit.bolg.common.JsonResponse;
import com.eatshit.bolg.service.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/user")
@Api(tags = "用户模块")
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
    @RequestMapping(value = "/phoneRegister", method = RequestMethod.POST)
    @ApiOperation(value = "手机号注册", notes = "")
    public JsonResponse<Void> phoneRegister(
            @RequestParam @ApiParam(name="phone",value="手机号",required = true) String phone,
            @RequestParam @ApiParam(name="password",value="密码(MD5加密)",required = true) String password,
            @RequestParam(value = "verify_code") @ApiParam(name="verify_code",value="短信验证码",required = true) String verifyCode,
            @RequestParam(value = "invitor_id", required = false, defaultValue = "0") @ApiParam(name="invitor_id",value="邀请码") int invitorId,
            @RequestParam(value = "user_name") @ApiParam(name="user_name",value="用户名(昵称)",required = true) String userName){
        return userService.phoneRegister(phone, password, verifyCode, invitorId, userName);
    }

    /**
     * 手机号登录
     *
     * @param phone
     * @param password
     * @return
     */
    @RequestMapping(value = "/phoneLogin", method = RequestMethod.POST)
    @ApiOperation(value = "手机号登录")
    public JsonResponse<HashMap<String, Object>> phoneLogin(
            @RequestParam @ApiParam(name="phone",value="手机号",required = true) String phone,
            @RequestParam @ApiParam(name="password",value="密码(MD5加密)",required = true) String password){
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
    @RequestMapping(value = "/mailRegister", method = RequestMethod.POST)
    @ApiOperation("邮箱注册")
    public JsonResponse<Void> mailRegister(
            @RequestParam @ApiParam(name="address",value="邮箱地址",required = true) String address,
            @RequestParam @ApiParam(name="password",value="密码(MD5加密)",required = true) String password,
            @RequestParam(value = "verify_code") @ApiParam(name="verify_code",value="短信验证码",required = true) String verifyCode,
            @RequestParam(value = "invitor_id", required = false, defaultValue = "0") @ApiParam(name="invitor_id",value="邀请码") int invitorId,
            @RequestParam(value = "user_name") @ApiParam(name="user_name",value="用户名(昵称)",required = true) String userName){
        return userService.mailRegister(address, password, verifyCode, invitorId, userName);
    }

    /**
     * 邮箱登录
     *
     * @param address 邮箱
     * @param password 密码
     * @return
     */
    @RequestMapping(value = "/mailLogin", method = RequestMethod.POST)
    @ApiOperation("邮箱登录")
    public JsonResponse<HashMap<String, Object>> mailLogin(
            @RequestParam @ApiParam(name="address",value="邮箱地址",required = true) String address,
            @RequestParam @ApiParam(name="password",value="密码(MD5加密)",required = true) String password){
        return userService.mailLogin(address, password);
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
