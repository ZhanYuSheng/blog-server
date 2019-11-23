package com.eatshit.bolg.util;

public class IntegerUtil {

    /**
     * 生成四位数验证码
     *
     * @return
     */
    public static Integer createVerifyCode(){
        return (int)((Math.random()*9+1)*1000);
    }
}
