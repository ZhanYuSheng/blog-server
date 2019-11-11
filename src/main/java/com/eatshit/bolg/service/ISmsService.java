package com.eatshit.bolg.service;

import com.eatshit.bolg.common.JsonResponse;

public interface ISmsService {
    JsonResponse<Void> sendVerifyCode(String phone);
}
