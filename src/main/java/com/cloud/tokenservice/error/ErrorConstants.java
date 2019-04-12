package com.cloud.tokenservice.error;

public class ErrorConstants {
    public static String APP_ERR_MSG                  =
            "An application error occured";
    public static String DISTRIBUTION_NOT_FOUND_MSG   =
            "Distribution not found";
    public static String DISTRIBUTION_GREATER_MAX_MSG =
            "Distribution percentages" + " were found to add up to" +
            " more than 100.";
    public static String DISTRIBUTION_LESSER_MIN_MSG  =
            "Distribution percentages " + "added up to less than 0";
}
