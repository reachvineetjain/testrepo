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
   //TODO use enums
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
   
   public static final Integer HSP_J1_HS_ID = 1;
   public static final Integer HSP_F1_ID = 2;
   public static final Integer HSP_STP_IHP_ID = 3;
   public static final Integer HSP_STP_GHP_ID = 4;
   public static final Integer HSP_STP_SSE_ID = 5;
   public static final Integer WP_WT_SUMMER_ID = 6;
   public static final Integer WP_WT_WINTER_ID = 7;
   public static final Integer WP_WT_SPRING_ID = 8;
   public static final Integer WP_WT_CAP_ID = 9;
   public static final Integer GHT_HS_ABRD_ID = 10;
   public static final Integer GHT_LANG_SCL_ID = 11;
   public static final Integer GHT_TEACH_ABRD_ID = 12;
   public static final Integer GHT_VOL_ABRD_ID = 13;
   public static final Integer GHT_WRK_ABRD_ID = 14;
   
   //bind urls for season program menu
   public static final String HSP_J1_URL= "j1hs/details/view/";
   public static final String HSP_F1_URL = "f1/view/details/";
   public static final String WP_SPRING_URL = "wp/spring/view/details/";
   public static final String WP_SUMM_URL = "wp/summer/view/details/";
   public static final String WP_WINT_URL = "wp/winter/view/details/";
   public static final String WP_CAP_URL = "wp/cap/view/details/";
   public static final String GHT_VA_URL = "ght/va/details/view/";
   public static final String GHT_WA_URL = "ght/wa/details/view/";
   public static final String GHT_TA_URL = "ght/ta/view/";
   public static final String GHT_LS_URL = "ght/ls/view/";
   public static final String GHT_HSA_URL = "ght/hsa/view/details/";
   
   //TODO use enums
   public static final String DEPT_HIGH_SCHOOL_PROGRAMS = "High School Programs";
   public static final String DEPT_WORK_PROGRAMS = "Work Programs";
   public static final String DEPT_GREEN_HEART_TRAVEL = "Greenheart Travel";
   public static final String DEPT_GREEN_HEART_CLUB = "Greenheart Club";
   public static final String DEPT_GREEN_HEART_TRANSFORMS = "Greenheart Transforms";
   public static final String DEPT_ACCOUNTING = "Accounting";
   public static final String DEPT_SYSTEM= "System";
   
   //department program options
   public static final String AUGUST_FY= "Aug-FY";
   public static final String JANUARY_FY= "Jan-FY";
   public static final String JOB_FAIR= "JF";
   public static final String SELF_PLACED= "SF";
   public static final String DIRECT_PLACEMENT= "DP";

}
