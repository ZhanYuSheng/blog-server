package com.eatshit.bolg.entity;

import lombok.Data;

/**
 * 该类禁止展示给用户,推荐使用UserInfo
 *
 */
@Data
public class User {
    //用户ID
    private Integer id;
    //邮箱
    private String email;
    //手机号
    private String phone;
    //密码
    private String password;
    //加盐
    private String salt;
    //邀请人ID
    private Integer invitorId;
    //注册时间
    private Long registerTime;
    //备注
    private String remark;
}
