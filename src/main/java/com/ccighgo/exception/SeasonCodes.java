/**
 * 
 */
package com.ccighgo.exception;

/**
 * @author ravi
 *
 */
public enum SeasonCodes {
   
   //common codes
   
   //service specific codes
   ERROR_GET_SEASON_LIST(1240101),
   ERROR_GET_SEASON_DETAILS(1240301),
   ERROR_CREATE_SEASON(1240401),
   ERROR_UPDATE_SEASON(1240501),
   ERROR_GET_SEASON_PROGRAMS(1240601), 
   ERROR_GET_SEASON_PROGRAM_DEPT(1240602),
   ERROR_GET_SEASON_STATUS(1240701), 
   ERROR_GET_HSPJ1_DETAILS(1240801),
   ERROR_GET_HSPJ1_BASIC_DETAILS(1240901),
   SUCCESS(400);
   
   
   
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

   private SeasonCodes(int itemId) {
      this.itemId = itemId;
   }

   public static SeasonCodes fromItemId(int itemId) {
      SeasonCodes[] errorCodes = SeasonCodes.values();
      for (SeasonCodes v : errorCodes) {
         if (v.itemId == itemId)
            return v;
      }
      return null;
   }

   public static SeasonCodes fromItemId(Integer itemId) {
      return fromItemId(itemId.intValue());
   }

}
