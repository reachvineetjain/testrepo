package com.ccighgo.exception;

/**
 * @author venkat
 *
 */
public enum HostFamilyCodes {

   // WhyHost
   ERROR_CREATE_WHYHOST(121000101), 
   ERROR_GET_WHYHOST(121000201),
   ERROR_UPDATE_WHYHOST(121000301),
   //Photos
   ERROR_UPLOAD_HF_PHOTOS(121000401),
   ERROR_UPLOAD_OPTIONAL_HF_PHOTOS(121000501),
   ERROR_GET_HF_PHOTOS(121000601),
   ERROR_DELETE_HF_PHOTO(121000701),
   //HF Home Page
   ERROR_GET_HF_HOME_PAGE(121000801),
   //Basic Data
   ERROR_SAVE_HF_BASIC_DATA(121000901),
   ERROR_GET_HF_BASIC_DATA(121001001),
   //LifeStyle
   ERROR_SAVE_HF_LIFE_STYLE(121001101),
   ERROR_GET_HF_LIFE_STYLE(121001201),
   //Reference
   ERROR_CREATE_HF_REFERENCE(121001301),
   ERROR_UPDATE_HF_REFERENCE(121001401),
   ERROR_GET_HF_REFERENCE(121001501),
   ERROR_ADD_POTENTIAL_REFERENCE(121001601),
   //HouseDescription
   ERROR_SAVE_HF_HOUSE_DESCRIPTION(121001701),
   ERROR_GET_HF_HOUSE_DESCRIPTION(121001801),
   //Community and School
   ERROR_SAVE_HF_COMMUNITY_AND_SCHOOL(121001901),
   ERROR_GET_HF_COMMUNITY_AND_SCHOOL(121002001),
   //Background
   ERROR_GET_HF_BACKGROUND_DETAILS(121002101),
   //Progress
   ERROR_GET_HF_APPLICATION_PROGRESS(121002201),
   //Submit
   ERROR_HF_SUBMIT_APPLICATION(121002301),
   //ProfilePic
   ERROR_UPDATE_HF_PROFILE_PHOTO(121002401),
   //Airport
   ERROR_GET_HF_AIRPORT_LIST(121002501),
   ERROR_REMOVE_HF_AIRPORT(121002601),
   //Removal
   ERROR_REMOVE_HF_PET(121002701),
   ERROR_REMOVE_HF_ADULT(121002801),
   //Profile
   ERROR_VIEW_HF_PROFILE(121002901),
   //HF Members
   ERROR_FETCH_HF_MEMBERS(121003001),
   ERROR_FETCH_HF_MEMBERS_DETAILS(121003101),
   //HF PetType
   ERROR_FETCH_HF_PETTYPE(121003201),
   
   
   NO_RECORD(10100),
   SUCCESS(10000);

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

   private HostFamilyCodes(int itemId) {
      this.itemId = itemId;
   }

   public static HostFamilyCodes fromItemId(int itemId) {
      HostFamilyCodes[] errorCodes = HostFamilyCodes.values();
      for (HostFamilyCodes v : errorCodes) {
         if (v.itemId == itemId)
            return v;
      }
      return null;
   }

   public static HostFamilyCodes fromItemId(Integer itemId) {
      return fromItemId(itemId.intValue());
   }

}
