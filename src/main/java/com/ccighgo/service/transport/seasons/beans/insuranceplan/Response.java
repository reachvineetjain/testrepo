//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.08.14 at 06:11:54 PM CDT 
//

package com.ccighgo.service.transport.seasons.beans.insuranceplan;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "response")
public class Response {
   @XmlElement(required = true)
   protected String id;
   @XmlElement(required = true)
   protected String title;
   @XmlElement(required = true)
   protected String deductible;
   @XmlElement(required = true)
   protected String policy_max;
   @XmlElement(required = true)
   protected String er_fee;
   @XmlElement(required = true)
   protected String policy_number;
   @XmlElement(required = true)
   protected String underwriter_id;
   @XmlElement(required = true)
   protected String status_id;
   @XmlElement(required = true)
   protected String has_id_card;
   @XmlElement(required = true)
   protected String card_url;
   @XmlElement(required = true)
   protected String card_username;
   @XmlElement(required = true)
   protected String cardPassword;
   @XmlElement(required = true)
   protected String id_card_url;
   @XmlElement(required = true)
   protected String price;
   @XmlElement(required = true)
   protected String medical_expenses;
   @XmlElement(required = true)
   protected String emergency_medical_evacuation;
   @XmlElement(required = true)
   protected String emergency_reunion;
   @XmlElement(required = true)
   protected String repatriation;
   @XmlElement(required = true)
   protected String trip_interuption;
   @XmlElement(required = true)
   protected String card_template;
   @XmlElement(required = true)
   protected String deductible_type;
   @XmlElement(required = true)
   protected String has_visa_letter;
   @XmlElement(required = true)
   protected String visa_template;
   @XmlElement(required = true)
   protected String brochure_url;
   @XmlElement(required = true)
   protected String has_trip_can_option;
   @XmlElement(required = true)
   protected String max_effective_date;
   @XmlElement(required = true)
   protected String min_effective_date;
   @XmlElement(required = true)
   protected String renewable_days;
   @XmlElement(required = true)
   protected String max_policy_length;
   @XmlElement(required = true)
   protected String backdate_length;
   @XmlElement(required = true)
   protected String group_id;
   @XmlElement(required = true)
   protected String nonUSA_price;
   @XmlElement(required = true)
   protected String plan_updated_date;
   @XmlElement(required = true)
   protected String plan_created_date;
   @XmlElement(required = true)
   protected String plan_updated_by;
   @XmlElement(required = true)
   protected String plan_created_by;
   @XmlElement(required = true)
   protected String certificate_url;
   @XmlElement(required = true)
   protected String use_brochure_link;
   @XmlElement(required = true)
   protected String min_age;
   @XmlElement(required = true)
   protected String max_age;
   @XmlElement(required = true)
   protected String coverage_start_template;
   @XmlElement(required = true)
   protected String renewal_template;
   @XmlElement(required = true)
   protected String message;

   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getTitle() {
      return title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public String getDeductible() {
      return deductible;
   }

   public void setDeductible(String deductible) {
      this.deductible = deductible;
   }

   public String getPolicy_max() {
      return policy_max;
   }

   public void setPolicy_max(String policy_max) {
      this.policy_max = policy_max;
   }

   public String getEr_fee() {
      return er_fee;
   }

   public void setEr_fee(String er_fee) {
      this.er_fee = er_fee;
   }

   public String getPolicy_number() {
      return policy_number;
   }

   public void setPolicy_number(String policy_number) {
      this.policy_number = policy_number;
   }

   public String getUnderwriter_id() {
      return underwriter_id;
   }

   public void setUnderwriter_id(String underwriter_id) {
      this.underwriter_id = underwriter_id;
   }

   public String getStatus_id() {
      return status_id;
   }

   public void setStatus_id(String status_id) {
      this.status_id = status_id;
   }

   public String getHas_id_card() {
      return has_id_card;
   }

   public void setHas_id_card(String has_id_card) {
      this.has_id_card = has_id_card;
   }

   public String getCard_url() {
      return card_url;
   }

   public void setCard_url(String card_url) {
      this.card_url = card_url;
   }

   public String getCard_username() {
      return card_username;
   }

   public void setCard_username(String card_username) {
      this.card_username = card_username;
   }

   public String getCardPassword() {
      return cardPassword;
   }

   public void setCardPassword(String cardPassword) {
      this.cardPassword = cardPassword;
   }

   public String getId_card_url() {
      return id_card_url;
   }

   public void setId_card_url(String id_card_url) {
      this.id_card_url = id_card_url;
   }

   public String getPrice() {
      return price;
   }

   public void setPrice(String price) {
      this.price = price;
   }

   public String getMedical_expenses() {
      return medical_expenses;
   }

   public void setMedical_expenses(String medical_expenses) {
      this.medical_expenses = medical_expenses;
   }

   public String getEmergency_medical_evacuation() {
      return emergency_medical_evacuation;
   }

   public void setEmergency_medical_evacuation(String emergency_medical_evacuation) {
      this.emergency_medical_evacuation = emergency_medical_evacuation;
   }

   public String getEmergency_reunion() {
      return emergency_reunion;
   }

   public void setEmergency_reunion(String emergency_reunion) {
      this.emergency_reunion = emergency_reunion;
   }

   public String getRepatriation() {
      return repatriation;
   }

   public void setRepatriation(String repatriation) {
      this.repatriation = repatriation;
   }

   public String getTrip_interuption() {
      return trip_interuption;
   }

   public void setTrip_interuption(String trip_interuption) {
      this.trip_interuption = trip_interuption;
   }

   public String getCard_template() {
      return card_template;
   }

   public void setCard_template(String card_template) {
      this.card_template = card_template;
   }

   public String getDeductible_type() {
      return deductible_type;
   }

   public void setDeductible_type(String deductible_type) {
      this.deductible_type = deductible_type;
   }

   public String getHas_visa_letter() {
      return has_visa_letter;
   }

   public void setHas_visa_letter(String has_visa_letter) {
      this.has_visa_letter = has_visa_letter;
   }

   public String getVisa_template() {
      return visa_template;
   }

   public void setVisa_template(String visa_template) {
      this.visa_template = visa_template;
   }

   public String getBrochure_url() {
      return brochure_url;
   }

   public void setBrochure_url(String brochure_url) {
      this.brochure_url = brochure_url;
   }

   public String getHas_trip_can_option() {
      return has_trip_can_option;
   }

   public void setHas_trip_can_option(String has_trip_can_option) {
      this.has_trip_can_option = has_trip_can_option;
   }

   public String getMax_effective_date() {
      return max_effective_date;
   }

   public void setMax_effective_date(String max_effective_date) {
      this.max_effective_date = max_effective_date;
   }

   public String getMin_effective_date() {
      return min_effective_date;
   }

   public void setMin_effective_date(String min_effective_date) {
      this.min_effective_date = min_effective_date;
   }

   public String getRenewable_days() {
      return renewable_days;
   }

   public void setRenewable_days(String renewable_days) {
      this.renewable_days = renewable_days;
   }

   public String getMax_policy_length() {
      return max_policy_length;
   }

   public void setMax_policy_length(String max_policy_length) {
      this.max_policy_length = max_policy_length;
   }

   public String getBackdate_length() {
      return backdate_length;
   }

   public void setBackdate_length(String backdate_length) {
      this.backdate_length = backdate_length;
   }

   public String getGroup_id() {
      return group_id;
   }

   public void setGroup_id(String group_id) {
      this.group_id = group_id;
   }

   public String getNonUSA_price() {
      return nonUSA_price;
   }

   public void setNonUSA_price(String nonUSA_price) {
      this.nonUSA_price = nonUSA_price;
   }

   public String getPlan_updated_date() {
      return plan_updated_date;
   }

   public void setPlan_updated_date(String plan_updated_date) {
      this.plan_updated_date = plan_updated_date;
   }

   public String getPlan_created_date() {
      return plan_created_date;
   }

   public void setPlan_created_date(String plan_created_date) {
      this.plan_created_date = plan_created_date;
   }

   public String getPlan_updated_by() {
      return plan_updated_by;
   }

   public void setPlan_updated_by(String plan_updated_by) {
      this.plan_updated_by = plan_updated_by;
   }

   public String getPlan_created_by() {
      return plan_created_by;
   }

   public void setPlan_created_by(String plan_created_by) {
      this.plan_created_by = plan_created_by;
   }

   public String getCertificate_url() {
      return certificate_url;
   }

   public void setCertificate_url(String certificate_url) {
      this.certificate_url = certificate_url;
   }

   public String getUse_brochure_link() {
      return use_brochure_link;
   }

   public void setUse_brochure_link(String use_brochure_link) {
      this.use_brochure_link = use_brochure_link;
   }

   public String getMin_age() {
      return min_age;
   }

   public void setMin_age(String min_age) {
      this.min_age = min_age;
   }

   public String getMax_age() {
      return max_age;
   }

   public void setMax_age(String max_age) {
      this.max_age = max_age;
   }

   public String getCoverage_start_template() {
      return coverage_start_template;
   }

   public void setCoverage_start_template(String coverage_start_template) {
      this.coverage_start_template = coverage_start_template;
   }

   public String getRenewal_template() {
      return renewal_template;
   }

   public void setRenewal_template(String renewal_template) {
      this.renewal_template = renewal_template;
   }

   public String getMessage() {
      return message;
   }

   public void setMessage(String message) {
      this.message = message;
   }

}
