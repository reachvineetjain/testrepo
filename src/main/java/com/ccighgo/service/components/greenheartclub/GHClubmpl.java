package com.ccighgo.service.components.greenheartclub;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ccighgo.service.components.gciapi.GCIAPI_Util;
import com.ccighgo.service.components.gciapi.GCIWithOAuth;
import com.ccighgo.service.components.greenheartclub.utils.GHC_Response;
import com.ccighgo.service.rest.greenheartclub.GciApiUser;

@Component
public class GHClubmpl implements GHClub {

   private static final Logger LOGGER = Logger.getLogger(GHClubmpl.class);

   public static final String TEST_READ_URL = "https://gcidev.wpengine.com/api/v2/test/read";
   private static final String READ_PRIVATE_URL = "https://gcidev.wpengine.com/api/v2/test/read/private";
   public static final String TEST_CREATE_URL = "https://gcidev.wpengine.com/api/v2/test/create";
   public static final String TEST_EDIT_URL = "https://gcidev.wpengine.com/api/v2/test/edit";
   public static final String TEST_DELETE_URL = "https://gcidev.wpengine.com/api/v2/test/delete";

   public static final String CREATE_USER = "https://gcidev.wpengine.com/api/v2/user/create";
   public static final String USER_EXIST = "https://gcidev.wpengine.com/api/v2/user/exists";
   public static final String CHECK_USER_EMAIL = "https://gcidev.wpengine.com/api/v2/user/check/email";
   public static final String CHECK_USERNAME = "https://gcidev.wpengine.com/api/v2/user/check/username";
   public static final String UPDATE_USER_ID = "https://gcidev.wpengine.com/api/v2/user/set/id";
   public static final String UPDATE_USER_PROGRAM = "https://gcidev.wpengine.com/api/v2/user/set/program";

   public static final String UPDATE_USER_NAME = "https://gcidev.wpengine.com/api/v2/user/set/username";
   public static final String UPDATE_USER_PASSWORD = "https://gcidev.wpengine.com/api/v2/user/set/password";
   public static final String UPDATE_USER_EMAIL = "https://gcidev.wpengine.com/api/v2/user/set/email";
   public static final String FETCH_USER = "https://gcidev.wpengine.com/api/v2/user/get";

   public static final String FETCH_HOURS_BY_PARTICIPANT = "https://gcidev.wpengine.com/api/v2/hours/get/id";
   public static final String FETCH_HOURS_BY_PROGRAM = "https://gcidev.wpengine.com/api/v2/hours/get/program";

   @Autowired GCIWithOAuth gciWithOAuth;

   @Override
   public GHC_Response testRead() {
      try {
         return GCIAPI_Util.parseGHCAPIResult(GCIAPI_Util.executeURL(TEST_READ_URL));
      } catch (Exception e) {
         LOGGER.error(e.getMessage(), e);
      }
      return null;
   }

   @Override
   public GHC_Response readPrivate() {
      try {
         return GCIAPI_Util.parseGHCAPIResult(gciWithOAuth.readPrivate(READ_PRIVATE_URL));
      } catch (Exception e) {
         LOGGER.error(e.getMessage(), e);
      }
      return null;
   }

   @Override
   public GHC_Response testCreate(String title) {
      try {
         return GCIAPI_Util.parseGHCAPIResult(gciWithOAuth.testCreate(TEST_CREATE_URL, title));
      } catch (Exception e) {
         LOGGER.error(e.getMessage(), e);
      }
      return null;
   }

   @Override
   public GHC_Response testEdit(String title, String id) {
      try {
         return GCIAPI_Util.parseGHCAPIResult(gciWithOAuth.testEdit(TEST_EDIT_URL, title, id));
      } catch (Exception e) {
         LOGGER.error(e.getMessage(), e);
      }
      return null;
   }

   @Override
   public GHC_Response testDelete(String id) {
      try {
         return GCIAPI_Util.parseGHCAPIResult(gciWithOAuth.testDelete(TEST_DELETE_URL, id));
      } catch (Exception e) {
         LOGGER.error(e.getMessage(), e);
      }
      return null;
   }

   @Override
   public GHC_Response createUser(GciApiUser user) {
      try {
         return GCIAPI_Util.parseGHCAPIResult(gciWithOAuth.createUser(CREATE_USER, user));
      } catch (Exception e) {
         LOGGER.error(e.getMessage(), e);
      }
      return null;
   }

   @Override
   public GHC_Response userExist(String id) {
      try {
         return GCIAPI_Util.parseGHCAPIResult(gciWithOAuth.userExist(USER_EXIST, id));
      } catch (Exception e) {
         LOGGER.error(e.getMessage(), e);
      }
      return null;
   }

   @Override
   public GHC_Response userEmailExist(String email) {
      try {
         return GCIAPI_Util.parseGHCAPIResult(gciWithOAuth.userEmailExist(CHECK_USER_EMAIL, email));
      } catch (Exception e) {
         LOGGER.error(e.getMessage(), e);
      }
      return null;
   }

   @Override
   public GHC_Response userNameExist(String username) {
      try {
         return GCIAPI_Util.parseGHCAPIResult(gciWithOAuth.userNameExist(CHECK_USERNAME, username));
      } catch (Exception e) {
         LOGGER.error(e.getMessage(), e);
      }
      return null;
   }

   @Override
   public GHC_Response setUserId(String currentId, String newId) {
      try {
         return GCIAPI_Util.parseGHCAPIResult(gciWithOAuth.setUserId(UPDATE_USER_ID, currentId, newId));
      } catch (Exception e) {
         LOGGER.error(e.getMessage(), e);
      }
      return null;
   }

   @Override
   public GHC_Response setUserProgram(String id, String program) {
      try {
         return GCIAPI_Util.parseGHCAPIResult(gciWithOAuth.setUserProgram(UPDATE_USER_PROGRAM, id, program));
      } catch (Exception e) {
         LOGGER.error(e.getMessage(), e);
      }
      return null;
   }

   @Override
   public GHC_Response setUserName(String id, String username) {
      try {
         return GCIAPI_Util.parseGHCAPIResult(gciWithOAuth.setUserName(UPDATE_USER_NAME, id, username));
      } catch (Exception e) {
         LOGGER.error(e.getMessage(), e);
      }
      return null;
   }

   @Override
   public GHC_Response setUserPassword(String id, String password) {
      try {
         return GCIAPI_Util.parseGHCAPIResult(gciWithOAuth.setUserPassword(UPDATE_USER_PASSWORD, id, password));
      } catch (Exception e) {
         LOGGER.error(e.getMessage(), e);
      }
      return null;
   }

   @Override
   public GHC_Response setUserEmail(String id, String email) {
      try {
         return GCIAPI_Util.parseGHCAPIResult(gciWithOAuth.setUserEmail(UPDATE_USER_EMAIL, id, email));
      } catch (Exception e) {
         LOGGER.error(e.getMessage(), e);
      }
      return null;
   }

   @Override
   public GHC_Response getUser(String goId) {
      try {
         return GCIAPI_Util.parseGHCAPIResult(gciWithOAuth.getUser(FETCH_USER, goId));
      } catch (Exception e) {
         LOGGER.error(e.getMessage(), e);
      }
      return null;
   }

}
