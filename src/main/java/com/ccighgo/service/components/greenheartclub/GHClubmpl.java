package com.ccighgo.service.components.greenheartclub;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ccighgo.service.components.gciapi.GCIAPI_Util;
import com.ccighgo.service.components.gciapi.GCIWithOAuth;
import com.ccighgo.service.components.greenheartclub.utils.GHC_Response;

@Component
public class GHClubmpl implements GHClub {

   private static final Logger LOGGER = Logger.getLogger(GHClubmpl.class);

   private final String TEST_READ_URL = "https://gcidev.wpengine.com/api/v2/test/read";
   private final String READ_PRIVATE_URL = "https://gcidev.wpengine.com/api/v2/test/read/private";
   private final String TEST_CREATE_URL = "https://gcidev.wpengine.com/api/v2/test/test/create";
   private final String TEST_EDIT_URL = "https://gcidev.wpengine.com/api/v2/test/test/edit";
   private final String TEST_DELETE_URL = "https://gcidev.wpengine.com/api/v2/test/test/delete";

   @Autowired GCIWithOAuth gciWithOAuth;

   @Override
   public GHC_Response testRead() {
      try {
         return GCIAPI_Util.parseGHCAPIResult(GCIAPI_Util.executeURL(TEST_READ_URL));
      } catch (Exception e) {
         e.printStackTrace();
      }
      return null;
   }

   @Override
   public GHC_Response readPrivate() {
      try {
         return GCIAPI_Util.parseGHCAPIResult(gciWithOAuth.readPrivate(READ_PRIVATE_URL));
      } catch (Exception e) {
         e.printStackTrace();
      }
      return null;
   }

   @Override
   public GHC_Response testCreate(String title) {
      try {
         return GCIAPI_Util.parseGHCAPIResult(gciWithOAuth.testCreate(TEST_CREATE_URL, title));
      } catch (Exception e) {
         e.printStackTrace();
      }
      return null;
   }

   @Override
   public GHC_Response testEdit(String title, String id) {
      try {
         return GCIAPI_Util.parseGHCAPIResult(gciWithOAuth.testEdit(TEST_EDIT_URL, title, id));
      } catch (Exception e) {
         e.printStackTrace();
      }
      return null;
   }

   @Override
   public GHC_Response testDelete(String id) {
      try {
         return GCIAPI_Util.parseGHCAPIResult(gciWithOAuth.testDelete(TEST_DELETE_URL, id));
      } catch (Exception e) {
         e.printStackTrace();
      }
      return null;
   }

}
