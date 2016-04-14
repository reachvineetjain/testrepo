package com.ccighgo.service.components.hf.participant.application.process.util;

import java.util.ArrayList;
import java.util.List;

import com.ccighgo.service.transport.common.response.beans.Response;

public class HFAirportList extends Response {

   private List<HFAirportData> airportInfo;

   public List<HFAirportData> getAirportInfo() {
      if (airportInfo == null)
         airportInfo = new ArrayList<HFAirportData>();

      return airportInfo;
   }

   public void setAirportInfo(List<HFAirportData> airportInfo) {
      this.airportInfo = airportInfo;
   }

}
