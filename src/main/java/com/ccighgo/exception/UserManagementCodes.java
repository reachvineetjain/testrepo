/*
 * Copyright (c) 2015, 2016, Creospan Solutions PVT LTD. All rights reserved.
 * CREOSPAN PROPRIETARY/CONFIDENTIAL.
 *
 *
 */
package com.ccighgo.exception;

/**
 * @author vijay
 *
 */
public enum UserManagementCodes {

   INVALID_PAGE_NUM_OR_SIZE(120200101), FAILED_GET_USER_LIST(120200102), FAILED_USER_NULL(120200103), USER_MANAGEMENT_CODE(120200104), FAILED_GET_ALL_USERS(120200105),

   FAILED_GET_ALL_SUPERVISORS(120200301),

   INVALID_USER_ID(120200401),

   USR_MGMT_CREATE_USER_NAME_EXIST(120200601), USR_MGMT_CREATE_USER_EMAIL_EXIST(120200602), FAILED_USER_DETAILS_CREATION(120200603), FAILED_USER_DEPT_PROGRAM_CREATION(120200604), FAILED_USER_ROLE_CREATION(
         120200605), FAILED_USER_PERMISSIONS_CREATION(120200606), USR_MGMT_CREATE_USER_PARAM_REQUIRED(120200607), USR_MGMT_CREATE_USER_FAILED(120200608),

   USR_MGMT_UPDATE_USER_FAILED(120200701),

   FAILED_GET_DEPARTMENT_WITH_PERMISSIONS(120200801),

   FAILED_GET_ROLE_BY_DEPARTMENT(120200901),

   USR_MGMT_UPDATE_USER_PERMISSIONS_FAILED(120201001), FAILED_UPDATE_PERMISSIONS(120201002),

   FAILED_UPDATE_USER_PICTURE(120201101),

   USR_MGMT_SEARCH_USER_ERROR(120201301),

   FAILED_DEFAULT_PERMISSIONS_BY_ROLE(120201401),

   UTILITY_SERVICE_CODE(120201501), NO_RECORD(120201502), ERROR_GET_USER(120201503),

   FAILED_USER_NOTES_NULL(120201701), FAILED_GET_USER_NOTES(120201702),

   FAILED_GET_RESOURCE_ACTION_LIST(120201801),

   USR_MGMT_UPDATE_USER_NAME_EXIST(120202001), USR_MGMT_UPDATE_USER_EMAIL_EXIST(120202002),

   USR_MGMT_UPDATE_USER_PARAM_REQUIRED(120202101);

   private int itemId;

   public int getValue() {
      return itemId;
   }

   public Integer itemId() {
      return itemId;
   }

   public String itemIdString() {
      return (new Integer(itemId)).toString();
   }

   private UserManagementCodes(int itemId) {
      this.itemId = itemId;
   }

   public static UserManagementCodes fromItemId(int itemId) {
      UserManagementCodes[] errorCodes = UserManagementCodes.values();
      for (UserManagementCodes v : errorCodes) {
         if (v.itemId == itemId)
            return v;
      }
      return null;
   }

   public static UserManagementCodes fromItemId(Integer itemId) {
      return fromItemId(itemId.intValue());
   }

}
