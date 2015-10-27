package com.ccighgo.exception;


public enum ErrorCode {
      UTILITY_SERVICE_CODE(290),
		INVALID_REQUEST(290001),
		INVALID_LOGINNAME(290002),
		INACTIVE_LOGIN(290024),
		INVALID_RESULT_ID(290003),
		INVALID_COUNTRY_CODE(290004),
		INVALID_DATE(290005),
		MISSING_REQUIRED_VALUE(290006),
		NO_RECORD(290007),
		INVALID_NAME(290008),
		DUPLICATE_COUNTRY_NAME(290009),
		DUPLICATE_COUNTRY_CODE(290010),
		ADD_COUNTRY_SERVICE_ERROR(290011),
		FAILED_GET_ALL_COUNTRIES(290012),
		FAILED_GET_ALL_STATES(290013),
		FAILED_GET_ALL_DEPARTMENTS(290014),
		FAILED_GET_ALL_PROGRAMS(290015),
		FAILED_GET_ALL_ROLES(290016),
		FAILED_GET_PROGRAMS_BY_DEPARTMENT(290017),
		FAILED_GET_ALL_USER_DEPARTMENTS(290018),
		FAILED_GET_ALL_REGIONS(290019),
		FAILED_GET_SEASON_STATUS(290020),
		FAILED_GET_GENDERS(290021),
		FAILED_RESET_PASSWORD(290022),
		FAILED_GET_SALUTATIONS(290023),
		RESET_PASSWORD_LINK_EXPIRED(290025),
		FAILED_GET_PROGRAMS_OPTIONS(290026),
		
		INVALID_ADDRESS_ID(213101),
		INVALID_VERIFICATION_CODE(213104),
		INVALID_LANGUAGE_CODE(213105),
		INVALID_USERTYPE_CODE(213106),
		INVALID_TELEPHONE_ID(213108),
		INVALID_EMAIL_ID(213109),
		DUPLICATE_LOGINNAME(213110),
		EMAIL_FAILURE(260000),
		NOT_ALLOWED_MODIFY(212002),
		FAILED_CREATE_ADDRESS(221101),
		FAILED_GET_DEFAULT_ADDRESS(221201),
		FAILED_GET_LIST_ADDRESSES(221301),
		FAILED_UPDATE_ADDRESS(221401),
		FAILED_DELETE_ADDRESS(221501),
		FAILED_CREATE_EMAIL(222101),
		FAILED_GET_DEFAULT_EMAIL(222201),
		FAILED_GET_LIST_EMAILS(222301),
		FAILED_UPDATE_EMAIL(222401),
		FAILED_DELETE_EMAIL(222501),
		FAILED_CREATE_TELEPHONE(223101),
		FAILED_GET_DEFAULT_TELEPHONE(223201),
		FAILED_GET_LIST_TELEPHONES(223301),
		FAILED_UPDATE_TELEPHONE(223401),
		FAILED_DELETE_TELEPHONE(223402),
		FAILED_GET_COMPLETE_USER_INFO(223501),
		FAILED_UPDATE_PERSONAL_INFO(225101),
		FAILED_UPDATE_LOGIN_INFO(225201),
		FAILED_UPDATE_CREDENTIAL(225301),
		SUPPORT_INVALID_ROLE(1009),
		
		//Location Service
		FAILED_GET_DETAILS_LOCATION(260),
		
		//Airport Service
		FAILED_GET_AIRPORTS_NEAR_LOCATION(270),
		
		//Activity Service
		FAILED_LIST_PRIMARY_ACTIVITIES(233001),
		
		//Seasons
		DUPLICATE_SEASON_NAME(2140401),
		
		//Region
		REGION_SERVICE_CODE(601),
		INVALID_ID_REGION_ALL(2160101),
		FAILED_GET_SUP_REGION(2160201),
		INVALID_SEASON_ID(2160301),
		FAILED_GET_SUP_REG_LIST(2160302),
		SUP_REG_NULL(2160401),
		INVALID_SUP_REG_ID(2160402),
		SUP_REG_NAME_NULL(2160402),
		FAILED_SUP_REG_ADD(2160403),
		FAILED_SUP_REG_UPDATE(2160404),
		FAILED_SUP_REG_DELETE(2160405),
		SUP_REG_ALREADY_EXIST(2160406),
		STATE_REGION_LIST(2160501),
		INVALID_REG_ID(2160601),
		FAILED_GET_REGION(2160602),
		FAILED_DELETE_REGION(2160701),
		REG_NAME_NULL(2160702),
		FAILED_ADD_REGION(2160703),
		REG_NULL(2160704),
		FAILED_UPDATE_REGION(2160705),
		STATE_REG_NULL(2160706),
		FAILED_ADD_STATES_REG(2160707),
		REG_ALREADY_EXIST(2160708),
		
		//user management(temp code)
		USER_MANAGEMENT_CODE(220300),
		INVALID_USER_ID(220301),
		FAILED_GET_ALL_USERS(220302),
		FAILED_CREATE_USER(220303),
		FAILED_UPDATE_USER(220304),
		FAILED_UPDATE_USER_PERMISSIONS(220305),
		FAILED_UPDATE_USER_PICTURE(220306),
		INVALID_USER_SEARCH(220307),
		FAILED_DEFAULT_PERMISSIONS_BY_ROLE(220308),
		FAILED_GET_DEPARTMENT_WITH_PERMISSIONS(220309),
		INVALID_PAGE_NUM_OR_SIZE(220310),
		FAILED_USER_NULL(220311),
		FAILED_GET_USER_LIST(220312),
		FAILED_USER_DETAILS_CREATION(220313),
		FAILED_USER_DEPT_PROGRAM_CREATION(220314),
		FAILED_USER_ROLE_CREATION(220315),
		FAILED_USER_PERMISSIONS_CREATION(220316),
		FAILED_UPDATE_PERMISSIONS(220317),
		FAILED_GET_RESOURCE_ACTION_LIST(220318),
		FAILED_USER_NOTES_NULL(220319),
		FAILED_GET_USER_NOTES(220320),
		FAILED_GET_GENDER_LIST(220321),
		USR_MGMT_CREATE_USER_EMAIL_EXIST(220322),
		FAILED_GET_ROLE_BY_DEPARTMENT(220323),
		FAILED_GET_ALL_SUPERVISORS(220324),
		USR_MGMT_CREATE_USER_NAME_EXIST(220325),
		USR_MGMT_UPDATE_USER_EMAIL_EXIST(220326),
		USR_MGMT_UPDATE_USER_NAME_EXIST(220327),
		
		//SeasonList
		SEASON_LIST_SERVICE_CODE(5550555),
		FAILED_GET_SEASON_LIST_SERVICE(5550556), 
		VIEW_SEASON_SERVICE_CODE(5550557), 
		FAILURE_VIEW_SEASON_SERVICE_CODE(5550558),
		
		//SeasonBean
		SEASON_BEAN_SERVICE_CODE(5550559),
		FAILED_EDIT_SEASON_BEAN_SERVICE(5550560),
		FAILED_TO_GET_SEASON_PROGRAMS(5550561),
		FAILED_TO_GET_SEASON_STATUS(5550562),
		FAILED_TO_GET_HSP_J1_HS_SEASON_DETAILS(5550563),
		FAILED_TO_GET_J1_HS_SEASON_NAME_AND_STATUS(5550564),
		FAILED_TO_GET_J1_HS_SEASON_JAN_START_DETAILS(5550565),
		FAILED_TO_GET_HSP_J1_HS_AUG_START(5550566),
		FAILED_TO_GET_HSP_J1_HS_SEASON_FIELD_SETTINGS(5550567),
		FAILED_TO_GET_HSP_J1_HS_PROGRAM_ALLOCATION(5550568),
		FAILED_UPDATE_HSP_J1_HS_SEASON_DETAILS(5550569),
		FAILED_UPDATE_HSP_J1_HS_SEASON_NAME_AND_STATUS(5550570),
		FAILED_TO_UPDATE_J1_HS_JAN_START(5550571),
		FAILED_TO_UPDATE_HSP_J1_HS_AUG_START(5550572),
		FAILED_TO_UPDATE_HSP_J1_HS_SEASON_FIELD_SETTINGS(5550573),
		FAILED_TO_UPDATE_HSP_J1_HS_PROGRAM_ALLOCATION(5550574),
		FAILED_TO_GET_HSP_F1_SEASON_DETAILS(5550575),
		FAILED_UPDATE_F1_DETAILS(5550576),
		FAILED_TO_GET_HSP_F1_NAME_AND_STATUS(5550577),
		FAILED_TO_UPDATE_HSP_F1_NAME_AND_STATUS(5550578),
		FAILED_TO_GET_HSP_F1_ACCOUNTING(5550579),
		FAILED_TO_UPDATE_HSP_F1_ACCOUNTING(5550580),
		FAILED_TO_GET_HSP_F1_JAN_2ND_SEM_DETAILS(5550581),
		FAILED_TO_UPDATE_HSP_F1_JAN_2ND_SEM_DETAILS(5550582),
		FAILED_GET_HSP_F1_JAN_FULL_YEAR_START_DETAIL(5550583),
		FAILED_UPDATE_HSP_F1_JAN_FULL_YEAR_START_DETAIL(5550584),
		FAILED_GET_HSP_F1_AUGUST_1ST_SEMISTER(5550585),
		FAILED_UPDATE_HSP_F1_AUGUST_1ST_SEMISTER(5550586),
		FAILED_GET_HSP_F1_AUGUST_FULL_YEAR_DETAIL(5550587),
		UPDATE_HSP_F1_AUGUST_FULL_YEAR_DETAIL(5550588),
		FAILED_GET_HSP_F1_FIELD_SETTINGS(5550589),
		FAILED_UPDATE_HSP_F1_FIELD_SETTINGS(5550590),
		FAILED_GET_HSP_F1_PROGRAM_ALLOCATION(5550591),
		FAILED_UPDATE_HSP_F1_PROGRAM_ALLOCATION(5550592),
		FAILED_GET_SEASON_GHTVA_DETAILS(5550593),
		FAILED_GET_GHTV_SEASON_NAME_STATUS(5550594),
		FAILED_GET_GHTVA_SEASON_DATE_DEAILS(5550595),
		FAILED_UPDATE_SEASON_GHT_DETAILS(5550596),
		FAILED_UPDAE_GHTV_SEASON_NAME_STATUS(5550597),
		FAILED_UPDATE_GHTVA_SEASON_DATE_DEAILS(5550598),
		FAILED_GET_SEASON_GHTWA_SEASON_DETAILS(5550599),
		FAILED_get_GHTWA_SEASON_NAME_STATUS(5550600),
		FAILED_GET_GHTWA_SEASON_DATE_DEAILS(5550601),
		FAILED_UPDATE_GHTWA_SEASON_DEAILS(5550602),
		FAILED_UPDATE_GHTWA_SEASON_NAME_STATUS(5550603),
		FAILED_UPDATE_GHTWA_SEASON_DATE_DEAILS(5550604),
		FAILED_GET_SEASON_GHT_DEAILS(5550605),
		FAILED_UPDATE_SEASON_GHT_DEAILS(5550606),
		FAILED_GET_GHTHS_SECTION_ONE_BASE_ABROAD(5550607),
		FAILED_UPDATE_GHTHS_SECTION_ONE_BASE_ABROAD(5550608),
		FAILED_GET_GHTHS_SECTION_TWO_DATES_ABROAD(5550609),
		FAILED_UPDATE_GHTHS_SECTION_ONE_TWO_DATES_ABROAD(5550610),
		FAILED_GET_GHT_LANGUAGE_SCHOOL(5550611),
		FAILED_UPDATE_GHT_LANGUAGE_SCHOOL(5550612),
		FAILED_GET_GHT_LANGUAGE_SCHOOL_SECTION_ONE(5550613),
		FAILED_UPDATE_GHT_LANGUAGE_SCHOOL_SECTION_ONE(5550614),
		FAILED_GET_GHT_LANGUAGE_SCHOOL_SECTION_2DATES(5550615),
		FAILED_UPDATE_GHT_LANGUAGE_SCHOOL_SECTION_2DATES(5550616),
		FAILED_GET_GHT_TEACH_ABROAD(5550617),
		FAILED_UPDATE_GHT_TEACH_ABROAD(5550618),
		FAILED_GET_GHT_TEACH_ABROAD_SECTION_ONE(5550619),
		FAILED_UPDATE_GHT_TEACH_ABROAD_SECTION_ONE(5550620),
		FAILED_GET_GHT_TEACH_ABROAD_SECTION_2DATES(5550621),
		FAILED_UPDATE_GHT_TEACH_ABROAD_SECTION_2DATES(5550621),
		FAILED_GET_WPCAP_DETAILS(5550622),
		FAILED_UPDATE_WPCAP_DETAILS(5550623),
		FAILED_GET_WPCAP_BASIC_DETAILS(5550624),
		FAILED_UPDATE_WPCAP_BASIC_DETAILS(5550627),
		FAILED_GET_WPCAP_INTERNSHIP_DETAILS(5550628),
		FAILED_UPDATE_WPCAP_INTERNSHIP_DETAILS(5550629),
		FAILED_GET_WPCAP_TRAINEE_DETAILS(5550630),
		FAILED_UPDATE_WPCAP_TRAINEE_DETAILS(5550631),
		FAILED_GET_WPCAP_ALLOCATION_DETAILS(5550632),
		FAILED_UPDATE_WPCAP_ALLOCATION_DETAILS(5550632),
		FAILED_GET_SEASON_WP_SUM_DETAILS(5550633),
	    FAILED_UPDATE_SEASON_WP_SUM_DETAILS(5550634),
	    FAILED_GET_WP_SUM_BASE_DETAILS(5550635),
	    FAILED_UPDATE_WP_SUM_BASE_DETAILS(5550636),
	    FAILED_GET_WP_SUM_SECTION_ONE_DETAILS(5550637),
	    FAILED_UPDATE_WP_SUM_SECTION_ONE_DETAILS(5550638),
	    FAILED_GET_WP_PROGRAM_ALLOCATION(5550639),
	    FAILED_UPDATE_WP_PROGRAM_ALLOCATION(5550640),
	    FAILED_GET_SEASON_WP_SPRING_DETAILS(5550641),
	    FAILED_UPDATE_SEASON_WP_SPRING_DETAILS(5550642),
	    FAILED_GET_WP_SPRING_BASE_DETAILS(5550643),
	    FAILED_UPDATE_WP_SPRING_BASE_DETAILS(5550644),
	    FAILED_GET_WP_SPRING_SECTION_ONE_DETAILS(5550645),
	    FAILED_UPDATE_WP_SPRING_SECTION_ONE_DETAILS(5550646),
	    FAILED_GET_WP_SPRING_ALLOCATION_DETAILS(5550647),
	    FAILED_UPDATE_WP_SPRING_ALLOCATION_DETAILS(5550648),
	    FAILED_GET_WP_WINTER_DETAILS(5550649),
	    FAILED_UPDATE_WP_WINTER_DETAILS(5550650),
	    FAILED_EDIT_WP_WINTER_DETAILS(5550651),
	    FAILED_GET_WP_WINTER_BASE_DETAILS(5550652),
	    FAILED_UPDATE_WP_WINTER_BASE_DETAILS(5550653),
	    FAILED_EDIT_WP_WINTER_BASE_DETAILS(5550654),
	    FAILED_GET_WP_WINTER_SECTION_ONE_DETAILS(5550655),
	    FAILED_UPDATE_WP_WINTER_SECTION_ONE_DETAILS(5550656),
	    FAILED_EDIT_WP_WINTER_SECTION_ONE_DETAILS(5550657),
	    FAILED_GET_WP_WINTER_ALLOCATION_DETAILS(5550658),
	    FAILED_UPDATE_WP_WINTER_ALLOCATION_DETAILS(5550659),
	    FAILED_EDIT_WP_WINTER_ALLOCATION_DETAILS(5550660),
	    FAILED_CLONE_SEASON(5550661),
	    FAILED_CREATE_SEASON_BEAN(5550662),
	    FAILED_UPDATE_SEASON(5550663),
	    FAILED_ADD_SEASON_DEPARTMENT_NOTE(5550664),
	    FAILED_ADD_SEASON_DEPARTMENT_DOC(5550665),
	    FAILED_ADD_SEASON_PROGRAM_NOTE(5550666),
	    FAILED_ADD_SEASON_PROGRAM_DOC(5550667),
	    FAILED_GET_DOCUMENT_TYPES(5550668),
	    FAILED_GET_IHP_DETAILS(5550669),
	    INVALID_PROGRAM_ID(5550670),
	    FAILED_GET_IHP_NAME_AND_STATUS(5550671),
	    FAILED_GET_IHP_DATES(5550672),
	    FAILED_GET_IHP_PROGRAM_CONFIGURATION_DETAILS(5550673),
	    FAILED_UPDATE_IHP_DETAILS(5550674),
	    DETAILS_NOT_NULL(5550675),
	    FAILED_UPDATE_IHP_NAME_AND_STATUS(5550676),
	    FAILED_UPDATE_DATES(5550677),
	    FAILED_UPDATE_IHP_PROGRAM_CONFIGURATION_DETAILS(5550678),
	    FAILED_SEASON_NAME_ALREADY_EXIST(5550679),
	    FAILED_J1HS_AUG_START_NOT_NULL(5550680),
	    FAILED_J1HS_FIELD_SETTINGS_NOT_NULL(5550681),
	    FAILED_WP_BASIC_DETAILS(5550682),
	    FAILED_GET_WP_SECTION_ONE_DETAILS(5550683),
	    FAILED_GET_SEASON_WP_DETAILS(5550684),
	    INVALID_DEPARTMENT_ID(5550685),
	    NO_SEASON_FOUND(5550686),
	    FAILED_TO_GET_J1_HS_JAN_START(5550687),
	    FAILED_TO_GET_J1_HS_BASIC_DETAILS(5550688),
	    INVALID_SEASON_GHT_DETAILS(5550689),
	    FAILED_TO_GET_DEPT_DETAILS(5550690),
	    INVALID_SEASON_ID_AND_PROGRAM_ID(5550691),
	    FAILED_GET_SEASON_GHT_DETAILS(5550692),
	    FAILED_CREATE_SEASON_NAME(5550693),
	    FAILED_GET_SEASSON_J1_DETAILS(5550694),
	    FAILED_GET_SEASON_F1_DETAILS(5550695),
	    FAILED_SEASON_HSP_F1_DETAILS(5550696),
	    FAILED_GET_SEASON_WP_CAP_DETAILS(5550697),
	    FAILED_GET_SEASON_DETAILS(5550698),
	    
		// REGION Assignment

		PROBLEM_FETCHING_SUPER_REGIONS(6660666), 
		REGION_ASSIGNMENT_SUPER_REGION_DETAILS(6660667), 
		DEFAULT_CODE(13),		

		
		
		//Partner User
		PARTNER_USER_cODE(7770777),
		FAILED_GET_PARTNER_USER_ID_NULL(7770778),
		FAILED_GET_PARTNER_USER(7770779),
		FAILED_GET_PARTNER(7770780),
		FAILED_GET_PARTNER_PERMISSIONS(7770781),
		FAILED_UPDATE_PARTNER_USER(7770782),
		PARTNER_USER_CREATE_USER_NAME_EXIST(7770783),
		PARTNER_USER_CREATE_USER_EMAIL_EXIST(7770784),	
		FAILED_GET_EMAIL(7770785),
		PARTNER_USER_UPDATE_USER_NAME_EXIST(7770786),
		PARTNER_USER_UPDATE_USER_EMAIL_EXIST(7770787),
		
		//PartnerSeasons
		INVALID_PARTNER_ID(700001),
		NO_PARTNER_SEASON_FOUND(700002),
		ERROR_GET_PARTNER_SEASON(700003),
		ERROR_GET_PARTNER_SEASON_DETAILS(700004),
		ERROR_GET_AVAILABLE_SEASONS(700005),
		ERROR_GET_PARTNER_COMPANY_DETAIL(700006),
		
		 
      //Sub-partner
		
		INVALID_SUB_PARTNER_ID(800001),
		ERROR_GET_SUB_PARTNER_DETAILS(800002),
   	 FAILED_SUB_PARTNER_DETAILS_NULL(800003),
   	 SUB_PARTNER_CODE(800004),
   	 FAILED_CREATE_SUB_PARTNER(800005),
   	 FAILED_UPDATE_SUB_PARTNER(800006),
   	 SUB_PARTNER_CREATE_USER_USERNAME_EXIST(800007),
   	 SUB_PARTNER_CREATE_USER_EMAIL_EXIST(800008),
   	 FAILED_TO_GET_EMAIL(800009),
   	 
   	 //PartnerAgent 
   	 PARTNER_AGENT_CODE(900001),
   	 FAILED_GET_SEASONS(900002),
   	 FAILED_GET_ADDED_SEASONS(900003),
   	 PARTNER_SEASON_NULL(900004),
   	 PARTNER_NULL(900005),
   	 //Partner Admin
   	 NO_WOEKQUEUE_SUBMITTED_APPLICATIONS(800010), 
   	 WOEKQUEUE_SUBMITTED_APPLICATIONS(800011),
	  NO_WOEKQUEUE_PARTNER_INQUIRY_OVERVIEW_DETAIL(800012),
	  NO_WOEKQUEUE_TYPE(800013),
	  NO_WOEKQUEUE_CATEGORY(800014), 
	  CANT_UPDATE_FOLLOW_UP_DATE(800015), 
	  FOLLOW_UP_DATE_UPDATED(800016),
	  
	  //partner dashboard
	  ERROR_PARTNER_GET_DETAILS(800017),
	  NO_PROGRAM_DETAILS_FOUND(800018),
	  ERROR_PARTNER_GET_J1_DETAILS(800019),
	  ERROR_PARTNER_GET_F1_DETAILS(800020),
	  ERROR_PARTNER_GET_IHP_DETAILS(8000217), 
	  //PARTNER ADMIN DASHBOARD
	  WORK_QUEUE_TYPE(8000218), 
	  WORK_QUEUE_CATEGORY(8000220), 
	  WORK_QUEUE_QUICK_STATS_TITLE(8000221), 
	  WORK_QUEUE_QUICK_STATS_CATEGORY(8000222), 
	  NO_WORK_QUEUE_QUICK_STATS_CATEGORY(8000223), 
	  NO_WORK_QUEUE_QUICK_STATS_TITLE(8000224), 
	  WORK_QUEUE_BENCHMARKS(8000225), 
	  NO_WORK_QUEUE_BENCHMARKS(8000226), 
	  WORK_QUEUE_QUICK_LINKS(8000227), 
	  NO_WORK_QUEUE_QUICK_LINKS(8000228), 
	  PARTNER_APPLICATION_STATUS_UPDATED(8000229), 
	  CANT_UPDATE_PARTNER_APPLICATION_STATUS(8000230), 
	  NO_WOEKQUEUE_PARTNER_INQUIRY_LEAD_DETAIL(8000231), 
	  PARTNER_INQUIURY_OVERVIEW(8000232), 
	  PARTNER_INQUIURY_OVERVIEW_UPDATE(8000233),
	  WOEKQUEUE_PARTNER_INQUIRY_OVERVIEW_UPDATE(8000234), 
	  NO_WOEKQUEUE_PARTNER_INQUIRY_LEAD_UPDATE(8000235), 
	  PARTNER_INQUIURY_LEAD(8000236), 
	  ERROR_UPDATING__WOEKQUEUE_PARTNER_INQUIRY_OVERVIEW(8000236), 
	  ERROR_UPDATING__WOEKQUEUE_PARTNER_INQUIRY_LEAD(8000237),
	  CHANGE_PARTICIPANT_SEASON(8000238), 
	  CANT_CHANGE_PARTICIPANT_SEASON(8000239), 
	  CHANGE_PARTICIPANT_SUBPARTNER(8000240), 
	  CANT_CHANGE_PARTICIPANT_SUBPARTNER(8000241)
		;
		

		
		private int itemId;
		
		public int getValue() {
			return itemId;
		}

		public Integer itemId(){
			return itemId;
		}
		public String itemIdString(){
			return (new Integer(itemId)).toString();
		}
		
		private ErrorCode( int itemId) {
			this.itemId = itemId;
		}
		
		public static ErrorCode fromItemId (int itemId) {
			ErrorCode[] errorCodes= ErrorCode.values();
			for (ErrorCode v:errorCodes) {
				if(v.itemId == itemId) return v;
			}
			return null;
		}
		
		public static ErrorCode fromItemId (Integer itemId) {
			 return fromItemId(itemId.intValue());
		}
}
