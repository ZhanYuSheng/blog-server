package com.eatshit.bolg.mapper;

import com.eatshit.bolg.entity.BlogTypeConfig;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * Mapper层
 * @Mapper 该注解声明该接口为Mapper层
 *
 * 用户与数据库交互
 */
@Mapper
public interface BlogMapper {

    @Select("SELECT * FROM t_blob_type_config")
    BlogTypeConfig demo();
}
