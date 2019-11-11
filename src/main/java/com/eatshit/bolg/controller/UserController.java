package com.eatshit.bolg.controller;

import com.eatshit.bolg.common.JsonResponse;
import com.eatshit.bolg.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping(value = "/phoneRegister")
    public JsonResponse<Void> phoneRegister(
            @RequestParam String phone, @RequestParam String password, @RequestParam(value = "verify_code") String verifyCode,
            @RequestParam(value = "invitor_id", required = false, defaultValue = "0") int invitorId){
        return userService.phoneRegister(phone, password, verifyCode, invitorId);
    }
}
