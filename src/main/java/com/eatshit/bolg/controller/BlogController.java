package com.eatshit.bolg.controller;

import com.eatshit.bolg.common.JsonResponse;
import com.eatshit.bolg.entity.BlogTypeConfig;
import com.eatshit.bolg.service.BlogServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public JsonResponse<BlogTypeConfig> demo(){
        return blogService.demo();
    }
}
