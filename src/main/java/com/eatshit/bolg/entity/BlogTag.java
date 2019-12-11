package com.eatshit.bolg.entity;

import lombok.Data;

@Data
public class BlogTag {
    //标签ID
    private Integer id;
    //标签名称
    private String tagName;
    //上级标签ID
    private Integer higherId;
    //备注
    private String remark;
}
