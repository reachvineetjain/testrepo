package com.ccighgo.service.components.greenheartclub;

import org.springframework.stereotype.Service;

import com.ccighgo.service.components.greenheartclub.utils.GHC_Response;
import com.ccighgo.service.rest.greenheartclub.GciApiUser;

@Service
public interface GHClub {

   GHC_Response testRead();

   GHC_Response testCreate(String title);

   GHC_Response testEdit(String title, String id);

   GHC_Response readPrivate();

   GHC_Response testDelete(String id);

   GHC_Response createUser(GciApiUser user);

   GHC_Response userExist(String id);

   GHC_Response userEmailExist(String email);

   GHC_Response userNameExist(String username);

   GHC_Response setUserId(String currentId, String newId);

   GHC_Response setUserProgram(String id, String program);

   GHC_Response setUserName(String id, String username);

   GHC_Response setUserPassword(String id, String password);

   GHC_Response setUserEmail(String id, String email);

   GHC_Response getUser(String goId);

   GHC_Response updatePrograms(String programs);

}
