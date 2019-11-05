package com.eatshit.bolg.controller;

import com.eatshit.bolg.common.JsonResponse;
import com.eatshit.bolg.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ResponseAdvice {
    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(ServiceException.class)
    public JsonResponse<Void> handle(ServiceException err) {
        return new JsonResponse<>(err.getCode(), messageSource.getMessage(String.valueOf(err.getCode()), null, null));
    }

    @ExceptionHandler(Throwable.class)
    public JsonResponse<Void> handle(Throwable t) {
        log.error(t.getMessage(), t);
        return handle(ServiceException.INTERNAL_ERROR);
    }
}
