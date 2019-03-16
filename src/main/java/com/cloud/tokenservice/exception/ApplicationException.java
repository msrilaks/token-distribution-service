package com.cloud.tokenservice.exception;

import com.cloud.tokenservice.model.Error;

public class ApplicationException extends RuntimeException {
    private Error error;

    public ApplicationException(Error error, Throwable cause) {
        super(error.getMessage(), cause);
        this.error = error;
    }

    public Error getApplicationError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }
}
