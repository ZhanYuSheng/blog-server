package com.eatshit.bolg.service;

import com.eatshit.bolg.common.JsonResponse;

import java.util.HashMap;

public interface IUserService {

    JsonResponse<Void> phoneRegister(String phone, String password, String verifyCode, int invitorId, String username);
}
