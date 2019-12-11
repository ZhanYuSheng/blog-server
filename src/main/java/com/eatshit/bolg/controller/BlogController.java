package com.eatshit.bolg.controller;

import com.eatshit.bolg.common.JsonResponse;
import com.eatshit.bolg.entity.BlogTag;
import com.eatshit.bolg.service.BlogServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/blog")
public class BlogController {
    @Autowired
    private BlogServiceImpl blogService;

    /**
     * 获取标签列表
     *
     * @return
     */
    @RequestMapping("/tagList")
    public JsonResponse<List<BlogTag>> tagList(){
        return blogService.tagList();
    }

//    public JsonResponse<Void> send(@RequestParam("user_id") Integer userId, @RequestParam("tag_id")Integer tagId,
//                                   @RequestParam()String type, ){
//
//    }
}
