package com.eatshit.bolg.service;

import com.eatshit.bolg.common.JsonResponse;
import com.eatshit.bolg.entity.Mail;
import com.eatshit.bolg.entity.Sms;
import com.eatshit.bolg.entity.User;
import com.eatshit.bolg.exception.ServiceException;
import com.eatshit.bolg.mapper.MailMapper;
import com.eatshit.bolg.mapper.SmsMapper;
import com.eatshit.bolg.mapper.UserMapper;
import com.eatshit.bolg.util.StringUtil;
import com.eatshit.bolg.util.TimeUtils;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
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
    @Autowired
    private MailMapper mailMapper;

    @Override
    public JsonResponse<Void> phoneRegister(
            String phone, String password, String verifyCode, int invitorId, String userName) {
        Long now = System.currentTimeMillis();
        Sms sms = smsMapper.selectVerifyCode(phone);
        //检测验证码是否正确
        if (sms == null || now > sms.getCreateTime() + TimeUtils.MILLISECOND_ONE_DAY || !sms.getMessage().equals(verifyCode)){
            throw ServiceException.VERIFY_CODE_ERROR;
        }
        //检测手机号是否被注册
        if (userMapper.phoneExist(phone))
            throw ServiceException.PHONE_EXIST;
        //检测用户名是否存在
        checkUserName(userName);
        //生成盐
        String salt = StringUtil.saltGenerate();
        //密码加盐
        String passwordSalt = DigestUtils.md5DigestAsHex((password + salt).getBytes());

        User user = new User().builder().phone(phone).password(passwordSalt).salt(salt).
                invitorId(invitorId).registerTime(System.currentTimeMillis()).username(userName).build();
        //写入用户表
        userMapper.insert(user);
        return new JsonResponse<>();
    }

    private void checkUserName(String username) {
        //用户名不能为纯数字，也不能带有@
        if (StringUtils.isNumeric(username) || username.contains("@")){
            throw ServiceException.USERNAME_ERROR;
        }
        if (userMapper.userNameExist(username)){
            throw ServiceException.USERNAME_EXIST;
        }
    }

    @Override
    public JsonResponse<HashMap<String, Object>> phoneLogin(String phone, String password) {
        //获取用户信息
        User user = userMapper.selectUserByPhone(phone);
        return login(password, user);
    }

    private JsonResponse<HashMap<String, Object>> login(String password, User user) {
        if (user == null)
            throw ServiceException.USERNAME_OR_PASSWORD_ERROR;
        String passwordSalt = DigestUtils.md5DigestAsHex((password + user.getSalt()).getBytes());
        if (!user.getPassword().equalsIgnoreCase(passwordSalt))
            throw ServiceException.USERNAME_OR_PASSWORD_ERROR;
        //登录验证完成,生成token
        Map<String, Object> tokenMap = new HashMap<>();
        String tokenValue = user.getUsername() + "|" + password + "|" + System.currentTimeMillis();
        String tokenKey = DigestUtils.md5DigestAsHex(tokenValue.getBytes());
        redis.opsForValue().set(tokenKey, tokenValue, 7, TimeUnit.DAYS);
        tokenMap.put("token", tokenKey);
        return new JsonResponse(tokenMap);
    }

    @Override
    public JsonResponse<Void> mailRegister(String mail, String password, String verifyCode, int invitorId, String userName) {
        Long now = System.currentTimeMillis();
        //检测验证码是否正确
        Mail verifyMail = mailMapper.selectVerifyCode(mail);
        String verifyMessage = verifyMail.getMessage();
        Integer start = verifyMessage.indexOf("【")+1;
        Integer end = verifyMessage.indexOf("】");
        String tableCode = verifyMessage.substring(start, end);
        if(now > verifyMail.getCreateTime() + TimeUtils.MILLISECOND_ONE_DAY || !tableCode.equalsIgnoreCase(verifyCode))
            throw ServiceException.VERIFY_CODE_ERROR;
        //检查邮箱格式
        if (!(mail.contains("@") && mail.contains(".")))
            throw ServiceException.EMAIL_ERROR;
        //检测邮箱是否被注册
        if (userMapper.mailExist(mail))
            throw ServiceException.EMAIL_EXIST;
        //检测用户名是否存在
        checkUserName(userName);
        //生成盐
        String salt = StringUtil.saltGenerate();
        //密码加盐
        String passwordSalt = DigestUtils.md5DigestAsHex((password + salt).getBytes());
        Long createTime = System.currentTimeMillis();
        User user = new User(mail, userName, passwordSalt, salt, createTime);
        //写入用户表
        userMapper.insert(user);
        return new JsonResponse<>();
    }

    @Override
    public JsonResponse<HashMap<String, Object>> mailLogin(String address, String password) {
        //获取用户信息
        User user = userMapper.selectUserByMail(address);
        return login(password, user);
    }

    @Override
    public JsonResponse<Void> forget(String username, String password, String verifyCode, String emailCode) {
        User user = userMapper.selectUser(username);
        if (user == null)
            throw ServiceException.USER_NOT_EXIST;
        //生成新的盐
        String salt = StringUtil.saltGenerate();
        password = DigestUtils.md5DigestAsHex((password + salt).getBytes());
        user.setPassword(password);
        userMapper.updatePassword(user);
        return new JsonResponse<>();
    }
}
