package com.ccighgo.service.transport.partner.beans.subpartner;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.ccighgo.service.transport.common.response.beans.Response;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SubPartnerDetails", propOrder = {
    "subPartnerDetails"
})
public class SubPartnerDetails extends Response {

   protected int count;
   @XmlElement(required = true)
   protected List<SubPartnerDetail> subPartnerDetails;

   public int getCount() {
      return count;
   }

   public void setCount(int count) {
      this.count = count;
   }

   public List<SubPartnerDetail> getSubPartnerDetails() {
      if (subPartnerDetails == null) {
         subPartnerDetails = new ArrayList<SubPartnerDetail>();
      }
      return this.subPartnerDetails;
   }
}
