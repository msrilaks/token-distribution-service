package com.cloud.tokenservice.advice;

import com.cloud.tokenservice.error.ErrorCodes;
import com.cloud.tokenservice.exception.DistributionException;
import com.cloud.tokenservice.exception.TokenException;
import com.cloud.tokenservice.model.Error;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ApplicationControllerAdvice {
    private static final Logger LOGGER = LoggerFactory.getLogger(
            ApplicationControllerAdvice.class.getName());

    @ExceptionHandler(TokenException.class)
    public ResponseEntity<Error> tokenException(final TokenException e) {
        LOGGER.error(e.getApplicationError()
                      .getMessage(), e);
        return new ResponseEntity<Error>(e.getApplicationError(),
                                         HttpStatus.OK);
    }

    @ExceptionHandler(DistributionException.class)
    public ResponseEntity<Error> distributionException(
            final DistributionException e) {
        LOGGER.error(e.getApplicationError()
                      .getMessage(), e);
        return new ResponseEntity<Error>(e.getApplicationError(),
                                         HttpStatus.OK);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> allException(final Exception e) {
        LOGGER.error(ErrorCodes.APPLICATION_ERROR.getErrorMessage(), e);
        return new ResponseEntity<Error>(
                ErrorCodes.APPLICATION_ERROR.constructError(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
