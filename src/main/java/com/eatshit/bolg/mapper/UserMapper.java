package com.eatshit.bolg.mapper;

import com.eatshit.bolg.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("SELECT 1=(SELECT COUNT(*) FROM t_user WHERE phone = #{phone})")
    boolean phoneExist(String phone);

    @Insert("INSERT INTO t_user VALUES(null, #{email}, #{phone}, #{username}, #{password}, #{salt}, #{invitorId}, #{registerTime}, #{remark})")
    void insert(User user);

    @Select("SELECT * FROM t_user WHERE phone = #{phone}")
    User selectUser(String phone);

    @Select("SELECT 1=(SELECT COUNT(*) FROM t_user WHERE username = #{username})")
    boolean userNameExist(String username);
}
