package com.eatshit.bolg.controller;

import com.eatshit.bolg.common.JsonResponse;
import com.eatshit.bolg.service.IMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController()
@RequestMapping("/mail")
public class MailController {
    @Autowired
    private IMailService mailService;

    /**
     * 发送邮箱验证码
     *
     * @param address
     * @param request
     * @return
     */
    @RequestMapping(value = "/sendVerifyCode")
    public JsonResponse sendVerifyCode(@RequestParam String address, HttpServletRequest request){
        return mailService.sendVerifyCode(address, request);
    }
}
