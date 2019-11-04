package com.eatshit.bolg.mapper;

import com.eatshit.bolg.common.JsonResponse;
import com.eatshit.bolg.entity.BlogTypeConfig;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface BlogMapper {

    @Select("SELECT * FROM t_blob_type_config")
    BlogTypeConfig demo();
}
