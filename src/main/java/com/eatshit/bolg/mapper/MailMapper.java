package com.eatshit.bolg.mapper;

import com.eatshit.bolg.entity.Mail;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface MailMapper {

    @Insert("INSERT INTO t_mail (address, type, subject, message, state, create_time, update_time, remark) VALUES" +
            "(#{address}, #{type}, #{subject}, #{message}, #{state}, #{createTime}, #{updateTime}, #{remark})")
    void insert(Mail mail);

    @Select("SELECT * FROM t_mail WHERE state = #{state}")
    List<Mail> selectByState(Integer state);

    @Update("UPDATE t_mail SET state = #{state}")
    Integer updateState(Mail mail);

    @Select("SELECT message FROM t_mail WHERE address = #{address} AND type = 0 AND state = 1 ORDER BY id DESC LIMIT 0, 1")
    String selectVerifyCode(String address);
}
