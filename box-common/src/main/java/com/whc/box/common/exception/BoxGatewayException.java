package com.whc.box.common.exception;

import com.whc.box.common.api.IErrorCode;

public class BoxGatewayException extends RuntimeException {
    private long code;

    private String message;

    public BoxGatewayException(IErrorCode iErrorCode) {
        this.code = iErrorCode.getCode();
        this.message = iErrorCode.getMessage();
    }
}
