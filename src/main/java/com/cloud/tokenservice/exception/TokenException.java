package com.cloud.tokenservice.exception;

import com.cloud.tokenservice.model.Error;

public class TokenException extends ApplicationException {
    public TokenException(Error error, Throwable cause) {
        super(error, cause);
    }
}
