package com.eatshit.bolg.controller;

import com.eatshit.bolg.common.JsonResponse;
import com.eatshit.bolg.service.IMailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;

@RestController()
@RequestMapping("/mail")
@Api(tags = "邮件模块")
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
    @PostMapping(value = "/sendVerifyCode")
    @ApiOperation(value = "发送邮箱验证码", notes = "")
    public JsonResponse sendVerifyCode(
            @RequestParam @ApiParam(name="address",value="邮箱地址",required = true) String address,
            @ApiIgnore() HttpServletRequest request){
        return mailService.sendVerifyCode(address, request);
    }
}
