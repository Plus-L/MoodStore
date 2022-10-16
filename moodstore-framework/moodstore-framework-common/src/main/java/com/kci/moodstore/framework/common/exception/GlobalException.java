package com.kci.moodstore.framework.common.exception;

import lombok.Getter;

/**
 * @program: moodstore
 * @description: 全局自定义异常类
 * @author: PlusL
 * @create: 2022-10-16 14:56
 **/
public class GlobalException extends RuntimeException{


    private static final String FAIL_CODE = "400";

    @Getter
    private final String code;

    @Getter
    private final String errorMessage;

    public GlobalException() {
        this(FAIL_CODE, null);
    }

    public GlobalException(String code, String errorMessage) {
        this.code = code;
        this.errorMessage = errorMessage;
    }

    public GlobalException(String errorMessage) {
        this(FAIL_CODE, errorMessage);
    }

    public GlobalException(Throwable cause) {
        this(FAIL_CODE, null, cause);
    }

    public GlobalException(Throwable cause, String errorMessage) {
        this(FAIL_CODE, errorMessage, cause);
    }

    public GlobalException(String code, String errorMessage, Throwable cause) {
        super(errorMessage, cause);
        this.code = code;
        this.errorMessage = errorMessage;
    }

}
