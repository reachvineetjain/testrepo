package com.ccighgo.service.components.greenheartclub;

import org.springframework.stereotype.Service;

import com.ccighgo.service.components.greenheartclub.utils.GHC_Response;

@Service
public interface GHClub {

   GHC_Response testRead();

   GHC_Response testCreate(String title);

   GHC_Response testEdit(String title, String id);

   GHC_Response readPrivate();

   GHC_Response testDelete(String id);

}
