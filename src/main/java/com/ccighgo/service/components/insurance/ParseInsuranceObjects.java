package com.ccighgo.service.components.insurance;

import javax.xml.bind.JAXBException;

import com.ccighgo.service.transport.seasons.beans.insuranceplan.InsurancePlan;
import com.google.gson.Gson;

public class ParseInsuranceObjects {

   private InsurancePlan planUnMarshalle(String planAsJson) throws JAXBException {
      Gson gson = new Gson();
      return gson.fromJson(planAsJson, InsurancePlan.class);
   }

   public static void main(String[] args) throws JAXBException {
      ParseInsuranceObjects p = new ParseInsuranceObjects();
      InsurancePlan result = p
            .planUnMarshalle("{\"id\":\"2\",\"title\":\"Amerex - IHI\",\"deductible\":\"100\",\"policy_max\":\"100000\",\"er_fee\":\"0\",\"policy_number\":\"8341537-2090\",\"underwriter_id\":\"1\",\"status_id\":\"2\",\"has_id_card\":\"1\",\"card_url\":\"\",\"card_username\":\"\",\"card_password\":\"\",\"id_card_url\":\"\",\"price\":\"0.00\",\"medical_expenses\":\"-1\",\"emergency_medical_evacuation\":\"0\",\"emergency_reunion\":\"0\",\"repatriation\":\"0\",\"trip_interuption\":\"0\",\"card_template\":\"A\",\"deductible_type\":\"0\"}");
      System.out.println(result);
   }
}
