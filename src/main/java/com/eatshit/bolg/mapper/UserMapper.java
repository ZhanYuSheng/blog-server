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
    User selectUserByPhone(String phone);

    @Select("SELECT 1=(SELECT COUNT(*) FROM t_user WHERE username = #{username})")
    boolean userNameExist(String username);

    @Select("SELECT 1=(SELECT COUNT(*) FROM t_user WHERE email = #{email}")
    boolean emailExist(String email);

    @Select("SELECT * FROM t_user WHERE email = #{email}")
    User selectUserByEMAIL(String email);

    @Select("SELECT * FROM t_user WHERE phone = #{username} OR email = #{username} OR username = #{username}")
    User selectUser(String username);
}
