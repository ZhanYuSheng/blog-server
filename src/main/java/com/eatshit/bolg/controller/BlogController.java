package com.eatshit.bolg.controller;

import com.eatshit.bolg.common.JsonResponse;
import com.eatshit.bolg.entity.BlogTypeConfig;
import com.eatshit.bolg.service.BlogServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller层
 *
 * @RestController 该注解声明该类为Controller层，并且返回实体。
 * @RequestMapping 该注解声明地址 如访问Demo方法 /blog/demo
 *
 * 注：该层用户发布接口，不处理业务和数据。
 *      返回实体使用common.JsonResponse
 */
@RestController
@RequestMapping("/blog")
public class BlogController {
    @Autowired
    private BlogServiceImpl blogService;

    /**
     * 测试接口
     *
     * @return
     */
    @RequestMapping("/demo")
    public JsonResponse<BlogTypeConfig> Demo(){
        return blogService.demo();
    }

}
