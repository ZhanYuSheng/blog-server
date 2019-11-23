package com.eatshit.bolg.mapper;

import com.eatshit.bolg.entity.Mail;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MailMapper {

    @Insert("INSERT INTO t_mail (address, type, message, state, create_time, update_time, remark) VALUES" +
            "(#{address}, #{type}, #{message}, #{state}, #{createTime}, #{updateTime}, #{remark})")
    void insert(Mail mail);

}
