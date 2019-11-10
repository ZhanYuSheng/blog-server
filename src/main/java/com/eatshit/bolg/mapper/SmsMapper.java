package com.eatshit.bolg.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SmsMapper {
    boolean checkVerifyCode(String verifyCode);
}
