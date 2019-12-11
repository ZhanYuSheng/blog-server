package com.eatshit.bolg.controller;

import com.eatshit.bolg.common.JsonResponse;
import com.eatshit.bolg.service.SmsServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sms")
@Api(tags = "短信模块")
public class SmsController {

    @Autowired
    private SmsServiceImpl smsService;

    /**
     * 发送短信验证码
     *
     * @param phone 手机号
     * @return
     */
    @PostMapping("/sendVerifyCode")
    @ApiOperation(value = "发送短信验证码", notes = "")
    public JsonResponse<Void> sendVerifyCode(@RequestParam @ApiParam(name="phone",value="手机号",required = true) String phone){
        return smsService.sendVerifyCode(phone);
    }
}
