package com.eatshit.bolg.service;

import com.eatshit.bolg.common.JsonResponse;
import com.eatshit.bolg.entity.SMS;
import com.eatshit.bolg.mapper.SmsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SmsServiceImpl implements ISmsService {

    @Autowired
    private SmsMapper smsMapper;

    @Override
    public JsonResponse<Void> sendVerifyCode(String phone) {
        //生成短信验证码,4位随机数
        int code = (int)((Math.random()*9+1)*1000);

        SMS sms = new SMS();
        sms.setMessage(String.valueOf(code));
        sms.setPhone(phone);
        sms.setState(0);
        sms.setType(0);
        sms.setCreateTime(System.currentTimeMillis());
        //生成短信记录
        smsMapper.insert(sms);
        return new JsonResponse<>();
    }
}
