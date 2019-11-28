package com.eatshit.bolg.mapper;

import com.eatshit.bolg.entity.Sms;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SmsMapper {

    @Insert("INSERT INTO t_sms (phone, type, message, state, create_time, update_time, remark) VALUES " +
            "(#{phone}, #{type}, #{message}, #{state}, #{createTime}, #{updateTime}, #{remark})")
    void insert(Sms sms);

    @Select("SELECT * FROM t_sms WHERE phone = #{phone} AND type = 0 AND state = 1 ORDER BY id DESC LIMIT 0, 1")
    Sms selectVerifyCode(String phone);
}
