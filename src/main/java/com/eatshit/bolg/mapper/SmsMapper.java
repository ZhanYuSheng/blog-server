package com.eatshit.bolg.mapper;

import com.eatshit.bolg.entity.SMS;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SmsMapper {

    @Select("SELECT #{verifyCode} = (SELECT message FROM t_sms WHERE phone = #{phone} AND state = 1 AND type = 0 ORDER BY id DESC LIMIT 1)")
    boolean checkVerifyCode(String phone, String verifyCode);

    @Insert("INSERT INTO t_sms (phone, type, message, state, create_time, update_time, remark) VALUES " +
            "(#{phone}, #{type}, #{message}, #{state}, #{createTime}, #{updateTime}, #{remark})")
    void insert(SMS sms);
}
