package com.exemple.exceptions;

public class ExceptionVO {
    private String message;

    public ExceptionVO(String message) {
        super();
        this.message = message;
    }

    public static final String ERROR_INFO = "ERROR";
    public static final String WARNING_INFO = "WARNING";
    public static final String SUCCESS_INFO = "SUCCESS";

    public static final String TIER_PERSISTENCE = "PERSISTENCE";
    public static final String TIER_BUSINESS_OBJECT = "BUSINESS OBJECT";
    public static final String TIER_APP_SERVICE = "APPLICATION SERVICE";
    public static final String TIER_WEB_SERVICE = "WEB SERVICE";
    public static final String TIER_VIEW = "VIEW";
    public static final String TIER_COMMONS = "COMMONS";
    public static final String TIER_INTEGRATION = "INTEGRATION";


}
