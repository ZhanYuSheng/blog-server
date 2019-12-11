package com.eatshit.bolg.service;

import com.eatshit.bolg.common.JsonResponse;
import com.eatshit.bolg.entity.BlogTag;
import com.eatshit.bolg.mapper.BlogTagMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class BlogServiceImpl implements IBlogService {
    @Autowired
    private BlogTagMapper blogTagMapper;


    @Override
    public JsonResponse<List<BlogTag>> tagList() {
        List<BlogTag> blogTagList = blogTagMapper.tagList();
        return new JsonResponse(blogTagList);
    }
}
