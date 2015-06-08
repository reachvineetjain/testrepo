package com.ccighgo.utils;

import java.sql.Timestamp;
import java.util.Calendar;

/**
 * This class is used to keep all constants to be used across application
 * Add a comment to categorize constants.
 * 
 * @author ravimishra
 * 
 */
public class CCIConstants {
    
    public static final String SUCCESS = "Success";
    public static final String FAILURE = "Failure";
    
    //date utility constants
    public static final String MM_dd_yyy = "MM-dd-yyyy";
    
    public static final String ALPHABETS_UPPER_CASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String ALPHABETS_LOWER_CASE = "abcdefghijklmnopqrstuvwxyz";
    public static final String NUMBERS = "0123456789";
    public static final String SPECIAL_CHARACTERS = "!@#$^&*_";
    
    public static final String EMPTY_DATA = "";
    public static final byte ACTIVE = 1;
    public static final byte INACTIVE = 0;
    public static final String DEFAULT_PAGE = "0";
    public static final String DEFAULT_NO_OF_RECORDS_SIZE = "10";
    public static final Timestamp CURRENT_TIMESTAMP = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
    public static final Integer MIN_PASS_LEN = 8;
    public static final Integer MAX_PASS_LEN = 8;
    public static final Integer MAX_UPPER_CASE = 1;
    public static final Integer MAX_NUMBERS = 1;
    public static final Integer MAX_SPL_CHARS = 1;
    public static final String ACTIVE_SEARCH = "'0,1'";
    
    //user type ids
    public static final Integer CCI_USER_TYPE = 1;

}
