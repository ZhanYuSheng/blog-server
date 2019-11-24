package com.eatshit.bolg.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Mail {
    //邮件ID
    private int id;
    //邮箱地址
    private String address;
    //邮件类型 0:邮箱注册
    private int type;
    //邮件标题
    private String subject;
    //邮件内容
    private String message;
    //邮件状态 0:未发送，1:已发送，2:发送失败
    private int state;
    //创建时间
    private long createTime;
    //操作时间
    private long updateTime;
    //备注
    private String remark;

    public Mail(String address, int type, String subject, String message, int state, long createTime, long updateTime, String remark) {
        this.address = address;
        this.type = type;
        this.subject = subject;
        this.message = message;
        this.state = state;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.remark = remark;
    }
}
