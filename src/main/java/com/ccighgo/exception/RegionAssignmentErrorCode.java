package com.ccighgo.exception;

public enum RegionAssignmentErrorCode {

   SUPER_REGION_RECORD_NOT_FOUND(120500101), PROBLEM_FETCHING_SUPER_REGIONS(120500102),

   SUPER_REGION_ASSIGNED_ERD_RECORD_NOT_FOUND(120500201), FAILED_GET_ASSIGNED_ERD_SUPER_REGION(120500202),

   REGION_RECORD_NOT_FOUND(120500301), PROBLEM_FETCHING_REGIONS(120500302),

   REGION_ASSIGNED_RD_RECORD_NOT_FOUND(120500401), FAILED_GET_ASSIGNED_RD_REGION(120500402),

   STATES_RECORD_NOT_FOUND(120500501), PROBLEM_FETCHING_STATES(120500502),

   STATES_ASSIGNED_FIELD_STAFF_RECORD_NOT_FOUND(120500601), FAILED_GET_ASSIGNED_FS_STATES(120500602),

   ASSIGN_ERD_TO_SUPER_REGION_FAILED(120500701),

   ASSIGN_RD_TO_REGION_FAILED(120500801),

   ASSIGN_FS_TO_STATES_FAILED(120500901),

   FAILED_TO_REMOVE_ASSIGNED_FS(120501001),

   DEFAULT_CODE(13),

   FAILED_GET_SUP_REG_LIST(120501501);

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

   private RegionAssignmentErrorCode(int itemId) {
      this.itemId = itemId;
   }

   public static RegionAssignmentErrorCode fromItemId(int itemId) {
      RegionAssignmentErrorCode[] errorCodes = RegionAssignmentErrorCode.values();
      for (RegionAssignmentErrorCode v : errorCodes) {
         if (v.itemId == itemId)
            return v;
      }
      return null;
   }

   public static RegionAssignmentErrorCode fromItemId(Integer itemId) {
      return fromItemId(itemId.intValue());
   }

}
