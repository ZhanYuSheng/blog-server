package com.eatshit.bolg.service;

import com.eatshit.bolg.common.JsonResponse;
import com.eatshit.bolg.entity.BlogTypeConfig;
import com.eatshit.bolg.exception.ServiceException;
import com.eatshit.bolg.mapper.BlogMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * Service层
 * @Service 该注解声明该类为Service层
 * 注：该层负责处理业务逻辑。
 */
@Service
@Slf4j
public class BlogServiceImpl implements IBlogService {
    @Autowired
    private BlogMapper blogMapper;
    @Autowired
    private StringRedisTemplate redis;

    @Override
    public JsonResponse<BlogTypeConfig> demo() {
        //数据库操作
        BlogTypeConfig blogTypeConfig = blogMapper.demo();

        //异常处理
        if (blogTypeConfig == null)
            throw ServiceException.INTERNAL_ERROR;

        //Redis操作
        redis.opsForValue().set("name", "lida");
        String result = redis.opsForValue().get("name");

        //日志
        log.info("result = {}", result);

        return new JsonResponse<>(blogTypeConfig);
    }
}
