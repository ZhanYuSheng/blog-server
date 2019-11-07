package com.eatshit.bolg.service;

import com.eatshit.bolg.common.JsonResponse;
import com.eatshit.bolg.entity.BlogTypeConfig;

public interface IBlogService {
    JsonResponse<BlogTypeConfig> demo();

    JsonResponse<Void> sendMail(String address, String message);
}
