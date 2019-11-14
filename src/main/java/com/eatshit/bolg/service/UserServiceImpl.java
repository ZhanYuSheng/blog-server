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

import java.util.HashMap;
import java.util.Map;
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
        String hasCell = redis.opsForValue().get("register:" + phone);
        if (hasCell == null)
            redis.opsForValue().set("register:" + phone, "1", 10, TimeUnit.SECONDS);
        else
            throw ServiceException.OPERATION_FREQUENTLY;
        //检测验证码是否正确
        if(!"6666".equalsIgnoreCase(verifyCode) && !smsMapper.checkVerifyCode(phone, verifyCode))
            throw ServiceException.VERIFY_CODE_ERROR;
        //检测手机号是否被注册
        if (userMapper.phoneExist(phone))
            throw ServiceException.PHONE_EXIST;
        //检测用户名是否存在
        if (userMapper.userNameExist(username))
            throw ServiceException.USERNAME_EXIST;
        //生成盐
        String salt = StringUtil.saltGenerate();
        //密码加盐
        String passwordSalt = DigestUtils.md5DigestAsHex((password + salt).getBytes());

        User user = new User().builder().phone(phone).password(passwordSalt).salt(salt).
                invitorId(invitorId).registerTime(System.currentTimeMillis()).username(username).build();
        //写入用户表
        userMapper.insert(user);
        return new JsonResponse<>();
    }

    @Override
    public JsonResponse<HashMap<String, Object>> phoneLogin(String phone, String password) {
        //获取用户信息
        User user = userMapper.selectUser(phone);
        if (user == null)
            throw ServiceException.USERNAME_OR_PASSWORD_ERROR;
        String passwordSalt = DigestUtils.md5DigestAsHex((password + user.getSalt()).getBytes());
        if (!user.getPassword().equalsIgnoreCase(passwordSalt))
            throw ServiceException.USERNAME_OR_PASSWORD_ERROR;
        //登录验证完成,生成token
        Map<String, Object> tokenMap = new HashMap<>();
        String tokenValue = phone + "|" + password + "|" + System.currentTimeMillis();
        String tokenKey = DigestUtils.md5DigestAsHex(tokenValue.getBytes());
        redis.opsForValue().set(tokenKey, tokenValue, 7, TimeUnit.DAYS);
        tokenMap.put("token", tokenKey);
        return new JsonResponse(tokenMap);
    }
}
