package com.cloud.tokenservice.exception;

import com.cloud.tokenservice.model.Error;

public class DistributionException extends ApplicationException {
    public DistributionException(Error error, Throwable cause) {
        super(error, cause);
    }
}
