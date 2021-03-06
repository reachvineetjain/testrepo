/**
 * 
 */
package com.ccighgo.exception;

/**
 * @author ravi
 *
 */
public enum SeasonCodes {
   
   //High level season
   ERROR_GET_SEASON_LIST(1240101),
   ERROR_GET_SEASON_DETAILS(1240301),
   ERROR_CREATE_SEASON(1240401),
   ERROR_UPDATE_SEASON(1240501),
   ERROR_GET_SEASON_PROGRAMS(1240601), 
   ERROR_GET_SEASON_PROGRAM_DEPT(1240602),
   ERROR_GET_SEASON_STATUS(1240701), 
   
   //J-1HS season
   ERROR_GET_HSPJ1_DETAILS(1240801),
   ERROR_GET_HSPJ1_BASIC_DETAILS(1240901),
   ERROR_GET_HSPJ1_JAN_START_DETAILS(1241001),
   ERROR_GET_HSPJ1_AUG_START_DETAILS(1241101),
   ERROR_GET_HSPJ1_FIELD_SETTINGS(1241201), 
   ERROR_GET_HSPJ1_PROGRAM_ALLOCATION(1241301),
   ERROR_UPDATE_J1HS_SEASON_DETAILS(1241401),
   ERROR_UPDATE_HSPJ1_BASIC_DETAILS(1241501),
   ERROR_UPDATE_HSPJ1_JAN_START(1241601),
   ERROR_UPDATE_HSPJ1_AUG_START(1241701),
   ERROR_UPDATE_HSPJ1_FIELD_SETTINGS(1241801),
   ERROR_UPDATE_HSPJ1_PROGRAM_ALLOCATION(1241901),
   
   //F1 season
   ERROR_GET_F1_DETAILS(1242001),
   ERROR_GET_F1_BASIC_DETAILS(1242101),
   ERROR_GET_F1_ACCOUNTING_DETAILS(1242201),
   ERROR_GET_F1_JAN_2ND_SEM_DETAILS(1242301),
   ERROR_GET_F1_JAN_FULL_YR_DETAILS(1242401),
   ERROR_GET_F1_AUG_1ST_SEM_DETAILS(1242501),
   ERROR_GET_F1_AUG_FULL_YR_DETAILS(1242601),
   ERROR_GET_F1_FIELD_SETTING_DETAILS(1242701),
   ERROR_GET_F1_PROGRAM_ALLOCATION(1242801),
   ERROR_UPDATE_F1_DETAILS(1242901),
   ERROR_UPDATE_F1_BASE_DETAILS(1243001),
   ERROR_UPDATE_F1_ACCOUNTING_DETAILS(1243101),
   ERROR_UPDATE_F1_JAN_2ND_SEM(1243201),
   ERROR_UPDATE_F1_JAN_FULL_YR(1243301),
   ERROR_UPDATE_F1_AUG_1ST_SEM(1243401),
   ERROR_UPDATE_F1_AUG_FULL_YR(1243501),
   ERROR_UPDATE_F1_FIELD_SETTINGS(1243601),
   ERROR_UPDATE_F1_ALLOCATIONS(1243701),
   
   //IHP season
   ERROR_GET_IHP_DETAILS(1243801),
   ERROR_GET_IHP_BASIC_DETAILS(1243901),
   ERROR_GET_IHP_DATES(1244001),
   ERROR_GET_IHP_CONFIG(1244101),
   ERROR_UPDATE_IHP_DETAILS(1244201),
   ERROR_UPDATE_IHP_BASIC_DETAILS(1244301),
   ERROR_UPDATE_IHP_DATES(1244401),
   ERROR_UPDATE_IHP_CONFIG(1244501),
   
   //Season GHT
   
   //GHT VA
   ERROR_GET_VA_DETAILS(1244601),
   ERROR_GET_VA_BASIC_DETAILS(1244701),
   ERROR_GET_VA_DATES(1244801),
   ERROR_UPDATE_VA_DETAILS(1244901),
   ERROR_UPDATE_VA_BASIC_DETAILS(1245001),
   ERROR_UPDATE_VA_DATES(1245101),
   
   //GHT WA
   ERROR_GET_WA_DETAILS(1245201),
   ERROR_GET_WA_BASIC_DETAILS(1245301),
   ERROR_GET_WA_DATES(1245401),
   ERROR_UPDATE_WA_DETAILS(1245501),
   ERROR_UPDATE_WA_BASIC_DETAILS(1245601),
   ERROR_UPDATE_WA_DATES(1245701),
   
   //GHT HSA
   ERROR_GET_HSA_DETAILS(1245801),
   ERROR_GET_HSA_BASIC_DETAILS(1245901),
   ERROR_GET_HSA_DATES(1246001),
   ERROR_UPDATE_HSA_DETAILS(1246101),
   ERROR_UPDATE_HSA_BASIC_DETAILS(1246201),
   ERROR_UPDATE_HSA_DATES(1246301),
   
   //GHT LS
   ERROR_GET_LS_DETAILS(1246401),
   ERROR_GET_LS_BASIC_DETAILS(1246501),
   ERROR_GET_LS_DATES(1246601),
   ERROR_UPDATE_LS_DETAILS(1246701),
   ERROR_UPDATE_LS_BASIC_DETAILS(1246801),
   ERROR_UPDATE_LS_DATES(1246901),
   
   //GHT TA
   ERROR_GET_TA_DETAILS(1247001),
   ERROR_GET_TA_BASIC_DETAILS(1247101),
   ERROR_GET_TA_DATES(1247201),
   ERROR_UPDATE_TA_DETAILS(1247301),
   ERROR_UPDATE_TA_BASIC_DETAILS(1247401),
   ERROR_UPDATE_TA_DATES(1247501),
   
   //Season WP
   
   //WP CAP
   ERROR_GET_CAP_DETAILS(1247601),
   ERROR_GET_CAP_BASIC_DETAILS(1247701),
   ERROR_GET_CAP_INTERNESHIP_DETAILS(1247801),
   ERROR_GET_CAP_TRAINEE_DETAILS(1247901),
   ERROR_GET_CAP_ALLOCATIONS(1248001),
   ERROR_UPDATE_CAP_DETAILS(1248101),
   ERROR_UPDATE_CAP_BASIC_DETAILS(1248201),
   ERROR_UPDATE_CAP_INTERNESHIP_DETAILS(1248301),
   ERROR_UPDATE_CAP_TRAINEE_DETAILS(1248401),
   ERROR_UPDATE_CAP_ALLOCATIONS(1248501),
   
   //WP Summer
   ERROR_GET_WP_SUMMER_DETAILS(1248601),
   ERROR_GET_WP_SUMMER_BASIC_DETAILS(1248701),
   ERROR_GET_WP_SUMMER_SECTION1(1248801),
   ERROR_GET_WP_SUMMER_ALLOCATIONS(1248901),
   ERROR_UPDATE_WP_SUMMER_DETAILS(1249001),
   ERROR_UPDATE_WP_SUMMER_BASIC_DETAILS(1249101),
   ERROR_UPDATE_WP_SUMMER_SECTION1(1249201),
   ERROR_UPDATE_WP_SUMMER_ALLOCATIONS(1249301),
   
   //WP Spring
   ERROR_GET_WP_SPRING_DETAILS(1249401),
   ERROR_GET_WP_SPRING_BASIC_DETAILS(1249501),
   ERROR_GET_WP_SPRING_SECTION1(1249601),
   ERROR_GET_WP_SPRING_ALLOCATIONS(1249701),
   ERROR_UPDATE_WP_SPRING_DETAILS(1249801),
   ERROR_UPDATE_WP_SPRING_BASIC_DETAILS(1249901),
   ERROR_UPDATE_WP_SPRING_SECTION1(1250001),
   ERROR_UPDATE_WP_SPRING_ALLOCATIONS(1250101),
   
   //WP Winter
   ERROR_GET_WP_WINTER_DETAILS(1250201),
   ERROR_GET_WP_WINTER_BASIC_DETAILS(1250301),
   ERROR_GET_WP_WINTER_SECTION1(1250401),
   ERROR_GET_WP_WINTER_ALLOCATIONS(1250501),
   ERROR_UPDATE_WP_WINTER_DETAILS(1250601),
   ERROR_UPDATE_WP_WINTER_BASIC_DETAILS(1250701),
   ERROR_UPDATE_WP_WINTER_SECTION1(1250801),
   ERROR_UPDATE_WP_WINTER_ALLOCATIONS(1250901),
   
   ERROR_CLONE_SEASON(1251001),
   ERROR_ADD_SEASON_NOTE(1251101),
   ERROR_ADD_SEASON_DOCUMENT(1251201),
   ERROR_ADD_SEASON_PROGRRAM_NOTE(1251301),
   ERROR_ADD_SEASON_PROGRAM_DOCUMENT(1251401),
   ERROR_GET_DOCYPE_LIST(1251501),
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
