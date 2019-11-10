package com.eatshit.bolg.service;

import com.eatshit.bolg.common.JsonResponse;
import com.eatshit.bolg.mapper.SmsMapper;
import com.eatshit.bolg.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private StringRedisTemplate redis;
    @Autowired
    private SmsMapper smsMapper;

    public JsonResponse<Void> phoneRegister(String phone, String password, String verifyCode) {
        //检测验证码是否正确
        boolean hasCode = smsMapper.checkVerifyCode(verifyCode);
        //检测手机号是否被注册
        //生成盐
        //密码加盐
        //写入用户表
        return new JsonResponse<>();
    }
}
