package com.eatshit.bolg.service;

import com.eatshit.bolg.common.JsonResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public interface IUserService {

    JsonResponse<Void> phoneRegister(String phone, String password, String verifyCode, int invitorId, String username, HttpServletRequest request);

    JsonResponse<HashMap<String, Object>> phoneLogin(String phone, String password);

    JsonResponse<Void> emailRegister(String email, String password, String verifyCode, int invitorId, String username, HttpServletRequest request);

    JsonResponse<HashMap<String, Object>> emailLogin(String email, String password);

    JsonResponse<Void> forget(String username, String password);
}
