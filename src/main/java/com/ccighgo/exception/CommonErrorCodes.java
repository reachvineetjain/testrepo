/**
 * 
 */
package com.ccighgo.exception;

/**
 * @author ravi
 *
 */
public enum CommonErrorCodes {

   SERVICE_SUCCESS(120000101), UTILITY_SERVICE_CODE(120000102);

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

   private CommonErrorCodes(int itemId) {
      this.itemId = itemId;
   }

   public static CommonErrorCodes fromItemId(int itemId) {
      CommonErrorCodes[] errorCodes = CommonErrorCodes.values();
      for (CommonErrorCodes v : errorCodes) {
         if (v.itemId == itemId)
            return v;
      }
      return null;
   }

   public static CommonErrorCodes fromItemId(Integer itemId) {
      return fromItemId(itemId.intValue());
   }

}
