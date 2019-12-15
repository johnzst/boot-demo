/*
 * RT MAP, Home of Professional MAP
 * Copyright 2016 Bit Main Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 */

package com.example.bootDemo.common.model.response;

/**
 * 通用响应异常
 *
 * @author John
 */
public class ResponseException extends RuntimeException {

    private static final long serialVersionUID = 5183671232767669805L;

    private int code;
    private String message;

    public ResponseException() {
        super();
    }

    public ResponseException(Throwable cause) {
        super(cause);
    }

    public ResponseException(String message) {
        super(message);
        this.message = message;
    }

    public ResponseException(int code, String message) {
        super(code + "");
        this.code = code;
        this.message = message;
    }

    public ResponseException(int code) {
        super(code + "");
        this.code = code;
    }

    public ResponseException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

}
