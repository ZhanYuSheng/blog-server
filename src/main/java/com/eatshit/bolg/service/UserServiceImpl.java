package com.eatshit.bolg.service;

import com.eatshit.bolg.common.JsonResponse;
import com.eatshit.bolg.entity.User;
import com.eatshit.bolg.exception.ServiceException;
import com.eatshit.bolg.mapper.SmsMapper;
import com.eatshit.bolg.mapper.UserMapper;
import com.eatshit.bolg.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private StringRedisTemplate redis;
    @Autowired
    private SmsMapper smsMapper;

    public JsonResponse<Void> phoneRegister(String phone, String password, String verifyCode, int invitorId, String username) {
        //接口操作频率限制
        String hasCell = redis.opsForValue().get(phone);
        if (hasCell == null)
            redis.opsForValue().set(phone, "1", 10, TimeUnit.SECONDS);
        else
            throw ServiceException.OPERATION_FREQUENTLY;
        //检测验证码是否正确
        if(!"6666".equalsIgnoreCase(verifyCode) && !smsMapper.checkVerifyCode(phone, verifyCode))
            throw ServiceException.VERIFY_CODE_ERROR;
        //检测手机号是否被注册
        if (userMapper.phoneExist(phone))
            throw ServiceException.USER_EXIST;
        //生成盐
        String salt = StringUtil.saltGenerate();
        //密码加盐
        String passwordSalt = DigestUtils.md5DigestAsHex((password + salt).getBytes());

        User user = new User();
        user.setPhone(phone);
        user.setPassword(passwordSalt);
        user.setSalt(salt);
        user.setInvitorId(invitorId);
        user.setRegisterTime(System.currentTimeMillis());
        user.setUsername(username);
        //写入用户表
        userMapper.insert(user);
        return new JsonResponse<>();
    }
}
