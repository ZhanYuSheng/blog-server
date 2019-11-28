package com.eatshit.bolg.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 该类禁止展示给用户,推荐使用UserInfo
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    //用户ID
    private Integer id;
    //邮箱
    private String mail;
    //手机号
    private String phone;
    //用户昵称
    private String username;
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

    public User(String mail, String phone, String username, String password, String salt, Integer invitorId, Long registerTime, String remark) {
        this.mail = mail;
        this.phone = phone;
        this.username = username;
        this.password = password;
        this.salt = salt;
        this.invitorId = invitorId;
        this.registerTime = registerTime;
        this.remark = remark;
    }

    public User(String mail, String username, String password, String salt, Integer invitorId, Long registerTime, String remark) {
        this.mail = mail;
        this.username = username;
        this.password = password;
        this.salt = salt;
        this.invitorId = invitorId;
        this.registerTime = registerTime;
        this.remark = remark;
    }

    public User(String mail, String username, String password, String salt, Long registerTime) {
        this.mail = mail;
        this.username = username;
        this.password = password;
        this.salt = salt;
        this.registerTime = registerTime;
    }

    public User(Long registerTime, String username, String password, String salt, String phone) {
        this.phone = phone;
        this.username = username;
        this.password = password;
        this.salt = salt;
        this.registerTime = registerTime;
    }
}
