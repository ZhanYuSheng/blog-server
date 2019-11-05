package com.eatshit.bolg.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
public class ServiceException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = -4199626983883864020L;
    private int code;

    ServiceException(int code){
        super("code: " + code);
        this.code = code;
    }

    //unknown error
    public static final ServiceException INTERNAL_ERROR = new ServiceException(10000);
}
