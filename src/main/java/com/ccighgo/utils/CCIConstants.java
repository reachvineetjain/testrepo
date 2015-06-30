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
    public static final String MM_DD_YY = "MM/dd/yy";
    public static final String MM_dd_yyy = "MM-dd-yyyy";
    
    public static final String ALPHABETS_UPPER_CASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String ALPHABETS_LOWER_CASE = "abcdefghijklmnopqrstuvwxyz";
    public static final String NUMBERS = "0123456789";
    public static final String SPECIAL_CHARACTERS = "!@#$^&*_";
    
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
	public static final int EMPTY_INTEGER_FIELD = -1;
	
	//spaces, hyphens, literals, commas etc
	public static final String EMPTY_DATA = "";
   public static final String HYPHEN_SPACE = " - ";
   public static final String SINGLE_QUOTE = "'";
   public static final String COMMA = ",";
   public static final Integer DRAFT_STATUS_NO = 3;
   
   //constants for department programs
   public static final String HSP_J1_HS = "J-1HS";
   public static final String HSP_F1 = "F-1";
   public static final String HSP_STP_IHP = "STP-IHP";
   public static final String HSP_STP_GHP = "STP-GHP";
   public static final String HSP_STP_SSE = "STP-SSE";
   public static final String WP_WT_SUMMER = "W&T Summer";
   public static final String WP_WT_WINTER = "W&T Winter";
   public static final String WP_WT_SPRING = "W&T Spring";
   public static final String WP_WT_CAP = "W&T CAP";
   public static final String GHT_HS_ABRD = "HS Abroad";
   public static final String GHT_LANG_SCL = "Language School";
   public static final String GHT_TEACH_ABRD = "Teach Abroad";
   public static final String GHT_VOL_ABRD = "Volunteer Abroad";
   public static final String GHT_WRK_ABRD = "Work Abroad";
   
   //bind urls for season program menu
   public static final String HSP_J1_URL= "j1hs/details/view/";
   public static final String HSP_F1_URL = "f1/view/details/";
   public static final String GHT_VA_URL = "ght/va/details/view/";
   public static final String GHT_WA_URL = "ght/wa/details/view/";
   
   public static final String DEPT_HIGH_SCHOOL_PROGRAMS = "High School Programs";
   public static final String DEPT_WORK_PROGRAMS = "Work Programs";
   public static final String DEPT_GREEN_HEART_TRAVEL = "Greenheart Travel";
   public static final String DEPT_GREEN_HEART_CLUB = "Greenheart Club";
   public static final String DEPT_GREEN_HEART_TRANSFORMS = "Greenheart Transforms";
   public static final String DEPT_ACCOUNTING = "Accounting";
   public static final String DEPT_SYSTEM= "System";
   
   

}
