package com.cloud.tokenservice.validator;

import com.cloud.tokenservice.error.ErrorCodes;
import com.cloud.tokenservice.exception.DistributionException;
import com.cloud.tokenservice.model.Distribution;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DistributionValidator
        implements ConstraintValidator<DistributionConstraint, Distribution> {
    @Override
    public void initialize(DistributionConstraint contactNumber) {
    }

    @Override
    public boolean isValid(
            Distribution distribution, ConstraintValidatorContext cxt) {
        int sum = distribution.getGroups()
                              .stream()
                              .mapToInt(group -> group.getPercentage())
                              .sum();
        if (sum > 100) {
            throw new DistributionException(
                    ErrorCodes.DISTRIBUTION_GREATER_MAX.constructError());
        } else if (sum < 0) {
            throw new DistributionException(
                    ErrorCodes.DISTRIBUTION_LESSER_MIN.constructError());
        }
        return true;
    }

}
