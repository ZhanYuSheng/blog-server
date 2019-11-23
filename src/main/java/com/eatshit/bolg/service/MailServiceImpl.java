package com.eatshit.bolg.service;

import com.eatshit.bolg.common.JsonResponse;
import com.eatshit.bolg.component.HttpComponent;
import com.eatshit.bolg.entity.Mail;
import com.eatshit.bolg.mapper.MailMapper;
import com.eatshit.bolg.util.Constant;
import com.eatshit.bolg.util.IntegerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class MailServiceImpl implements IMailService {
    @Autowired
    private MailMapper mailMapper;
    @Autowired
    private HttpComponent httpComponent;

    @Value("${mail.verify.code.template}")
    private String mailTemplate;

    private final static Long  SEND_VERIFY_CODE_LIMIT_TIME = 10L;

    @Override
    public JsonResponse sendVerifyCode(String address, HttpServletRequest request) {
        //接口调用次数限制
        httpComponent.limitTime(request, Constant.SEND_MAIL_VERIFY_CODE, SEND_VERIFY_CODE_LIMIT_TIME);
        Integer verifyCode = IntegerUtil.createVerifyCode();
        Long createTime = System.currentTimeMillis();
        Long updateTime = createTime;
        String message = String.format(mailTemplate, verifyCode);
        Mail mail = new Mail(address, Constant.REGISTER_BY_MAIL, message,
                Constant.MAIL_NOT_SEND, createTime, updateTime, "");
        mailMapper.insert(mail);
        return new JsonResponse();
    }
}
