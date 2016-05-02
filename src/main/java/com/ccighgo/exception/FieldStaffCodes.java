package com.ccighgo.exception;

public enum FieldStaffCodes {
   //Field Staff Admin
   ERROR_GETTING_FIELDSTAFF_LIST(120800101),
   ERROR_GETTING_FIELDSTAFF_TYPES(120800201),
   ERROR_GETTING_FS_LC_SEASON_CONTACTS(120800301),
   ERROR_GETTING_FS_AC_SEASON_CONTACTS(120800401),
   ERROR_GETTING_FS_RD_SEASON_CONTACTS(120800501),
   ERROR_GETTING_FS_RM_SEASON_CONTACTS(120800601),
   ERROR_GETTING_FS_ERD_SEASONS(120800701),
   ERROR_GET_FS_SEASON(120800801),
   ERROR_UPDATE_FSL_SEASON_SIGN_CONTRACT(120800901),
   ERROR_GET_FS_ADMIN_SEASON_DETAILS(120801001),
   ERROR_GET_FS_STATUS_LIST(120801101),
   ERROR_GET_PAYMENT_SCHEDULE_LIST(120801201),
   ERROR_UPDATE_FS_ADMIN_SEASON_DETAILS(120801301),
   ERROR_DELETE_FSL_SEASON(120801401),
   ERROR_DEACTIVATE_FSL_SEASON(120801501),
   ERROR_GETTING_FIELDSATFF_BY_ROLL(120801601),
   ERROR_GETTING_FIELDSTAFF_DETAILE(120801701),
   ERROR_UPDATE_FS_DETAILS(120801801),
   ERROR_UPDATE_FS_STATUS(120801901),
   ERROR_WOEKQUEUE_FS_PENDING_APPROVAL(120802601),
   ERROR_FS_ADMIN_APPLICATION_STATS(120802701),
   ERROR_FS_ADMIN_PROGRAM_STATS(120802801),
   ERROR_FS_ADMIN_HOST_FAMILY(120802901),
   ERROR_RESET_PASSWORD(120803001),
   ERROR_FS_ADMIN_ADDED_SCHOOL(120803101),
   ERROR_CHANGE_PARTNER_APPLICATION_STATUS(120803401),
   ERROR_UPDATE_FOLLOW_UP_DATE(120803501),
   //Admin ERD
   ERROR_GETTING_FIELDSTAFF_PARTICIPANT(120803601),
   ERROR_GETTING_MY_TEAM(120803701),
   ERROR_GETTING_ERD_MY_PLACEMENT(120803801),
   ERROR_GETTING_ERD_MY_TEAM_PLACEMENT(120803901),
   
   //Field Staff
   ERROR_GET_MY_FS_LEADERSHIP_LIST(120900101),
   ERROR_GET_FS_NETWORK_LIST(120900201),
   ERROR_GET_FS_LEADERSHIP(120900301),
   ERROR_GETTING_FS_NETWORK_LIST(120900401),
   ERROR_GET_FS_PLACEMENT_CATEGORIES_LIST(120900501),
   ERROR_GET_FS_MONITORING_CATEGORIES_LIST(120900601),
   ERROR_GET_FS_NETWORK_CATEGORIES_LIST(120900701),
   ERROR_GET_FS_HF_CATEGORIES_LIST(120900801),
   ERROR_GETTING_FIELDSTAFF_DASHBOARD(120900901),
   ERROR_GET_ERD_MY_ACCOUNT(120901001),
   ERROR_GETTING_FIELDSTAFF_PLACEMENT(120901101),
   ERROR_GETTING_FIELDSTAFF_TEAM_PLACEMENT(120901201),
   ERROR_GET_ERD_PLACEMENT_PARTICIPANT(120901301),
   ERROR_GET_ALL_PARTICIPANT(120901401),
   ERROR_GET_MY_TEAM_PARTICIPANT(120901501),
   
   
   NO_RECORD(8100),
   SUCCESS(8000);

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

   private FieldStaffCodes(int itemId) {
      this.itemId = itemId;
   }

   public static FieldStaffCodes fromItemId(int itemId) {
      FieldStaffCodes[] errorCodes = FieldStaffCodes.values();
      for (FieldStaffCodes v : errorCodes) {
         if (v.itemId == itemId)
            return v;
      }
      return null;
   }

   public static FieldStaffCodes fromItemId(Integer itemId) {
      return fromItemId(itemId.intValue());
   }

}