package com.ccighgo.service.components.hf.participant.application.process.util;

import java.util.List;

import com.ccighgo.service.transport.common.response.beans.Response;
import com.ccighgo.service.transport.hostfamily.beans.application.familydetails.HFPets;

public class HFPetsList extends Response {

   List<HFPets> petsList;

   public List<HFPets> getPetsList() {
      return petsList;
   }

   public void setPetsList(List<HFPets> petsList) {
      this.petsList = petsList;
   }

}
