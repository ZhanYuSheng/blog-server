package com.eatshit.bolg.service;

import com.eatshit.bolg.common.JsonResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public interface IUserService {

    JsonResponse<Void> phoneRegister(String phone, String password, String verifyCode, int invitorId, String userName);

    JsonResponse<HashMap<String, Object>> phoneLogin(String phone, String password);

    JsonResponse<Void> mailRegister(String mail, String password, String verifyCode, int invitorId, String userMame);

    JsonResponse<HashMap<String, Object>> mailLogin(String mail, String password);

    JsonResponse<Void> forget(String username, String password, String verifyCode, String mailCode);
}
