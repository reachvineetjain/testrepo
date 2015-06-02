package com.ccighgo.exception;


public enum ErrorCode {
		INVALID_REQUEST(290001),
		INVALID_LOGINNAME(290002),
		INVALID_RESULT_ID(290005),
		INVALID_COUNTRY_CODE(290006),
		INVALID_DATE(290007),
		MISSING_REQUIRED_VALUE(290008),
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
		
		
		
		//user management(temp code)
		INVALID_USER_ID(301)
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
