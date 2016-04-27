/**
 * 
 */
package com.ccighgo.exception;

/**
 * @author ravi
 *
 */
public enum PartnerCodes {

   // Partner user

    NULL_PARTNER_ID(201), 
    EXCEPTION_IN_FETCHING_PARTNER_USER_LIST(120761200), 
    PARTNER_USER_STATUS_NULL(120761300), 
    ERROR_UPDATE_PARTNER_USER_STATUS(120761301),
    ERROR_RESETING_PARTNER_USER_PASSWORD(120761400),
    ERROR_VIEWING_PARTNER_USER_DATA(120761500), 
    ERROR_FETCHING_PARTNER_USER_OFFICE(120761700), 
    ERROR_USER_WITH_THE_SAME_LOGINNAME_EXIST(120761800),
    ERROR_USER_WITH_THE_SAME_EMAIL_EXIST(120761801),
    ERROR_ADDING_PARTNERUSER(120761802),
    ERROR_UPDATING_PARTNERUSER_DATA(120761900), 
    
    // Partner Generic Note
    FAILED_TO_CREATE_NOTE(120759000), 
    FAILED_TO_DELETE_NOTE(120759100), 
    FAILED_TO_VIEW_NOTE(120759200),
    UPDATE_TOPIC_TAG(120759300),
    ERROR_CREATE_TOPIC(120759400), 
   
   //partner company
    
    ERROR_GET_PARTNER_COMPANY_DETAIL(120758600), 
    DUPLICATE_LOGINNAME(120758700),
    ERROR_UPDATE_PARTNER_COMPANY_DETAIL(120758701), 
    ERROR_ADDING_NEW_PARTNER_OFFICE(120758800), 
    ERROR_DELETE_PARTNER_OFFICE(120758900), 
    FAILED_GET_ADDED_SEASONS(120758100), 
    FAILED_GET_SEASONS(120758200),
    PARTNER_SEASON_NULL(120758300), 
    FAILED_ADD_SEASONS(120758301);
   
   //partner Agent
    
    
   
   


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

   private PartnerCodes(int itemId) {
      this.itemId = itemId;
   }

   public static PartnerCodes fromItemId(int itemId) {
      PartnerCodes[] errorCodes = PartnerCodes.values();
      for (PartnerCodes v : errorCodes) {
         if (v.itemId == itemId)
            return v;
      }
      return null;
   }

   public static PartnerCodes fromItemId(Integer itemId) {
      return fromItemId(itemId.intValue());
   }

}
