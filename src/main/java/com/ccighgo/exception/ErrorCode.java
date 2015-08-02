package com.ccighgo.exception;


public enum ErrorCode {
		INVALID_REQUEST(290001),
		INVALID_LOGINNAME(290002),
		INVALID_RESULT_ID(290005),
		INVALID_COUNTRY_CODE(290006),
		INVALID_DATE(290007),
		MISSING_REQUIRED_VALUE(290008),
		NO_RECORD(2900010),
		INVALID_NAME(290009),
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
		SEASON_CODE(2140402),
		FAILED_GET_ALL_SEASONS(2140403),
		FAILED_CREATE_SEASON(2140404),
		FAILED_TO_VIEW_SEASON(2140405),
	
		FAILED_UPDATE_SEASON(2140406),
		FAILED_TO_GET_SEASON_PROGRAMS(2140407),
		FAILED_TO_GET_SEASON_STATUS(2140408),
		FAILED_TO_GET_HSP_J1_HS_SEASON_DETAILS(2140409),
		FAILED_TO_GET_J1_HS_BASIC_DETAIL(21404010),
		FAILED_TO_GET_J1_HS_JAN_START(21404011),
		FAILED_TO_UPDATE_J1_HS_JAN_START(21404012),
		FAILED_TO_GET_J1_HS_AUG_START(21404013),
		FAILED_TO_UPDATE_J1_HS_AUG_START_DETAILS(21404014),
		FAILED_TO_GET_HSP_J1_HS_SEASON_FIELD_SETTINGS(21404015),
		FAILED_TO_UPDATE_HSP_J1_HS_SEASON_FIELD_SETTINGS(21404016),
		INVALID_J1_HS_DETAILS(21404017),
		FAILED_TO_GET_HSP_J1_HS_PROGRAM_ALLOCATION(21404018),
		FAILED_TO_UPDATE_HSP_J1_HS_PROGRAM_ALLOCATION(21404020),
		FAILED_TO_GET_HSP_F1_PROGRAM_ALLOCATION(21404021),
		FAILED_TO_GET_HSP_F1_FIELD_SETTINGS(21404022),
		FAILED_TO_GET_HSP_F1_AUGUST_START_1ST_SEMISTER(21404023),
		FAILED_TO_GET_HSP_F1_AUGUST_FULL_YEAR_DETAILS(21404024),
		FAILED_TO_GET_HSP_F1_JAN_2ND_SEM_DETAILS(21404025),
		FAILED_TO_GET_HSP_F1_SEASON_DETAILS(21404026),
		FAILED_TO_GET_HSP_F1(21404027),
		UPDATE_F1_DETAILS(21404028),
		
		FAILED_UPDATE_HSP_F1_DETAILS(21404029),
		FAILED_UPDATE_HSP_J1_HS_SEASON_NAME_AND_STATUS(21404030),
		INVALID_ID(21404031),
		FAILED_UPDATE_HSP_J1_HS_SEASON_DETAILS(21404032),
		FAILED_TO_EDIT_SEASON(21404033),
		UPDATE_F1(21404035),
		FAILED_SEASON_GHT_DEAILS(21404036),
		FAILED_SEASON_GHTVA_DEAILS(21404036),
		FAILED_SEASON_GHTWA_DEAILS(21404037),
		FAILED_UPDATE_SEASON_GHTWA_DEAILS(21404038),
		FAILED_GHTVA_SEASON_NAME_STATUS(21404039),
		FAILED_GHTWA_SEASON_NAME_STATUS(21404040),
		FAILED_GHT_SECTION_2DATES(21404041),
		FAILED_SEASON_WP(21404042),
		FAILED_WP_BASE(21404043),
		FAILED_WP_SECTION_ONE(214040434),
		FAILED_WP_PROGRAM_ALLOCATION(214040435),
		FAILED_SEASON_DEPT_NOTES(214040436),
		FAILED_SEASON_PROGRAM_NOTES(214040437),
		FAILED_SEASON_PROGRAM_DOC(214040438),
		FAILED_WPCAP_PROGRAM_ALLOCATION(2140404389),
		
		//Region
		REGION_SERVICE_CODE(601),
		INVALID_ID_REGION_ALL(2160101),
		FAILED_GET_SUP_REGION(2160201),
		
		//user management(temp code)
		USER_MANAGEMENT_CODE(220300),
		INVALID_USER_ID(220301),
		FAILED_GET_ALL_USERS(220302),
		FAILED_CREATE_USER(220303),
		FAILED_UPDATE_USER(220304),
		FAILED_UPDATE_USER_PERMISSIONS(220305),
		FAILED_UPDATE_USER_PICTURE(220306),
		INVALID_USER_SEARCH(220307),
		FAILED_DEFAULT_PERMISSIONS_BY_ROLE(220308)
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
