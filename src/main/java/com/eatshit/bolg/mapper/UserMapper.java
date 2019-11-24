package com.eatshit.bolg.mapper;

import com.eatshit.bolg.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {

    @Select("SELECT 1=(SELECT COUNT(*) FROM t_user WHERE phone = #{phone})")
    boolean phoneExist(String phone);

    @Insert("INSERT INTO t_user VALUES(null, #{mail}, #{phone}, #{username}, #{password}, #{salt}, #{invitorId}, #{registerTime}, #{remark})")
    void insert(User user);

    @Select("SELECT * FROM t_user WHERE phone = #{phone}")
    User selectUserByPhone(String phone);

    @Select("SELECT 1=(SELECT COUNT(*) FROM t_user WHERE username = #{username})")
    boolean userNameExist(String username);

    @Select("SELECT 1=(SELECT COUNT(*) FROM t_user WHERE mail = #{mail})")
    boolean mailExist(String mail);

    @Select("SELECT * FROM t_user WHERE mail = #{mail}")
    User selectUserByMail(String mail);

    @Select("SELECT * FROM t_user WHERE phone = #{username} OR mail = #{username} OR username = #{username}")
    User selectUser(String username);

    @Update("UPDATE t_user SET password = #{password} where id = #{id}")
    void updatePassword(User user);
}
