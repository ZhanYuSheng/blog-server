package com.eatshit.bolg.controller;

import com.eatshit.bolg.common.JsonResponse;
import com.eatshit.bolg.service.SmsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sms")
public class SmsController {

    @Autowired
    private SmsServiceImpl smsService;

    /**
     * 发送短信验证码
     *
     * @param phone 手机号
     * @return
     */
    @RequestMapping("/sendVerifyCode")
    public JsonResponse<Void> sendVerifyCode(@RequestParam String phone){
        return smsService.sendVerifyCode(phone);
    }
}
