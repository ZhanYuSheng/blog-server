package com.eatshit.bolg.mapper;

import com.eatshit.bolg.entity.BlogTag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BlogTagMapper {

    @Select("SELECT * FROM t_blog_tag")
    List<BlogTag> tagList();
}
