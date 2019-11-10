package com.eatshit.bolg.controller;

import com.eatshit.bolg.common.JsonResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sms")
public class SmsController {

    public JsonResponse<Void> sendVerifyCode(){
        return null;
    }
}
