package com.ccighgo.service.components.hf.participant.application.process.util;

import java.util.ArrayList;
import java.util.List;

import com.ccighgo.service.transport.common.response.beans.Response;

public class HFSeasonList extends Response {

   private List<HFSeasonDetails> seasonDetails;

   public List<HFSeasonDetails> getSeasonDetails() {
      if (seasonDetails == null)
         seasonDetails = new ArrayList<HFSeasonDetails>();
      return seasonDetails;
   }

   public void setSeasonDetails(List<HFSeasonDetails> seasonDetails) {
      this.seasonDetails = seasonDetails;
   }

}
