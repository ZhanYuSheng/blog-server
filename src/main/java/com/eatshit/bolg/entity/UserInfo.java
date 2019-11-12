package com.eatshit.bolg.entity;

import lombok.Data;

@Data
public class UserInfo {
    //用户ID
    private Integer id;
    //邮箱
    private String email;
    //手机号
    private String phone;
    //用户昵称
    private String username;
    //邀请人ID
    private Integer invitorId;
    //注册时间
    private Long registerTime;
    //备注
    private String remark;
}
