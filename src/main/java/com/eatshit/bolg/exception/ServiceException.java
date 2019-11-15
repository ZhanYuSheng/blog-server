package com.eatshit.bolg.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
public class ServiceException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = -4199626983883864020L;
    private int code;

    ServiceException(int code){
        super("code: " + code);
        this.code = code;
    }

    //unknown error
    public static final ServiceException INTERNAL_ERROR = new ServiceException(10000);
    //验证码错误
    public static final ServiceException VERIFY_CODE_ERROR = new ServiceException(10001);
    //用户名已存在
    public static final ServiceException USERNAME_EXIST = new ServiceException(10002);
    //操作频繁
    public static final ServiceException OPERATION_FREQUENTLY = new ServiceException(10003);
    //用户名或密码错误
    public static final ServiceException USERNAME_OR_PASSWORD_ERROR = new ServiceException(10004);
    //手机号已存在
    public static final ServiceException PHONE_EXIST = new ServiceException(10005);
    //邮箱已存在
    public static final ServiceException EMAIL_EXIST = new ServiceException(10006);
    //邮箱错误
    public static final ServiceException EMAIL_ERROR = new ServiceException(10007);
    //用户名错误
    public static final ServiceException USERNAME_ERROR = new ServiceException(10008);
    //用户不存在
    public static final ServiceException USER_NOT_EXIST = new ServiceException(10009);
}
