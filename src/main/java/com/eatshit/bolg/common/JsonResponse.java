package com.eatshit.bolg.common;

import lombok.Data;

@Data
public class JsonResponse<T> {
    private int code = 200;
    private String message = "OK";
    private T data;

    public JsonResponse() {
    }

    public JsonResponse(T data) {
        this.data = data;
    }

    public JsonResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
