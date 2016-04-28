package com.ccighgo.exception;

public enum RegionManagementErrorCode {

   SEASON_ID_INVALID(120500101), NO_RECORD(120500102), REGION_SERVICE_CODE(120500103), ERROR_GET_SUP_REG_LIST(120500104),

   SUP_REG_ID_ZERO_OR_NEG(120500201), SUP_REG_GET_ERROR(120500202),

   SUP_REG_NULL(120500301), SUP_REG_NAME_NULL(120500302), SUP_REG_ALREADY_EXIST_SAME_SEASON(120500303), SUP_REG_ALREADY_EXIST(120500304), FAILED_SUP_REG_ADD(120500305),

   FAILED_SUP_REG_UPDATE(120500501),

   FAILED_SUP_REG_DELETE(120500601),

   REG_ID_ZERO_OR_NEG(120500701), FAILED_GET_REGION(120500702),

   REGION_NULL(120500801), REG_NAME_NULL(120500802), REG_ALREADY_EXIST_SAME_SEASON(120500803), REG_ALREADY_EXIST(120500804), FAILED_ADD_REGION(120500805),

   FAILED_UPDATE_REGION(120501001),

   FAILED_DELETE_REGION(120501101),

   STATE_REGION_GET_ERROR(120501201),

   STATE_REGION_NULL(120501301), STATE_REGION_MOVE_ERROR(120501302),

   STATE_LIST_NULL(120501401), FAILED_ADD_STATES_REG(120501402),

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

   private RegionManagementErrorCode(int itemId) {
      this.itemId = itemId;
   }

   public static RegionManagementErrorCode fromItemId(int itemId) {
      RegionManagementErrorCode[] errorCodes = RegionManagementErrorCode.values();
      for (RegionManagementErrorCode v : errorCodes) {
         if (v.itemId == itemId)
            return v;
      }
      return null;
   }

   public static RegionManagementErrorCode fromItemId(Integer itemId) {
      return fromItemId(itemId.intValue());
   }

}
