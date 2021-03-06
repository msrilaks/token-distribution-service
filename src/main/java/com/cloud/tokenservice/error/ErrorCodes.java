package com.cloud.tokenservice.error;

import com.cloud.tokenservice.model.Error;

import static com.cloud.tokenservice.error.ErrorConstants.*;

public enum ErrorCodes {
    APPLICATION_ERROR(1, APP_ERR_MSG, APP_ERR_MSG),
    DISTRIBUTION_NOT_FOUND(2, DISTRIBUTION_NOT_FOUND_MSG,
                           DISTRIBUTION_NOT_FOUND_MSG),
    DISTRIBUTION_GREATER_MAX(3, DISTRIBUTION_GREATER_MAX_MSG,
                             DISTRIBUTION_GREATER_MAX_MSG),
    DISTRIBUTION_LESSER_MIN(4, DISTRIBUTION_LESSER_MIN_MSG,
                            DISTRIBUTION_LESSER_MIN_MSG);

    private int    errorCode;
    private String errorMessage;
    private String errorDescription;

    ErrorCodes(int errorCode, String errorMessage, String errorDescription) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.errorDescription = errorDescription;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public Error constructError() {
        return new Error(errorCode, errorMessage, errorDescription);
    }
}
