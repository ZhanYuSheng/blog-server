package com.eatshit.bolg.entity;

import lombok.Data;

@Data
public class BlogTypeConfig {
    //文章ID
    private Integer id;
    //文章类型
    private String type;
    //上级ID
    private Integer higherId;
    //备注
    private String remark;
}
