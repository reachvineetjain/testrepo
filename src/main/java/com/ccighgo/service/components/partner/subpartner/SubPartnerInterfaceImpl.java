/**
 * 
 */
package com.ccighgo.service.components.partner.subpartner;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ccighgo.service.transport.partner.beans.subpartner.PartnerSubPartners;
import com.ccighgo.service.transport.partner.beans.subpartner.SubPartner;
import com.ccighgo.service.transport.partner.beans.subpartner.SubPartnerCountry;
import com.ccighgo.service.transport.partner.beans.subpartner.SubPartnerSeasons;
import com.ccighgo.service.transport.partner.beans.subpartner.SubPartnerStatus;
import com.ccighgo.service.transport.partner.beans.subpartner.SubPartners;

/**
 * @author ravi
 *
 */
@Component
public class SubPartnerInterfaceImpl implements SubPartnerInterface {

   @Override
   public PartnerSubPartners getSubPartnersOfpartners(String partnerId) {
      PartnerSubPartners psp = new PartnerSubPartners();
      psp.setCount(2);
      psp.setPartnerGoId(1111);
      List<SubPartners> subPartners = new ArrayList<SubPartners>();
      
      SubPartnerCountry subPartnerCountry = new SubPartnerCountry();
      subPartnerCountry.setSubPartnerCountry("USA");
      subPartnerCountry.setSubPartnerCountryId(1);
      
      SubPartnerStatus subPartnerStatus = new SubPartnerStatus();
      subPartnerStatus.setSubPartnerStatusId(1);
      subPartnerStatus.setSubPartnerStatus("Active");
      
      List<SubPartnerSeasons> subPartnerSeasons = new ArrayList<SubPartnerSeasons>();
      SubPartnerSeasons partnerSeasons = new SubPartnerSeasons();
      partnerSeasons.setSubPartnerSeasonId(1);
      partnerSeasons.setSubPartnerSeasonProgramId(1);
      partnerSeasons.setSubPartnerSeasonProgram("J1HS");
      
      SubPartnerSeasons partnerSeasons1 = new SubPartnerSeasons();
      partnerSeasons1.setSubPartnerSeasonId(2);
      partnerSeasons1.setSubPartnerSeasonProgramId(2);
      partnerSeasons1.setSubPartnerSeasonProgram("F1");
      subPartnerSeasons.add(partnerSeasons);
      subPartnerSeasons.add(partnerSeasons1);
      
      SubPartners sPart= new SubPartners();
      sPart.setSubPartnerId(123);
      sPart.setSubPartnerFirstName("Sub");
      sPart.setSubPartnerLastName("Partner 1");
      sPart.setSubPartnerCountry(subPartnerCountry);
      sPart.setSubPartnerStatus(subPartnerStatus);
      sPart.getSubPartnerSeasons().addAll(subPartnerSeasons);
      
      SubPartners sPart1= new SubPartners();
      sPart1.setSubPartnerId(1234);
      sPart1.setSubPartnerFirstName("Sub");
      sPart1.setSubPartnerLastName("Partner 2");
      sPart1.setSubPartnerCountry(subPartnerCountry);
      sPart1.setSubPartnerStatus(subPartnerStatus);
      sPart1.getSubPartnerSeasons().addAll(subPartnerSeasons);
      subPartners.add(sPart);
      subPartners.add(sPart1);
      psp.getSubPartners().addAll(subPartners);
      return psp;
   }

   @Override
   public SubPartner viewSubPartners(String subPartner) {
      // TODO Auto-generated method stub
      return null;
   }

}
