/**
 * 
 */
package com.ccighgo.service.components.partner.season;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ccighgo.service.transport.partner.beans.partnerseason.PartnerSeason;
import com.ccighgo.service.transport.partner.beans.partnerseason.PartnerSeasonDepartment;
import com.ccighgo.service.transport.partner.beans.partnerseason.PartnerSeasonProgramOption;
import com.ccighgo.service.transport.partner.beans.partnerseason.PartnerSeasonProgramStatus;
import com.ccighgo.service.transport.partner.beans.partnerseason.PartnerSeasons;


/**
 * @author ravi
 *
 */
@Component
public class PartnerSeasonInterfaceImpl implements PartnerSeasonInterface {

   @Override
   public PartnerSeasons getPartnerSeasons(String partnerId) {
      PartnerSeasons partnerSeasons = new PartnerSeasons();
      partnerSeasons.setParticipantCount(6);
      List<PartnerSeason> partnerSeasonsList = new ArrayList<PartnerSeason>();
      
      PartnerSeasonDepartment partnerSeasonDepartment = new PartnerSeasonDepartment();
      partnerSeasonDepartment.setPartnerSeasonDepartmentId(1);
      partnerSeasonDepartment.setPartnerSeasonDepartmentCode("HSP");
      partnerSeasonDepartment.setPartnerSeasonDepartmentName("High School Programs");
      
      PartnerSeasonProgramOption partnerProgramOption = new PartnerSeasonProgramOption();
      partnerProgramOption.setPartnerProgramOptionId(1);
      partnerProgramOption.setPartnerProgramOption("J1HS");
      
      PartnerSeasonProgramStatus seasonProgramStatus = new PartnerSeasonProgramStatus();
      seasonProgramStatus.setPartnerSeasonProgramStatusId(1);
      seasonProgramStatus.setPartnerSeasonProgramStatus("Active");
      
      PartnerSeason pSeason = new PartnerSeason();
      pSeason.setPartnerId("1111");
      pSeason.setPartnerSeasonId(1);
      pSeason.setPartnerSeasonProgramId(1);
      pSeason.setPartnerSeasonProgramName("HSP 2015 J1HS");
      pSeason.setParticipantAllocated("10 of 240");
      pSeason.setPartnerStartDate("09/09/2015");
      pSeason.setPartnerEndDate("09/09/2016");
      pSeason.setPartnerApplicationDeadlineDate("10/09/2015");
      pSeason.setPartnerSeasonDepartment(partnerSeasonDepartment);
      pSeason.setPartnerProgramOption(partnerProgramOption);
      pSeason.setSeasonProgramStatus(seasonProgramStatus);
      partnerSeasonsList.add(pSeason);
      partnerSeasonsList.add(pSeason);
      partnerSeasonsList.add(pSeason);
      partnerSeasonsList.add(pSeason);
      partnerSeasonsList.add(pSeason);
      partnerSeasonsList.add(pSeason);
      partnerSeasons.getPartnerSeasons().addAll(partnerSeasonsList);
      return partnerSeasons;
   }

}
