package com.eatshit.bolg.service;

import com.eatshit.bolg.common.JsonResponse;
import com.eatshit.bolg.entity.BlogTag;

import java.util.List;

public interface IBlogService {

    JsonResponse<List<BlogTag>> tagList();
}
