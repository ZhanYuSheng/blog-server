package com.eatshit.bolg.service;

import com.eatshit.bolg.common.JsonResponse;
import com.eatshit.bolg.entity.BlogTypeConfig;
import com.eatshit.bolg.mapper.BlogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service层
 * @Service 该注解声明该类为Service层
 * 注：该层负责处理业务逻辑。
 */
@Service
public class BlogServiceImpl implements IBlogService {
    @Autowired
    private BlogMapper blogMapper;

    public JsonResponse<BlogTypeConfig> demo() {
        BlogTypeConfig blogTypeConfig = blogMapper.demo();
        return new JsonResponse<>(blogTypeConfig);
    }
}
