package com.eatshit.bolg.service;

import com.eatshit.bolg.common.JsonResponse;

import javax.servlet.http.HttpServletRequest;

public interface IMailService {
    JsonResponse sendVerifyCode(String mail, HttpServletRequest request);
}
