package com.ccighgo.service.components.errormessages.constants;

/**
 * @author ravi
 *
 */
public class RegionManagementMessageConstants {

   // Super Region
   public static final String SUPER_REGION = "Super Region";
   public static final String SUP_REG_ID_ZERO_OR_NEG = "super.region.id.zero.or.negative";
   public static final String SUP_REG_GET_ERROR = "error.get.super.region";
   public static final String FAILED_SUP_REG_UPDATE = "error.update.super.region";
   public static final String FAILED_SUP_REG_ADD = "error.add.super.region";
   public static final String ERROR_GET_SUP_REG_LIST = "error.get.super.region.list";
   public static final String SUP_REG_NULL = "error.add.super.region.null";
   public static final String SUP_REG_NAME_NULL = "error.add.super.region.name.null";
   public static final String SUP_REG_ALREADY_EXIST = "error.add.super.region.name.already.exists";
   public static final String SUP_REG_ALREADY_EXIST_SAME_SEASON = "error.add.super.region.name.already.exists.same.season";
   public static final String FAILED_SUP_REG_DELETE = "error.delete.super.region";

   // Region
   public static final String REGION = "Region";
   public static final String STATE = "State";
   public static final String REG_ID_ZERO_OR_NEG = "region.id.zero.or.negative";
   public static final String FAILED_GET_REGION = "error.get.region";
   public static final String FAILED_UPDATE_REGION = "error.update.region";
   public static final String FAILED_ADD_REGION = "error.add.region";
   public static final String FAILED_DELETE_REGION = "error.super.region";
   public static final String REGION_NULL = "error.add.region.null";
   public static final String REG_NAME_NULL = "error.add.region.name.null";
   public static final String REG_ALREADY_EXIST = "error.add.region.name.already.exists";
   public static final String REG_ALREADY_EXIST_SAME_SEASON ="error.add.region.name.already.exists.same.season";

   // Region State
   public static final String STATE_REGION_GET_ERROR = "error.get.state.region.list";
   public static final String STATE_REGION_NULL = "error.move.state.region";
   public static final String STATE_REGION_MOVE_ERROR = "error.update.state.region";

   // Add state to a region
   public static final String STATE_LIST_NULL = "state.list.null";
   public static final String STATE_LIST_ADD_ERROR = "error.add.state.region";

}
