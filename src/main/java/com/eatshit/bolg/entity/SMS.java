package com.eatshit.bolg.entity;

import lombok.Data;

@Data
public class SMS {
    //短信ID
    private int id;
    //手机号码
    private String phone;
    //短信类型 0:手机号注册
    private int type;
    //短信内容
    private String message;
    //短信状态 0:未发送，1:已发送，2:发送失败
    private int state;
    //创建时间
    private long createTime;
    //操作时间
    private long updateTime;
    //备注
    private String remark;
}
