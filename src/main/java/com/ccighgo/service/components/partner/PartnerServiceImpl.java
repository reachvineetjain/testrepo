/**
 * 
 */
package com.ccighgo.service.components.partner;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.ccighgo.service.transport.partner.beans.partnerdetails.PartnerDashboardSections;
import com.ccighgo.service.transport.partner.beans.partnerdetails.PartnerDetails;
import com.ccighgo.service.transport.partner.beans.partnerdetails.PartnerPrograms;


/**
 * @author ravi
 *
 */
@Component
public class PartnerServiceImpl implements PartnerService {

   @Override
   public PartnerDetails getPartnerDetails(String userId) {
      PartnerDetails partnerDetails = new PartnerDetails();
      partnerDetails.setPartnerFirstName("Super");
      partnerDetails.setPartnerLastName("Man");
      partnerDetails.setPartnerAddress("Planet Krypton");
      partnerDetails.setPartnerCountry("Doesn't matter");
      partnerDetails.setPartnerCompany("Save the world");
      partnerDetails.setPartnerEmail("superman@planetkrypton.com");
      partnerDetails.setPartnerId(007);
      partnerDetails.setPartnerLogo("Logo stolen by batman");
      List<PartnerPrograms> partnerPrograms = new ArrayList<PartnerPrograms>();
      PartnerPrograms pp = new PartnerPrograms();
      pp.setPartnerDepartmentId("1");
      pp.setPartnerDepartmentName("Save the world");
      pp.setPartnerProgramId("1");
      pp.setPartnerProgramName("Help Joker");
      PartnerPrograms pp1 = new PartnerPrograms();
      pp1.setPartnerDepartmentId("1");
      pp1.setPartnerDepartmentName("Save the world");
      pp1.setPartnerProgramId("1");
      pp1.setPartnerProgramName("Help Joker");
      partnerPrograms.add(pp);
      partnerPrograms.add(pp1);
      List<PartnerDashboardSections> dashboardSections = new ArrayList<PartnerDashboardSections>();
      return partnerDetails;
   }

}
