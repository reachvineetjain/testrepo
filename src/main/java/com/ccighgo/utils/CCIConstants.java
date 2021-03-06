package com.ccighgo.utils;

import java.sql.Timestamp;

/**
 * This class is used to keep all constants to be used across application Add a
 * comment to categorize constants.
 * 
 * @author ravimishra
 * 
 */
public class CCIConstants {

   // !Important: use this constant only for readonly fields
   public static final String EMPTY = "";

   // service status codes
   public static final String SUCCESS = "Success";
   public static final String FAILURE = "Failure";

   // common service message codes
   public static final String TYPE_ERROR = "Error";
   public static final String TYPE_INFO = "Info";
   public static final String SERVICE_SUCCESS = "service.success.message";
   public static final String NO_RECORD = "no.record.found";
   public static final String SEASON_ID_INVALID = "season.id.zero.or.negative";

   // date utility constants
   public static final String MM_DD_YY = "MM/dd/yyyy";
   public static final String DATE_TIME = "MM/dd/yyyy HH:mm:ss";
   public static final String DATE_TIME2 = "MM/dd/yyyy HH:mm:ss";
   public static final String MM_dd_yyy = "MM-dd-yyyy";
   public static final String YYYY_MM_DD = "yyyy/MM/dd";

   public static final String ALPHABETS_UPPER_CASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
   public static final String ALPHABETS_LOWER_CASE = "abcdefghijklmnopqrstuvwxyz";
   public static final String NUMBERS = "0123456789";
   public static final String SPECIAL_CHARACTERS = "!@#$^&*_";

   public static final byte ACTIVE = 1;
   public static final byte INACTIVE = 0;

   public static final String STATUS_ACTIVE = "1";
   public static final String STATUS_INACTIVE = "0";
   public static final String DEFAULT_PAGE = "0";
   public static final String DEFAULT_NO_OF_RECORDS_SIZE = "10";
   public static Timestamp CURRENT_TIMESTAMP = new java.sql.Timestamp(System.currentTimeMillis());
   public static Timestamp CURRENT_TIMESTAMP_M = new Timestamp(System.currentTimeMillis());

   public static final Integer MIN_PASS_LEN = 8;
   public static final Integer MAX_PASS_LEN = 8;
   public static final Integer MAX_UPPER_CASE = 1;
   public static final Integer MAX_NUMBERS = 1;
   public static final Integer MAX_SPL_CHARS = 1;
   public static final String ACTIVE_SEARCH = "'0,1'";

   // user type ids
   public static final Integer CCI_USER_TYPE = 1;
   public static final Integer PARTNER_USER_TYPE = 2;
   public static final int EMPTY_INTEGER_FIELD = -1;
   public static final Integer PARTICIPANT_USER_TYPE = 6;

   // Designation
   public static final String SUB_PARTNER = "Sub_Partner";

   // spaces, hyphens, literals, commas etc
   public static final String EMPTY_DATA = "";
   public static final String HYPHEN_SPACE = " - ";
   public static final String SINGLE_QUOTE = "'";
   public static final String COMMA = ",";
   public static final Integer DRAFT_STATUS_NO = 3;

   // constants for department programs
   public static final String HSP_J1_HS = "J-1HS";
   public static final String HSP_F1 = "F-1";
   public static final String HSP_STP_IHP = "STP-IHP";
   public static final String HSP_STP_GHP = "STP-GHP";
   public static final String HSP_STP_SSE = "STP-SSE";
   public static final String WP_WT_SUMMER = "W&T Summer";
   public static final String WP_WT_WINTER = "W&T Winter";
   public static final String WP_WT_SPRING = "W&T Spring";
   public static final String WP_WT_CAP = "CAP";
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

   // partnerUsers
   public static final String PARTNER_PERMISSIONS_TABLE_NAME = "PartnerPermissions";

   // Partner Agent
   public static final String SEASON_OPTION_1 = "Season Defaults";
   public static final String SEASON_OPTION_2 = "Partner Values";
   public static final String SEMESTER_START = "Start";

   // Partner Notes
   public static final String Work_Travels = "Work&Travels";
   public static final String HSP_J1 = "HSP-J1";
   public static final String GHT = "GHT";
   public static final String STBound = "STBound";
   public static final String Intern = "Intern";
   public static final String Trainee = "Trainee";
   public static final String Meeting_Visit = "Meeting/Visit";
   public static final String CompitetorInfo = "CompitetorInfo";
   public static final String Embassy_VisaInfo = "Embassy/VisaInfo";
   public static final String SeasonInfo = "SeasonInfo";
   public static final String HSPF1 = "HSPF1";

   // bind urls for season program menu
   public static final String HSP_J1_URL = "j1hs/details/view/";
   public static final String HSP_F1_URL = "f1/view/details/";
   public static final String HSP_IHP_URL = "ihp/details/view/";
   public static final String WP_SPRING_URL = "wp/spring/view/details/";
   public static final String WP_SUMM_URL = "wp/summer/view/details/";
   public static final String WP_WINT_URL = "wp/winter/view/details/";
   public static final String WP_CAP_URL = "wp/cap/view/details/";
   public static final String GHT_VA_URL = "ght/va/details/view/";
   public static final String GHT_WA_URL = "ght/wa/details/view/";
   public static final String GHT_TA_URL = "ght/ta/view/";
   public static final String GHT_LS_URL = "ght/ls/view/";
   public static final String GHT_HSA_URL = "ght/hsa/view/details/";

   // TODO use enums
   public static final String DEPT_HIGH_SCHOOL_PROGRAMS = "High School Programs";
   public static final String DEPT_WORK_PROGRAMS = "Work Programs";
   public static final String DEPT_GREEN_HEART_TRAVEL = "Greenheart Travel";
   public static final String DEPT_GREEN_HEART_CLUB = "Greenheart Club";
   public static final String DEPT_GREEN_HEART_TRANSFORMS = "Greenheart Transforms";
   public static final String DEPT_ACCOUNTING = "Accounting";
   public static final String DEPT_SYSTEM = "System";

   // department program options code
   public static final String AUGUST_FY_J1 = "Aug-FY";
   public static final String JANUARY_FY_J1 = "Jan-FY";
   public static final String AUGUST_FSEM_J1 = "Aug-1Sem";
   public static final String JANUARY_SSEM_J1 = "Jan-2Sem";

   public static final String AUGUST_FY_F1 = "Aug-FY";
   public static final String JANUARY_FY_F1 = "Jan-FY";

   public static final String AUGUST_FSEM_F1 = "Aug-1Sem";
   public static final String JANUARY_SSEM_F1 = "Jan-2Sem";

   public static final String JOB_FAIR_SUMMER = "JF";
   public static final String SELF_PLACED_SUMMER = "SP";
   public static final String DIRECT_PLACEMENT_SUMMER = "DP";
   public static final String JOB_FAIR_SPRING = "JF";
   public static final String SELF_PLACED_SPRING = "SP";
   public static final String DIRECT_PLACEMENT_SPRING = "DP";
   public static final String JOB_FAIR_WINTER = "JF";
   public static final String SELF_PLACED_WINTER = "SP";
   public static final String DIRECT_PLACEMENT_WINTER = "DP";
   public static final String CAP_INTERNSHIP = "Int-SP";
   public static final String CAP_TRAINEE = "Trn-SP";

   // department program options id
   public static final Integer AUGUST_FY_J1_ID = 1;
   public static final Integer AUGUST_FY_F1_ID = 5;
   public static final Integer JANUARY_FY_J1_ID = 3;
   public static final Integer JANUARY_FY_F1_ID = 8;
   public static final Integer JOB_FAIR_SUMMER_ID = 10;
   public static final Integer SELF_PLACED_SUMMER_ID = 11;
   public static final Integer DIRECT_PLACEMENT_SUMMER_ID = 12;
   public static final Integer JOB_FAIR_WINTER_ID = 13;
   public static final Integer SELF_PLACED_WINTER_ID = 14;
   public static final Integer DIRECT_PLACEMENT_WINTER_ID = 15;
   public static final Integer JOB_FAIR_SPRING_ID = 16;
   public static final Integer SELF_PLACED_SPRING_ID = 17;
   public static final Integer DIRECT_PLACEMENT_SPRING_ID = 18;
   public static final Integer CAP_INTERNSHIP_ID = 19;
   public static final Integer CAP_TRAINEE_ID = 20;

   public static final String HTTP = "http://";
   public static final String HTTPS = "https://";
   public static final String FTP = "ftp://";

   // Season statuses
   public static final String STATUS_OPEN = "Open";
   public static final String STATUS_CLOSE = "Close";
   public static final String STATUS_DRAFT = "Draft";
   public static final String STATUS_ARCHIVED = "Archived";

   // background check constants
   // ScreenResponse and Status Response node error codes
   public static final String BACKGROUND_CHECK_RESPONSE_MESSAGE_FOR_CODE_0000 = "Request processed successfully";
   public static final String BACKGROUND_CHECK_RESPONSE_MESSAGE_FOR_CODE_1000 = "Invalid XML request";
   public static final String BACKGROUND_CHECK_RESPONSE_MESSAGE_FOR_CODE_2000 = "No XML request";
   public static final String BACKGROUND_CHECK_RESPONSE_MESSAGE_FOR_CODE_3000 = "Authorization failure: Password supplied is invalid.";
   public static final String BACKGROUND_CHECK_RESPONSE_MESSAGE_FOR_CODE_4000 = "Authorization failure: UserID supplied could not be found or is inactive.";
   public static final String BACKGROUND_CHECK_RESPONSE_MESSAGE_FOR_CODE_5000 = "System error. Call TE Technical Support at 1-952-259-3007 or email RHR-IT@TrustedEmployees.com.";

   // Applicant node error codes
   public static final String BACKGROUND_CHECK_RESPONSE_MESSAGE_FOR_CODE_1001 = "[element] is invalid/missing. One or many error message node(s) will be present. See sample error messages below.";

   public static final Integer UNDEFINED_GENDER = 3;

   // Field Staff Code
   public static final Integer FIELD_STAFF_LIST = 0;
   public static final Integer FIELD_STAFF_NETWORK_LIST = 1;
   public static final Integer FieldStaffTypeCode_RD = 3;
   public static final Integer FieldStaffTypeCode_ERD = 5;
   public static final String SERVICE_FAILURE = "service.failure";
   public static final String EQUAL = "=";
   public static final String BITWISE_AND = "&";

   // User Login
   public static final String CCI_USR = "CCI";
   public static final String PARTNER_USER = "PARTNER";
   public static final String FIELD_STAFF_USER = "FS";
   public static final String HOST_FAMILY_USER = "HF";
   public static final String EMPLOYEE_USER = "EMP";
   public static final String PARTICPANT_USER = "PARTICIPANT";
   public static final String PARTNER_AGENT = "PA";
   public static final String RESET_PASSWORD_LINK = "/module/login/#/reset/";
   public static final String RESET_PASSWORD_SUBJECT = "Greenheart Online Password Request";
   public static final String TEST_SUBJECT = "Greenheart Online Test Email";
   public static final String CREATE_CCI_USER_SUBJECT = "Greenheart Online application account creation notification";
   public static final String SERVICE_URL_WORK_QUEUE_CATEGORY_SUBMITTED_TYPE_APPLICATION_1 = "partnerAdmin/workQueueSubmittedApplications/";
   public static final String SERVICE_URL_NDY = "Not Determined Yet";

   // Partner stats
   public static final Integer SEND_LOGIN = 1;
   public static final Integer APPL_J1 = 1;
   public static final Integer APPL_F1 = 3;
   public static final Integer BLACKLIST = 3;
   public static final Integer PENDING_STATUS = 4;
   public static final Integer APPL_IHP = 5;
   public static final Integer APPROVED_STATUS = 5;
   public static final Integer JUNK = 10;
   public static final Integer VALID = 11;
   public static final Integer INVALID = 12;
   public static final Integer DELETED_STATUS = 13;
   public static final String PLACED = "Placed";
   


   public static final String SUBMITTED = "Submitted";
   public static final String PARTNER_REVIEW = "Partner Review";
   public static final String GREENHEART_REVIEW = "Greenheart Review";
   public static final String APPROVED = "Approved";
   public static final String NOT_APPROVED = "Not Approved";

   // Partner Office
   public static final String PRIMARY_OFFICE = "Main";

   // agreement type
   public static final String OPERATING_AGGREMENT = "Operating Agreement";
   public static final byte TRUE_BYTE = 1;
   public static final byte FALSE_BYTE = 0;
   public static final Integer PARTICIPANT_STATUS_PENDING_VERIFICATION = 10;
   public static final String MYSQL_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

   public static final String MYSQL_DATE_FORMAT_SLASH = "MM/dd/yyyy HH:mm:ss";

   // FieldStaff listings
   public static final int LOCAL_COORDINATOR = 1;
   public static final int REGIONAL_MANAGER = 2;
   public static final int REGIONAL_DIRECTOR = 3;
   public static final int AREA_COORDINATOR = 4;
   public static final String LC = "Local Coordinator";
   public static final String RM = "Regional Manager";
   public static final String RD = "Regional Director";
   public static final String AC = "Area Coordinator";
   public static final String ERD = "Executive Regional Director";

   public static final int FAMILY_BASICS = 1;
   public static final int FAMILY_LIFESTYLE = 2;
   public static final int HOUSE_HOME = 3;
   public static final int COMMUNITY = 4;
   public static final int WHY_HOST = 5;
   public static final int PHOTO_ALBUM = 6;
   public static final int REFRENCES = 7;
   public static final int BG_CHECK = 8;
   public static final int SUBMIT_APPLICATION = 9;

   public static final int MALE = 1;
   public static final int FEMALE = 2;
   public static final int GENDER_UNDEFINED = 3;

   public static final String MM_dd_yyy_T_H_M_S = "yyyy-MM-dd'T'HH:mm:ss";
   public static final String MM_dd_yyy_H_M_S = "yyyy-MM-dd HH:mm:ss";

   public static final int SUCCESS_CODE = 200;

   public static final int NO_DATA_CODE = 201;
   public static final int WRONG_PARAM = 212;

   public static final String NO_RECORD_IN_DB = "NO DATA FOUND IN DB!";

   public static final int NULL_PARAM = 213;

   public static final int NULL_PARTNER_ID = 214;

   public static final int INVALID_PARTNER_ID = 214;

   public static final int INVALID_PARAM = 215;

   public static final String NO_UPDATE = "NO UPDATE";   
   //Partner permissions
   public static final Integer Admin = 1;
   public static final Integer Applications = 2;
   public static final Integer Flights = 3;
   public static final Integer PlacementInfo = 4;
   public static final Integer Monitoring = 5;
   public static final Integer AccountingInsurance = 6;
   public static final Integer StudentPreProgram = 7;
   public static final Integer Contracting = 8;
   public static final Integer Insurance = 9;

   public static final Integer NO_SETTED_ERROR_CODE = 11111111;
}
